package cn.thinkjoy.common.mybatis.core.mybatis.criteria.impl;

import cn.thinkjoy.common.mybatis.core.mybatis.criteria.Condition;
import cn.thinkjoy.common.mybatis.core.mybatis.criteria.Criteria;
import cn.thinkjoy.common.mybatis.core.mybatis.criteria.Logic;
import cn.thinkjoy.common.mybatis.core.mybatis.criteria.OrderBy;
import cn.thinkjoy.common.mybatis.core.mybatis.paging.Pagination;
import cn.thinkjoy.common.mybatis.core.mybatis.utils.Lists;
import cn.thinkjoy.common.mybatis.core.mybatis.utils.Maps;
import com.google.common.collect.Sets;

import java.io.Serializable;
import java.util.*;

/**
 * 
 * Criteria 默认实现类
 * 
 */
@SuppressWarnings("serial")
public class DefaultCriteria implements Criteria, Serializable {

	protected boolean limitable = false;

	protected Long start;

	protected Long end;

	protected Pagination pagination;

	protected List<Condition> conditions = Lists.newList();

	protected List<OrderBy> orderBys = Lists.newList();

	protected Map<Object, Object> params = new HashMap<Object, Object>();

	protected Set<Object> selector = new HashSet<>();

	public Criteria limit(Long start, Long end) {
		this.start = start;
		this.end = end;
		this.limitable = true;
		return this;
	}

	public Criteria page(Pagination pagination){
		this.pagination = pagination;
		return this;
	}

	public Criteria add(Condition... conditions) {
		List<Condition> cnds = Lists.of(conditions);
		add(cnds);
		return this;
	}

	public Criteria add(List<Condition> conditions) {
		if (conditions != null && conditions.size() >= 1)
			conditions.get(0).setLogic(Logic.NONE);
		this.conditions.addAll(conditions);
		return this;
	}

	public Criteria orderBy(OrderBy order) {
		orderBys.add(order);
		return this;
	}

	public Criteria useLimitit(Boolean limitable) {
		this.limitable = limitable;
		return this;
	}

	public Boolean isHasLimit() {
		return limitable;
	}

	public List<Condition> getConditions() {
		return conditions;
	}

	public List<OrderBy> getOrderBys() {
		return orderBys;
	}

	public Boolean isHasConditionon() {
		return conditions != null && !conditions.isEmpty();
	}

	public Boolean isHasOrderBy() {
		return orderBys != null && !orderBys.isEmpty();
	}

	public Long getFirst() {
		return (null == start || start < 1) ? 1L : start;
	}

	public Long getLast() {
		return (null == end || end < 1) ? 10L : end;
	}

	public Map<Object, Object> getParams() {
		return params;
	}

	public Criteria setParams(Map<Object, Object> params) {
		this.params = params;
		return this;
	}

	public Criteria addParam(Object paramName, Object value) {
		this.params.put(paramName, value);
		return this;
	}

	public Criteria addParams(Object... values) {
		Map<Object, Object> map = Maps.map(values);
		this.params.putAll(map);
		return this;
	}

	public Criteria setSelector(Set<Object> selector) {
		this.selector = selector;
		return this;
	}

	public Criteria addSelector(Object paramName) {
		this.selector.add(paramName);
		return this;
	}

	public Criteria addSelector(Object... params) {
		Set<Object> set = Sets.newHashSet(params);
		this.selector.addAll(set);
		return this;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public Pagination getPagination() {
		return this.pagination;
	}
}
