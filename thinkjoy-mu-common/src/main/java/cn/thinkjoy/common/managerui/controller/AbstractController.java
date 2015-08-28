package cn.thinkjoy.common.managerui.controller;

import cn.thinkjoy.common.domain.SearchField;
import cn.thinkjoy.common.domain.SearchFilter;
import cn.thinkjoy.common.enumration.SearchEnum;
import cn.thinkjoy.common.service.IDataPermAware;
import cn.thinkjoy.common.service.IDataPermService;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * TODO 一句话描述该类用途
 * <p/>
 * 创建时间: 15/1/19 下午5:13<br/>
 *
 * @author qyang
 * @since v0.0.1
 */
public abstract class AbstractController implements IDataPermAware {

    @Autowired
    protected IDataPermService dataPermService;

    protected Map<String, Object> makeQueryCondition(HttpServletRequest request,HttpServletResponse response, String uri){
        //获取参数
        Map<String, Object> conditions = Maps.newHashMap();


        String sidx = request.getParameter("sidx");
        String sord = request.getParameter("sord");
        conditions.put("sort", sidx + " " + sord);

        String searchField = request.getParameter("searchField");
        String searchOper = request.getParameter("searchOper");
        String searchString = request.getParameter("searchString");
        conditions.put(searchField, searchString);

        //需要在controller处  对 是否有数据权限进行设定和处理
        if(getEnableDataPerm()) { //需要进行数据权限处理
            String whereSql = dataPermService.makeDataPermSql(uri);
            if (whereSql != null) {
                conditions.put("whereSql", whereSql);
            }
        }

        //增加搜索
        String filters = request.getParameter("filters");
        if (StringUtils.isNotBlank(filters)) {
            SearchFilter searchFilter = JSON.parseObject(filters,
                    SearchFilter.class);
            enhanceSearchFilter(searchFilter);
            if (!CollectionUtils.isEmpty(searchFilter.getRules())) {
                conditions.put("groupOp", searchFilter.getGroupOp());
                for (SearchField field : searchFilter.getRules()) {
                    if(field.getOp().equals(SearchEnum.lk.getCode()))
                        field.setData("%"+String.valueOf(field.getData()).trim()+"%");
                    field.setOp(SearchEnum.codeOf(field.getOp()).getDes());
                    conditions.put(getField(field.getField()), field);
                }
            }

        }

        return conditions;
    }


    private String getField(String field)
    {
        return field.replace(".","");
    }
    /**
     * 增强 或 改变 过滤条件内容
     * @param searchFilter
     */
    protected void enhanceSearchFilter(SearchFilter searchFilter) {
    }
}
