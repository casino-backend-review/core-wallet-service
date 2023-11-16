package com.core.walletservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionRequest {

    @NotNull(message = "Username is required")
    private String username;

    @NotNull(message = "Amount is required")
    private Double amount;

    private String remark;
    private String refId;
}

