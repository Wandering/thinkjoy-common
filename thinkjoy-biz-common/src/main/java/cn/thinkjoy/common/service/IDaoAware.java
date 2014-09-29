package cn.thinkjoy.common.service;

import cn.thinkjoy.common.dao.IBaseDAO;
import cn.thinkjoy.common.domain.BaseDomain;

/**
 * 业务主对象DAO注入
 * <p/>
 * 创建时间: 14-9-23 下午2:04<br/>
 *
 * @author qyang
 * @since v0.0.1
 */
public interface IDaoAware<D extends IBaseDAO,T extends BaseDomain> {
    public D getDao();
}
