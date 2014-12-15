/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: ehr
 * $Id:  ResourceGridService.java 2014-10-03 17:00:43 $
 */

package cn.thinkjoy.common.managerui.service;
import cn.thinkjoy.common.service.IBaseService;
import cn.thinkjoy.common.service.IPageService;
import cn.thinkjoy.common.managerui.dao.IResourceGridDAO;
import cn.thinkjoy.common.managerui.domain.ResourceGrid;

public interface IResourceGridService extends IBaseService<IResourceGridDAO,ResourceGrid>,IPageService<IResourceGridDAO,ResourceGrid>{
}
