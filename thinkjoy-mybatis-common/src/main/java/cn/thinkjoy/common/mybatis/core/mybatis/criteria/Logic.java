package cn.thinkjoy.common.mybatis.core.mybatis.criteria;

/**
 * 
 * 逻辑运算符
 * 
 */
public enum Logic {

	NONE(""), AND("AND"), OR("OR");

	private Logic(String value) {
		this.value = value;
	}

	private String value;

	public String toString() {
		return value;
	}
}
