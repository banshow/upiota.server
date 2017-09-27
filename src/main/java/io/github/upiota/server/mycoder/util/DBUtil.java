package io.github.upiota.server.mycoder.util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import io.github.upiota.server.mycoder.model.Column;
import io.github.upiota.server.mycoder.model.Table;

public class DBUtil {
		
	public static Connection getConnection(String driver, String url, String user, String password) {
		try {
			Class.forName(driver);
//			String url = "jdbc:oracle:thin:@192.168.27.135:1521:orcl";
//			String user = "amode_develop";
//			String password = "amode_develop";
			Properties properties = new Properties();
			properties.put("user", user);
			properties.put("password", password);
			properties.put("remarksReporting", "true");// 想要获取数据库结构中的注释，这个值是重点
			return DriverManager.getConnection(url, properties);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Table getTableInfo(String tableName, String driver, String url, String user, String password) throws Exception {
		DatabaseMetaData dbmd = null;
		Connection conn = getConnection(driver, url, user, password);
		dbmd = conn.getMetaData();
		ResultSet resultSet = dbmd.getTables(null, "%", tableName, new String[] { "TABLE" });

		while (resultSet.next()) {
			Table table = new Table();
			table.setTableName(tableName);
			List<Column> columns = new ArrayList<Column>();
			
			String schema = getSchema(conn);
			ResultSet pks = dbmd.getPrimaryKeys(null, schema, tableName.toUpperCase());
			String pkColumnName = "";
			while(pks.next()){
				pkColumnName = pks.getString("COLUMN_NAME");
				table.setPkColumnName(pkColumnName);
				break;
			}
			
			ResultSet rs = dbmd.getColumns(null, schema, tableName.toUpperCase(), "%");
			while (rs.next()) {

				Column column = new Column();

				String columnName = rs.getString("COLUMN_NAME");
				column.setColumnName(columnName);
				column.setPrimaryKey(columnName.equals(pkColumnName));

				int sqlType = rs.getInt("DATA_TYPE");
				column.setSqlType(sqlType);

				String remarks = rs.getString("REMARKS");
				column.setRemarks(remarks);

				String columnType = rs.getString("TYPE_NAME");
				column.setColumnType(columnType);

				columns.add(column);
			}
			table.setColumns(columns);
			return table;
		}
		return null;
	}

	// 其他数据库不需要这个方法 oracle和db2需要
	private static String getSchema(Connection conn) throws Exception {
		String schema;
		schema = conn.getMetaData().getUserName();
		if ((schema == null) || (schema.length() == 0)) {
			throw new Exception("ORACLE数据库模式不允许为空");
		}
		return schema.toUpperCase().toString();

	}

	public static void main(String[] args) throws Exception {
		String driver = "oracle.jdbc.OracleDriver";
		String url = "jdbc:oracle:thin:@192.168.27.21:1521:orcl";
		String user = "amode_develop";
		String password = "amode_develop";
		// 这里是Oracle连接方法
		String tableName = "AM_SELLER";
		
		Table table = getTableInfo(tableName,driver,url,user,password);

//		List<Column> list;
		if (table != null) {
			System.out.println(table);
//			list = table.getColumns();
//			list.stream().forEach(System.out::println);
		}

	}

}
