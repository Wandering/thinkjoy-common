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
public abstract class AbstractBaseService<T extends BaseDomain> implements IBaseService, IDaoAware{
    @Override
    public void add(BaseDomain entity) {
        getDao().insert(entity);
    }

    @Override
    public void edit(BaseDomain entity) {
        getDao().update(entity);
    }

    @Override
    public void delete(Long id) {
        getDao().deleteById(id);
    }

    @Override
    public BaseDomain view(Long id) {
        return getDao().fetch(id);
    }

    @Override
    public void insert(BaseDomain entity) {
        getDao().insert(entity);
    }

    @Override
    public void update(BaseDomain entity) {
        getDao().update(entity);
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
    public void deleteByProperty(String property, Object value) {
        getDao().deleteByProperty(property,value);

    }

    @Override
    public BaseDomain fetch(Long id) {
        return getDao().fetch(id);
    }

    @Override
    public BaseDomain findOne(@Param("property") String property, @Param("value") Object value) {
        return getDao().findOne(property,value);
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
    public void updateOrSave(BaseDomain entity, Long id) {
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
    public BaseDomain selectOne(String mapperId, Object obj) {
        return getDao().selectOne(mapperId,obj);
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
    public BaseDomain queryOne(Map condition) {
        return getDao().queryOne(condition);
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
