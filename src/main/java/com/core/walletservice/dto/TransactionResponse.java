package com.core.walletservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionResponse {

    private String token;
    private String username;
    private Double wallet;
    private Double walletAmountBefore;
    private Double walletAmountAfter;
    private String txId;


}
