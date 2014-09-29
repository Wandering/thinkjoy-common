package cn.thinkjoy.common.service.impl;

import cn.thinkjoy.common.dao.IBaseDAO;
import cn.thinkjoy.common.domain.BaseDomain;
import cn.thinkjoy.common.domain.view.BizData4Page;
import cn.thinkjoy.common.service.IDataPermAware;
import cn.thinkjoy.common.service.IDataPermService;
import cn.thinkjoy.common.service.IPageService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * 分页处理的抽象实现，继承自baseService，具备业务模型的基本业务逻辑处理
 * <p/>
 * 创建时间: 14-9-3 下午10:21<br/>
 *
 * @author qyang
 * @since v0.0.1
 */
public abstract class AbstractPageService<D extends IBaseDAO,T extends BaseDomain> extends AbstractBaseService<D,T> implements IPageService<D,T>, IDataPermAware{
    @Autowired
    private IDataPermService dataPermService;

    @Override
    public BizData4Page queryPageByDataPerm(String resUri, Map<String, Object> conditions, int curPage, int offset, int rows) {
        if(getEnableDataPerm()) { //需要进行数据权限处理
            String whereSql = dataPermService.makeDataPermSql(resUri);
            if (whereSql != null) {
                conditions.put("whereSql", whereSql);
            }
        }

        List<T> mainData = getDao().queryPage(conditions, offset, rows);
        int records =  getDao().count(conditions);

        BizData4Page bizData4Page = new BizData4Page();
        bizData4Page.setRows(mainData);
        bizData4Page.setPage(curPage);
        bizData4Page.setRecords(records);

        int total = records / rows;
        int mod = records % rows;
        if(mod > 0){
            total = total + 1;
        }
        bizData4Page.setTotal(total);

        return bizData4Page;
    }
}
