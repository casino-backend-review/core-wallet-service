package com.core.walletservice.services;

import com.core.walletservice.dto.CreateWalletRequest;
import com.core.walletservice.dto.GetWalletRequest;
import com.core.walletservice.dto.UpdateWalletRequest;
import com.core.walletservice.dto.WalletResponse;
import com.core.walletservice.entity.Wallet;
import com.core.walletservice.exceptions.EntityNotFoundException;

public interface WalletService {

    Wallet createWallet(CreateWalletRequest walletRequest) throws Exception;

    Wallet getWallet(GetWalletRequest walletRequest) throws Exception;

    Wallet updateWallet(UpdateWalletRequest walletRequest) throws Exception;

    WalletResponse getBalance(GetWalletRequest balanceRequest) throws EntityNotFoundException;

}
