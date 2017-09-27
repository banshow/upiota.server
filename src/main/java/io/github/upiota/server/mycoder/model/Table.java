package io.github.upiota.server.mycoder.model;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import io.github.upiota.server.mycoder.util.StringUtil;


public class Table {
	
	private String tableName;
	private String remarks;
	
	private String pkColumnName;
	
	private String pkPropertyName;
	
	private List<Column> columns;
	
	private Set<String> packages;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public List<Column> getColumns() {
		return columns;
	}

	public void setColumns(List<Column> columns) {
		packages = columns.stream().filter(c->c.isNeedImport()).map(c->c.getPropertyFullType()).collect(Collectors.toSet());
		this.columns = columns;
	}

	public Set<String> getPackages() {
		return packages;
	}

	public String getPkColumnName() {
		return pkColumnName;
	}

	public void setPkColumnName(String pkColumnName) {
		this.pkPropertyName = pkColumnName.toLowerCase();
		this.pkPropertyName = StringUtil._toA(this.pkPropertyName);
		this.pkColumnName = pkColumnName;
	}
	
	
	
	public String getPkPropertyName() {
		return pkPropertyName;
	}

	public void setPkPropertyName(String pkPropertyName) {
		this.pkPropertyName = pkPropertyName;
	}

	@Override
	public String toString() {
		String format = "[表名:%s,主键:%s,备注:%s,列:%s]";
		return String.format(format, this.tableName, this.pkColumnName, this.remarks, this.columns.toString());
	}

}

