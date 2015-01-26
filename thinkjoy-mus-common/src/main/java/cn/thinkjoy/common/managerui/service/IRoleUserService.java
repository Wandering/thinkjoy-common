/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: ehr
 * $Id:  RoleUserService.java 2014-10-03 17:00:43 $
 */

package cn.thinkjoy.common.managerui.service;
import cn.thinkjoy.common.service.IBaseService;
import cn.thinkjoy.common.service.IPageService;
import cn.thinkjoy.common.managerui.dao.IRoleUserDAO;
import cn.thinkjoy.common.managerui.domain.RoleUser;

public interface IRoleUserService extends IBaseService<IRoleUserDAO,RoleUser>,IPageService<IRoleUserDAO,RoleUser>{
}
