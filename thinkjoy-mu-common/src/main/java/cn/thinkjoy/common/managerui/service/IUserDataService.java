/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: ehr
 * $Id:  UserDataService.java 2014-10-03 17:00:44 $
 */

package cn.thinkjoy.common.managerui.service;
import cn.thinkjoy.common.service.IBaseService;
import cn.thinkjoy.common.service.IPageService;
import cn.thinkjoy.common.managerui.dao.IUserDataDAO;
import cn.thinkjoy.common.managerui.domain.UserData;

public interface IUserDataService extends IBaseService<IUserDataDAO,UserData>,IPageService<IUserDataDAO,UserData>{
}
