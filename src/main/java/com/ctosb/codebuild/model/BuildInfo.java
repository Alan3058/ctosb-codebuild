package com.ctosb.codebuild.model;

import java.util.Collection;

public class BuildInfo {

	private TableInfo tableInfo;
	private Collection<ColumnInfo> columnInfos;
	private String basePackageName;
	private String packageName;

	public TableInfo getTableInfo() {
		return tableInfo;
	}

	public Collection<ColumnInfo> getColumnInfos() {
		return columnInfos;
	}

	public String getBasePackageName() {
		return basePackageName;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setTableInfo(TableInfo tableInfo) {
		this.tableInfo = tableInfo;
	}

	public void setColumnInfos(Collection<ColumnInfo> columnInfos) {
		this.columnInfos = columnInfos;
	}

	public void setBasePackageName(String basePackageName) {
		this.basePackageName = basePackageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

}
