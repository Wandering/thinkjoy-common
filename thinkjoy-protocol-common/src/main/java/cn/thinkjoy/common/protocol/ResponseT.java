package cn.thinkjoy.common.protocol;


import cn.thinkjoy.common.exception.BizException;
import cn.thinkjoy.common.utils.RtnCodeEnum;

import java.io.Serializable;

/**
 * http服务返回的数据包装类   使用泛型
 * Created by qyang on 14-6-17.
 */
public class ResponseT<T> implements Serializable {
    /** 返回的响应码 为空，说明是正常返回*/
    private String rtnCode;
    /** 错误信息 有业务异常的时候，来源于BizException；否则网关出错（系统异常），使用通用异常 */
    private String msg;
    /** 错误堆栈信息，便于排查问题   正常是调试模式下该字段才返回信息 */
    private String developMsg;
    /** 错误说明url 有业务异常的时候，来源于BizException；否则网关出错（系统异常），使用通用异常 */
    private String uri;
    /** 返回的业务 有业务异常的时候，来源于BizException；否则网关出错（系统异常），使用通用异常 */
    private T bizData;

    public ResponseT(){}
    public ResponseT(RtnCodeEnum rtnCode){ this.rtnCode = rtnCode.getCode();}

    public ResponseT(BizException bizException) {
        this.rtnCode = bizException.getErrorCode();
        this.msg = bizException.getMsg();
        this.developMsg = bizException.getDevelopMsg();
        this.uri = bizException.getUri();
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

    public void setBizData(T bizData) {
        this.bizData = bizData;
    }
}
