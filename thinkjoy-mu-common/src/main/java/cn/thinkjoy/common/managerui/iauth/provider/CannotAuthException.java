package cn.thinkjoy.common.managerui.iauth.provider;

import cn.thinkjoy.common.exception.BizException;

/**
 * Created by Michael on 11/10/14.
 *
 */
public class CannotAuthException extends RuntimeException {

    public CannotAuthException() {
    }

    public CannotAuthException(String message) {
        super(message);
    }

    public CannotAuthException(String message, Throwable cause) {
        super(message, cause);
    }

    public CannotAuthException(Throwable cause) {
        super(cause);
    }

    public CannotAuthException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
