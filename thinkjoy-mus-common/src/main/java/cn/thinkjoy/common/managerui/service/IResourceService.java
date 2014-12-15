/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: ehr
 * $Id:  ResourceService.java 2014-10-03 17:00:42 $
 */

package cn.thinkjoy.common.managerui.service;
import cn.thinkjoy.common.service.IBaseService;
import cn.thinkjoy.common.service.IPageService;
import cn.thinkjoy.common.managerui.dao.IResourceDAO;
import cn.thinkjoy.common.managerui.domain.Resource;

public interface IResourceService extends IBaseService<IResourceDAO,Resource>,IPageService<IResourceDAO,Resource>{
}
