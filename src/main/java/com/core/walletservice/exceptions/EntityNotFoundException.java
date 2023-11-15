package com.core.walletservice.exceptions;

public class EntityNotFoundException extends Exception {

    // Constructor that accepts a message
    public EntityNotFoundException(String message) {
        super(message);
    }

    // Optionally, you could add a constructor that accepts a message and a cause
    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    // Optionally, add a constructor that takes no arguments
    public EntityNotFoundException() {
        super("The requested entity could not be found.");
    }

    // Optionally, add a constructor that takes a cause
    public EntityNotFoundException(Throwable cause) {
        super(cause);
    }
}
