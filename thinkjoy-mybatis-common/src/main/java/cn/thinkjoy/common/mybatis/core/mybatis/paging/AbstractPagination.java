package cn.thinkjoy.common.mybatis.core.mybatis.paging;

import com.google.common.base.Objects;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;

import java.io.Serializable;
import java.util.List;

public abstract class AbstractPagination implements Pagination, Ordering, Serializable {

	private static final int DEFAULT_PAGE_SIZE = 10;

	private static final int DEFAULT_PAGE_NO = 1;

	private static final long serialVersionUID = -3408899809645520650L;

	/**
	 * 当前页
	 */
	protected int pageNo = DEFAULT_PAGE_NO;

	/**
	 * 每页显示记录数
	 */
	protected int pageSize = DEFAULT_PAGE_SIZE;

	/**
	 * asc-升序 desc-降序
	 */
	protected String order;

	/**
	 * 排序字段
	 */
	protected String orderBy;

	public AbstractPagination() {
		super();
	}

	public AbstractPagination(int pageNo, int pageSize) {
		this.pageNo = pageNo;
		this.pageSize = pageSize;
	}

	/**
	 * 获得当前页的页号,序号从1开始,默认为1.
	 */
	public int getPageNo() {

		return pageNo;
	}

	/**
	 * 根据pageNo和pageSize计算当前页第一条记录在总结果集中的位置,序号从1开始. 注意：相应的分页SQL应该是>=getFirst()的。
	 */
	public int getFirst() {
		return ((pageNo - 1) * pageSize) + 1;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	/**
	 * 获得每页的记录数量,默认为1.
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 设置每页的记录数量,低于1时自动调整为1.
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;

		if (pageSize < 1) {
			this.pageSize = 1;
		}
	}

	/**
	 * 取得分页查询的索引下标值。 注意 ： 相应的分页SQL应该是<getLast();
	 */
	public int getLast() {
		return getFirst() + pageSize;
	}

	/**
	 * 获得排序方向.
	 */
	public String getOrder() {
		return order;
	}

	/**
	 * 设置排序方式向.
	 * 
	 * @param order
	 *            可选值为desc或asc,多个排序字段时用','分隔.
	 */
	public void setOrder(String order) {
		// 检查order字符串的合法值
		String temp = "";

		if (!Strings.isNullOrEmpty(order)) {
			temp = order.toUpperCase();
		}
		List<String> orders = Splitter.on(",").splitToList(temp);
		for (String orderStr : orders) {
			if (!Objects.equal(DESC, orderStr) && !Objects.equal(ASC, orderStr)) {
				throw new RuntimeException("The order '%s' is not valid! " + orderStr);
			}
		}

		this.order = temp;
	}

	/**
	 * 是否已设置排序字段,无默认值.
	 */
	public boolean isOrderBySetted() {
		return (!Strings.isNullOrEmpty(orderBy) && !Strings.isNullOrEmpty(order));
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

}