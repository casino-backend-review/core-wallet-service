package com.core.walletservice.services;

import com.core.walletservice.dto.*;
import com.core.walletservice.entity.Wallet;
import com.core.walletservice.exceptions.EntityNotFoundException;

import java.util.List;

public interface WalletService {

    Wallet createWallet(CreateWalletRequest walletRequest) throws Exception;

    Wallet getWallet(GetWalletRequest walletRequest) throws Exception;

    Wallet updateWallet(UpdateWalletRequest walletRequest) throws Exception;

    WalletResponse getBalance(GetWalletRequest balanceRequest) throws EntityNotFoundException;

    List<Wallet> getWalletByUpline(GetWalletsByUplineRequest walletsByUplineRequest);
}
