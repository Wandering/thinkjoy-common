package cn.thinkjoy.common.managerui.controller;

import cn.thinkjoy.common.domain.BaseDomain;
import cn.thinkjoy.common.domain.BizStatusEnum;
import cn.thinkjoy.common.exception.BizException;
import cn.thinkjoy.common.exception.BizExceptionEnum;
import cn.thinkjoy.common.managerui.controller.helpers.ActionPermHelper;
import cn.thinkjoy.common.managerui.controller.helpers.BasePersistenceProviderMaps;
import cn.thinkjoy.common.managerui.controller.helpers.BaseServiceMaps;
import cn.thinkjoy.common.managerui.domain.ResourceGrid;
import cn.thinkjoy.common.managerui.service.IResourceGridService;
import cn.thinkjoy.common.service.IBaseService;
import cn.thinkjoy.common.utils.ActionEnum;
import cn.thinkjoy.common.utils.SqlOrderEnum;
import cn.thinkjoy.common.utils.UserContext;
import com.alibaba.fastjson.JSON;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import org.apache.poi.ss.usermodel.Workbook;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

/**
 * 每个页面grid数据的每个对象保存   基类，业务系统controller重载， requeatmapping的命名规则为  “/admin/$业务系统名称/$业务模型”    例如  "/admin/ehr/commonsave"
 * <p/>
 * 创建时间: 14-9-4 下午3:19<br/>
 *
 * @author qyang
 * @since v0.0.1
 */
public abstract class AbstractCommonController<T>  extends AbstractController{
    public static final Logger logger = LoggerFactory.getLogger(AbstractCommonController.class);

    private final static String OPER = "oper";

    protected HttpServletRequest request;

    protected HttpServletResponse response;

    @Autowired
    private ActionPermHelper actionPermHelper;

