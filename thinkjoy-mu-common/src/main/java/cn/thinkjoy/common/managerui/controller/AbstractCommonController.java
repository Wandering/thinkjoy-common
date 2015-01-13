package cn.thinkjoy.common.managerui.controller;

import cn.thinkjoy.common.exception.BizException;
import cn.thinkjoy.common.managerui.controller.helpers.ActionPermHelper;
import cn.thinkjoy.common.managerui.controller.helpers.BaseServiceMaps;
import cn.thinkjoy.common.service.IBaseService;
import cn.thinkjoy.common.utils.ActionEnum;
import cn.thinkjoy.common.utils.UserContext;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 每个页面grid数据的每个对象保存   基类，业务系统controller重载， requeatmapping的命名规则为  “/admin/$业务系统名称/$业务模型”    例如  "/admin/ehr/commonsave"
 * <p/>
 * 创建时间: 14-9-4 下午3:19<br/>
 *
 * @author qyang
 * @since v0.0.1
 */
public abstract class AbstractCommonController {
    private final static String OPER = "oper";

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ActionPermHelper actionPermHelper;

    @RequestMapping(value="/commonsave/{mainObj}")
    @ResponseBody
    public String doSave(@PathVariable String mainObj){
        //TODO   支持多对象保存
        Map<String, Object> dataMap = Maps.newHashMap();

        String prop = null;
        Enumeration<String> names = request.getParameterNames();
        while (names.hasMoreElements()) {
            prop = names.nextElement();
            dataMap.put(prop, request.getParameter(prop));
        }
        String operValue = request.getParameter(OPER);

        Set<String> actionSet = actionPermHelper.getActionPerm(mainObj);

        //对dataMap进行处理
        enhanceDataMap(dataMap);

        //功能权限处理
        if(ActionEnum.EDIT.getAction().equals(operValue)) { //修改
            if(!actionSet.contains(ActionEnum.EDIT.getAction())){
                //无业务权限的异常
                throw new BizException("", "");
            }

            dataMap.put("lastModifier", UserContext.getCurrentUser().getId());
            dataMap.put("lastModDate", System.currentTimeMillis());
            //getMainService(mainObj).updateMap(dataMap);
            innerHandleUpdate(mainObj, dataMap);
        } else if(ActionEnum.ADD.getAction().equals(operValue)){//新增
            if(!actionSet.contains(ActionEnum.ADD.getAction())){
                //无业务权限的异常
                throw new BizException("", "");
            }

            dataMap.put("creator", UserContext.getCurrentUser().getId());
            dataMap.put("createDate", System.currentTimeMillis());
            dataMap.put("lastModifier", UserContext.getCurrentUser().getId());
            dataMap.put("lastModDate", System.currentTimeMillis());
            //getMainService(mainObj).insertMap(dataMap);
            innerHandleAdd(mainObj, dataMap);
        } else if(ActionEnum.DEL.getAction().equals(operValue)){//删除
            if(!actionSet.contains(ActionEnum.DEL.getAction())){
                //无业务权限的异常
                throw new BizException("", "");
            }

            dataMap.put("lastModifier", UserContext.getCurrentUser().getId());
            dataMap.put("lastModDate", System.currentTimeMillis());
            long id = Long.parseLong(dataMap.get("id").toString());
            //getMainService(mainObj).delete(id);
            innerHandleDel(mainObj, dataMap);
        } else {
            return "false";
        }

        return "true";
    }

    /**
     * 子类可重载
     * @param dataMap
     */
    protected void enhanceDataMap(Map<String, Object> dataMap) {

    }

    /**
     * 子类可重载, 主要用于级联对象处理   新增
     * @param mainObj
     * @return
     */
    protected void innerHandleAdd(String mainObj, Map<String, Object> dataMap){
        getServiceMaps().get(mainObj).insertMap(dataMap);
    }

    /**
     * 子类可重载, 主要用于级联对象处理  更新
     * @param mainObj
     * @param dataMap
     */
    protected void innerHandleUpdate(String mainObj, Map<String, Object> dataMap){
        getServiceMaps().get(mainObj).updateMap(dataMap);
    }

    /**
     * 子类可重载, 主要用于级联对象处理  删除
     * @param mainObj
     * @param dataMap
     */
    protected void innerHandleDel(String mainObj, Map<String, Object> dataMap){
        long id = Long.parseLong(dataMap.get("id").toString());
        getServiceMaps().get(mainObj).delete(id);
    }

    @RequestMapping(value="/import/{mainObj}")
    @ResponseBody
    public String doImport(@RequestParam("file") MultipartFile file, @PathVariable String mainObj){
        Map<String, Object> dataMap = Maps.newHashMap();
        String prop = null;
        Enumeration<String> names = request.getParameterNames();
        while(names.hasMoreElements()){
            prop = names.nextElement();
            dataMap.put(prop, request.getParameter(prop));
        }
        //daoMaps.get(mainObj).updateMap(dataMap);

        if (!file.isEmpty()) {
            try {
                String originalFilename = file.getOriginalFilename();
                // 文件保存路径
                // FIXME
                // 避免使用request session
                String filePath = request.getSession().getServletContext().getRealPath("/") + "/upload/"
                        + originalFilename;
                // 转存文件
                file.transferTo(new File(filePath));

                if(originalFilename.endsWith("xls")){
                    //Map<String, Object> datas =  OldExcelUtil;

                    //daoMaps.get(mainObj).insert();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return "true";
    }

    protected abstract BaseServiceMaps getServiceMaps();

    /**
     * 设定多对象的存储顺序
     * @return
     */
//    protected abstract List<String> getCascadeObjList();

    /**
     * 设定每个对象保存的service
     * @return
     */
//    protected abstract Map<String, IBaseService> getCascadeObjServices();

//    @RequestMapping(value="/export/{mainObj}")
//    @ResponseBody
//    public String doExport(@RequestParam("file") MultipartFile file, @PathVariable String mainObj){
//        String filename = mainObj + ".xlsx";//设置下载时客户端Excel的名称
//        response.setContentType("application/vnd.ms-excel");
//        response.setHeader("Content-disposition", "attachment;filename=" + filename);
//
//        try {
//            OutputStream ouputStream = response.getOutputStream();
//            //workbook.write(ouputStream);
//
//            ouputStream.flush();
//            ouputStream.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//        return "true";
//    }
}
