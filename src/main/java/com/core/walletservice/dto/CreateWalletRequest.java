package com.core.walletservice.dto;

import com.core.walletservice.enums.UserType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateWalletRequest {

    @NotNull(message = "Username is required")
    private String username;

    @NotNull(message = "Token is required")
    private String token;

    @PositiveOrZero(message = "Balance must be positive or zero")
    private double balance;

    @NotNull(message = "Type is required")
    private UserType type;

    @NotNull(message = "Upline is required")
    private String upline;

    private String refSale;


}
