package cn.thinkjoy.common.mybatis.core.mybatis.dao.impl;

import cn.thinkjoy.MVCException;
import cn.thinkjoy.common.mybatis.core.mybatis.criteria.Cnd;
import cn.thinkjoy.common.mybatis.core.mybatis.criteria.Criteria;
import cn.thinkjoy.common.mybatis.core.mybatis.criteria.CriteriaBuilder;
import cn.thinkjoy.common.mybatis.core.mybatis.dao.MyBatisDAO;
import cn.thinkjoy.common.mybatis.core.mybatis.domain.PKEntity;
import cn.thinkjoy.common.mybatis.core.mybatis.domain.PageBean;
import cn.thinkjoy.common.mybatis.core.mybatis.paging.DefaultPagingResult;
import cn.thinkjoy.common.mybatis.core.mybatis.paging.PagingResult;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * MyBatisDAO CRUD封装实现类
 * 
 * @author shadow
 * 
 * @param <E>
 * @param <PK>
 */
public class SimpleMyBatisDAO<E extends PKEntity<PK>, PK extends Serializable> extends SqlSessionDaoSupport implements
		MyBatisDAO<E, PK> {

	private final static Logger log = LoggerFactory.getLogger(SimpleMyBatisDAO.class);

	@Autowired
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	public int insert(E entity) throws MVCException {
		validateNullObject(entity);
		try {
			return getSqlSession().insert(sqlId("insert"), entity);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new MVCException("保存数据失败", e, "10002");
		}
	}

	public int insert(List<E> entities) throws MVCException {
		validateNullObject(entities);
		if (entities.isEmpty()) {
			throw new MVCException("实体参数集合不能为空", "10003");
		}
		try {
			for (E entity : entities) {
				this.insert(entity);
			}
			return entities.size();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new MVCException("保存数据失败", e, "10002");
		}
	}

	public int update(E entity) throws MVCException {
		validateNullObject(entity);
		try {
			return getSqlSession().update(sqlId("update"), entity);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new MVCException("更新数据失败", e, "10004");
		}
	}

	public int delete(E... entities) throws MVCException {
		validateNullObject(entities);
		if (entities.length <= 0) {
			throw new MVCException("实体参数集合不能为空", "10003");
		}
		try {
			for (E entity : entities) {
				validateNullObject(entities);
				if (entity.getId() == null) {
					throw new MVCException("实体参数主键不能为空", "10004");
				}
				getSqlSession().delete(sqlId("deleteById"), entity.getId());
			}
			return entities.length;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new MVCException("删除数据失败", e, "10005");
		}
	}

	public int delete(E entity) throws MVCException {
		try {
			validateNullObject(entity);
			if (entity.getId() == null) {
				throw new MVCException("实体参数主键不能为空", "10004");
			}
			return getSqlSession().delete(sqlId("deleteById"), entity.getId());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new MVCException("删除数据失败", e, "10005");
		}
	}

	public int delete(PK... pks) throws MVCException {
		validateNullObject(pks);
		if (pks.length <= 0) {
			throw new MVCException("实体参数集合不能为空", "10003");
		}
		try {
			for (PK pk : pks) {
				getSqlSession().delete(sqlId("deleteById"), pk);
			}
			return pks.length;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new MVCException("删除数据失败", e, "10005");
		}
	}

	public int delete(PK pk) throws MVCException {
		try {
			validateNullObject(pk);
			return getSqlSession().delete(sqlId("deleteById"), pk);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new MVCException("删除数据失败", e, "10005");
		}
	}

	public E findById(PK pk) throws MVCException {
		validateNullObject(pk);
		try {
			return findOneByCriteria(createBuilder().andEQ("id", pk).buildCriteria());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new MVCException("查询数据失败", e, "10006");
		}
	}

	public int countByCriteria(Criteria criteria) throws MVCException {
		validateNullObject(criteria);
		try {
			return getSqlSession().selectOne(sqlId("countByCriteria"), criteria);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new MVCException("查询数据记录失败", e, "10007");
		}
	}

	public List<E> findByCriteria(Criteria criteria) throws MVCException {
		validateNullObject(criteria);
		try {
			return getSqlSession().selectList(sqlId("findByCriteria"), criteria);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new MVCException("查询数据记录失败", e, "10007");
		}
	}

	public int countAll() throws MVCException {
		try {
			return getSqlSession().selectOne(sqlId("countByCriteria"), createBuilder().buildCriteria());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new MVCException("查询数据记录失败", e, "10007");
		}
	}

	public List<E> findAll() throws MVCException {
		try {
			return getSqlSession().selectList(sqlId("findByCriteria"), createBuilder().buildCriteria());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new MVCException("查询数据记录失败", e, "10007");
		}
	}

	public E findOneByCriteria(Criteria criteria) throws MVCException {
		List<E> list = findByCriteria(criteria);
		if (list != null && !list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

	public PagingResult<E> pagingByCriteria(Criteria criteria) throws MVCException {
		validateNullObject(criteria);
		try {
			return pagingQuery(sqlId("countByCriteria"), sqlId("findByCriteria"), criteria);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new MVCException("查询数据记录失败", e, "10007");
		}
	}

	/** 校验空参数 */
	private void validateNullObject(Object obj) throws MVCException {
		if (obj == null) {
			throw new MVCException("实体参数对象不能为空", "10001");
		}
	}

	/** 分页查询封装 */
	protected PagingResult<E> pagingQuery(String countSqlId, String findSqlId, Criteria criteria) {
		if (!criteria.isHasLimit().booleanValue()) {
			List<E> allResult = getSqlSession().selectList(findSqlId, criteria);
			long size = allResult != null ? allResult.size() : 0L;
			return new DefaultPagingResult<E>(-1l, -1l, size, allResult);
		} else {
			Long totalCount = Long.parseLong(getSqlSession().selectOne(countSqlId, criteria).toString());
			RowBounds bounds = new RowBounds(criteria.getFirst().intValue(), criteria.getLast().intValue());
			List<E> result = getSqlSession().selectList(findSqlId, criteria, bounds);
			return new DefaultPagingResult<E>(criteria.getFirst(), criteria.getLast(), totalCount, result);
		}
	}

	/**
	 * 自定义分页查询抽象方法
	 * 
	 * @param countSqlId
	 *            统计语句
	 * @param findSqlId
	 *            集合语句
	 * @param pageBean
	 *            自定义对象需要继承PageBean
	 * @return PagingResult<Y>
	 */
	protected <Y> PagingResult<Y> pagingQueryByBean(String countSqlId, String findSqlId, PageBean pageBean) {
		Long totalCount = Long.parseLong(getSqlSession().selectOne(countSqlId, pageBean).toString());
		RowBounds bounds = new RowBounds(pageBean.getPageNo().intValue(), pageBean.getPageSize().intValue());
		List<Y> result = getSqlSession().selectList(findSqlId, pageBean, bounds);
		return new DefaultPagingResult<Y>(pageBean.getPageNo(), pageBean.getPageSize(), totalCount, result);
	}

	/**
	 * 自定义分页查询抽象方法
	 * 
	 * @param countSqlId
	 *            统计语句
	 * @param findSqlId
	 *            集合语句
	 * @param criteria
	 *            通过addParam方法添加自定义参数
	 * @return PagingResult<Y>
	 */
	protected <Y> PagingResult<Y> pagingQueryByCriteria(String countSqlId, String findSqlId, Criteria criteria) {
		Long totalCount = Long.parseLong(getSqlSession().selectOne(countSqlId, criteria).toString());
		RowBounds bounds = new RowBounds(criteria.getFirst().intValue(), criteria.getLast().intValue());
		List<Y> result = getSqlSession().selectList(findSqlId, criteria, bounds);
		return new DefaultPagingResult<Y>(criteria.getFirst(), criteria.getLast(), totalCount, result);
	}

	/**
	 * 获取实体泛型
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private Class<E> getGenType() throws MVCException {
		Type genType = getClass().getGenericSuperclass();
		if (genType == null) {
			throw new MVCException("没有找到上级接口或父类");
		}
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		if (params == null || params.length <= 0) {
			throw new MVCException("没有找到上级接口或父类的泛型声明");
		}
		return (Class<E>) params[0];
	}

	/** 创建查询对象 */
	private CriteriaBuilder createBuilder() throws MVCException {
		return Cnd.builder(getGenType());
	}

	/**
	 * 获取DAO模型的访问路径
	 * 
	 * @throws MVCException
	 */
	protected String sqlId(String suffix) throws MVCException {
		StringBuffer buffer = new StringBuffer();
		String sqlId = buffer.append(getGenType().getName()).append(".").append(suffix).toString();
		buffer.setLength(0);
		return sqlId;
	}

}
