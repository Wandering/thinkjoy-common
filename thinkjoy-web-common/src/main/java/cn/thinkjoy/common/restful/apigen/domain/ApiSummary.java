package cn.thinkjoy.common.restful.apigen.domain;

import java.io.Serializable;

/**
 * TODO 一句话描述该类用途
 * <p/>
 * 创建时间: 15/4/4 下午3:39<br/>
 *
 * @author qyang
 * @since v0.0.1
 */
public class ApiSummary implements Serializable {
    private int seq;
    private String name;
    private String url;
    private String desc;
    private String requestType;
    private String request;
    private String response;
    private String owner;

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    private String group;

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
