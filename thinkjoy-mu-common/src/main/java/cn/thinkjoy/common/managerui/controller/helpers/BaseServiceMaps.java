
/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: ehr
 * $Id:  BaseServiceMaps.java 2014-09-26 11:10:51 $
 */

package cn.thinkjoy.common.managerui.controller.helpers;

import cn.thinkjoy.common.managerui.service.*;
import cn.thinkjoy.common.service.IBaseService;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;


/**
 * Created by shurrik on 14-9-24.
 */
public class BaseServiceMaps {
    @Autowired
    private IDataModelService dataModelService;

    @Autowired
    private IModelService modelService;
    @Autowired
    private IResourceService resourceService;

    @Autowired
    private IResourceActionService resourceActionService;

    @Autowired
    private IResourceGridService resourceGridService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IRoleResourceService roleResourceService;

    @Autowired
    private IRoleUserService roleUserService;

    @Autowired
    private IUserDataService userDataService;

    protected final Map<String, IBaseService> serviceMap = Maps.newHashMap();

    protected void init(){
            serviceMap.put("dataModel",dataModelService);
            serviceMap.put("model",modelService);
            serviceMap.put("resource",resourceService);
            serviceMap.put("resourceAction",resourceActionService);
            serviceMap.put("resourceGrid",resourceGridService);
            serviceMap.put("role",roleService);
            serviceMap.put("roleResource",roleResourceService);
            serviceMap.put("roleUser",roleUserService);
            serviceMap.put("userData",userDataService);
    }

    public IBaseService get(String mainObj){
        return serviceMap.get(mainObj);
    }

}
