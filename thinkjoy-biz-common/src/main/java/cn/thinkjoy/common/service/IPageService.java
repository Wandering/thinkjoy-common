package cn.thinkjoy.common.service;

import cn.thinkjoy.common.dao.IBaseDAO;
import cn.thinkjoy.common.domain.BaseDomain;
import cn.thinkjoy.common.domain.view.BizData4Page;
import cn.thinkjoy.common.utils.SqlOrderEnum;

import java.util.Map;

/**
 * 进行分页数据查询的service
 * <p/>
 * 创建时间: 14-9-3 下午9:57<br/>
 *
 * @author qyang
 * @since v0.0.1
 */
//public interface IPageService<T> {
public interface IPageService<D extends IBaseDAO,T extends BaseDomain> {
    /**
     * 数据权限后的分页数据获取
     * @param resUri
     * @param conditions
     * @param offset
     * @param rows
     * @return
     */
    public BizData4Page queryPageByDataPerm(String resUri, Map<String, Object> conditions, int curPage, int offset, int rows);

    /**
     * 条件查询包含各种查询
     * @return
     */
    public void queryPageByDataPerm(BizData4Page bizData4Page);

    /**
     * 条件查询包含各种查询
     * @return
     */
    public void queryPageByDataPerm(BizData4Page bizData4Page, String orderBy, SqlOrderEnum sqlOrderEnum);


    /**
     * 具有排序的分页
     * @param resUri
     * @param conditions
     * @param curPage
     * @param offset
     * @param rows
     * @param orderBy
     * @param sqlOrderEnum
     * @return
     */
    public BizData4Page queryPageByDataPerm(String resUri, Map<String, Object> conditions, int curPage, int offset, int rows, String orderBy, SqlOrderEnum sqlOrderEnum);
}
