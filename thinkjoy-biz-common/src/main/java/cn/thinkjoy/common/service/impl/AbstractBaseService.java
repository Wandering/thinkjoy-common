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
    public void add(T entity) {
        getDao().insert(entity);
    }

    @Override
    public void edit(T entity) {
        getDao().update(entity);
    }

    @Override
    public void delete(Long id) {
        getDao().deleteById(id);
    }

    @Override
    public T view(Long id) {
        return (T)getDao().fetch(id);
    }

    @Override
    public void insert(T entity) {
        getDao().insert(entity);
    }

    @Override
    public void update(T entity) {
        getDao().update(entity);
    }

    @Override
    public void updateNull(T entity) {
        getDao().updateNull(entity);

    }

    @Override
    public void deleteById(Long id) {

        getDao().deleteById(id);
    }

    @Override
    public void deleteByProperty(String property, Object value) {
        getDao().deleteByProperty(property,value);

    }

    @Override
    public T fetch(Long id) {
        return (T)getDao().fetch(id);
    }

    @Override
    public T findOne(@Param("property") String property, @Param("value") Object value) {
        return (T)getDao().findOne(property,value);
    }

    @Override
    public List findList(String property, Object value) {
        return getDao().findList(property,value);
    }

    @Override
    public List findAll() {
       return getDao().findAll();
    }

    @Override
    public List like(String property, Object value) {
        return getDao().like(property,value);
    }

    @Override
    public Long selectMaxId() {
        return getDao().selectMaxId();
    }

    @Override
    public void updateOrSave(T entity, Long id) {
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
    public T selectOne(String mapperId, Object obj) {
        return (T)getDao().selectOne(mapperId,obj);
    }

    @Override
    public List selectList(String mapperId, Object obj) {
        return getDao().selectList(mapperId,obj);
    }

    @Override
    public int count(Map condition) {
       return getDao().count(condition);
    }

    @Override
    public T queryOne(Map condition) {
        return (T)getDao().queryOne(condition);
    }

    @Override
    public List queryList(@Param("condition") Map condition, @Param("orderBy") String orderBy, @Param("sortBy") String sortBy) {
        return getDao().queryList(condition,orderBy,sortBy);
    }

    @Override
    public List queryPage(@Param("condition") Map condition, @Param("offset") int offset, @Param("rows") int rows) {
       return getDao().queryPage(condition,offset,rows);
    }

    @Override
    public void deleteByCondition(Map condition) {
        getDao().deleteByCondition(condition);
    }

    @Override
    public void updateMap(@Param("map") Map entityMap) {
        getDao().updateMap(entityMap);
    }

    @Override
    public void insertMap(@Param("map") Map entityMap) {
        getDao().insertMap(entityMap);
    }

    @Override
    public List listByPage(Map condition, int offset, int rows) {
        return getDao().queryPage(condition, offset, rows);
    }

}
