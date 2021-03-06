package cn.thinkjoy.common.mybatis.core.mybatis.criteria;

import cn.thinkjoy.common.mybatis.core.mybatis.paging.Pagination;
import cn.thinkjoy.common.mybatis.core.mybatis.utils.Maps;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * 封装页面查询条件
 * 
 */
public interface Criteria {

	/**
	 * 设置分页查询的起止索引值
	 * 
	 * @param start
	 *            分页查询开始索引
	 * @param end
	 *            分页查询结束索引
	 */
	Criteria limit(Long start, Long end);

	/**
	 * 设置分页对象
	 * @param pagination
	 * @return
	 */
	Criteria page(Pagination pagination);

	/**
	 * 获取分页查询开始索引
	 */
	Long getFirst();

	/**
	 * 获取结束查询开始索引
	 */
	Long getLast();

	/**
	 * 添加一个或多个过滤条件
	 * 
	 * @param conditions
	 *            过滤条件
	 */
	Criteria add(Condition... conditions);

	/**
	 * 添加一个或多个过滤条件
	 * 
	 * @param conditions
	 *            过滤条件
	 */
	Criteria add(List<Condition> conditions);

	/**
	 * 添加一个查询排序条件
	 * 
	 * @param order
	 *            排序条件
	 */
	Criteria orderBy(OrderBy order);

	/**
	 * 是否要使用分页查询,如果设置为false，就算设置了分页查询起止索引也不会执行分页查询。
	 * 
	 * @param limitable
	 *            分页查询标志
	 */
	Criteria useLimitit(Boolean limitable);

	/**
	 * 是否要使用分页查询
	 */
	Boolean isHasLimit();

	/**
	 * 是否有过滤条件
	 * 
	 * @return true表示有，否则没有。
	 */
	Boolean isHasConditionon();

	/**
	 * 是否有排序条件
	 * 
	 * @return true表示有，否则没有。
	 */
	Boolean isHasOrderBy();

	/**
	 * 返回所有的查询过滤条件
	 */
	List<Condition> getConditions();

	/**
	 * 返回所有的排序条件
	 * 
	 * @return
	 */
	List<OrderBy> getOrderBys();

	/**
	 * 返回所有扩展的属性，用于Condition无法满足的情况下，可以自定义一些参数传给Dao
	 * 
	 * @return
	 */
	Map<Object, Object> getParams();

	/**
	 * 设定义参数，　做为过滤条件
	 * 
	 * @param params
	 * @return
	 */
	Criteria setParams(Map<Object, Object> params);

	/**
	 * 增加自定义条件参数
	 * 
	 * @param paramName
	 * @param value
	 * @return
	 */
	Criteria addParam(Object paramName, Object value);

	/**
	 * 增加自定义条件参数
	 * 
	 * @param values
	 * @return
	 */
	Criteria addParams(Object... values);

	/**
	 * 增加更新的字段
	 *
	 * @param paramName
	 * @param value
	 * @return
	 */
	Criteria addUpdateParam(Object paramName, Object value);

	/**
	 * 增加更新的字段
	 *
	 * @param updateParams
	 * @return
	 */
	Criteria addUpdateParam(Map<Object, Object> updateParams);

	/**
	 * 增加自定义查询字段
	 * @param selector
	 * @return
	 */
	Criteria setSelector(Set<Object> selector);

	/**
	 * 增加自定义查询字段
	 * @param paramName
	 * @return
	 */
	Criteria addSelector(Object paramName);

	/**
	 * 增加自定义查询字段
	 * @param param
	 * @return
	 */
	Criteria addSelector(Object... param);

	/**
	 * 设置分页对象
	 * @param pagination
	 */
	void setPagination(Pagination pagination);

	/**
	 * 获取分页对象
	 * @return
	 */
	Pagination getPagination();

	String toString();

}
