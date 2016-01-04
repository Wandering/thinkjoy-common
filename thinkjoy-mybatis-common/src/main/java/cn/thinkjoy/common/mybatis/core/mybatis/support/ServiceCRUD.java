package cn.thinkjoy.common.mybatis.core.mybatis.support;

import cn.thinkjoy.MVCException;
import cn.thinkjoy.common.mybatis.core.mybatis.domain.PKEntity;
import cn.thinkjoy.common.mybatis.core.mybatis.paging.PagingResult;

import java.io.Serializable;
import java.util.List;

public interface ServiceCRUD<T extends PKEntity<PK>, PK extends Serializable> {

	public abstract T findById(PK pk) throws MVCException;

	public abstract T save(T entity) throws MVCException;

	public abstract T update(T entity) throws MVCException;

	public abstract int delete(T entity) throws MVCException;

	public abstract int delete(PK pk) throws MVCException;

	public abstract List<T> findAll() throws MVCException;

	public abstract PagingResult<T> findByPagingResult(long offset, long limit) throws MVCException;

}
