package org.springframework.data.mybatis.repository.query;

import java.io.Serializable;

import org.springframework.data.mybatis.repository.support.MybatisRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CustomMybatisRepository<T, ID extends Serializable> extends MybatisRepository<T, ID>{
	<S extends T> S insertIgnoreNull(S entity);
}
