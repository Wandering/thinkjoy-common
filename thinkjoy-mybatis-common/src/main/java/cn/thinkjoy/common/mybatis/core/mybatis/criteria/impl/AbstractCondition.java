package cn.thinkjoy.common.mybatis.core.mybatis.criteria.impl;

import cn.thinkjoy.common.mybatis.core.mybatis.criteria.Condition;
import cn.thinkjoy.common.mybatis.core.mybatis.criteria.Logic;
import cn.thinkjoy.common.mybatis.core.mybatis.utils.Maps;

import java.util.Map;

public abstract class AbstractCondition implements Condition {

	protected Logic logic;

	protected Map<String, Object> attributes = Maps.newMap();

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	public void addAttribute(String attrName, Object attrValue) {
		attributes.put(attrName, attrValue);
	}

	public Object getAttribute(String attrName) {
		return attributes.get(attrName);
	}

	public Logic getLogic() {
		return logic;
	}

	public void setLogic(Logic logic) {
		this.logic = logic;
	}

}
