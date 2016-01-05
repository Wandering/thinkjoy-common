package cn.thinkjoy.common.mybatis.core.mybatis.support.req;

import java.io.Serializable;

@SuppressWarnings("serial")
public class PagingBean implements Serializable {

	private long offset;
	private long limit;

	public long getOffset() {
		return offset;
	}

	public void setOffset(long offset) {
		this.offset = offset;
	}

	public long getLimit() {
		return limit;
	}

	public void setLimit(long limit) {
		this.limit = limit;
	}

}
