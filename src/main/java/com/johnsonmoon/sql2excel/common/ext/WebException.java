package com.johnsonmoon.sql2excel.common.ext;

/**
 * Create by xuyh at 2019/5/8 11:49.
 */
public class WebException extends RuntimeException {
    public WebException() {
    }

    public WebException(String message) {
        super(message);
    }

    public WebException(String message, Throwable cause) {
        super(message, cause);
    }

    public WebException(Throwable cause) {
        super(cause);
    }

    public WebException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
