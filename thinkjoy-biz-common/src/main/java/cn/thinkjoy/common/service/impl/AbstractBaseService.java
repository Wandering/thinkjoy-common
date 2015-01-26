package cn.thinkjoy.common.service.impl;

import cn.thinkjoy.common.dao.IBaseDAO;
import cn.thinkjoy.common.domain.BaseDomain;
import cn.thinkjoy.common.domain.CreateBaseDomain;
import cn.thinkjoy.common.service.IBaseService;
import cn.thinkjoy.common.service.IDaoAware;
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
    public final int add(T entity) {
        enhanceNewCreateBaseDomain(entity);
        return getDao().insert(entity);
    }

    @Override
    public final int edit(T entity) {
        enhanceCreateBaseDomain(entity);

        return getDao().update(entity);
    }

    @Override
    public final int delete(Long id) {
        return getDao().deleteById(id);
    }

    @Override
    public final T view(Long id) {
        return (T)getDao().fetch(id);
    }

    @Override
    public final int insert(T entity) {
        enhanceNewCreateBaseDomain(entity);
        return getDao().insert(entity);
    }

    @Override
    public final int update(T entity) {
        enhanceCreateBaseDomain(entity);
        return getDao().update(entity);
    }

    @Override
    public final int updateNull(T entity) {
        enhanceCreateBaseDomain(entity);

        return getDao().updateNull(entity);

    }

    @Override
    public final int deleteById(Long id) {

        return getDao().deleteById(id);
    }

    @Override
    public final int deleteByProperty(String property, Object value) {
        return getDao().deleteByProperty(property,value);

    }

    @Override
    public final T fetch(Long id) {
        return (T)getDao().fetch(id);
    }

    @Override
    public final T findOne(String property, Object value) {
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
        if(id!=null&&!StringUtils.isEmpty(id)){
            enhanceCreateBaseDomain(entity);
            getDao().update(entity);
        }else{
            enhanceNewCreateBaseDomain(entity);
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
    public final List queryList(Map condition, String orderBy, String sortBy) {
        return getDao().queryList(condition,orderBy,sortBy);
    }

    @Override
    public final List queryPage(Map condition, int offset, int rows) {
       return getDao().queryPage(condition,offset,rows);
    }

    @Override
    public final int deleteByCondition(Map condition) {
        return getDao().deleteByCondition(condition);
    }

    @Override
    public final int updateMap(Map entityMap) {
        enhanceCreateBaseDomain(entityMap);
        return getDao().updateMap(entityMap);
    }

    @Override
    public final int insertMap(Map entityMap) {
        enhanceNewCreateBaseDomain(entityMap);
        return getDao().insertMap(entityMap);
    }

    @Override
    public final List listByPage(Map condition, int offset, int rows) {
        return getDao().queryPage(condition, offset, rows);
    }

    private final T enhanceCreateBaseDomain(T entity){
        if(entity instanceof CreateBaseDomain){
            ((CreateBaseDomain) entity).setLastModDate(System.currentTimeMillis());
            //TODO 当前用户
        }

        return entity;
    }

    private final T enhanceNewCreateBaseDomain(T entity){
        if(entity instanceof CreateBaseDomain){
            ((CreateBaseDomain) entity).setCreateDate(System.currentTimeMillis());
            //TODO 当前用户
        }

        return entity;
    }

    private final Map enhanceCreateBaseDomain(Map entityMap){
        entityMap.put("lastModDate", System.currentTimeMillis());

        return entityMap;
    }

    private final Map enhanceNewCreateBaseDomain(Map entityMap){
        entityMap.put("createDate", System.currentTimeMillis());

        return entityMap;
    }
}
