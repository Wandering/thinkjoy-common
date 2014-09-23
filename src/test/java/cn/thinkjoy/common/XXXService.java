package cn.thinkjoy.common;

import cn.thinkjoy.common.dao.IBaseDAO;
import cn.thinkjoy.common.service.impl.AbstractPageService;

/**
 * TODO 一句话描述该类用途
 * <p/>
 * 创建时间: 14-9-23 下午2:25<br/>
 *
 * @author qyang
 * @since v0.0.1
 */
public class XXXService extends AbstractPageService {
    @Override
    public IBaseDAO getDao() {
        return null;
    }

    @Override
    public boolean getEnableDataPerm() {
        return false;
    }
}
