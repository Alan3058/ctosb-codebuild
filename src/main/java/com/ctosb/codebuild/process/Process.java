package com.ctosb.codebuild.process;

import java.util.Collection;

import com.ctosb.codebuild.model.ColumnInfo;
import com.ctosb.codebuild.model.TableInfo;

/**
 * 代码生成器构建接口
 * 
 * @author Alan
 * @date 2015-11-10 下午05:21:07
 * 
 */
public interface Process {

    /**
     * 通过表名构建
     * 
     * @param tableName
     * @param type
     * @param withLoadColumn 是否要加载列信息
     * @return
     * @author Alan
     * @time 2015-11-10 下午05:20:04
     */
    public Collection<TableInfo> getTableInfoByTableName(String tableName, String type, boolean withLoadColumn);

    /**
     * 通过表名构建
     * 
     * @param tableName
     * @param type
     * @return
     * @author Alan
     * @time 2015-11-10 下午05:20:04
     */
    public Collection<ColumnInfo> getColumnInfoByTableName(String tableName, String type);

    /**
     * 通过sql构建
     * 
     * @param sql
     * @return
     * @author Alan
     * @time 2015-11-10 下午05:20:25
     */
    public TableInfo getTableInfoBySql(String sql);

    /**
     * 通过sql构建
     * 
     * @param sql
     * @return
     * @author Alan
     * @time 2015-11-10 下午05:20:25
     */
    public Collection<ColumnInfo> getColumnInfoBySql(String sql);

}
