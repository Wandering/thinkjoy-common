package cn.thinkjoy.common.service;

import cn.thinkjoy.common.domain.BaseDomain;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * TODO 一句话描述该类用途
 * <p/>
 * 创建时间: 14-9-5 上午10:27<br/>
 *
 * @author qyang
 * @since v0.0.1
 */
public interface IBaseService<T extends BaseDomain> {


    /**
     * 保存单一对象，如果要保存多个对象集合，请参看{@link #insertList(java.util.List)}
     *
     * @param entity
     */
    public void insert(T entity);

    /**
     * 更新对象,如果属性为空，则不会进行对应的属性值更新,如果有属性要更新为null，请参看{@link #updateNull(T)}
     *
     * @param entity
     *            要更新的实体对象
     */
    public void update(T entity);

    public void updateMap(Map<String, Object> entityMap);

    /**
     * 更新对象,如果属性为空，会进行对应的属性值更新,如果有属性不想更新为null，请参看{@link #update(T)}
     *
     * @param entity
     */
    public void updateNull(T entity);

    /**
     * 根据id删除对象
     *
     * @param id
     */
    public void deleteById(Long id);

    /**
     * 根据条件集合删除对象
     *
     * @param condition
     */
    public void deleteByCondition(Map<String, Object> condition);

    /**
     * 根据属性和属性值删除对象
     *
     * @param property
     * @param value
     */
    public void deleteByProperty(String property, Object value);

    /**
     * 根据id进行对象查询
     *
     * @param id
     * @return
     */
    public T fetch(Long id);

    /**
     * 根据任意属性和属性值进行对象查询，如果返回多个对象，将抛出异常
     *
     * @param property
     *            进行对象匹配的属性
     * @param value
     *            进行对象匹配的属性值
     * @return 返回泛型参数类型对象，如何取到泛型类型参数，请参看{@link #getEntityClass()}
     */
    public T findOne(String property, Object value);

    /**
     * 根据任意（单一）属性和属性值进行集合查询
     *
     * @param property
     *            进行对象匹配的属性
     * @param value
     *            进行对象匹配的属性值
     * @return 返回泛型参数类型的对象集合，如何取到泛型类型参数，请参看{@link #getEntityClass()}
     */
    public List<T> findList(String property, Object value);

    /**
     * 根据传入的泛型参数类型查询该类型对应表中的所有数据，返回一个集合对象
     *
     * @return 返回泛型参数类型的对象集合，如何取到泛型类型参数，请参看{@link #getEntityClass()}
     */
    public List<T> findAll();

    /**
     * 根据条件集合进行分页查询
     *
     * @param condition
     *            查询条件
     * @param offset
     *            偏移
     * @param rows
     *            查询条数
     * @return 返回Pager对象
     */
    public List<T> queryPage(Map<String, Object> condition, int offset, int rows);

    /**
     * 根据任意属性和属性值进行对象模糊查询
     *
     * @param property
     *            进行对象匹配的属性
     * @param value
     *            进行对象匹配的模糊属性值
     * @return
     */
    public List<T> like(String property, Object value);

    /**
     * 根据条件集合进行指定类型对象集合查询
     *
     * @param condition
     *            进行查询的条件集合
     * @return 返回泛型参数类型的对象集合，如何取到泛型类型参数，请参看{@link #getEntityClass()}
     */
    public List<T> queryList(Map<String, Object> condition, String orderBy, String sortBy);

    /**
     * 根据条件集合进行指定类型单一对象查询
     *
     * @param condition
     *            进行查询的条件集合
     * @return 返回泛型参数类型的对象，如何取到泛型类型参数，请参看{@link #getEntityClass()}，
     */
    public T queryOne(Map<String, Object> condition);

    /**
     * 根据条件进行数量的查询
     *
     * @param condition
     * @return 返回符合条件的泛型参数对应表中的数量
     */
    public int count(Map<String, Object> condition);

    /**
     * 该方法只有在主键为long时才有用，如果主键为其他数据类型进行使用，则会抛出异常
     *
     * @name selectMaxId
     * @active 查询实体对应表最大Id
     * @time 上午10:04:05
     * @exception/throws 如果主键类型不为long，会抛出类型转换异常
     * @return 返回泛型参数对应表的主键最大值
     */
    public Long selectMaxId();

    /**
     * 更新或保存，涉及到Mabatis使用的bean只是一个简单的值对象，不能进行id的注解，不知道哪个是主键，所以，必须同时指定t的主键值
     *
     * @param t
     *            要更新或保存的对象
     * @param id
     *            要更新或保存的对象的id
     * @return 返回更新或保存的对象。
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws SecurityException
     * @throws IllegalArgumentException
     */
    public T updateOrSave(T t, Long id);

    /**
     * 根据泛型类型，执行最原始的sql
     *
     * @param mapperId
     * @param obj
     * @return 返回泛型类型对象，如果返回多个结果集会抛出异常，如果要返回多个结果集，请参看
     *         {@link #selectList(String, Object)}
     */
    public T selectOne(String mapperId, Object obj);

    /**
     * 根据泛型类型，执行最原始的sql
     *
     * @param mapperId
     * @param obj
     * @return 返回泛型类型对象集合，如果要返回单个结果对象，请参看{@link #selectOne(String, Object)}
     */
    public List<T> selectList(String mapperId, Object obj);

    /**
     * 取得泛型类型
     *
     * @return
     */
    public Class<T> getEntityClass();
}
