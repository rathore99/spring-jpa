package com.rr.example.spring_jpa.exception.handler;

public class SecondaryExceptionHandler extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public SecondaryExceptionHandler() {
        super();
    }

    public SecondaryExceptionHandler(String message) {
        super(message);
    }

    public SecondaryExceptionHandler(String message, Throwable cause) {
        super(message, cause);
    }

    public SecondaryExceptionHandler(Throwable cause) {
        super(cause);
    }
}
