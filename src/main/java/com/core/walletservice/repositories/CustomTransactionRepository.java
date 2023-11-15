package com.core.walletservice.repositories;

import com.core.walletservice.entity.Transaction;

public interface CustomTransactionRepository {
    Transaction findRecentTransaction(String username, String action, double amount);

}
