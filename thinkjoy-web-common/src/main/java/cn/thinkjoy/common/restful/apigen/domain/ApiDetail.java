package cn.thinkjoy.common.restful.apigen.domain;

import java.util.List;

/**
 * TODO 一句话描述该类用途
 * <p/>
 * 创建时间: 15/4/4 下午3:40<br/>
 *
 * @author qyang
 * @since v0.0.1
 */
public class ApiDetail {
    private String url;
    private String name;
    private String desc;

    private List<Param> pathVar;
    private List<Param> params;

    private String requestDesc;
    private String requestType;
    private List<Param> request;
    private String returnDesc;
    private String responseType;
    private List<Param> response;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<Param> getPathVar() {
        return pathVar;
    }

    public void setPathVar(List<Param> pathVar) {
        this.pathVar = pathVar;
    }

    public List<Param> getParams() {
        return params;
    }

    public void setParams(List<Param> params) {
        this.params = params;
    }

    public List<Param> getRequest() {
        return request;
    }

    public void setRequest(List<Param> request) {
        this.request = request;
    }

    public List<Param> getResponse() {
        return response;
    }

    public void setResponse(List<Param> response) {
        this.response = response;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getResponseType() {
        return responseType;
    }

    public void setResponseType(String responseType) {
        this.responseType = responseType;
    }

    public String getReturnDesc() {
        return returnDesc;
    }

    public void setReturnDesc(String returnDesc) {
        this.returnDesc = returnDesc;
    }

    public String getRequestDesc() {
        return requestDesc;
    }

    public void setRequestDesc(String requestDesc) {
        this.requestDesc = requestDesc;
    }
}
