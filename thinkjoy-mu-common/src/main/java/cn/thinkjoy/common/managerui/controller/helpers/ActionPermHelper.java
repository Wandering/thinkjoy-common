package cn.thinkjoy.common.managerui.controller.helpers;

import cn.thinkjoy.common.managerui.domain.Resource;
import cn.thinkjoy.common.managerui.service.IResourceService;
import cn.thinkjoy.common.managerui.service.IActionPermService;

import cn.thinkjoy.common.utils.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

        return actionPermService.getActionPermsByRes(UserContext.getCurrentUser().getId(),resource.getId());
    }

    public final List<Resource> getResourcePerm() {
        return actionPermService.getResourcePerms(UserContext.getCurrentUser().getId());
    }

    public final Set<String> getActionPerm(String uid,String mainObj) {
        Map<String,Object> map = new HashMap<>();
        Resource resource = resourceService.findOne("bizModelName", mainObj);

        return actionPermService.getActionPermsByRes(uid,resource.getId());
    }

    public final List<Resource> getResourcePerm(String uid,String product,String bizSys) {
        return actionPermService.getResourcePerms( uid, product, bizSys);
    }

}
