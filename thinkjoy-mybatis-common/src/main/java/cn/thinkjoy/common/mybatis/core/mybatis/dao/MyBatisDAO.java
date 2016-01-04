package cn.thinkjoy.common.mybatis.core.mybatis.dao;

import cn.thinkjoy.MVCException;
import cn.thinkjoy.common.mybatis.core.mybatis.criteria.Criteria;
import cn.thinkjoy.common.mybatis.core.mybatis.domain.PKEntity;
import cn.thinkjoy.common.mybatis.core.mybatis.paging.PagingResult;

import java.io.Serializable;
import java.util.List;

/**
 * MyBatis-CRUD接口
 * 
 * @author shadow
 * 
 * @param <E>
 *            数据模型
 * @param <PK>
 *            模型主键
 */
public interface MyBatisDAO<E extends PKEntity<PK>, PK extends Serializable> {

	/**
	 * 保存实体信息
	 * 
	 * @param entity
	 * @return int
	 * @throws MVCException
	 */
	public int insert(E entity) throws MVCException;

	/**
	 * 保存实体集合信息
	 * 
	 * @param entities
	 * @return int
	 * @throws MVCException
	 */
	public int insert(List<E> entities) throws MVCException;

	/**
	 * 更新实体信息
	 * 
	 * @param entity
	 * @return int
	 * @throws MVCException
	 */
	public int update(E entity) throws MVCException;

	/**
	 * 通过对象删除实体信息
	 * 
	 * @param entities
	 * @return int
	 * @throws MVCException
	 */
	public int delete(E... entities) throws MVCException;

	/**
	 * 通过对象删除实体信息
	 * 
	 * @param entity
	 * @return int
	 * @throws MVCException
	 */
	public int delete(E entity) throws MVCException;

	/**
	 * 通过PK删除实体信息
	 * 
	 * @param pks
	 * @return int
	 * @throws MVCException
	 */
	public int delete(PK... pks) throws MVCException;

	/**
	 * 通过PK删除实体信息
	 * 
	 * @param pk
	 * @return int
	 * @throws MVCException
	 */
	public int delete(PK pk) throws MVCException;

	/**
	 * 通过PK查询实体信息
	 * 
	 * @param pk
	 * @return E
	 * @throws MVCException
	 */
	public E findById(PK pk) throws MVCException;

	/**
	 * 通过Criteria条件对象查询实体数目
	 * 
	 * @param criteria
	 * @return int
	 * @throws MVCException
	 */
	public int countByCriteria(Criteria criteria) throws MVCException;

	/**
	 * 查询实体数目
	 * 
	 * @return int
	 * @throws MVCException
	 */
	public int countAll() throws MVCException;

	/**
	 * 通过Criteria条件对象查询实体集合
	 * 
	 * @param criteria
	 * @return List<E>
	 * @throws MVCException
	 */
	public List<E> findByCriteria(Criteria criteria) throws MVCException;

	/**
	 * 查询实体集合
	 * 
	 * @return List<E>
	 * @throws MVCException
	 */
	public List<E> findAll() throws MVCException;

	/**
	 * 通过Criteria条件对象查询实体
	 * 
	 * @param criteria
	 * @return E
	 * @throws MVCException
	 */
	public E findOneByCriteria(Criteria criteria) throws MVCException;

	/**
	 * 通过Criteria条件对象分页查询实体
	 * 
	 * @param criteria
	 * @return PagingResult<E>
	 * @throws MVCException
	 */
	public PagingResult<E> pagingByCriteria(Criteria criteria) throws MVCException;

}
