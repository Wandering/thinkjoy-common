package cn.thinkjoy.common.managerui.controller;

import cn.thinkjoy.common.exception.BizException;
import cn.thinkjoy.common.managerui.controller.helpers.ActionPermHelper;
import cn.thinkjoy.common.managerui.controller.helpers.BaseServiceMaps;
import cn.thinkjoy.common.utils.ActionEnum;
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
        Map<String, Object> dataMap = Maps.newHashMap();

        String prop = null;
        Enumeration<String> names = request.getParameterNames();
        while (names.hasMoreElements()) {
            prop = names.nextElement();
            dataMap.put(prop, request.getParameter(prop));
        }
        String operValue = request.getParameter(OPER);

        Set<String> actionSet = actionPermHelper.getActionPerm(mainObj);

        //功能权限处理
        if(ActionEnum.EDIT.getAction().equals(operValue)) { //修改
            if(!actionSet.contains(ActionEnum.EDIT.getAction())){
                //无业务权限的异常
                throw new BizException("", "");
            }
            getServiceMaps().get(mainObj).updateMap(dataMap);
        } else if(ActionEnum.ADD.getAction().equals(operValue)){//新增
            if(!actionSet.contains(ActionEnum.ADD.getAction())){
                //无业务权限的异常
                throw new BizException("", "");
            }

            getServiceMaps().get(mainObj).insertMap(dataMap);
        } else if(ActionEnum.DEL.getAction().equals(operValue)){//删除
            if(!actionSet.contains(ActionEnum.DEL.getAction())){
                //无业务权限的异常
                throw new BizException("", "");
            }

            long id = Long.parseLong(dataMap.get("id").toString());
            getServiceMaps().get(mainObj).delete(id);
        } else {
            return "false";
        }

        return "true";
    }

//    @RequestMapping(value="/commondel/{mainObj}")
//    @ResponseBody
//    public String doDel(@PathVariable String mainObj){
//        Map<String, Object> dataMap = Maps.newHashMap();
//        String prop = null;
//        Enumeration<String> names = request.getParameterNames();
//        while(names.hasMoreElements()){
//            prop = names.nextElement();
//            dataMap.put(prop, request.getParameter(prop));
//        }
////        daoMaps.get(mainObj).deleteById();
//        long id = Long.parseLong(dataMap.get("id").toString());
//        serviceMaps.get(mainObj).delete(id);
//
//        return "true";
//    }
//
//    @RequestMapping(value="/commonadd/{mainObj}")
//    @ResponseBody
//    public String doAdd(@PathVariable String mainObj){
//        Map<String, Object> dataMap = Maps.newHashMap();
//        String prop = null;
//        Enumeration<String> names = request.getParameterNames();
//        while(names.hasMoreElements()){
//            prop = names.nextElement();
//            dataMap.put(prop, request.getParameter(prop));
//        }
////        daoMaps.get(mainObj).deleteById();
//
////        serviceMaps.get(mainObj).
//
//        return "true";
//    }

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
