package com.core.walletservice.dto;

import jakarta.validation.constraints.NotBlank;

public class GetWalletRequest {

    @NotBlank(message = "Username is required")
    private String username;

    // Constructors
    public GetWalletRequest() {
        // No-args constructor
    }

    public GetWalletRequest(String username) {
        this.username = username;
    }

    // Getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // toString method for debugging
    @Override
    public String toString() {
        return "GetWalletRequest{" +
                "username='" + username + '\'' +
                '}';
    }
}
