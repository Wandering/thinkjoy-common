package cn.thinkjoy.common.mybatis.core.mybatis.criteria.impl;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * 数据表列
 * 
 */
public class Column {

	protected String name;
	protected String table;

	protected Class<?> type;

	public Column(String name, String table, Class<?> type) {
		this.name = name;
		this.table = table;
		this.type = type;
	}

	public Column(String name, Class<?> type) {
		super();
		this.name = name;
		this.type = type;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Class<?> getType() {
		return type;
	}

	public void setType(Class<?> type) {
		this.type = type;
	}

	public String getConditionName() {
		return StringUtils.isNotEmpty(table) ? String.format("%s.%s", table, name) : name;
	}

}
