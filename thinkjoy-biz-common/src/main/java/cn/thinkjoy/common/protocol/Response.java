package cn.thinkjoy.common.protocol;


import cn.thinkjoy.common.exception.BizException;
import cn.thinkjoy.common.utils.RtnCodeEnum;

import java.io.Serializable;

/**
 * http服务返回的数据包装类
 * Created by qyang on 14-6-17.
 */
public class Response implements Serializable {
    /** 返回的响应码 为空，说明是正常返回*/
    private String rtnCode;
    /** 错误信息 有业务异常的时候，来源于BizException；否则网关出错（系统异常），使用通用异常 */
    private String msg;
    /** 错误说明url 有业务异常的时候，来源于BizException；否则网关出错（系统异常），使用通用异常 */
    private String uri;
    /** 返回的业务 有业务异常的时候，来源于BizException；否则网关出错（系统异常），使用通用异常 */
    private Object bizData;

    private Response(ResponseBuilder builder) {
        this.rtnCode = builder.rtnCode;
        this.msg = builder.msg;
        this.uri = builder.uri;
        this.bizData = builder.bizData;
    }

    public String getRtnCode() {
        return rtnCode;
    }

    public void setRtnCode(String rtnCode) {
        this.rtnCode = rtnCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Object getBizData() {
        return bizData;
    }

    public void setBizData(Object bizData) {
        this.bizData = bizData;
    }

    public static class ResponseBuilder {
        private String rtnCode;
        private String msg;
        private String uri;
        private Object bizData;

        public ResponseBuilder(BizException bizException) {
            this.rtnCode = bizException.getErrorCode();
            this.msg = bizException.getMsg();
            this.uri = bizException.getUri();
        }

        public ResponseBuilder(RtnCodeEnum rtnCodeEnum) {
            this.rtnCode = rtnCodeEnum.getValue();
            this.msg = rtnCodeEnum.getDesc();
        }

        public ResponseBuilder rtnCode(String rtnCode) {
            this.rtnCode = rtnCode;
            return this;
        }

        public ResponseBuilder msg(String msg) {
            this.msg = msg;
            return this;
        }

        public ResponseBuilder uri(String uri) {
            this.uri = uri;
            return this;
        }

        public ResponseBuilder bizData(String bizData) {
            this.bizData = bizData;
            return this;
        }

        public Response build() {
            return new Response(this);
        }
    }
}
