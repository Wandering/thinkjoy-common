package cn.thinkjoy.common.utils;

import cn.thinkjoy.common.dao.IBaseDAO;
import cn.thinkjoy.common.domain.view.BizData4Page;

import java.util.List;
import java.util.Map;

/**
 * BizData4Page 构建者
 * <p/>
 * 创建时间: 15/7/31 下午2:57<br/>
 *
 * @author qyang
 * @since v0.0.1
 */
public class BizData4PageBuilder {

    /**
     * 生成 BizData4Page 实例
     * @param dao
     * @param conditions
     * @param curPage
     * @param offset
     * @param rows
     * @return
     */
    public static BizData4Page createBizData4Page(IBaseDAO dao, Map<String, Object> conditions, int curPage, int offset, int rows){
        List mainData = dao.queryPage(conditions, offset, rows, null, null);
        int records = dao.count(conditions);

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
