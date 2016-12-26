package com.ctosb.codebuild.model;

import java.util.ArrayList;
import java.util.Collection;

import com.ctosb.codebuild.util.CamelCaseUtil;
import com.ctosb.codebuild.util.EmptyUtil;

/**
 * 表信息
 * 
 * @author Alan
 * @date 2015-11-21 上午12:58:15
 * 
 */
public class TableInfo {

    /**
     * 表名
     */
    private String tabName;
    /**
     * 类型
     */
    private String tabType;
    /**
     * 表备注
     */
    private String tabComment;
    /**
     * 主键列
     */
    private String primaryCol;
    /**
     * 表的列
     */
    private Collection<ColumnInfo> columns;
    /**
     * 驼峰式表名
     */
    private String camelTabName;
    /**
     * 首字母大写式驼峰式表名
     */
    private String upperCamelTabName;

    public String getTabName() {
        return tabName;
    }

    public void setTabName(String tabName) {
        this.tabName = tabName;
    }

    public String getTabType() {
        return tabType;
    }

    public void setTabType(String tabType) {
        this.tabType = tabType;
    }

    public String getTabComment() {
        return tabComment;
    }

    public void setTabComment(String tabComment) {
        this.tabComment = tabComment;
    }

    public Collection<ColumnInfo> getColumns() {
        return columns;
    }

    public void setColumns(Collection<ColumnInfo> columns) {
        this.columns = columns;
    }

    public String getCamelTabName() {
        return camelTabName != null ? camelTabName : CamelCaseUtil.toCamelCase(tabName);
    }

    public void setCamelTabName(String camelTabName) {
        this.camelTabName = camelTabName;
    }

    public String getUpperCamelTabName() {
        return upperCamelTabName != null ? upperCamelTabName : CamelCaseUtil.toUpperCamelCase(tabName);
    }

    public void setUpperCamelTabName(String upperCamelTabName) {
        this.upperCamelTabName = upperCamelTabName;
    }

    /**
     * 添加列信息
     * 
     * @param columnInfo
     * @return
     * @author Alan
     * @time 2015-11-16 下午08:06:23
     */
    public TableInfo addColumns(ColumnInfo columnInfo) {
        if (EmptyUtil.isEmpty(this.columns)) {
            this.columns = new ArrayList<ColumnInfo>();
        }
        this.columns.add(columnInfo);
        return this;
    }

    /**
     * 批量添加列信息
     * 
     * @param columnInfos
     * @return
     * @author Alan
     * @time 2015-11-16 下午08:06:34
     */
    public TableInfo addColumns(Collection<ColumnInfo> columnInfos) {
        if (EmptyUtil.isEmpty(this.columns)) {
            this.columns = new ArrayList<ColumnInfo>();
        }
        this.columns.addAll(columnInfos);
        return this;
    }

    public String getPrimaryCol() {
        return primaryCol;
    }

    public void setPrimaryCol(String primaryCol) {
        this.primaryCol = primaryCol;
    }

}
