package com.core.walletservice.repositories;

import com.core.walletservice.entity.Wallet;
import com.core.walletservice.exceptions.EntityNotFoundException;

public interface CustomWalletRepository {
    Wallet updateBalanceByUsername(String username, double newBalance) throws EntityNotFoundException;

}
