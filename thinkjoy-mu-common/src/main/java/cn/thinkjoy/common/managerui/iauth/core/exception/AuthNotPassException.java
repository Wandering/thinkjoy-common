package cn.thinkjoy.common.managerui.iauth.core.exception;

/**
 * Created by Michael on 11/10/14.
 *
 * 验证失败，认证不通过
 * 异常
 *
 */
public class AuthNotPassException extends RuntimeException {

    public AuthNotPassException() {
        super("认证失败。认证不通过。");
    }

    public AuthNotPassException(String message) {
        super(message);
    }

    public AuthNotPassException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthNotPassException(Throwable cause) {
        super(cause);
    }

    public AuthNotPassException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
