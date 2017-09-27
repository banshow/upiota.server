package org.springframework.data.mybatis.repository.query;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mybatis.repository.support.MybatisEntityInformation;
import org.springframework.data.mybatis.repository.support.MybatisNoHintException;
import org.springframework.data.mybatis.repository.support.SqlSessionRepositorySupport;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

public class CustomSimpleMybatisRepository<T, ID extends Serializable> extends SqlSessionRepositorySupport
		implements CustomMybatisRepository<T, ID> {

	private static final String STATEMENT_INSERT = "_insert";
	private static final String STATEMENT_INSERT_IGNORE_NULL = "_insertIgnoreNull";
	private static final String STATEMENT_UPDATE = "_update";
	private static final String STATEMENT_UPDATE_IGNORE_NULL = "_updateIgnoreNull";
	private static final String STATEMENT_GET_BY_ID = "_getById";
	private static final String STATEMENT_DELETE_BY_ID = "_deleteById";

	private final MybatisEntityInformation<T, ID> entityInformation;
	private AuditorAware<Long> auditorAware;

	public CustomSimpleMybatisRepository(MybatisEntityInformation<T, ID> entityInformation,
			SqlSessionTemplate sqlSessionTemplate) {
		super(sqlSessionTemplate);
		this.entityInformation = entityInformation;
	}

	@Override
	protected String getNamespace() {
		return entityInformation.getJavaType().getName();
	}

	@Override
	@Transactional
	public <S extends T> S insert(S entity) {
		entityInformation.setCreatedDate(entity);
		entityInformation.setCreatedBy(entity);

		if (entityInformation.hasVersion()) {
			entityInformation.setVersion(entity, 0);
		}

		insert(STATEMENT_INSERT, entity);
		return entity;
	}
	
	@Override
	@Transactional
	public <S extends T> S insertIgnoreNull(S entity) {
		entityInformation.setCreatedDate(entity);
		entityInformation.setCreatedBy(entity);
		
		if (entityInformation.hasVersion()) {
			entityInformation.setVersion(entity, 0);
		}
		
		insert(STATEMENT_INSERT_IGNORE_NULL, entity);
		return entity;
	}

	@Override
	@Transactional
	public <S extends T> S update(S entity) {
		entityInformation.setLastModifiedDate(entity);
		entityInformation.setLastModifiedBy(entity);

		int row = update(STATEMENT_UPDATE, entity);
		if (row == 0) {
			throw new MybatisNoHintException("update effect 0 row, maybe version control lock occurred.");
		}
		if (entityInformation.hasVersion()) {
			entityInformation.increaseVersion(entity);
		}
		return entity;
	}

	@Override
	@Transactional
	public <S extends T> S updateIgnoreNull(S entity) {
		entityInformation.setLastModifiedDate(entity);
		entityInformation.setLastModifiedBy(entity);

		int row = update(STATEMENT_UPDATE_IGNORE_NULL, entity);
		if (row == 0) {
			throw new MybatisNoHintException("update effect 0 row, maybe version control lock occurred.");
		}
		if (entityInformation.hasVersion()) {
			entityInformation.increaseVersion(entity);
		}
		return entity;
	}

	@Override
	@Transactional
	public <S extends T> S save(S entity) {
		Assert.notNull(entity, "entity can not be null");

		if (entityInformation.isNew(entity)) {
			// insert
			insert(entity);
		} else {
			// update
			update(entity);
		}
		return entity;
	}

	@Override
	@Transactional
	public <S extends T> S saveIgnoreNull(S entity) {
		Assert.notNull(entity, "entity can not be null");

		if (entityInformation.isNew(entity)) {
			// insert
			insertIgnoreNull(entity);
		} else {
			// update
			updateIgnoreNull(entity);
		}
		return entity;
	}

	@Override
	public T findOne(ID id) {
		Assert.notNull(id, "id can not be null");
		return selectOne(STATEMENT_GET_BY_ID, id);
	}

	@Override
	public T findBasicOne(ID id, String... columns) {
		Assert.notNull(id);
		return selectOne("_getBasicById", id);
	}

	@Override
	public boolean exists(ID id) {
		return null != findOne(id);
	}

	@Override
	public long count() {
		return selectOne("_count");
	}

	@Override
	@Transactional
	public void delete(ID id) {
		Assert.notNull(id);
		super.delete(STATEMENT_DELETE_BY_ID, id);
	}

	@Override
	@Transactional
	public void delete(T entity) {
		Assert.notNull(entity);

		delete(entityInformation.getId(entity));
	}

	@Override
	@Transactional
	public void delete(Iterable<? extends T> entities) {
		if (null == entities) {
			return;
		}
		for (T entity : entities) {
			delete(entity);
		}
	}

	@Override
	@Transactional
	public void deleteAll() {
		super.delete("_deleteAll");
	}

	@Override
	public List<T> findAll() {
		return findAll((T) null);
	}

	@Override
	public List<T> findAll(Sort sort) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("_sorts", sort);
		return selectList("_findAll", params);
	}

	@Override
	public List<T> findAll(Iterable<ID> ids) {
		if (null == ids) {
			return Collections.emptyList();
		}

		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("_ids", ids);
		return selectList("_findAll", params);
	}

	@Override
	@Transactional
	public <S extends T> List<S> save(Iterable<S> entities) {
		if (null == entities) {
			return Collections.emptyList();
		}
		for (S entity : entities) {
			save(entity);
		}
		return (List<S>) entities;
	}

	@Override
	public <S extends T> List<S> findAll(Example<S> example) {
		return null;
	}

	@Override
	public <S extends T> List<S> findAll(Example<S> example, Sort sort) {
		return null;
	}

	@Override
	public <S extends T> S findOne(Example<S> example) {
		return null;
	}

	@Override
	public <S extends T> Page<S> findAll(Example<S> example, Pageable pageable) {
		return null;
	}

	@Override
	public <S extends T> long count(Example<S> example) {
		return 0;
	}

	@Override
	public <S extends T> boolean exists(Example<S> example) {
		return false;
	}

	@Override
	public Page<T> findAll(Pageable pageable) {
		if (null == pageable) {
			return new PageImpl<T>(findAll());
		}
		return findAll(pageable, null);
	}

	@Override
	public <X extends T> T findOne(X condition, String... columns) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("_condition", condition);
		if (null != columns) {
			params.put("_specifiedFields", columns);
		}
		return selectOne("_findAll", params);
	}

	@Override
	public <X extends T> List<T> findAll(X condition, String... columns) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("_condition", condition);
		if (null != columns) {
			params.put("_specifiedFields", columns);
		}
		return selectList("_findAll", params);
	}

	@Override
	public <X extends T> List<T> findAll(Sort sort, X condition, String... columns) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("_condition", condition);
		params.put("_sorts", sort);
		if (null != columns) {
			params.put("_specifiedFields", columns);
		}
		return selectList("_findAll", params);
	}

	@Override
	public <X extends T> Page<T> findAll(Pageable pageable, X condition, String... columns) {
		Map<String, Object> otherParam = new HashMap<String, Object>();
		if (null != columns) {
			otherParam.put("_specifiedFields", columns);
		}
		return findByPager(pageable, "_findByPager", "_countByCondition", condition, otherParam);
	}

	@Override
	public <X extends T> Long countAll(X condition) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("_condition", condition);
		return selectOne("_countByCondition", params);
	}

	@Override
	public <X extends T> T findBasicOne(X condition, String... columns) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("_condition", condition);
		if (null != columns) {
			params.put("_specifiedFields", columns);
		}
		return selectOne("_findBasicAll", params);
	}

	@Override
	public <X extends T> List<T> findBasicAll(X condition, String... columns) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("_condition", condition);
		if (null != columns) {
			params.put("_specifiedFields", columns);
		}
		return selectList("_findBasicAll", params);
	}

	@Override
	public <X extends T> List<T> findBasicAll(Sort sort, X condition, String... columns) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("_condition", condition);
		params.put("_sorts", sort);
		if (null != columns) {
			params.put("_specifiedFields", columns);
		}
		return selectList("_findBasicAll", params);
	}

	@Override
	public <X extends T> Page<T> findBasicAll(Pageable pageable, X condition, String... columns) {
		return findByPager(pageable, "_findBasicByPager", "_countBasicByCondition", condition, columns);
	}

	@Override
	public <X extends T> Long countBasicAll(X condition) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("_condition", condition);
		return selectOne("_countBasicByCondition", params);
	}

	@Override
	@Transactional
	public <X extends T> int deleteByCondition(X condition) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("_condition", condition);
		return super.delete("_deleteByCondition", params);
	}

	@Override
	@Transactional
	public void deleteInBatch(Iterable<T> entities) {
		// FIXME improve delete in batch
		delete(entities);
	}
}