package cn.thinkjoy.common.mybatis.core.mybatis.domain;

public class PageBean {

	private Long pageNo;
	private Long pageSize;

	public PageBean() {

	}

	public PageBean(Long pageNo, Long pageSize) {
		this.pageNo = pageNo;
		this.pageSize = pageSize;
	}

	public Long getPageNo() {
		return pageNo;
	}

	public void setPageNo(Long pageNo) {
		this.pageNo = pageNo;
	}

	public Long getPageSize() {
		return pageSize;
	}

	public void setPageSize(Long pageSize) {
		this.pageSize = pageSize;
	}

}
