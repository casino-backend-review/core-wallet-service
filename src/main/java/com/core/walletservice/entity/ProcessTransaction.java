package com.core.walletservice.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("process_transactions")
public class ProcessTransaction {
    private String actionType;
    private String action;
    private double beforeBalance;
    private double amount;
    private double afterBalance;
    private String roundID;
    private String gameID;
    private String gameName;
    private String refSale;
    private String username;
    private String upline;
    private String productID;
    private String description;
    private boolean isFeatureBuy;
    private boolean endRound;

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public double getBeforeBalance() {
        return beforeBalance;
    }

    public void setBeforeBalance(double beforeBalance) {
        this.beforeBalance = beforeBalance;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getAfterBalance() {
        return afterBalance;
    }

    public void setAfterBalance(double afterBalance) {
        this.afterBalance = afterBalance;
    }

    public String getRoundID() {
        return roundID;
    }

    public void setRoundID(String roundID) {
        this.roundID = roundID;
    }

    public String getGameID() {
        return gameID;
    }

    public void setGameID(String gameID) {
        this.gameID = gameID;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getRefSale() {
        return refSale;
    }

    public void setRefSale(String refSale) {
        this.refSale = refSale;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUpline() {
        return upline;
    }

    public void setUpline(String upline) {
        this.upline = upline;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isFeatureBuy() {
        return isFeatureBuy;
    }

    public void setFeatureBuy(boolean featureBuy) {
        isFeatureBuy = featureBuy;
    }

    public boolean isEndRound() {
        return endRound;
    }

    public void setEndRound(boolean endRound) {
        this.endRound = endRound;
    }
}