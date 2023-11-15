package com.core.walletservice.enums;

public enum TransactionType {
    DEPOSIT("deposit"),
    WITHDRAW("withdraw");

    private final String value;

    TransactionType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
