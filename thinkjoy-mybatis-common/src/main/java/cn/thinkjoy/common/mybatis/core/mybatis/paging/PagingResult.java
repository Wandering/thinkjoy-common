package cn.thinkjoy.common.mybatis.core.mybatis.paging;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * 分页查询返回的查询结果集
 * 
 */
public interface PagingResult<E> extends Serializable{

	/** 分页查询时的总记录条数 */
	Long getTotalCount();

	/** 分页查询时某一页的查询结果 */
	List<E> getResult();

	/** 获取分页总数 */
	Long getTotalPage();

	/** 获取分页条数 */
	Long getLimit();

	/** 获取分页下标 */
	Long getOffset();

	/** 获取分页导航,默认函数pageClick() */
	String getNavigation();

	/** 获取分页当行,自定义按钮函数 */
	String getNavigation(String func);

}
