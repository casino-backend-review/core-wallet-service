package com.core.walletservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateWalletRequest {

    @NotBlank(message = "Username is required")
    private String username;

    @NotNull(message = "Amount after update is required")
    private Double amountAfter;


}

