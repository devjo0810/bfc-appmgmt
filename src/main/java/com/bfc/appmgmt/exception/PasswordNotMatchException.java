package com.bfc.appmgmt.exception;

/**
 * packageName    : com.bfc.appmgmt.exception
 * fileName       : PasswordNotMatchException
 * author         : joyousang
 * date           : 2023/07/01
 * description    :
 */
public class PasswordNotMatchException extends RuntimeException {
    public PasswordNotMatchException() {
        super();
    }

    public PasswordNotMatchException(String message) {
        super(message);
    }

    public PasswordNotMatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public PasswordNotMatchException(Throwable cause) {
        super(cause);
    }

    protected PasswordNotMatchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
