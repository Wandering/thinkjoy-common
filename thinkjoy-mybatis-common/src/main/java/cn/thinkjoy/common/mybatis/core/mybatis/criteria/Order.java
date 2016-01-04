package cn.thinkjoy.common.mybatis.core.mybatis.criteria;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * 排序值枚举类
 * 
 */
public enum Order {

	ASC("ASC"), DESC("DESC");

	private Order(String value) {
		if (StringUtils.isBlank(value)) {
			this.value = "ASC";
		} else {
			this.value = value.trim().toUpperCase();
		}
	}

	private String value;

	public String toString() {
		return value;
	}
	
}
