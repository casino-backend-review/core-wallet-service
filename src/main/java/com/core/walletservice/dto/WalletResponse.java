package com.core.walletservice.dto;

import com.core.walletservice.entity.Wallet;

public class WalletResponse {

    private String errorCode;
    private String message;
    private String username;
    private Double balance;

    // Constructors
    public WalletResponse() {
        // Default constructor
    }

    public WalletResponse(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public WalletResponse(String username, Double balance) {
        this.username = username;
        this.balance = balance;
    }

    // Getters and setters for errorCode and message
    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    // Getters and setters for username and balance
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Wallet toWallet() {
        return toWallet();
    }

    // toString method
    @Override
    public String toString() {
        return "WalletResponse{" +
                "errorCode='" + errorCode + '\'' +
                ", message='" + message + '\'' +
                ", username='" + username + '\'' +
                ", balance=" + balance +
                '}';
    }


}
