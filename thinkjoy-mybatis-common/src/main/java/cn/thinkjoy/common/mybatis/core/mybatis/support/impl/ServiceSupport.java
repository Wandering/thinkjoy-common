package cn.thinkjoy.common.mybatis.core.mybatis.support.impl;

import cn.thinkjoy.MVCException;
import cn.thinkjoy.common.mybatis.core.mybatis.criteria.Cnd;
import cn.thinkjoy.common.mybatis.core.mybatis.dao.MyBatisDAO;
import cn.thinkjoy.common.mybatis.core.mybatis.domain.PKEntity;
import cn.thinkjoy.common.mybatis.core.mybatis.paging.PagingResult;
import cn.thinkjoy.common.mybatis.core.mybatis.support.ServiceCRUD;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
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
public abstract class ServiceSupport<T extends PKEntity<PK>, PK extends Serializable> implements ServiceCRUD<T, PK> {

	protected Logger log = LoggerFactory.getLogger(getClass());

	public abstract MyBatisDAO<T, PK> getMyBatisDAO();

	public T findById(PK pk) throws MVCException {
		return getMyBatisDAO().findById(pk);
	}

	public T save(T entity) throws MVCException {
		getMyBatisDAO().insert(entity);
		return entity;
	}

	public T update(T entity) throws MVCException {
		getMyBatisDAO().update(entity);
		return entity;
	}

	public int delete(T entity) throws MVCException {
		return getMyBatisDAO().delete(entity);
	}

	public int delete(PK pk) throws MVCException {
		return getMyBatisDAO().delete(pk);
	}

	public List<T> findAll() throws MVCException {
		return getMyBatisDAO().findAll();
	}

	public PagingResult<T> findByPagingResult(long offset, long limit) throws MVCException {
		return getMyBatisDAO().pagingByCriteria(Cnd.builder(getGenType()).buildCriteria().limit(offset, limit));
	}

	@SuppressWarnings("unchecked")
	private Class<T> getGenType() throws MVCException {
		Type genType = getClass().getGenericSuperclass();
		if (genType == null) {
			throw new MVCException("没有找到上级接口或父类");
		}
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		if (params == null || params.length <= 0) {
			throw new MVCException("没有找到上级接口或父类的泛型声明");
		}
		return (Class<T>) params[0];
	}

	protected <E extends PKEntity<PK>> List<PK> getEntityIds(List<E> entities) {
		if (entities != null && !entities.isEmpty()) {
			List<PK> ids = new ArrayList<PK>(entities.size());
			for (E entity : entities) {
				ids.add(entity.getId());
			}
			return ids;
		}
		return null;
	}

}
