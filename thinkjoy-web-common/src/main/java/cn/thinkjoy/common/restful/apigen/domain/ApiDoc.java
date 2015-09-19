package cn.thinkjoy.common.restful.apigen.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO 一句话描述该类用途
 * <p/>
 * 创建时间: 15/4/4 下午3:38<br/>
 *
 * @author qyang
 * @since v0.0.1
 */
public class ApiDoc implements Serializable {
    private List<ApiSummary> apiSummaryList = new ArrayList<>();

    private List<ApiDetail> apiDetailList = new ArrayList<>();

    public void addApiSummary(ApiSummary apiSummary){
        apiSummaryList.add(apiSummary);
    }

    public void addApiDetail(ApiDetail apiDetail){
        apiDetailList.add(apiDetail);
    }

    public List<ApiSummary> getApiSummaryList() {
        return apiSummaryList;
    }

    public List<ApiDetail> getApiDetailList() {
        return apiDetailList;
    }
}
