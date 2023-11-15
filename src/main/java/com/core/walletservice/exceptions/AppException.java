package com.core.walletservice.exceptions;

import org.springframework.http.HttpStatus;

public class AppException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private final HttpStatus httpStatus;
    private final String message;

    public AppException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

