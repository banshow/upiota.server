package org.springframework.data.mybatis.repository.support;

import org.springframework.data.mybatis.mapping.MybatisPersistentEntity;
import org.springframework.data.mybatis.repository.dialect.Dialect;

public class CustomMybatisMapperGenerator extends MybatisMapperGenerator{

	private final Dialect                    dialect;
    private final MybatisPersistentEntity<?> persistentEntity;
	
	public CustomMybatisMapperGenerator(Dialect dialect, MybatisPersistentEntity<?> persistentEntity) {
		super(dialect, persistentEntity);
		this.dialect = dialect;
	    this.persistentEntity = persistentEntity;
	}

	
	
	
}
