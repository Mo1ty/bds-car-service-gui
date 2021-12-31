package org.but.feec.carservice.exceptions;

public class WrongDataInputException extends RuntimeException {
    public WrongDataInputException() {
    }

    public WrongDataInputException(String message) {
        super(message);
    }

    public WrongDataInputException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongDataInputException(Throwable cause) {
        super(cause);
    }

    public WrongDataInputException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
