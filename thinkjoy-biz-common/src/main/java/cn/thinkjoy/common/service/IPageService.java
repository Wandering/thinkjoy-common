package cn.thinkjoy.common.service;

import cn.thinkjoy.common.dao.IBaseDAO;
import cn.thinkjoy.common.domain.BaseDomain;
import cn.thinkjoy.common.domain.view.BizData4Page;
import cn.thinkjoy.common.mybatis.core.mybatis.criteria.Criteria;
import cn.thinkjoy.common.mybatis.core.mybatis.paging.PagingResult;
import cn.thinkjoy.common.utils.SqlOrderEnum;

import java.util.List;
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

    /***
     * 条件查询包含各种查询
     * baseDAO 主要为扩展exdao
     * @param baseDAO
     * @param bizData4Page
     */
    void queryPageByDataPerm(IBaseDAO baseDAO,BizData4Page bizData4Page);


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



    /**
     * 条件查询包含各种查询
     * @return
     */
    public void queryPageByDataPerm(BizData4Page bizData4Page, String orderBy, SqlOrderEnum sqlOrderEnum, Map<String, Object> selector);
    
    public void queryPageByDataPerm(IBaseDAO dao,BizData4Page bizData4Page, String orderBy, SqlOrderEnum sqlOrderEnum, Map<String, Object> selector);

    /**
     * 具有排序的分页
     * @param dao
     * @param conditions
     * @param curPage
     * @param offset
     * @param rows
     * @param orderBy
     * @param sqlOrderEnum
     * @return
     */
    public BizData4Page queryPageByDataPerm(IBaseDAO dao, Map<String, Object> conditions, int curPage, int offset, int rows, String orderBy, SqlOrderEnum sqlOrderEnum, Map<String, Object> selector);

    /**
     * 通过Criteria条件对象分页查询实体
     * @param criteria
     * @return
     */
    public BizData4Page pagingByCriteria(Criteria criteria);

    /**
     * 通过Criteria条件对象查询实体数目
     * @param criteria
     * @return int
     */
    public int countByCriteria(Criteria criteria);
}
