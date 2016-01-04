package cn.thinkjoy.service;

/**
 * TODO 一句话描述该类用途
 * <p/>
 * 创建时间: 16/1/4<br/>
 *
 * @author xule
 * @since v0.0.1
 */

import cn.thinkjoy.common.service.IBaseService;
import cn.thinkjoy.common.service.IPageService;
import cn.thinkjoy.dao.IRoleDAO;
import cn.thinkjoy.domain.Role;

public interface IRoleService extends IBaseService<IRoleDAO,Role>,IPageService<IRoleDAO,Role> {
}