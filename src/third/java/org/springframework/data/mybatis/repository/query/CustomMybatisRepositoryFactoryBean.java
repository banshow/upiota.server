package org.springframework.data.mybatis.repository.query;

import java.io.Serializable;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.mybatis.domains.AuditDateAware;
import org.springframework.data.mybatis.mapping.MybatisMappingContext;
import org.springframework.data.mybatis.repository.dialect.Dialect;
import org.springframework.data.mybatis.repository.support.MybatisRepositoryFactory;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;
import org.springframework.data.repository.core.support.TransactionalRepositoryFactoryBeanSupport;
import org.springframework.util.Assert;

public class CustomMybatisRepositoryFactoryBean<T extends Repository<S, ID>, S, ID extends Serializable>
		extends TransactionalRepositoryFactoryBeanSupport<T, S, ID> {

	private SqlSessionTemplate sqlSessionTemplate;
	private Dialect dialect;
	private MybatisMappingContext mappingContext;

	@Autowired(required = false)
	private AuditorAware<?> auditorAware;
	@Autowired(required = false)
	private AuditDateAware<?> auditDateAware;

	/**
	 * Creates a new {@link TransactionalRepositoryFactoryBeanSupport} for the
	 * given repository interface.
	 *
	 * @param repositoryInterface
	 *            must not be {@literal null}.
	 */
	public CustomMybatisRepositoryFactoryBean(Class<? extends T> repositoryInterface) {
		super(repositoryInterface);
	}

	@Override
	public void afterPropertiesSet() {
		Assert.notNull(sqlSessionTemplate, "SqlSessionTemplate must not be null.");
		Assert.notNull(dialect, "Database dialect must not be null.");
		super.afterPropertiesSet();
	}

	public void setMappingContext(MybatisMappingContext mappingContext) {
		this.mappingContext = mappingContext;
		super.setMappingContext(mappingContext);
	}

	@Override
	protected RepositoryFactorySupport doCreateRepositoryFactory() {
		return new CustomMybatisRepositoryFactory(mappingContext, sqlSessionTemplate, dialect, auditorAware, auditDateAware);
	}

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	public void setDialect(Dialect dialect) {
		this.dialect = dialect;
	}

	public void setAuditorAware(AuditorAware<?> auditorAware) {
		this.auditorAware = auditorAware;
	}
}
