package cn.thinkjoy.common.service.impl;

import cn.thinkjoy.cache.spring.CacheConstants;
import cn.thinkjoy.common.dao.IBaseDAO;
import cn.thinkjoy.common.domain.BaseDomain;
import cn.thinkjoy.common.domain.BizStatusEnum;
import cn.thinkjoy.common.domain.CreateBaseDomain;
import cn.thinkjoy.common.mybatis.core.mybatis.criteria.Criteria;
import cn.thinkjoy.common.service.IBaseService;
import cn.thinkjoy.common.service.IDaoAware;
import cn.thinkjoy.common.utils.SqlOrderEnum;
import cn.thinkjoy.common.utils.UserContext;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
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
@CacheConfig(cacheNames = {CacheConstants.ALLUNUSED})
public abstract class AbstractBaseService<D extends IBaseDAO, T extends BaseDomain> implements IBaseService<D, T>, IDaoAware<D, T> {
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
    public final int delete(Object id) {
        return getDao().deleteById(id);
    }

    @Override
//    @CacheEvict(key = "#id")
    public final int deleteByIds(List list) {
        return getDao().deleteByIds(list);
    }

    @Override
    @Cacheable(key = "#id")
    public final T view(Object id) {
        return (T) getDao().fetch(id);
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
    public final int deleteById(Object id) {

        return getDao().deleteById(id);
    }

    @Override
    @CacheEvict(allEntries = true)
    public final int deleteByProperty(String property, Object value) {
        return getDao().deleteByProperty(property, value);

    }

    @Override
    @Cacheable(key = "#id")
    public final T fetch(Object id) {
        return (T) getDao().fetch(id);
    }

    @Override
    @Cacheable()
    public final T findOne(String property, Object value) {
        return (T) getDao().findOne(property, value, null, null);
    }

    @Override
    @Cacheable()
    public final List findList(String property, Object value) {
        return getDao().findList(property, value, null, null);
    }

    @Override
    @Cacheable()
    public final List findList(String property, Object value, String orderBy, SqlOrderEnum sqlOrderEnum) {
        return getDao().findList(property, value, orderBy, (sqlOrderEnum != null ? sqlOrderEnum.getAction() : null));
    }

    @Override
    public final List findAll() {
        return getDao().findAll(null, null);
    }

    @Override
    public final List findAll(String orderBy, SqlOrderEnum sqlOrderEnum) {
        return getDao().findAll(orderBy, (sqlOrderEnum != null ? sqlOrderEnum.getAction() : null));
    }

    @Override
    @Cacheable()
    public final List like(Map<String, Object> condition) {
        return getDao().like(condition, null, null, null);
    }

    @Override
    @Cacheable()
    public final List like(Map<String, Object> condition, String orderBy, SqlOrderEnum sqlOrderEnum) {
        return getDao().like(condition, orderBy, (sqlOrderEnum != null ? sqlOrderEnum.getAction() : null), null);
    }

    @Override
    public final Object selectMaxId() {
        return getDao().selectMaxId();
    }

    @Override
    @CacheEvict(key = "#id")
    public final void updateOrSave(T entity, Object id) {
        if (id != null && !StringUtils.isEmpty(id)) {
            enhanceCreateBaseDomain(entity);
            getDao().update(entity);
        } else {
            enhanceNewCreateBaseDomain(entity);
            getDao().insert(entity);
        }
    }

    @Override
    public final T selectOne(String mapperId, Object obj) {
        return (T) getDao().selectOne(mapperId, obj);
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
        return (T) getDao().queryOne(condition, null, null, null);
    }

    @Override
    public final T queryOne(Map condition, String orderBy, SqlOrderEnum sqlOrderEnum) {
        return (T) getDao().queryOne(condition, orderBy, (sqlOrderEnum != null ? sqlOrderEnum.getAction() : null), null);
    }

    @Override
    public final List queryList(Map condition, String orderBy, String sortBy) {
        return getDao().queryList(condition, orderBy, sortBy, null);
    }

    @Override
    public final List queryPage(Map condition, int offset, int rows) {
        return getDao().queryPage(condition, offset, rows, null, null, null);
    }

    public List<T> queryPage(Map<String, Object> condition, int offset, int rows, String orderBy, SqlOrderEnum sqlOrderEnum) {
        return getDao().queryPage(condition, offset, rows, orderBy, (sqlOrderEnum != null ? sqlOrderEnum.getAction() : null), null);
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
        return getDao().queryPage(condition, offset, rows, null, null, null);
    }

    @Override
    public final List listByPage(Map condition, int offset, int rows, String orderBy, SqlOrderEnum sqlOrderEnum) {
        return getDao().queryPage(condition, offset, rows, orderBy, (sqlOrderEnum != null ? sqlOrderEnum.getAction() : null), null);
    }

    private final T enhanceCreateBaseDomain(T entity) {
        if (entity instanceof CreateBaseDomain) {
            CreateBaseDomain createBaseDomain = (CreateBaseDomain) entity;

            if (createBaseDomain.getLastModifier() == null) {
                //当前用户
                createBaseDomain.setLastModifier(UserContext.getCurrentUser() != null ? UserContext.getCurrentUser().getId() : 0L);
            }
            createBaseDomain.setLastModDate(System.currentTimeMillis());
        }

        return entity;
    }

    private final T enhanceNewCreateBaseDomain(T entity) {
        if (entity instanceof CreateBaseDomain) {
            CreateBaseDomain createBaseDomain = (CreateBaseDomain) entity;
            //设置默认值，如果默认值和common不一样，需要自行设置初始值
            if (createBaseDomain.getStatus() == null) {
                createBaseDomain.setStatus(BizStatusEnum.N.getCode());
            }
            if (createBaseDomain.getCreator() == null) {
                //当前用户
                createBaseDomain.setCreator(UserContext.getCurrentUser() != null ? UserContext.getCurrentUser().getId() : 0L);
            }

            createBaseDomain.setCreateDate(System.currentTimeMillis());
            if (createBaseDomain.getLastModifier() == null) {
                createBaseDomain.setLastModifier(UserContext.getCurrentUser() != null ? UserContext.getCurrentUser().getId() : 0L);
            }
            createBaseDomain.setLastModDate(System.currentTimeMillis());
        }

        return entity;
    }

    private final Map enhanceCreateBaseDomain(Map entityMap) {

        if (!entityMap.containsKey("lastModifier")) {
            //当前用户
            entityMap.put("lastModifier", (UserContext.getCurrentUser() != null ? UserContext.getCurrentUser().getId() : 0L));
        }
        entityMap.put("lastModDate", System.currentTimeMillis());

        return entityMap;
    }

    private final Map enhanceNewCreateBaseDomain(Map entityMap) {
        //设置默认值，如果默认值和common不一样，需要自行设置初始值
        if (!entityMap.containsKey("status")) {
            entityMap.put("status", BizStatusEnum.N.getCode());
        }
        if (!entityMap.containsKey("creator")) {
            //当前用户
            entityMap.put("creator", (UserContext.getCurrentUser() != null ? UserContext.getCurrentUser().getId() : 0L));
        }

        entityMap.put("createDate", System.currentTimeMillis());
        if (entityMap.containsKey("lastModifier")) {
            entityMap.put("lastModifier", (UserContext.getCurrentUser() != null ? UserContext.getCurrentUser().getId() : 0L));
        }
        entityMap.put("lastModDate", System.currentTimeMillis());

        return entityMap;
    }

    // ---------------- 后加接口，在这里默认做空实现，避免实现类报错，如果需要使用这些特性，需要重载 -----------------//
    @Override
    public List<T> queryPage(Map<String, Object> condition, int offset, int rows, String orderBy, SqlOrderEnum sqlOrderEnum, Map<String, Object> selectorpage) {
        return getDao().queryPage(condition, offset, rows, orderBy, (sqlOrderEnum != null ? sqlOrderEnum.getAction() : null), selectorpage);
    }

    @Override
    public List<T> like(Map<String, Object> condition, String orderBy, SqlOrderEnum sqlOrderEnum, Map<String, Object> selector) {
        return getDao().like(condition, orderBy, (sqlOrderEnum != null ? sqlOrderEnum.getAction() : null), selector);
    }

    @Override
    public List<T> queryList(Map<String, Object> condition, String orderBy, String sortBy, Map<String, Object> selector) {
        return getDao().queryList(condition, orderBy, sortBy, selector);
    }

    @Override
    public T queryOne(Map<String, Object> condition, String orderBy, SqlOrderEnum sqlOrderEnum, Map<String, Object> selector) {
        return (T) getDao().queryOne(condition, orderBy, (sqlOrderEnum != null ? sqlOrderEnum.getAction() : null), selector);
    }


    /**
     * 通用的更新操作
     *
     * @param updateMap    需要更新的值
     * @param conditionMap 需要被更新的条件
     */
    public int updateByCondition(Map<String, Object> updateMap, Map<String, Object> conditionMap) {
        return getDao().updateByCondition(updateMap, conditionMap);
    }

    /**
     * 通过Criteria条件对象查询实体集合
     *
     * @param criteria
     * @return List<T>
     */
    public List<T> findByCriteria(Criteria criteria) {
        return getDao().findByCriteria(criteria);
    }

    /**
     * 通过Criteria条件对象查询实体
     *
     * @param criteria
     * @return T
     */
    public T findOneByCriteria(Criteria criteria) {
        return (T) getDao().findOneByCriteria(criteria);
    }

    /**
     * 通过Criteria条件修改实体
     *
     * @param criteria
     * @return int
     */
    public int updateByCriteria(Criteria criteria) {
        return getDao().updateByCriteria(criteria);
    }

    /**
     * 通过Criteria条件删除
     *
     * @param criteria
     * @return int
     */
    public int deleteByCriteria(Criteria criteria) {
        return getDao().deleteByCriteria(criteria);
    }

}
