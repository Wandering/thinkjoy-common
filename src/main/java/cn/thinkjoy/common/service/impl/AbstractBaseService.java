package cn.thinkjoy.common.service.impl;

import cn.thinkjoy.common.dao.IBaseDAO;
import cn.thinkjoy.common.domain.BaseDomain;
import cn.thinkjoy.common.service.IBaseService;
import cn.thinkjoy.common.service.IDaoAware;
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
    public List listByPage(Map condition, int offset, int rows) {
        return getDao().queryPage(condition, offset, rows);
    }
}
