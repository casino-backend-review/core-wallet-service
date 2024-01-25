package com.core.walletservice.repositories;

import com.core.walletservice.entity.Wallet;

public interface CustomWalletRepository {
    Wallet updateBalanceByUsername(String username, double newBalance);

}
