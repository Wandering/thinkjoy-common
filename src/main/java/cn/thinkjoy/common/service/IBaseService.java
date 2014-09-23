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
     * 新增一条数据
     *
     * @param entity
     */
    public void add(T entity);

    /**
     * 修改一条数据
     *
     * @param entity
     *            要更新的实体对象
     */
    public void edit(T entity);

    /**
     * 删除一条数据
     *
     * @param id
     */
    public void delete(Long id);

    /**
     * 查看一条确定的数据
     *
     * @param id
     * @return
     */
    public T view(Long id);

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
    public List<T> listByPage(Map<String, Object> condition, int offset, int rows);
}
