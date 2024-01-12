package com.core.walletservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public BadRequestException(String message) {
        super(message);
    }

    // You can overload the constructor to add more information like errors, etc.
    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
