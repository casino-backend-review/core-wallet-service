package com.core.walletservice.dto;

public class TransactionResponse {

    private String token;
    private String username;
    private Double wallet;
    private Double walletAmountBefore;
    private Double walletAmountAfter;
    private String txId;


    public TransactionResponse(String token, String username, Double wallet, Double walletAmountBefore, Double walletAmountAfter, String txId) {
        this.token = token;
        this.username = username;
        this.wallet = wallet;
        this.walletAmountBefore = walletAmountBefore;
        this.walletAmountAfter = walletAmountAfter;
        this.txId = txId;
    }

    // Getters and setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Double getWallet() {
        return wallet;
    }

    public void setWallet(Double wallet) {
        this.wallet = wallet;
    }

    public Double getWalletAmountBefore() {
        return walletAmountBefore;
    }

    public void setWalletAmountBefore(Double walletAmountBefore) {
        this.walletAmountBefore = walletAmountBefore;
    }

    public Double getWalletAmountAfter() {
        return walletAmountAfter;
    }

    public void setWalletAmountAfter(Double walletAmountAfter) {
        this.walletAmountAfter = walletAmountAfter;
    }

    public String getTxId() {
        return txId;
    }

    public void setTxId(String txId) {
        this.txId = txId;
    }
}
