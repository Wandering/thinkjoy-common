package cn.thinkjoy.common.mybatis.core.mybatis.criteria.impl;

import cn.thinkjoy.common.mybatis.core.mybatis.criteria.MapCriteria;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * MapCriteria 默认实现类
 * 
 */
@SuppressWarnings("serial")
public class DefaultMapCriteria implements MapCriteria, Serializable {

	private Boolean limitable = false;

	private Long start;

	private Long end;

	private Long totalCount;

	private Map<Object, Object> params;

	public DefaultMapCriteria() {
		params = new HashMap<Object, Object>();
	}

	public MapCriteria limit(Long start, Long end) {
		this.start = start;
		this.end = end;
		this.limitable = true;
		return this;
	}

	public Boolean isHasLimit() {
		return limitable;
	}

	public Long getFirst() {
		return (null == start || start <= 0) ? 0L : start;
	}

	public Long getLast() {
		return (null == end || end <= 0) ? 10L : end;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map getParams() {
		return params;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public MapCriteria setParams(Map params) {
		this.params = params;
		return this;
	}

	public MapCriteria addParam(Object paramName, Object value) {
		this.params.put(paramName, value);
		return this;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	public Long getTotalCount() {
		return totalCount;
	}

}
