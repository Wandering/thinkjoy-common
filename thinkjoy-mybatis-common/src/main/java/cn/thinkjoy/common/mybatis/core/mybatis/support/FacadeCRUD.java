package cn.thinkjoy.common.mybatis.core.mybatis.support;

import cn.thinkjoy.common.mybatis.core.mybatis.domain.PKEntity;
import cn.thinkjoy.common.mybatis.core.mybatis.paging.PagingResult;
import cn.thinkjoy.common.mybatis.core.mybatis.support.req.PagingBean;
import cn.thinkjoy.common.mybatis.core.mybatis.support.resp.FacadeResp;

import java.io.Serializable;
import java.util.List;

public interface FacadeCRUD<T extends PKEntity<PK>, PK extends Serializable> {

	public abstract FacadeResp<T> findById(PK pk);

	public abstract FacadeResp<T> save(T entity);

	public abstract FacadeResp<T> update(T entity);

	public abstract FacadeResp<T> delete(T entity);

	public abstract FacadeResp<T> delete(PK pk);

	public abstract FacadeResp<List<T>> findAll();

	public abstract FacadeResp<PagingResult<T>> findByPagingResult(PagingBean pagingBean);

}
