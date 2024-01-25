package com.core.walletservice.services;

import com.core.walletservice.dto.*;
import com.core.walletservice.entity.Wallet;

import java.util.List;

public interface WalletService {

    Wallet createWallet(CreateWalletRequest walletRequest);

    Wallet getWallet(GetWalletRequest walletRequest);

    Wallet updateWallet(UpdateWalletRequest walletRequest) ;

    List<Wallet> getWalletByUpline(GetWalletsByUplineRequest walletsByUplineRequest);
}
