package com.core.walletservice.dto;

public class TransactionDTO {
    private String actionType;
    private String action;
    private double beforeBalance;
    private double amount;
    private double afterBalance;
    private String roundID;
    private String gameID;
    private String gameName;
    private String gameCategory;
    private boolean isFeatureBuy;
    private boolean endRound;
    private String username;
    private String upline;
    private String refSale;
    private String description;
    private String productID;
    private String productName;
    private String provider;
    private double percentage;
    private String syncDate;
    private String createdAt;
    private String createdAtIso;
    private String created;
    private String ip;
    private boolean FRunning;
    private String FRunningDate;

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

    public String getGameCategory() {
        return gameCategory;
    }

    public void setGameCategory(String gameCategory) {
        this.gameCategory = gameCategory;
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

    public String getRefSale() {
        return refSale;
    }

    public void setRefSale(String refSale) {
        this.refSale = refSale;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public String getSyncDate() {
        return syncDate;
    }

    public void setSyncDate(String syncDate) {
        this.syncDate = syncDate;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedAtIso() {
        return createdAtIso;
    }

    public void setCreatedAtIso(String createdAtIso) {
        this.createdAtIso = createdAtIso;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public boolean isFRunning() {
        return FRunning;
    }

    public void setFRunning(boolean FRunning) {
        this.FRunning = FRunning;
    }

    public String getFRunningDate() {
        return FRunningDate;
    }

    public void setFRunningDate(String FRunningDate) {
        this.FRunningDate = FRunningDate;
    }
}