package com.core.walletservice.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class AppException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    @Getter
    private final HttpStatus httpStatus;
    private final String message;

    // Constructor with both HttpStatus and message
    public AppException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
        this.message = message;
    }

    // Constructor with only message, defaulting HttpStatus to INTERNAL_SERVER_ERROR
    public AppException(String message) {
        super(message);
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR; // Default status
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
