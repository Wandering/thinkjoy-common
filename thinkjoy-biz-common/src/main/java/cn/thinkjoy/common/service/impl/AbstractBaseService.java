package cn.thinkjoy.common.service.impl;

import cn.thinkjoy.cache.spring.CacheConstants;
import cn.thinkjoy.common.dao.IBaseDAO;
import cn.thinkjoy.common.domain.BaseDomain;
import cn.thinkjoy.common.domain.CreateBaseDomain;
import cn.thinkjoy.common.service.IBaseService;
import cn.thinkjoy.common.service.IDaoAware;
import cn.thinkjoy.common.utils.SqlOrderEnum;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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
@CacheConfig(cacheNames={CacheConstants.ALLUNUSED})
public abstract class AbstractBaseService<D extends IBaseDAO,T extends BaseDomain> implements IBaseService<D,T>, IDaoAware<D,T>{
//public abstract class AbstractBaseService<D extends IBaseDAO,T extends BaseDomain> implements IBaseService<D,T>{


    @Override
    //@CachePut(key = "#entity.id") 暂时不加缓存，使用的主键自增，并且没有返回整个对象  TODO 可以使用编程式解决
    public final int add(T entity) {
        enhanceNewCreateBaseDomain(entity);
        return getDao().insert(entity);
    }

    @Override
    @CacheEvict(key = "#entity.id")
    public final int edit(T entity) {
        enhanceCreateBaseDomain(entity);

        return getDao().update(entity);
    }

    @Override
    @CacheEvict(key = "#id")
    public final int delete(Long id) {
        return getDao().deleteById(id);
    }

    @Override
    @Cacheable(key = "#id")
    public final T view(Long id) {
        return (T)getDao().fetch(id);
    }

    @Override
    //@CachePut(key = "#entity.id") 暂时不加缓存，使用的主键自增，并且没有返回整个对象  TODO 可以使用编程式解决
    public final int insert(T entity) {
        enhanceNewCreateBaseDomain(entity);
        return getDao().insert(entity);
    }

    @Override
    @CacheEvict(key = "#entity.id")
    public final int update(T entity) {
        enhanceCreateBaseDomain(entity);
        return getDao().update(entity);
    }

    @Override
    @CacheEvict(key = "#entity.id")
    public final int updateNull(T entity) {
        enhanceCreateBaseDomain(entity);

        return getDao().updateNull(entity);

    }

    @Override
    @CacheEvict(key = "#entity.id")
    public final int deleteById(Long id) {

        return getDao().deleteById(id);
    }

    @Override
    @CacheEvict(allEntries = true)
    public final int deleteByProperty(String property, Object value) {
        return getDao().deleteByProperty(property,value);

    }

    @Override
    @Cacheable(key = "#id")
    public final T fetch(Long id) {
        return (T)getDao().fetch(id);
    }

    @Override
    @Cacheable()
    public final T findOne(String property, Object value) {
        return (T)getDao().findOne(property,value, null, null);
    }

    @Override
    @Cacheable()
    public final List findList(String property, Object value) {
        return getDao().findList(property, value, null, null);
    }

    @Override
    @Cacheable()
    public final List findList(String property, Object value, String orderBy, SqlOrderEnum sqlOrderEnum) {
        return getDao().findList(property, value, orderBy, sqlOrderEnum.getAction());
    }

    @Override
    public final List findAll() {
       return getDao().findAll(null, null);
    }

    @Override
    public final List findAll(String orderBy, SqlOrderEnum sqlOrderEnum) {
        return getDao().findAll(orderBy, sqlOrderEnum.getAction());
    }

    @Override
    @Cacheable()
    public final List like(Map<String, Object> condition) {
        return getDao().like(condition, null, null);
    }

    @Override
    @Cacheable()
    public final List like(Map<String, Object> condition, String orderBy, SqlOrderEnum sqlOrderEnum) {
        return getDao().like(condition, orderBy, sqlOrderEnum.getAction());
    }

    @Override
    public final Long selectMaxId() {
        return getDao().selectMaxId();
    }

    @Override
    @CacheEvict(key = "#id")
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
        return getDao().selectList(mapperId, obj);
    }

    @Override
    public final int count(Map condition) {
       return getDao().count(condition);
    }

    @Override
    public final T queryOne(Map condition) {
        return (T)getDao().queryOne(condition, null, null);
    }

    @Override
    public final T queryOne(Map condition, String orderBy, SqlOrderEnum sqlOrderEnum) {
        return (T)getDao().queryOne(condition, orderBy, sqlOrderEnum.getAction());
    }

    @Override
    public final List queryList(Map condition, String orderBy, String sortBy) {
        return getDao().queryList(condition, orderBy, sortBy);
    }

    @Override
    public final List queryPage(Map condition, int offset, int rows) {
       return getDao().queryPage(condition, offset, rows, null, null);
    }

    public List<T> queryPage(Map<String, Object> condition, int offset, int rows, String orderBy, SqlOrderEnum sqlOrderEnum){
        return getDao().queryPage(condition, offset, rows, orderBy, sqlOrderEnum.getAction());
    }

    @Override
    @CacheEvict(allEntries = true) //失效本对象所有缓存  尽量不要调用该方法
    public final int deleteByCondition(Map condition) {
        return getDao().deleteByCondition(condition);
    }

    @Override
    @CacheEvict(allEntries = true)
    public final int updateMap(Map entityMap) {
        enhanceCreateBaseDomain(entityMap);
        return getDao().updateMap(entityMap);
    }

    @Override
    //@CachePut(key = "#entityMap.id") 暂时不加缓存，使用的主键自增，并且没有返回整个对象  TODO 可以使用编程式解决
    public final int insertMap(Map entityMap) {
        enhanceNewCreateBaseDomain(entityMap);
        return getDao().insertMap(entityMap);
    }

    @Override
    public final List listByPage(Map condition, int offset, int rows) {
        return getDao().queryPage(condition, offset, rows, null, null);
    }

    @Override
    public final List listByPage(Map condition, int offset, int rows, String orderBy, SqlOrderEnum sqlOrderEnum) {
        return getDao().queryPage(condition, offset, rows, orderBy, sqlOrderEnum.getAction());
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
            //设置默认值，如果默认值和common不一样，需要自行设置初始值
            if (((CreateBaseDomain) entity).getCreateDate() == null){
                ((CreateBaseDomain) entity).setCreateDate(System.currentTimeMillis());
            }
            if (((CreateBaseDomain) entity).getStatus() == null){
                ((CreateBaseDomain) entity).setStatus(0);
            }
            if (((CreateBaseDomain) entity).getLastModDate() == null){
                ((CreateBaseDomain) entity).setLastModDate(0l);
            }
            if (((CreateBaseDomain) entity).getCreator() == null){
                ((CreateBaseDomain) entity).setCreator(0l);
            }
            if (((CreateBaseDomain) entity).getLastModifier() == null){
                ((CreateBaseDomain) entity).setLastModifier(0l);
            }
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
