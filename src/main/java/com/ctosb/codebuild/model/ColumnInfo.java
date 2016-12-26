package com.ctosb.codebuild.model;

import com.ctosb.codebuild.util.CamelCaseUtil;
import com.ctosb.codebuild.util.TypeMappingUtil;

/**
 * 列信息
 * 
 * @author Alan
 * @date 2015-11-21 上午12:58:25
 * 
 */
public class ColumnInfo {

    /**
     * 列名
     */
    private String colName;
    /**
     * 列备注
     */
    private String colComment;
    /**
     * 列数据库类型
     */
    private String colType;
    /**
     * 是否主键
     */
    private boolean primary;
    /**
     * 是否可为空
     */
    private boolean nullable;
    /**
     * 列类型对应java类型
     */
    private String javaColType;
    /**
     * 驼峰式列名
     */
    private String camelColName;
    /**
     * 首字母大写式驼峰式列名
     */
    private String upperCamelColName;

    public String getColName() {
        return colName;
    }

    public void setColName(String colName) {
        this.colName = colName;
    }

    public String getColComment() {
        return colComment;
    }

    public void setColComment(String colComment) {
        this.colComment = colComment;
    }

    public String getColType() {
        return colType;
    }

    public void setColType(String colType) {
        this.colType = colType;
    }

    public String getCamelColName() {
        return camelColName != null ? camelColName : CamelCaseUtil.toCamelCase(colName);
    }

    public void setCamelColName(String camelColName) {
        this.camelColName = camelColName;
    }

    public String getUpperCamelColName() {
        return upperCamelColName != null ? upperCamelColName : CamelCaseUtil.toUpperCamelCase(colName);
    }

    public void setUpperCamelColName(String upperCamelColName) {
        this.upperCamelColName = upperCamelColName;
    }

    public String getJavaColType() {
        return javaColType != null ? javaColType : TypeMappingUtil.getType(colType.toUpperCase());
    }

    public void setJavaColType(String javaColType) {
        this.javaColType = javaColType;
    }

    public boolean isPrimary() {
        return primary;
    }

    public void setPrimary(boolean primary) {
        this.primary = primary;
    }

    public boolean isNullable() {
        return nullable;
    }

    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }

}
