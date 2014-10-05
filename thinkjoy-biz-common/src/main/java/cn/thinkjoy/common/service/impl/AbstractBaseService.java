package cn.thinkjoy.common.service.impl;

import cn.thinkjoy.common.dao.IBaseDAO;
import cn.thinkjoy.common.domain.BaseDomain;
import cn.thinkjoy.common.service.IBaseService;
import cn.thinkjoy.common.service.IDaoAware;
import org.apache.ibatis.annotations.Param;
import org.springframework.util.StringUtils;

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
public abstract class AbstractBaseService<D extends IBaseDAO,T extends BaseDomain> implements IBaseService<D,T>, IDaoAware<D,T>{
//public abstract class AbstractBaseService<D extends IBaseDAO,T extends BaseDomain> implements IBaseService<D,T>{


    @Override
    public final void add(T entity) {
        getDao().insert(entity);
    }

    @Override
    public final void edit(T entity) {
        getDao().update(entity);
    }

    @Override
    public final void delete(Long id) {
        getDao().deleteById(id);
    }

    @Override
    public final T view(Long id) {
        return (T)getDao().fetch(id);
    }

    @Override
    public final void insert(T entity) {
        getDao().insert(entity);
    }

    @Override
    public final void update(T entity) {
        getDao().update(entity);
    }

    @Override
    public final void updateNull(T entity) {
        getDao().updateNull(entity);

    }

    @Override
    public final void deleteById(Long id) {

        getDao().deleteById(id);
    }

    @Override
    public final void deleteByProperty(String property, Object value) {
        getDao().deleteByProperty(property,value);

    }

    @Override
    public final T fetch(Long id) {
        return (T)getDao().fetch(id);
    }

    @Override
    public final T findOne(@Param("property") String property, @Param("value") Object value) {
        return (T)getDao().findOne(property,value);
    }

    @Override
    public final List findList(String property, Object value) {
        return getDao().findList(property,value);
    }

    @Override
    public final List findAll() {
       return getDao().findAll();
    }

    @Override
    public final List like(String property, Object value) {
        return getDao().like(property,value);
    }

    @Override
    public final Long selectMaxId() {
        return getDao().selectMaxId();
    }

    @Override
    public final void updateOrSave(T entity, Long id) {
        if(id!=null&&!StringUtils.isEmpty(id))
        {
           getDao().update(entity);
        }
        else
        {
           getDao().insert(entity);
        }
    }

    @Override
    public final T selectOne(String mapperId, Object obj) {
        return (T)getDao().selectOne(mapperId,obj);
    }

    @Override
    public final List selectList(String mapperId, Object obj) {
        return getDao().selectList(mapperId,obj);
    }

    @Override
    public final int count(Map condition) {
       return getDao().count(condition);
    }

    @Override
    public final T queryOne(Map condition) {
        return (T)getDao().queryOne(condition);
    }

    @Override
    public final List queryList(@Param("condition") Map condition, @Param("orderBy") String orderBy, @Param("sortBy") String sortBy) {
        return getDao().queryList(condition,orderBy,sortBy);
    }

    @Override
    public final List queryPage(@Param("condition") Map condition, @Param("offset") int offset, @Param("rows") int rows) {
       return getDao().queryPage(condition,offset,rows);
    }

    @Override
    public final void deleteByCondition(Map condition) {
        getDao().deleteByCondition(condition);
    }

    @Override
    public final void updateMap(@Param("map") Map entityMap) {
        getDao().updateMap(entityMap);
    }

    @Override
    public final void insertMap(@Param("map") Map entityMap) {
        getDao().insertMap(entityMap);
    }

    @Override
    public final List listByPage(Map condition, int offset, int rows) {
        return getDao().queryPage(condition, offset, rows);
    }

}
