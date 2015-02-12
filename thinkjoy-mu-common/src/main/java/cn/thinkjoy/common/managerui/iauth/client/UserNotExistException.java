package cn.thinkjoy.common.managerui.iauth.client;

import cn.thinkjoy.common.managerui.iauth.core.exception.CannotAuthException;

/**
 * Created by Michael on 11/10/14.
 *
 * 不能验证的异常
 *
 */
public class UserNotExistException extends CannotAuthException {

    public UserNotExistException() {
        super("用户不存在。");
    }

    public UserNotExistException(String message) {
        super(message);
    }

    public UserNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotExistException(Throwable cause) {
        super(cause);
    }

    public UserNotExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
