package cn.thinkjoy.common.service.impl;

import cn.thinkjoy.common.dao.IBaseDAO;
import cn.thinkjoy.common.domain.BaseDomain;
import cn.thinkjoy.common.service.IBaseService;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 服务抽象类
 * <p/>
 * 创建时间: 14-9-22 下午5:51<br/>
 *
 * @author qyang
 * @since v0.0.1
 */
public abstract class AbstractBaseService<T extends BaseDomain> implements IBaseService{
    @Override
    public void insert(BaseDomain entity) {
        getDao().insert(entity);
    }

    @Override
    public void update(BaseDomain entity) {
        getDao().update(entity);
    }

    @Override
    public void updateMap(Map entityMap) {
        getDao().updateMap(entityMap);
    }

    @Override
    public void updateNull(BaseDomain entity) {
        getDao().updateNull(entity);
    }

    @Override
    public void deleteById(Long id) {
        getDao().deleteById(id);
    }

    @Override
    public void deleteByCondition(Map condition) {
        getDao().deleteByCondition(condition);
    }

    @Override
    public void deleteByProperty(String property, Object value) {
        getDao().deleteByProperty(property, value);
    }

    @Override
    public BaseDomain fetch(Long id) {
        return getDao().fetch(id);
    }

    @Override
    public BaseDomain findOne(String property, Object value) {
        return getDao().findOne(property, value);
    }

    @Override
    public List findList(String property, Object value) {
        return getDao().findList(property, value);
    }

    @Override
    public List findAll() {
        return getDao().findAll() ;
    }

    @Override
    public List queryPage(Map condition, int offset, int rows) {
        return getDao().queryPage(condition, offset, rows);
    }

    @Override
    public List like(String property, Object value) {
        return getDao().like(property, value);
    }

    @Override
    public List queryList(Map queryList, String orderBy, String sortBy) {
        return getDao().queryList(queryList, orderBy, sortBy);
    }

    @Override
    public BaseDomain queryOne(Map condition) {
        return getDao().queryOne(condition);
    }

    @Override
    public int count(Map condition) {
        return getDao().count(condition);
    }

    @Override
    public Long selectMaxId() {
        return getDao().selectMaxId();
    }

    @Override
    public BaseDomain updateOrSave(BaseDomain baseDomain, Long id) {
        return getDao().updateOrSave(baseDomain, id);
    }

    @Override
    public BaseDomain selectOne(String mapperId, Object obj) {
        return getDao().selectOne(mapperId, obj);
    }

    @Override
    public List selectList(String mapperId, Object obj) {
        return getDao().selectList(mapperId, obj);
    }

    @Override
    public Class getEntityClass() {
        return null;
    }

    protected abstract IBaseDAO getDao();
}
