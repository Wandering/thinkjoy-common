package cn.thinkjoy.common.mybatis.core.mybatis.paging.dialect.impl;

import cn.thinkjoy.common.mybatis.core.mybatis.paging.dialect.Dialect;

public class DerbyDialect implements Dialect {

	public DerbyDialect() {
	}

	public boolean supportsLimit() {
		return false;
	}

	public boolean supportsLimitOffset() {
		return false;
	}

	public String getLimitString(String sql, int offset, String offsetPlaceholder, int limit, String limitPlaceholder) {
		throw new UnsupportedOperationException("paged queries not supported");
	}

	public String getLimitString(String sql, int offset, int limit) {
		return getLimitString(sql, offset, Integer.toString(offset), limit, Integer.toString(limit));
	}

}