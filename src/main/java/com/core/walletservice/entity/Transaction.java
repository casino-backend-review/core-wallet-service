package com.core.walletservice.entity;


import com.core.walletservice.enums.TransactionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "transactions")
public class Transaction {

    @Id
    private String id;

   // @NotBlank(message = "ActionType is required")
    private TransactionType actionType;

    private String action;

    //@NotNull(message = "BeforeBalance must not be null")
    private Double beforeBalance;

    //@NotNull(message = "Amount must not be null")
    private Double amount;

    private Double betAmount;
    private Double winAmount;
    private Double transferAmount;

    private String betId;
    private String transactionId;

   // @NotNull(message = "AfterBalance must not be null")
    private Double afterBalance;

    //@NotBlank(message = "RoundID is required")
    private String roundId;

    private Integer gameId;

    private String gameName;
    private String gameCode;

    private String gameCategory;

    private boolean isFeature;
    private boolean isFeatureBuy;
    private boolean isEndRound;
    private String traceId;
    private String userToken;

    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Upline is required")
    private String upline;

    private String refSale;

    private String description;

    @NotBlank(message = "ProductID is required")
    private String productId;

    private String productName;

    private String provider;

    private Double percentage;

    private String syncDate;

    private LocalDateTime createdAt;

    private LocalDateTime createdAtIso;

    private LocalDateTime created;

    private String ip;

    private boolean fRunning;

    private String fRunningDate;
    private LocalDateTime updatedAt;
    private String createdBy;


}
