package cn.thinkjoy.common.managerui.controller.helpers;

import cn.thinkjoy.common.managerui.domain.Resource;
import cn.thinkjoy.common.managerui.service.IResourceService;
import cn.thinkjoy.common.managerui.service.IActionPermService;

import cn.thinkjoy.common.utils.UserContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 功能权限助手类
 * <p/>
 * 创建时间: 14-10-2 下午6:00<br/>
 *
 * @author qyang
 * @since v0.0.1
 */
@Service("actionPermHelper")
public class ActionPermHelper {
    @Autowired
    private IResourceService resourceService;

    @Autowired
    private IActionPermService actionPermService;

    public final Set<String> getActionPerm(String mainObj) {
        Resource resource = resourceService.findOne("bizModelName", mainObj);
        if(resource==null)
            return null;
        return actionPermService.getActionPermsByRes(UserContext.getCurrentUser().getId(),resource.getId());
    }

    public final Set<String> getActionPerm(String mainObj,String product) {
        Map<String,Object> map = new HashMap<>();
        map.put("bizModelName", mainObj);
        map.put("product", product);
        Resource resource = resourceService.queryOne(map);
        if(resource==null)
            return null;
        return actionPermService.getActionPermsByRes(UserContext.getCurrentUser().getId(),resource.getId());
    }

    public final List<Resource> getResourcePerm() {
        return actionPermService.getResourcePerms(UserContext.getCurrentUser().getId(),null,"0");
    }


    public final List<Resource> getResourcePerm(String product) {
        return getResourcePerm(UserContext.getCurrentUser().getId(), product,"0");
    }

    public final List<Resource> getResourcePerm(Object uid,String product) {
        return actionPermService.getResourcePerms( uid, product);
    }

    public final List<Resource> getResourcePerm(Object uid,String product,String hide) {
        return actionPermService.getResourcePerms( uid, product,hide);
    }

}
