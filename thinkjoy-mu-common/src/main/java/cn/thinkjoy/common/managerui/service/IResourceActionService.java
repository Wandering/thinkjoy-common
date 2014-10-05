/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: ehr
 * $Id:  ResourceActionService.java 2014-10-03 17:00:42 $
 */

package cn.thinkjoy.common.managerui.service;
import cn.thinkjoy.common.service.IBaseService;
import cn.thinkjoy.common.service.IPageService;
import cn.thinkjoy.common.managerui.dao.IResourceActionDAO;
import cn.thinkjoy.common.managerui.domain.ResourceAction;

public interface IResourceActionService extends IBaseService<IResourceActionDAO,ResourceAction>,IPageService<IResourceActionDAO,ResourceAction>{
}
