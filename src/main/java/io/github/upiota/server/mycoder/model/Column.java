package io.github.upiota.server.mycoder.model;

import java.sql.JDBCType;

import io.github.upiota.server.mycoder.util.StringUtil;


public class Column {

	private String columnName;
	private String columnType;
	private String propertyName;
	private String propertyType;
	private String propertyFullType;
	private boolean needImport;

	private int sqlType;
	private String jdbcType;
	private String remarks;
	
	private boolean primaryKey;

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.propertyName = columnName.toLowerCase();
		this.propertyName = StringUtil._toA(this.propertyName);
		this.columnName = columnName;
	}

	public String getColumnType() {
		return columnType;
	}

	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}

	public String getPropertyFullType() {
		return propertyFullType;
	}

	public void setPropertyFullType(String propertyFullType) {
		this.propertyFullType = propertyFullType;
	}

	public boolean isNeedImport() {
		return needImport;
	}

	public void setNeedImport(boolean needImport) {
		this.needImport = needImport;
	}

	public int getSqlType() {
		return sqlType;
	}

	public void setSqlType(int sqlType) {
		this.jdbcType = JDBCType.valueOf(sqlType).name();
		switch (sqlType) {
		case -5:
			this.propertyType = "Long";
			this.propertyFullType = "java.lang.Long";
			this.needImport = true;
			break;
		case 4:
			this.propertyType = "Integer";
			this.propertyFullType = "java.lang.Integer";
			this.needImport = true;
			break;
		case 93:
			this.propertyType = "Date";
			this.propertyFullType = "java.util.Date";
			this.needImport = true;
			break;
		default:
			this.propertyType = "String";
			this.propertyFullType = "java.lang.String";
			this.needImport = false;
			break;
		}
		this.sqlType = sqlType;
	}

	public String getJdbcType() {
		return jdbcType;
	}

	public void setJdbcType(String jdbcType) {
		this.jdbcType = jdbcType;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public boolean getPrimaryKey() {
		return primaryKey;
	}
	public boolean isPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(boolean primaryKey) {
		this.primaryKey = primaryKey;
	}

	@Override
	public String toString() {
		String format = "[列名:%s,列类型:%s,是否主键:%b,属性名:%s,属性类型:%s,属性类型全名:%s,sqlType:%d,jdbcType:%s,备注:%s,是否需要引入包:%b]";
		return String.format(format, this.columnName, this.columnType,this.primaryKey,this.propertyName, this.propertyType,
				this.propertyFullType, this.sqlType, this.jdbcType, this.remarks, this.needImport);
	}

}
