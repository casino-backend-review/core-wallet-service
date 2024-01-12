package com.core.walletservice.exceptions;

// If you want it to be a checked exception, extend Exception
public class NotFoundException extends Exception {

    // Constructor that accepts a message
    public NotFoundException(String message) {
        super(message);
    }
}

