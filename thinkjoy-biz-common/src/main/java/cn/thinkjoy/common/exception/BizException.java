package cn.thinkjoy.common.exception;

/**
 * 业务异常，为检查时异常，必须捕获
 * ==包装成本异常需要log记录原始msg==
 * Created by qyang on 14-6-17.
 */
public class BizException extends Exception {
    /** 异常码 例如： 0010001 业务异常，业务模块01的0001错误（0业务异常、1系统异常）*/
    private String errorCode;
    /** 对用户友好的错误信息 */
    private String msg;
    /** 表示这个错误相关的web页面，可以帮助开发人员获取更多的错误的信息 */
    private String uri;

    public BizException(String errorCode, String msg) {
        super(msg);
        this.errorCode = errorCode;
        this.msg = msg;       
    }

    public BizException(String errorCode, String msg, String uri) {
        super(msg);
        this.errorCode = errorCode;
        this.msg = msg;
        this.uri = uri;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
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
}
