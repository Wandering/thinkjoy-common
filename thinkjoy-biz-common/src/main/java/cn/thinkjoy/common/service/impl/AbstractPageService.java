package cn.thinkjoy.common.service.impl;

import cn.thinkjoy.common.dao.IBaseDAO;
import cn.thinkjoy.common.domain.BaseDomain;
import cn.thinkjoy.common.domain.view.BizData4Page;
import cn.thinkjoy.common.mybatis.core.mybatis.criteria.Criteria;
import cn.thinkjoy.common.service.IPageService;
import cn.thinkjoy.common.utils.BizData4PageBuilder;
import cn.thinkjoy.common.utils.SqlOrderEnum;

import java.util.List;
import java.util.Map;

/**
 * 分页处理的抽象实现，继承自baseService，具备业务模型的基本业务逻辑处理
 * <p/>
 * 创建时间: 14-9-3 下午10:21<br/>
 *
 * @author qyang
 * @since v0.0.1
 */
public abstract class AbstractPageService<D extends IBaseDAO,T extends BaseDomain> extends AbstractBaseService<D,T> implements IPageService<D,T>{


    @Override
    public BizData4Page queryPageByDataPerm(String resUri, Map<String, Object> conditions, int curPage, int offset, int rows) {
        return BizData4PageBuilder.createBizData4Page(getDao(), conditions, curPage, offset, rows);
    }

    @Override
    public BizData4Page queryPageByDataPerm(String resUri, Map<String, Object> conditions, int curPage, int offset, int rows, String orderBy, SqlOrderEnum sqlOrderEnum) {
        return BizData4PageBuilder.createBizData4Page(getDao(), conditions, curPage, offset, rows);
    }

    /**
     * 条件查询包含各种查询
     * @return
     */
    public void queryPageByDataPerm(BizData4Page bizData4Page, String orderBy, SqlOrderEnum sqlOrderEnum)
    {
        int offset = (bizData4Page.getPage()-1)*bizData4Page.getPagesize();
        int rows = bizData4Page.getPagesize();
        List<T> mainData = getDao().queryPage(bizData4Page.getConditions(), offset, rows, orderBy, sqlOrderEnum.getAction(),null);
        int records =  getDao().count(bizData4Page.getConditions());

        bizData4Page.setRows(mainData);
        bizData4Page.setPage(bizData4Page.getPage());
        bizData4Page.setRecords(records);

        int total = records / rows;
        int mod = records % rows;
        if(mod > 0){
            total = total + 1;
        }
        bizData4Page.setTotal(total);
    }

    /**
     * 条件查询包含各种查询
     * @return
     */
    public void queryPageByDataPerm(BizData4Page bizData4Page)
    {
        int offset = (bizData4Page.getPage()-1)*bizData4Page.getPagesize();
        int rows = bizData4Page.getPagesize();
        List<T> mainData = getDao().queryPage(bizData4Page.getConditions(), offset, rows, null, null,null);
        int records =  getDao().count(bizData4Page.getConditions());

        bizData4Page.setRows(mainData);
        bizData4Page.setPage(bizData4Page.getPage());
        bizData4Page.setRecords(records);

        int total = records / rows;
        int mod = records % rows;
        if(mod > 0){
            total = total + 1;
        }
        bizData4Page.setTotal(total);
    }


    /***
     * 条件查询包含各种查询
     * baseDAO 主要为扩展exdao
     * @param baseDAO
     * @param bizData4Page
     */
    public void queryPageByDataPerm(IBaseDAO baseDAO,BizData4Page bizData4Page)
    {
        int offset = (bizData4Page.getPage()-1)*bizData4Page.getPagesize();
        int rows = bizData4Page.getPagesize();
        List<T> mainData = baseDAO.queryPage(bizData4Page.getConditions(), offset, rows, null, null,null);
        int records =  baseDAO.count(bizData4Page.getConditions());

        bizData4Page.setRows(mainData);
        bizData4Page.setPage(bizData4Page.getPage());
        bizData4Page.setRecords(records);

        int total = records / rows;
        int mod = records % rows;
        if(mod > 0){
            total = total + 1;
        }
        bizData4Page.setTotal(total);
    }

