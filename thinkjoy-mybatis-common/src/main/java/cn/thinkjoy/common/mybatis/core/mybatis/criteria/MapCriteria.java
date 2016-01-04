package cn.thinkjoy.common.mybatis.core.mybatis.criteria;

import java.util.Map;

/**
 * 
 * 封装页面查询条件
 * 
 */
public interface MapCriteria {

	/**
	 * 设置分页查询的起止索引值
	 * 
	 * @param start
	 *            分页查询开始索引
	 * @param end
	 *            分页查询结束索引
	 */
	MapCriteria limit(Long start, Long end);

	/**
	 * 设置分页查询开始索引
	 * 
	 * @param start
	 *            分页查询开始索引
	 */
	Long getFirst();

	/**
	 * 设置结束查询开始索引
	 * 
	 * @param end
	 *            分页查询结束索引
	 */
	Long getLast();

	void setTotalCount(Long totalCount);

	Long getTotalCount();

	/**
	 * 是否要使用分页查询
	 */
	Boolean isHasLimit();

	/**
	 * 返回所有扩展的属性，用于Condition无法满足的情况下，可以自定义一些参数传给Dao
	 * 
	 * @return
	 */
	Map<?, ?> getParams();

	/**
	 * 设定义参数，　做为过滤条件
	 * 
	 * @param params
	 * @return
	 */
	MapCriteria setParams(Map<?, ?> params);

	/**
	 * 增加自定义条件参数
	 * 
	 * @param paramName
	 * @param value
	 * @return
	 */
	MapCriteria addParam(Object paramName, Object value);

	String toString();

}
