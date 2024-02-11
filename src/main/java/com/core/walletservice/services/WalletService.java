package com.core.walletservice.services;

import com.core.walletservice.dto.*;
import com.core.walletservice.entity.Wallet;
import com.core.walletservice.exception.ApiException;

import java.util.List;

public interface WalletService {

    Wallet createWallet(CreateWalletRequest walletRequest) throws ApiException;

    Wallet getWallet(GetWalletRequest walletRequest) throws ApiException;

    Wallet updateWallet(UpdateWalletRequest walletRequest) throws ApiException;

    List<Wallet> getWalletByUpline(GetWalletsByUplineRequest walletsByUplineRequest) throws ApiException;

    void deleteWallet(String id) throws ApiException;
}
