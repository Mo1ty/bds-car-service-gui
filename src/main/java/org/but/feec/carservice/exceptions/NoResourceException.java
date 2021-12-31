package org.but.feec.carservice.exceptions;

public class NoResourceException extends RuntimeException {

    public NoResourceException() {
    }

    public NoResourceException(String message) {
        super(message);
    }

    public NoResourceException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoResourceException(Throwable cause) {
        super(cause);
    }

    public NoResourceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
