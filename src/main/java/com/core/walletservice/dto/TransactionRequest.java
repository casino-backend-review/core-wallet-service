package com.core.walletservice.dto;

import jakarta.validation.constraints.NotNull;

public class TransactionRequest {

    @NotNull(message = "Username is required")
    private String username;

    @NotNull(message = "Amount is required")
    private Double amount;

    private String remark;
    private String refId;

    // Getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }
}

