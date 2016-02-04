package cn.thinkjoy.common.service.impl;

import cn.thinkjoy.common.dao.IBaseDAO;
import cn.thinkjoy.common.domain.BaseDomain;
import cn.thinkjoy.common.mybatis.core.mybatis.criteria.Criteria;
import cn.thinkjoy.common.service.IBaseService;
import cn.thinkjoy.common.service.IPersistenceProvider;
import cn.thinkjoy.common.utils.SqlOrderEnum;

import java.util.List;
import java.util.Map;

/**
 * TODO 一句话描述该类用途
 * <p/>
 * 创建时间: 16/1/10 上午11:43<br/>
 *
 * @author qyang
 * @since v0.0.1
 */
public abstract class AbstractPersistenceProvider implements IPersistenceProvider {

    public abstract IBaseService getService();

    @Override
    public void verifyData(Map<String, Object> dataMap) {

    }

    @Override
    public IBaseDAO getDao() {
        return null;
    }

    @Override
    public int insertMap(Map entityMap) {
        return getService().insertMap(entityMap);
    }

    @Override
    public int updateMap(Map entityMap) {
        return getService().updateMap(entityMap);
    }

    @Override
    public int add(BaseDomain entity) {
        return getService().add(entity);
    }

    @Override
    public int edit(BaseDomain entity) {
        return getService().edit(entity);
    }

    @Override
    public int delete(Object id) {
        return getService().delete(id);
    }

    @Override
    public BaseDomain view(Object id) {
        return getService().view(id);
    }

    @Override
    public int insert(BaseDomain entity) {
        return getService().insert(entity);
    }

    @Override
    public int update(BaseDomain entity) {
        return getService().update(entity);
    }

    @Override
    public int updateNull(BaseDomain entity) {
        return getService().updateNull(entity);
    }

    @Override
    public int deleteById(Object id) {
        return getService().deleteById(id);
    }

    @Override
    public int deleteByIds(List list) {
        return getService().deleteByIds(list);
    }

    @Override
    public int deleteByProperty(String property, Object value) {
        return getService().deleteByProperty(property, value);
    }

    @Override
    public BaseDomain fetch(Object id) {
        return getService().fetch(id);
    }

    @Override
    public BaseDomain findOne(String property, Object value) {
        return getService().findOne(property, value);
    }

    @Override
    public List findList(String property, Object value) {
        return getService().findList(property, value);
    }

    @Override
    public List findList(String property, Object value, String orderBy, SqlOrderEnum sqlOrderEnum) {
        return getService().findList(property, value, orderBy, sqlOrderEnum);
    }

    @Override
    public List findAll() {
        return getService().findAll();
    }

    @Override
    public List findAll(String orderBy, SqlOrderEnum sqlOrderEnum) {
        return getService().findAll(orderBy, sqlOrderEnum);
    }

    @Override
    public Object selectMaxId() {
        return getService().selectMaxId();
    }

    @Override
    public void updateOrSave(BaseDomain baseDomain, Object id) {
        getService().updateOrSave(baseDomain, id);
    }

    @Override
    public BaseDomain selectOne(String mapperId, Object obj) {
        return getService().selectOne(mapperId, obj);
    }

    @Override
    public List selectList(String mapperId, Object obj) {
        return getService().selectList(mapperId, obj);
    }

    @Override
    public List findByCriteria(Criteria criteria) {
        return getService().findByCriteria(criteria);
    }

    @Override
    public BaseDomain findOneByCriteria(Criteria criteria) {
        return getService().findOneByCriteria(criteria);
    }

    @Override
    public int updateByCondition(Map updateMap, Map conditionMap) {
        return getService().updateByCondition(updateMap, conditionMap);
    }

    @Override
    public int count(Map condition) {
        return getService().count(condition);
    }

    @Override
    public BaseDomain queryOne(Map condition, String orderBy, SqlOrderEnum sqlOrderEnum, Map selector) {
        return getService().queryOne(condition, orderBy, sqlOrderEnum, selector);
    }

    @Override
    public BaseDomain queryOne(Map condition, String orderBy, SqlOrderEnum sqlOrderEnum) {
        return getService().queryOne(condition, orderBy, sqlOrderEnum);
    }

    @Override
    public BaseDomain queryOne(Map condition) {
        return getService().queryOne(condition);
    }

    @Override
    public List queryList(Map condition, String orderBy, String sortBy, Map selector) {
        return getService().queryList(condition, orderBy, sortBy, selector);
    }

    @Override
    public List queryList(Map condition, String orderBy, String sortBy) {
        return getService().queryList(condition, orderBy, sortBy);
    }

    @Override
    public List like(Map condition, String orderBy, SqlOrderEnum sqlOrderEnum, Map selector) {
        return getService().like(condition, orderBy, sqlOrderEnum, selector);
    }

    @Override
    public List like(Map condition, String orderBy, SqlOrderEnum sqlOrderEnum) {
        return getService().like(condition, orderBy, sqlOrderEnum);
    }

    @Override
    public List like(Map condition) {
        return getService().like(condition);
    }

    @Override
    public List queryPage(Map condition, int offset, int rows, String orderBy, SqlOrderEnum sqlOrderEnum, Map selectorpage) {
        return getService().queryPage(condition, offset, rows, orderBy, sqlOrderEnum, selectorpage);
    }

    @Override
    public List queryPage(Map condition, int offset, int rows, String orderBy, SqlOrderEnum sqlOrderEnum) {
        return getService().queryPage(condition, offset, rows, orderBy, sqlOrderEnum);
    }

    @Override
    public List queryPage(Map condition, int offset, int rows) {
        return getService().queryPage(condition, offset, rows);
    }

    @Override
    public int deleteByCondition(Map condition) {
        return getService().deleteByCondition(condition);
    }

    @Override
    public List listByPage(Map condition, int offset, int rows, String orderBy, SqlOrderEnum sqlOrderEnum) {
        return getService().listByPage(condition, offset, rows, orderBy, sqlOrderEnum);
    }

    @Override
    public List listByPage(Map condition, int offset, int rows) {
        return getService().listByPage(condition, offset, rows);
    }
}
