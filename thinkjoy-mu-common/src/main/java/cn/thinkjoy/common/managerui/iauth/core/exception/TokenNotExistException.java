package cn.thinkjoy.common.managerui.iauth.core.exception;

/**
 * Created by Michael on 11/10/14.
 *
 * Token不存在
 * 异常
 *
 */
public class TokenNotExistException extends CannotAuthException {

    public TokenNotExistException() {
        super("Token不存在。");
    }

    public TokenNotExistException(String message) {
        super(message);
    }

    public TokenNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public TokenNotExistException(Throwable cause) {
        super(cause);
    }

    public TokenNotExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