    @Autowired
    protected IResourceGridService resourceGridService;

    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request,
                             HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

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
                throw new BizException(BizExceptionEnum.NOTEXISTS.getCode(), "没有修改权限");
            }

            //做通用校验 TODO 支持全局开启
            verifyData(dataMap, mainObj, true);

            dataMap.put("lastModifier", UserContext.getCurrentUser().getId());
            dataMap.put("lastModDate", System.currentTimeMillis());
            //getMainService(mainObj).updateMap(dataMap);
            innerHandleUpdate(mainObj, dataMap);
        } else if(ActionEnum.ADD.getAction().equals(operValue)){//新增
            if(!actionSet.contains(ActionEnum.ADD.getAction())){
                //无业务权限的异常
                throw new BizException(BizExceptionEnum.NOTEXISTS.getCode(), "没有新增权限");
            }

            //做通用校验 TODO 支持全局开启
            verifyData(dataMap, mainObj, false);

            dataMap.put("creator", UserContext.getCurrentUser().getId());
            dataMap.put("createDate", System.currentTimeMillis());
            dataMap.put("lastModifier", UserContext.getCurrentUser().getId());
            dataMap.put("lastModDate", System.currentTimeMillis());
            if(dataMap.get("status") == null || ((String)dataMap.get("status")).trim().length() == 0){
                dataMap.put("status", BizStatusEnum.N.getCode());
            }
            //getMainService(mainObj).insertMap(dataMap);
            innerHandleAdd(mainObj, dataMap);
        } else if(ActionEnum.DEL.getAction().equals(operValue)){//删除
            if(!actionSet.contains(ActionEnum.DEL.getAction())){
                //无业务权限的异常
                throw new BizException(BizExceptionEnum.NOTEXISTS.getCode(), "没有删除权限");
            }

            dataMap.put("lastModifier", UserContext.getCurrentUser().getId());
            dataMap.put("lastModDate", System.currentTimeMillis());
            //long id = Long.parseLong(dataMap.get("id").toString());
            //getMainService(mainObj).delete(id);
            innerHandleDel(mainObj, dataMap);
        } else {
            return "false";
        }

        return "true";
    }

    /**
     *
     * @param dataMap
     * @param mainObj
     * @param isEdit  true 编辑； false 新增         true，检查重复性 需要排除自己本身
     */
    private void verifyData(Map<String, Object> dataMap, String mainObj,  boolean isEdit) {
        Map<String, Object> condition = Maps.newHashMap();
        condition.put("moduleName", mainObj);
        List<ResourceGrid> resourceGridList = resourceGridService.queryList(condition, "orderNum", SqlOrderEnum.ASC.getAction());

        String editRules = null;
        Map<String, Object> rules = null;
        //"a,b,c" 这样的设置方式
        String existRules = null;
        for(ResourceGrid resourceGrid : resourceGridList){
            editRules = resourceGrid.getEditrules();
            rules = JSON.parseObject(editRules, Map.class);
            if(rules.get("exists") != null && (String.valueOf(rules.get("exists"))).trim().length() > 0) {//唯一性校验
                existRules = (String)rules.get("exists");
            }

            if(rules.get("required") != null && (Boolean)rules.get("required")){//required
                if(dataMap.containsKey(resourceGrid.getColId()) && (dataMap.get(resourceGrid.getColId()) == null || ((String)dataMap.get(resourceGrid.getColId())).trim().length() == 0)){
                    throw new BizException(BizExceptionEnum.REQUIRED.getCode(), resourceGrid.getDisplayName() + BizExceptionEnum.REQUIRED.getDesc());
                }
            }

            if(rules.get("maxLength") != null && (String.valueOf(rules.get("maxLength"))).trim().length() > 0){//最大长度校验
                if(dataMap.containsKey(resourceGrid.getColId()) && (dataMap.get(resourceGrid.getColId()) == null || ((String)dataMap.get(resourceGrid.getColId())).trim().length() == 0)){
                    throw new BizException(BizExceptionEnum.REQUIRED.getCode(), resourceGrid.getDisplayName() + BizExceptionEnum.REQUIRED.getDesc());
                } else {
                    if(dataMap.containsKey(resourceGrid.getColId()) && (((String)dataMap.get(resourceGrid.getColId())).trim().length() > (Integer) rules.get("maxLength"))){
                        throw new BizException(BizExceptionEnum.MAXLENGTH.getCode(), resourceGrid.getDisplayName() + BizExceptionEnum.MAXLENGTH.getDesc()+rules.get("maxLength"));
                    }
                }
            }

            if(rules.get("length") != null && (String.valueOf(rules.get("length"))).trim().length() > 0){//长度校验
                if(dataMap.containsKey(resourceGrid.getColId()) && (dataMap.get(resourceGrid.getColId()) == null || ((String)dataMap.get(resourceGrid.getColId())).trim().length() == 0)){
                    throw new BizException(BizExceptionEnum.REQUIRED.getCode(), resourceGrid.getDisplayName() + BizExceptionEnum.REQUIRED.getDesc());
                } else {
                    if(dataMap.containsKey(resourceGrid.getColId()) && (((String)dataMap.get(resourceGrid.getColId())).trim().length() != (Integer) rules.get("length"))){
                        throw new BizException(BizExceptionEnum.LENGTH.getCode(), resourceGrid.getDisplayName() + BizExceptionEnum.LENGTH.getDesc()+rules.get("length"));
                    }
                }
            }
        }

        //全局 做唯一性校验
        if(!Strings.isNullOrEmpty(existRules)){//唯一性校验
            String[] fields = existRules.split(",");
            Map<String, Object> conditions = new HashMap<>();
            for(String field : fields) {
                conditions.put(field, dataMap.get(field));
            }
            if(isEdit){ //编辑
                conditions.put("id", dataMap.get("id"));

                List existObjList = getServiceMaps().get(mainObj).queryList(conditions, null, null);
                if(existObjList != null && existObjList.size() > 1){
                    throw new BizException(BizExceptionEnum.EXISTS.getCode(), existRules + BizExceptionEnum.EXISTS.getDesc());
                }
            } else {
                Object existObj = getServiceMaps().get(mainObj).queryOne(conditions);
                if(existObj != null){
                    throw new BizException(BizExceptionEnum.EXISTS.getCode(), existRules + BizExceptionEnum.EXISTS.getDesc());
                }
            }
        }
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
        //getServiceMaps().get(mainObj).insertMap(dataMap);

        getPersistenceProviderMaps().get(getServiceMaps(), mainObj).insertMap(dataMap);
    }

    /**
     * 子类可重载, 主要用于级联对象处理  更新
     * @param mainObj
     * @param dataMap
     */
    protected void innerHandleUpdate(String mainObj, Map<String, Object> dataMap){
        //getServiceMaps().get(mainObj).updateMap(dataMap);

        getPersistenceProviderMaps().get(getServiceMaps(), mainObj).updateMap(dataMap);
    }

    /**
     * 子类可重载, 主要用于级联对象处理  删除
     * @param mainObj
     * @param dataMap
     */
    protected void innerHandleDel(String mainObj, Map<String, Object> dataMap){
        //long id = Long.parseLong(dataMap.get("id").toString());
        //getServiceMaps().get(mainObj).delete(id);
        dataMap.put("status", BizStatusEnum.D.getCode());
        // 只做逻辑删除  modify by qyang 2015.9.2
        //getServiceMaps().get(mainObj).updateMap(dataMap);
        getPersistenceProviderMaps().get(getServiceMaps(), mainObj).updateMap(dataMap);
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
            try {
                ImportParams params = new ImportParams();
                params.setTitleRows(2);
                params.setHeadRows(2);
                //params.setSheetNum(9);
                params.setNeedSave(true);

//                long start = new Date().getTime();
                List<T> lists = ExcelImportUtil.importExcelByIs(file.getInputStream()
                , getGenericType(0), params);

                for(T t : lists) {
                    //getServiceMaps().get(mainObj).insert((BaseDomain) t);

                    getPersistenceProviderMaps().get(getServiceMaps(), mainObj).insert((BaseDomain) t);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return "true";
    }

    protected abstract BaseServiceMaps getServiceMaps();

    protected abstract BasePersistenceProviderMaps getPersistenceProviderMaps();

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

    @RequestMapping(value="/export/{mainObj}")
    @ResponseBody
    public String doExport(HttpServletRequest request,HttpServletResponse response) throws Exception {
        String uri = request.getRequestURI().substring(0, request.getRequestURI().length() - 1);
        String title = request.getParameter("fileName");
        //post 中文支持 仅支持 tomcat容器
        title = new String(title.getBytes("ISO-8859-1"), "utf-8");
        //获取参数
        Map<String, Object> conditions = makeQueryCondition(request, response, uri);

        List<T> expertUserDetails = getExportService().queryPage(conditions, 0, Integer.MAX_VALUE);
        OutputStream out = null;
        try {
            response.setContentType("application/x-msdownload");
            out = new BufferedOutputStream(response.getOutputStream());
            //解决中文乱码
            response.setHeader("Content-Disposition", "attachment;filename="+new String((title + ".xls").getBytes("utf-8"),"ISO-8859-1"));
            response.setContentType("application/octet-stream");

            Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(null, title),
                    getGenericType(0), expertUserDetails);

            workbook.write(out);
//            OutputStream out= response.getOutputStream();
//           ex.exportExcel("专家信息",headers, expertUserExcelBeans, out,"yyyy-MM-dd");

        }catch(Exception e){
            //日志打印错误
            logger.error("导出数据失败", e);
        }finally{
            if(out!=null) {
                out.flush();
                out.close();
            }
        }
        return null;

    }

    protected abstract IBaseService getExportService();

    /**
     * 获取泛型类泛型
     * @param index
     * @return
     */
    private final Class getGenericType(int index) {
        Type genType = getClass().getGenericSuperclass();
        if (!(genType instanceof ParameterizedType)) {
            return Object.class;
        }
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        if (index >= params.length || index < 0) {
            throw new RuntimeException("Index outof bounds");
        }
        if (!(params[index] instanceof Class)) {
            return Object.class;
        }
        return (Class) params[index];
    }

}
