package com.core.walletservice.exceptions;

// If you want to make it an unchecked (runtime) exception, extend RuntimeException
public class NotFoundException extends RuntimeException {

    // Constructors

    // Default constructor
    public NotFoundException() {
        super("The requested resource was not found.");
    }

    // Constructor that accepts a message
    public NotFoundException(String message) {
        super(message);
    }

    // Constructor with message and cause
    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    // Constructor with cause
    public NotFoundException(Throwable cause) {
        super(cause);
    }

    // Constructor with message, cause, suppression enabled/disabled, and writable stack trace enabled/disabled.
    public NotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
