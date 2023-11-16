package com.core.walletservice.entity;

import com.core.walletservice.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("transactions")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transaction {
    @Id
    private String id;
    private TransactionType type;
    private double amount;
    private double afterBalance;
    private String username;
    private String upline;
    private String createdBy;
    private String timestamp;
    private String hstamp;
    private String remark;
    private String refID;
    private String IP;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}