    /***
     * 条件查询包含各种查询
     * baseDAO 主要为扩展exdao
     * @param baseDAO
     * @param bizData4Page
     */
    public void queryPageByDataPerm(IBaseDAO baseDAO,BizData4Page bizData4Page, String orderBy, SqlOrderEnum sqlOrderEnum)
    {
        int offset = (bizData4Page.getPage()-1)*bizData4Page.getPagesize();
        int rows = bizData4Page.getPagesize();
        List<T> mainData = baseDAO.queryPage(bizData4Page.getConditions(), offset, rows, orderBy, sqlOrderEnum.getAction(),null);
        int records =  baseDAO.count(bizData4Page.getConditions());

        bizData4Page.setRows(mainData);
        bizData4Page.setPage(bizData4Page.getPage());
        bizData4Page.setRecords(records);

        int total = records / rows;
        int mod = records % rows;
        if(mod > 0){
            total = total + 1;
        }
        bizData4Page.setTotal(total);
    }


    /**
     * 条件查询包含各种查询
     * @return
     */
    public void queryPageByDataPerm(BizData4Page bizData4Page, String orderBy, SqlOrderEnum sqlOrderEnum, Map<String, Object> selector)
    {
        queryPageByDataPerm(getDao(),bizData4Page,orderBy,sqlOrderEnum,selector);
    }

    /**
     * 条件查询包含各种查询
     * @return
     */
    public void queryPageByDataPerm(IBaseDAO dao,BizData4Page bizData4Page, String orderBy, SqlOrderEnum sqlOrderEnum, Map<String, Object> selector)
    {
        int offset = (bizData4Page.getPage()-1)*bizData4Page.getPagesize();
        int rows = bizData4Page.getPagesize();
        List<T> mainData = dao.queryPage(bizData4Page.getConditions(), offset, rows, orderBy, sqlOrderEnum.getAction(), selector);
        int records =  dao.count(bizData4Page.getConditions());

        bizData4Page.setRows(mainData);
        bizData4Page.setPage(bizData4Page.getPage());
        bizData4Page.setRecords(records);

        int total = (records-1) / rows+1;
        bizData4Page.setTotal(total);
    }

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
    public BizData4Page queryPageByDataPerm(IBaseDAO dao, Map<String, Object> conditions, int curPage, int offset, int rows, String orderBy, SqlOrderEnum sqlOrderEnum, Map<String, Object> selector)
    {
        List mainData = dao.queryPage(conditions, offset, rows, orderBy, sqlOrderEnum.getAction(),selector);
        int records = dao.count(conditions);

        BizData4Page bizData4Page = new BizData4Page();
        bizData4Page.setRows(mainData);
        bizData4Page.setPage(curPage);
        bizData4Page.setRecords(records);

        int total = records / rows;
        int mod = records % rows;
        if(mod > 0){
            total = total + 1;
        }
        bizData4Page.setTotal(total);

        return bizData4Page;
    }

    /**
     * 通过Criteria条件对象分页查询实体
     * @param criteria
     * @return
     */
    public BizData4Page pagingByCriteria(Criteria criteria){
        int records = getDao().countByCriteria(criteria);
        List<T> result = getDao().pagingByCriteria(criteria);

        BizData4Page bizData4Page = new BizData4Page();
        bizData4Page.setRows(result);
        bizData4Page.setPage(criteria.getPagination().getPageNo());
        bizData4Page.setPagesize(criteria.getPagination().getPageSize());
        bizData4Page.setRecords(records);

        int total = records / criteria.getPagination().getPageSize();
        int mod = records % criteria.getPagination().getPageSize();
        if(mod > 0){
            total = total + 1;
        }
        bizData4Page.setTotal(total);

        return bizData4Page;
    }

    /**
     * 通过Criteria条件对象查询实体数目
     * @param criteria
     * @return int
     */
    public int countByCriteria(Criteria criteria){
        return getDao().countByCriteria(criteria);
    }

}


