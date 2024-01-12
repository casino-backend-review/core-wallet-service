package com.core.walletservice.exceptions;

public class ApiException extends RuntimeException {

    public ApiException() {

    }

    public ApiException(String msg) {
        super(msg);
    }
}
