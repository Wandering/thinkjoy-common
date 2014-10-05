/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: ehr
 * $Id:  RoleService.java 2014-10-03 17:00:43 $
 */

package cn.thinkjoy.common.managerui.service;
import cn.thinkjoy.common.service.IBaseService;
import cn.thinkjoy.common.service.IPageService;
import cn.thinkjoy.common.managerui.dao.IRoleDAO;
import cn.thinkjoy.common.managerui.domain.Role;

public interface IRoleService extends IBaseService<IRoleDAO,Role>,IPageService<IRoleDAO,Role>{
}
