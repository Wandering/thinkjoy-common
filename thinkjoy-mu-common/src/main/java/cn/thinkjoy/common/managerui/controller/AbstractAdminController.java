package cn.thinkjoy.common.managerui.controller;

import cn.thinkjoy.common.domain.view.BizData4Page;
import cn.thinkjoy.common.managerui.controller.helpers.ActionPermHelper;
import cn.thinkjoy.common.managerui.domain.Resource;
import cn.thinkjoy.common.managerui.domain.ResourceGrid;
import cn.thinkjoy.common.service.IDataPermService;
import cn.thinkjoy.common.managerui.service.IResourceGridService;
import cn.thinkjoy.common.service.IDataPermAware;
import cn.thinkjoy.common.service.IPageService;

import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 管理类controller抽象类
 *  一般的页面都是主页面表格展示，然后弹出页面编辑
 *  对数据权限进行处理
 * <p/>
 * 创建时间: 14-9-3 上午12:03<br/>
 *
 * @author qyang
 * @since v0.0.1
 */
public abstract class AbstractAdminController<T extends IPageService> implements IDataPermAware{
    /** 当前页面的主service  */
    protected T mainService;
    @Autowired
    protected IResourceGridService resourceGridService;
    /** 当前页面的主业务模型  */
    protected String mainObjName;

    @Autowired
    private ActionPermHelper actionPermHelper;

    @Autowired
    private IDataPermService dataPermService;

    /**
     * 供子类注入主service
     * @return
     */
    protected abstract T getMainService();
    protected abstract String getBizSys();
    protected abstract String getMainObjName();
    /** 当前页面title */
    protected abstract String getViewTitle();
    /** 父菜单title */
    protected abstract String getParentTitle();

    protected ModelAndView doRenderMainView(HttpServletRequest request,HttpServletResponse response){
        //request.getRequestURI()
        ModelAndView mav=new ModelAndView("module/"+getMainObjName());

        ///这里顺便回顾下HashMap的使用方法
        	/*      创建：Map<String,String> map = new HashMap<String,String>();
        	        插入元素：map.put("1","a");
        	        移除元素: map.remove("1");
        	        清空: map.clear();*/

        List<Resource> resourceList = actionPermHelper.getResourcePerm();
        mav.addObject("resources", resourceList);

//        List<ResourceGrid> resourceGridList = resourceGridService.findAll();
        List<ResourceGrid> resourceGridList = resourceGridService.findList("moduleName",getMainObjName());
        mav.addObject("cols", resourceGridList);

        mav.addObject("bizSys", getBizSys());
        mav.addObject("mainObj", getMainObjName());
        mav.addObject("parentTitle", getParentTitle());
        mav.addObject("title", getViewTitle());

        //按钮功能权限处理
        mav.addObject("actions", actionPermHelper.getActionPerm(getMainObjName()));

        return mav;
    }

    protected BizData4Page doPage(HttpServletRequest request,HttpServletResponse response){
        //获取参数
        Map<String, Object> conditions = Maps.newHashMap();

        Integer page = 1;
        if(request.getParameter("page") != null) {
            page = Integer.valueOf(request.getParameter("page"));
        }
        Integer rows = 10;
        if(request.getParameter("rows") != null) {
            rows = Integer.valueOf(request.getParameter("rows"));
        }

        String sidx = request.getParameter("sidx");
        String sord = request.getParameter("sord");
        conditions.put("sort", sidx + " " + sord);

        String searchField = request.getParameter("searchField");
        String searchOper = request.getParameter("searchOper");
        String searchString = request.getParameter("searchString");
        conditions.put(searchField, searchString);

        String uri = request.getRequestURI().substring(0, request.getRequestURI().length() - 1);

        //需要在controller处  对 是否有数据权限进行设定和处理
        if(getEnableDataPerm()) { //需要进行数据权限处理
            String whereSql = dataPermService.makeDataPermSql(uri);
            if (whereSql != null) {
                conditions.put("whereSql", whereSql);
            }
        }

        return getMainService().queryPageByDataPerm(uri, conditions, page, (page-1)*rows, rows);
    }
}
