package com.core.walletservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UpdateWalletRequest {

    @NotBlank(message = "Username is required")
    private String username;

    @NotNull(message = "Amount after update is required")
    private Double amountAfter;

    // Constructors
    public UpdateWalletRequest() {
        // No-args constructor
    }

    public UpdateWalletRequest(String username, Double amountAfter) {
        this.username = username;
        this.amountAfter = amountAfter;
    }

    // Getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Double getAmountAfter() {
        return amountAfter;
    }

    public void setAmountAfter(Double amountAfter) {
        this.amountAfter = amountAfter;
    }

    // toString method for debugging
    @Override
    public String toString() {
        return "UpdateWalletRequest{" +
                "username='" + username + '\'' +
                ", amountAfter=" + amountAfter +
                '}';
    }
}

