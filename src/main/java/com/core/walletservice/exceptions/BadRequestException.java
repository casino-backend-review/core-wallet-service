package com.core.walletservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.List;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private List<String> errors; // Optional: to hold a list of error messages

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    // Constructor with a list of errors
    public BadRequestException(String message, List<String> errors) {
        super(message);
        this.errors = errors;
    }

    // Getter for errors
    public List<String> getErrors() {
        return errors;
    }

    // Optionally, you can add a setter or methods to add individual error messages
}
