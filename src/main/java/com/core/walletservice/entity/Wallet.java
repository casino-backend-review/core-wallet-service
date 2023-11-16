package com.core.walletservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "wallet")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Wallet {
    private String id; // Assuming you use a String representation for ObjectID
    private String token;
    private String username;
    private double balance;
    private String type;// UserType
    private String refSale;
    private String upline;
    private Date createdAt;
    private Date updatedAt;


}
