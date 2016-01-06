package cn.thinkjoy.common.mybatis.core.mybatis.criteria;

import cn.thinkjoy.common.mybatis.core.mybatis.criteria.impl.DefaultCriteria;
import cn.thinkjoy.common.mybatis.core.mybatis.criteria.impl.DefaultCriteriaBuilder;
import cn.thinkjoy.common.mybatis.core.mybatis.criteria.impl.DefaultCriterion;
import cn.thinkjoy.common.mybatis.core.mybatis.criteria.impl.DefaultCriterionGroup;
import cn.thinkjoy.common.mybatis.core.mybatis.criteria.impl.DefaultOrderBy;
import cn.thinkjoy.common.mybatis.core.mybatis.criteria.impl.EntityCriteriaBuilder;
import cn.thinkjoy.common.mybatis.core.mybatis.paging.Comparator;
import cn.thinkjoy.common.mybatis.core.mybatis.paging.PropertyFilter;
import cn.thinkjoy.common.mybatis.core.mybatis.utils.Assert;
import cn.thinkjoy.common.mybatis.core.mybatis.utils.Lists;
import cn.thinkjoy.common.mybatis.core.mybatis.utils.Maps;

import java.util.List;
import java.util.Map;

public class Cnd {

	/**
	 * 创建一个子条件
	 */
	public static Criterion createCriterion(Logic logic, String name, Operator operator, Object value) {
		return new DefaultCriterion(logic, name, operator, value, null);
	}

	/**
	 * 创建一个子条件
	 */
	public static Criterion createCriterion(Logic logic, String name, Operator operator, Object value,
			Map<String, Object> attributes) {
		return new DefaultCriterion(logic, name, operator, value, null, attributes);
	}

	/**
	 * 创建一个组合条件
	 * 
	 * @return
	 */
	public static CriterionGroup createCriterionGroup() {
		return new DefaultCriterionGroup();
	}

	/**
	 * 创建一个criteria实例
	 * 
	 * @return
	 */
	public static Criteria createCriteria() {
		return new DefaultCriteria();
	}

	public static Criterion like(Logic logic, String name, String value) {
		return createCriterion(logic, name, Operator.LIKE, value);
	}

	public static Criterion likeAll(Logic logic, String name, String value) {
		return createCriterion(logic, name, Operator.LIKE, "%" + value + "%");
	}

	public static Criterion likeLeft(Logic logic, String name, String value) {
		return createCriterion(logic, name, Operator.LIKE, "%" + value);
	}

	public static Criterion likeRight(Logic logic, String name, String value) {
		return createCriterion(logic, name, Operator.LIKE, value + "%");
	}

	public static Criterion eq(Logic logic, String name, Object value) {
		return createCriterion(logic, name, Operator.EQ, value);
	}

	public static Criterion notEq(Logic logic, String name, Object value) {
		return createCriterion(logic, name, Operator.NOT_EQ, value);
	}

	public static Criterion ge(Logic logic, String name, Object value) {
		return createCriterion(logic, name, Operator.GE, value);
	}

	public static Criterion gt(Logic logic, String name, Object value) {
		return createCriterion(logic, name, Operator.GT, value);
	}

	public static Criterion le(Logic logic, String name, Object value) {
		return createCriterion(logic, name, Operator.LE, value);
	}

	public static Criterion lt(Logic logic, String name, Object value) {
		return createCriterion(logic, name, Operator.LT, value);
	}

	public static Criterion between(Logic logic, String name, Object start, Object end) {
		return createCriterion(logic, name, Operator.BETWEEN, Maps.map("start", start, "end", end));
	}

	public static Criterion notBetween(Logic logic, String name, Object start, Object end) {
		return createCriterion(logic, name, Operator.NOT_BETWEEN, Maps.map("start", start, "end", end));
	}

	public static Criterion in(Logic logic, String name, List<?> values) {
		return createCriterion(logic, name, Operator.IN, values);
	}

	public static Criterion in(Logic logic, String name, Object... values) {
		return createCriterion(logic, name, Operator.IN, Lists.of(values));
	}

	public static Criterion notIn(Logic logic, String name, List<?> values) {
		return createCriterion(logic, name, Operator.NOT_IN, values);
	}

	public static Criterion notIn(Logic logic, String name, Object... values) {
		return createCriterion(logic, name, Operator.NOT_IN, Lists.of(values));
	}

	public static Criterion isNull(Logic logic, String name) {
		return createCriterion(logic, name, Operator.IS_NULL, null);
	}

	public static Criterion isNotNull(Logic logic, String name) {
		return createCriterion(logic, name, Operator.IS_NOT_NULL, null);
	}

	public static Criterion bitEQ(Logic logic, String name, Object value) {
		return createCriterion(logic, name, Operator.BIT_EQ, value);
	}

	public static Criterion bitEQWithoutName(Logic logic, Object value, Object value2) {
		Assert.notNull(value);
		return createCriterion(logic, value.toString(), Operator.BIT_EQ, value2);
	}

	public static Criterion bitOR(Logic logic, String name, Object value) {
		return createCriterion(logic, name, Operator.BIT_OR, value);
	}

	public static Criterion bitORWithoutName(Logic logic, Object value, Object value2) {
		Assert.notNull(value);
		return createCriterion(logic, value.toString(), Operator.BIT_OR, value2);
	}

	public static CriteriaBuilder builder() {
		return new DefaultCriteriaBuilder();
	}

	public static OrderBy orderBy(String orderBy, String order) {
		return new DefaultOrderBy(orderBy, Order.valueOf(order));
	}

	public static CriteriaBuilder builder(Class<?> entityClass) {
		return new DefaultCriteriaBuilder(entityClass);
	}

	@SuppressWarnings("rawtypes")
	public static <T> CriteriaBuilder builder(Class<T> entityClass, List<PropertyFilter<T>> filters) {
		CriteriaBuilder cb = builder(entityClass);
		if (Lists.isNotEmpty(filters)) {
			for (PropertyFilter pf : filters) {
				Comparator cmp = pf.getComparator();
				if (cmp == Comparator.EQ)
					cb.andEQ(pf.getName(), pf.getValue());
				else if (cmp == Comparator.GE)
					cb.andGE(pf.getName(), pf.getValue());
				else if (cmp == Comparator.GT)
					cb.andGT(pf.getName(), pf.getValue());
				else if (cmp == Comparator.LE)
					cb.andLE(pf.getName(), pf.getValue());
				else if (cmp == Comparator.LT)
					cb.andLT(pf.getName(), pf.getValue());
				else if (cmp == Comparator.LIKE)
					cb.andLike(pf.getName(), pf.getValue() == null ? "" : pf.getValue().toString());
			}
		}
		return cb;

	}
}
