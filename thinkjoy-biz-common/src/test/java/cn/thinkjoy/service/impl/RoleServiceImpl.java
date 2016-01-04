package cn.thinkjoy.service.impl;

import cn.thinkjoy.common.service.impl.AbstractPageService;
import cn.thinkjoy.dao.IRoleDAO;
import cn.thinkjoy.domain.Role;
import cn.thinkjoy.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * TODO 一句话描述该类用途
 * <p/>
 * 创建时间: 16/1/4<br/>
 *
 * @author xule
 * @since v0.0.1
 */
@Service("roleServiceImpl")
public class RoleServiceImpl extends AbstractPageService<IRoleDAO,Role> implements IRoleService {
    @Autowired
    private IRoleDAO roleDAO;

    @Override
    public IRoleDAO getDao() {
        return roleDAO;
    }
}
