package com.core.walletservice.services;

import com.core.walletservice.dto.GetWalletRequest;
import com.core.walletservice.dto.WalletResponse;
import com.core.walletservice.exceptions.EntityNotFoundException;

public interface WalletService {
    WalletResponse getBalance(GetWalletRequest balanceRequest) throws EntityNotFoundException;

}
