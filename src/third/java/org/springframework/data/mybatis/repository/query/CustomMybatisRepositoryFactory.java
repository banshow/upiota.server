package org.springframework.data.mybatis.repository.query;

import static org.springframework.data.querydsl.QueryDslUtils.QUERY_DSL_PRESENT;

import java.io.Serializable;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.mybatis.domains.AuditDateAware;
import org.springframework.data.mybatis.mapping.MybatisMappingContext;
import org.springframework.data.mybatis.repository.dialect.Dialect;
import org.springframework.data.mybatis.repository.query.CustomMybatisQueryLookupStrategy;
import org.springframework.data.mybatis.repository.query.MybatisQueryLookupStrategy;
import org.springframework.data.mybatis.repository.support.MybatisEntityInformation;
import org.springframework.data.mybatis.repository.support.MybatisEntityInformationSupport;
import org.springframework.data.mybatis.repository.support.MybatisSimpleRepositoryMapperGenerator;
import org.springframework.data.mybatis.repository.support.QueryDslMybatisRepository;
import org.springframework.data.mybatis.repository.support.SimpleMybatisRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;
import org.springframework.data.repository.query.EvaluationContextProvider;
import org.springframework.data.repository.query.QueryLookupStrategy;
import org.springframework.data.repository.query.QueryLookupStrategy.Key;
import org.springframework.util.Assert;

public class CustomMybatisRepositoryFactory extends RepositoryFactorySupport {

    private final SqlSessionTemplate    sessionTemplate;
    private final Dialect               dialect;
    private final MybatisMappingContext mappingContext;
    private final AuditorAware<?>       auditorAware;
    private final AuditDateAware<?>     auditDateAware;

    public CustomMybatisRepositoryFactory(final MybatisMappingContext mappingContext,
                                    final SqlSessionTemplate sessionTemplate,
                                    final Dialect dialect,
                                    AuditorAware<?> auditorAware,
                                    AuditDateAware<?> auditDateAware) {
        Assert.notNull(sessionTemplate,"sessionTemplate must not null");
        Assert.notNull(dialect,"dialect must not null");
        this.mappingContext = mappingContext;
        this.sessionTemplate = sessionTemplate;
        this.dialect = dialect;
        this.auditorAware = auditorAware;
        this.auditDateAware = auditDateAware;
    }

    @Override
    public <T, ID extends Serializable> MybatisEntityInformation<T, ID> getEntityInformation(Class<T> domainClass) {

        return (MybatisEntityInformation<T, ID>)MybatisEntityInformationSupport.getEntityInformation(mappingContext, auditorAware, auditDateAware, domainClass);
    }

    @Override
    protected Object getTargetRepository(RepositoryInformation information) {

        // generate Mapper statements.
        new CustomMybatisSimpleRepositoryMapperGenerator(sessionTemplate.getConfiguration(), dialect, mappingContext, information.getDomainType())
                .generate();


        return getTargetRepositoryViaReflection(information,
                getEntityInformation(information.getDomainType()),
                sessionTemplate);

    }

    @Override
    protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
        if (isQueryDslExecutor(metadata.getRepositoryInterface())) {
            return QueryDslMybatisRepository.class;
        } else {
            return SimpleMybatisRepository.class;
        }
    }

    @Override
    protected QueryLookupStrategy getQueryLookupStrategy(Key key, EvaluationContextProvider evaluationContextProvider) {
        return CustomMybatisQueryLookupStrategy.create(mappingContext, sessionTemplate, dialect, key, evaluationContextProvider);
    }


    private boolean isQueryDslExecutor(Class<?> repositoryInterface) {
        return QUERY_DSL_PRESENT && QueryDslPredicateExecutor.class.isAssignableFrom(repositoryInterface);
    }


}