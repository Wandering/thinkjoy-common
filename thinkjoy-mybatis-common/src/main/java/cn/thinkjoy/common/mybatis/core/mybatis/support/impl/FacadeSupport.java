package cn.thinkjoy.common.mybatis.core.mybatis.support.impl;

import cn.thinkjoy.MVCException;
import cn.thinkjoy.common.mybatis.core.mybatis.domain.PKEntity;
import cn.thinkjoy.common.mybatis.core.mybatis.paging.PagingResult;
import cn.thinkjoy.common.mybatis.core.mybatis.support.FacadeCRUD;
import cn.thinkjoy.common.mybatis.core.mybatis.support.ServiceCRUD;
import cn.thinkjoy.common.mybatis.core.mybatis.support.req.PagingBean;
import cn.thinkjoy.common.mybatis.core.mybatis.support.resp.FacadeResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.List;

/**
 * 服务基层类
 * 
 * @author shadow
 * 
 * @param <T> 
 *            数据模型
 * @param <PK>
 *            数据主键
 */
public abstract class FacadeSupport<T extends PKEntity<PK>, PK extends Serializable> implements FacadeCRUD<T, PK> {

	protected Logger log = LoggerFactory.getLogger(getClass());

	public abstract ServiceCRUD<T, PK> getServiceCRUD();

	public FacadeResp<T> findById(PK pk) {
		try {
			return new FacadeResp<T>(getServiceCRUD().findById(pk));
		} catch (MVCException e) {
			log.error(e.getMessage());
			return new FacadeResp<T>(e.getErrorCode(), e.getMessage());
		}
	}

	public FacadeResp<T> save(T entity) {
		try {
			return new FacadeResp<T>(getServiceCRUD().save(entity));
		} catch (MVCException e) {
			log.error(e.getMessage());
			return new FacadeResp<T>(e.getErrorCode(), e.getMessage());
		}
	}

	public FacadeResp<T> update(T entity) {
		try {
			return new FacadeResp<T>(getServiceCRUD().update(entity));
		} catch (MVCException e) {
			log.error(e.getMessage());
			return new FacadeResp<T>(e.getErrorCode(), e.getMessage());
		}
	}

	public FacadeResp<T> delete(T entity) {
		try {
			getServiceCRUD().delete(entity);
			return new FacadeResp<T>(entity);
		} catch (MVCException e) {
			log.error(e.getMessage());
			return new FacadeResp<T>(e.getErrorCode(), e.getMessage());
		}
	}

	public FacadeResp<T> delete(PK pk) {
		try {
			getServiceCRUD().delete(pk);
			FacadeResp<T> facadeResp = new FacadeResp<T>();
			facadeResp.setSuccess(true);
			return facadeResp;
		} catch (MVCException e) {
			log.error(e.getMessage());
			return new FacadeResp<T>(e.getErrorCode(), e.getMessage());
		}
	}

	public FacadeResp<List<T>> findAll() {
		try {
			return new FacadeResp<List<T>>(getServiceCRUD().findAll());
		} catch (MVCException e) {
			log.error(e.getMessage());
			return new FacadeResp<List<T>>(e.getErrorCode(), e.getMessage());
		}
	}

	public FacadeResp<PagingResult<T>> findByPagingResult(PagingBean pagingBean) {
		try {
			return new FacadeResp<PagingResult<T>>(getServiceCRUD().findByPagingResult(pagingBean.getOffset(),
					pagingBean.getLimit()));
		} catch (MVCException e) {
			log.error(e.getMessage());
			return new FacadeResp<PagingResult<T>>(e.getErrorCode(), e.getMessage());
		}
	}

}
