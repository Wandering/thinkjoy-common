package cn.thinkjoy.common.mybatis.core.mybatis.paging;

import java.util.List;

/**
 * 
 * 默认的分页结果实现
 * 
 * @param <T>
 * 
 */
@SuppressWarnings("serial")
public class DefaultPagingResult<T> implements PagingResult<T> {

	protected Long totalCount;
	protected Long totalPage;
	private Long limit;
	private Long offset;
	protected List<T> result;

	public DefaultPagingResult(Long offset, Long limit, Long totalCount, List<T> result) {
		super();
		this.totalCount = totalCount;
		this.result = result;
		this.limit = limit;
		this.offset = offset;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalRows(Long totalCount) {
		this.totalCount = totalCount;
	}

	public List<T> getResult() {
		return result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}

	public Long getTotalPage() {
		long r = totalCount % limit == 0 ? totalCount / limit : totalCount / limit + 1;
		if (r > 0) {
			return r;
		} else {
			return 0l;
		}
	}

	public void setTotalPage(Long totalPage) {
		this.totalPage = totalPage;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	public Long getLimit() {
		if (limit == null || limit < 1) {
			limit = 10l;
		}
		return limit;
	}

	public void setLimit(Long limit) {
		this.limit = limit;
	}

	public Long getOffset() {
		if (offset < 1) {
			offset = 1l;
		}
		return offset;
	}

	public void setOffset(Long offset) {
		this.offset = offset;
	}

	public String getNavigation() {
		return getNavigation(null);
	}

	public String getNavigation(String func) {
		if (func == null || "".equals(func)) {
			return PageUtils.build(offset, limit, totalCount);
		}
		return PageUtils.build(offset, limit, totalCount, func);
	}

}
