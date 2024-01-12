package com.core.walletservice.exceptions;

// If you want to keep it as a checked exception, extend Exception
// If you want to make it an unchecked (runtime) exception, extend RuntimeException
public class InsufficientFundsException extends RuntimeException {

    // Constructors

    // Default constructor
    public InsufficientFundsException() {
        super("Insufficient funds available.");
    }

    // Constructor with message
    public InsufficientFundsException(String message) {
        super(message);
    }

    // Constructor with message and cause
    public InsufficientFundsException(String message, Throwable cause) {
        super(message, cause);
    }

    // Constructor with cause
    public InsufficientFundsException(Throwable cause) {
        super(cause);
    }

    // Constructor with message, cause, suppression enabled/disabled, and writable stack trace enabled/disabled.
    public InsufficientFundsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
