package cn.thinkjoy.common.mybatis.core.mybatis.criteria.impl;

import cn.thinkjoy.common.mybatis.core.mybatis.criteria.Order;
import cn.thinkjoy.common.mybatis.core.mybatis.criteria.OrderBy;
import org.apache.commons.lang3.StringUtils;

public class DefaultOrderBy implements OrderBy {
	/**
	 * 排序字段
	 */
	private String name;

	/**
	 * 排序值
	 */
	private Order order;

	private Column column;

	public DefaultOrderBy(String name, Order order, Column column) {
		super();
		this.name = name;
		this.order = order;
		this.column = column;
	}

	public DefaultOrderBy(String name, Order order) {
		super();
		this.name = name;
		this.order = order;
	}

	public String getName() {
		if (column != null) {
			return column.getConditionName();
		} else {
			return name;
		}
	}

	public void setName(String name) {
		this.name = name;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public String toString() {
		String sortfield = name;
		if (column != null)
			sortfield = column.getConditionName();
		if (StringUtils.isNotBlank(sortfield) && order != null)
			return sortfield + " " + order;
		return "";
	}

	public void setColumn(Column column) {
		this.column = column;
	}

	public Column getColumn() {
		return column;
	}

}
