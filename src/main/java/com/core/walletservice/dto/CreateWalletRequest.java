package com.core.walletservice.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class CreateWalletRequest {

    @NotNull(message = "Username is required")
    private String username;

    @NotNull(message = "Token is required")
    private String token;

    @PositiveOrZero(message = "Balance must be positive or zero")
    private double balance;

    @NotNull(message = "Type is required")
    private String type;

    @NotNull(message = "Upline is required")
    private String upline;

    private String refSale;

    // Constructors, getters, and setters...

    public CreateWalletRequest() {
    }

    // Additional constructor(s) can be added if needed

    // Getters
    public String getUsername() {
        return username;
    }

    // Setters
    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUpline() {
        return upline;
    }

    public void setUpline(String upline) {
        this.upline = upline;
    }

    public String getRefSale() {
        return refSale;
    }

    public void setRefSale(String refSale) {
        this.refSale = refSale;
    }

    // toString method can be added for better logging and debugging
    @Override
    public String toString() {
        return "CreateWalletRequest{" +
                "username='" + username + '\'' +
                ", token='" + token + '\'' +
                ", balance=" + balance +
                ", type='" + type + '\'' +
                ", upline='" + upline + '\'' +
                ", refSale='" + refSale + '\'' +
                '}';
    }
}
