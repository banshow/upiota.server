package io.github.upiota.framework.mybatis.data;

import java.lang.reflect.Method;

import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.springframework.data.repository.query.DefaultParameters;
import org.springframework.data.repository.query.Parameters;

import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.mapperhelper.MapperTemplate;
import tk.mybatis.mapper.mapperhelper.SqlHelper;

public class MyProvider extends MapperTemplate {

	public MyProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
		super(mapperClass, mapperHelper);
	}
	
	
	public String createSql(MappedStatement ms) {
        Class<?> entityClass = getEntityClass(ms);
        //修改返回值类型为实体类型
        setResultType(ms, entityClass);
        StringBuilder sql = new StringBuilder();
        sql.append(SqlHelper.selectAllColumns(entityClass));
        sql.append(SqlHelper.fromTable(entityClass, tableName(entityClass)));
        sql.append(SqlHelper.whereAllIfColumns(entityClass, isNotEmpty()));
        return sql.toString();
    }


	public void setSqlSource(MappedStatement ms,Method method) throws Exception {
		Class<?> entityClass = getEntityClass(ms);
		
		PartTree tree = new PartTree(method.getName(), entityClass);
		
		Parameters<?,?> parameters = new DefaultParameters(method);
		
        //修改返回值类型为实体类型
        //setResultType(ms, entityClass);
        StringBuilder sql = new StringBuilder();
        sql.append(MySqlHelper.selectColumns(entityClass,tree));
        sql.append(MySqlHelper.fromTable(entityClass, tableName(entityClass)));
        sql.append(MySqlHelper.getWhereClause(entityClass,tree,parameters));
        
        
        SqlSource sqlSource = createSqlSource(ms, sql.toString());
        //替换原有的SqlSource
        setSqlSource(ms, sqlSource);
	}
	
	

}
