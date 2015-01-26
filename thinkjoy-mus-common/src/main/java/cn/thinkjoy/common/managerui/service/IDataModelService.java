/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: ehr
 * $Id:  DataModelService.java 2014-10-03 17:00:40 $
 */

package cn.thinkjoy.common.managerui.service;
import cn.thinkjoy.common.service.IBaseService;
import cn.thinkjoy.common.service.IPageService;
import cn.thinkjoy.common.managerui.dao.IDataModelDAO;
import cn.thinkjoy.common.managerui.domain.DataModel;

public interface IDataModelService extends IBaseService<IDataModelDAO,DataModel>,IPageService<IDataModelDAO,DataModel>{
}
