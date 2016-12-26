package com.ctosb.codebuild.process;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang.ArrayUtils;

import com.ctosb.codebuild.Context;
import com.ctosb.codebuild.exception.ProcessException;
import com.ctosb.codebuild.model.ColumnInfo;
import com.ctosb.codebuild.model.TableInfo;
import com.ctosb.codebuild.util.CloseUtil;
import com.ctosb.codebuild.util.DbUtil;
import com.ctosb.codebuild.util.EmptyUtil;

/**
 * 默认构建器，通过jdbc的metadata数据信息获取， 由于数据库考虑到安全性，可能会导致一些信息无法获取，比如mysql表备注等
 * 
 * @author Alan
 * @date 2015-11-10 下午05:23:25
 * 
 */
public class DefaultlProcess implements Process {

    private Context context;

    public DefaultlProcess() {}

    /**
     * 构造函数
     * 
     * @param context
     */
    public DefaultlProcess(Context context) {
        this.context = context;
    }

    @Override
    public Collection<TableInfo> getTableInfoByTableName(String tableName, String type, boolean withLoadColumn) {
        DatabaseMetaData dbmd = getDatabaseMetaData();
        if (dbmd == null) {
            return null;
        }
        Collection<TableInfo> tableInfos = new ArrayList<TableInfo>();
        try {
            // 获取表信息
            ResultSet rs = dbmd.getTables(null, null, tableName, (type == null ? null : new String[] {type}));
            while (rs.next()) {
                TableInfo tableInfo = new TableInfo();
                tableInfo.setTabName(rs.getString("TABLE_NAME"));// 表名
                tableInfo.setTabComment(rs.getString("REMARKS"));// 表备注
                tableInfo.setTabType(rs.getString("TABLE_TYPE"));// 表类型

                if (withLoadColumn) {
                    // 加载列信息
                    tableInfo.setColumns(getColumnInfoByTableName(tableName, type));
                }
                tableInfos.add(tableInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tableInfos;
    }

    @Override
    public TableInfo getTableInfoBySql(String sql) {
        Connection connection = getConnection();
        TableInfo tableInfo = new TableInfo();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                ColumnInfo columnInfo = new ColumnInfo();
                columnInfo.setColName(rsmd.getColumnLabel(i));// 列名
                columnInfo.setColType(rsmd.getColumnTypeName(i));// 列类型
                columnInfo.setJavaColType(rsmd.getColumnClassName(i));// java类型
                tableInfo.addColumns(columnInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseUtil.close(connection);
        }

        return tableInfo;
    }

    @Override
    public Collection<ColumnInfo> getColumnInfoByTableName(String tableName, String type) {
        // 校验context是否为空
        if (context == null) {
            throw new ProcessException("The context is null");
        }
        DatabaseMetaData dbmd = getDatabaseMetaData();
        if (dbmd == null) {
            return null;
        }
        Collection<ColumnInfo> cols = new ArrayList<ColumnInfo>();
        String[] filterFields = context.getFilterFields();// 过滤公共字段
        Collection<String> primaryKeys = getPrimaryKeyNames(tableName, type);// 获取主键列名
        try {
            ResultSet rs = dbmd.getColumns(null, null, tableName, null);// 获取表的信息
            while (rs.next()) {
                String columnName = rs.getString("COLUMN_NAME");// 列名
                if (ArrayUtils.contains(filterFields, columnName)) {
                    continue;// 排除过滤的字段
                }

                String columnType = rs.getString("TYPE_NAME");// 列类型
                int nullable = rs.getInt("NULLABLE");
                String columnRemark = rs.getString("REMARKS");// 列注释
                // 判断备注是否为空，为空就用字段名
                if (EmptyUtil.isEmpty(columnRemark)) {
                    columnRemark = columnName;
                }
                // 装配列对象
                ColumnInfo col = new ColumnInfo();
                col.setColName(columnName);
                col.setColType(columnType);
                col.setNullable(nullable == 1 ? true : false);// 是否可为空
                col.setColComment(columnRemark);// 设置备注
                col.setPrimary(primaryKeys.contains(columnName) ? true : false); // 判断是否主键
                cols.add(col);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cols;
    }

    @Override
    public Collection<ColumnInfo> getColumnInfoBySql(String sql) {
        Connection connection = getConnection();
        Collection<ColumnInfo> cols = new ArrayList<ColumnInfo>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                ColumnInfo columnInfo = new ColumnInfo();
                columnInfo.setColName(rsmd.getColumnLabel(i));// 列名
                columnInfo.setColType(rsmd.getColumnTypeName(i));// 列类型
                columnInfo.setJavaColType(rsmd.getColumnClassName(i));// java类型
                cols.add(columnInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseUtil.close(connection);
        }
        return cols;
    }


    /**
     * 获取主键名称
     * 
     * @param tableName
     * @param type
     * @return
     * @author Alan
     * @time 2015-11-20 下午09:15:37
     */
    private Collection<String> getPrimaryKeyNames(String tableName, String type) {
        DatabaseMetaData dbmd = getDatabaseMetaData();
        if (dbmd == null) {
            return null;
        }
        Collection<String> primaryKeys = new ArrayList<String>();
        try {
            ResultSet rsPrimary = dbmd.getPrimaryKeys(null, null, tableName);
            while (rsPrimary.next()) {
                primaryKeys.add(rsPrimary.getString("COLUMN_NAME"));// 列名
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return primaryKeys;
    }


    /**
     * 获取数据库信息
     * 
     * @param conn
     * @return
     * @author Alan
     * @time 2015-11-11 上午09:53:43
     */
    private DatabaseMetaData getDatabaseMetaData() {
        return getDatabaseMetaData(getConnection());
    }

    /**
     * 获取数据库信息
     * 
     * @param conn
     * @return
     * @author Alan
     * @time 2015-11-11 上午09:53:43
     */
    private DatabaseMetaData getDatabaseMetaData(Connection conn) {
        if (conn == null) {
            return null;
        }
        try {
            return conn.getMetaData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 初始化jdbc连接驱动
     * 
     * @return
     * @author Alan
     * @time 2015-11-10 下午06:03:08
     */
    protected Connection getConnection() {
        // 校验context是否为空
        if (context == null) {
            throw new ProcessException("The context is null");
        }
        return DbUtil.getConnection(context.getJdbcDriver(), context.getJdbcUrl(), context.getJdbcUserName(), context.getJdbcPassword());
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public static void main(String[] args) {
        new DefaultlProcess(Context.getInstance()).getTableInfoBySql("select t.id as ids from bbs_post t left join blog on 1=1 ");
    }

}
