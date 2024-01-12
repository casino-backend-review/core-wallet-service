package com.core.walletservice.services;

import com.core.walletservice.dto.CreateWalletRequest;
import com.core.walletservice.dto.GetWalletRequest;
import com.core.walletservice.dto.UpdateWalletRequest;
import com.core.walletservice.dto.WalletResponse;
import com.core.walletservice.entity.Wallet;

public interface WalletService {

    Wallet createWallet(CreateWalletRequest walletRequest);

    Wallet getWallet(GetWalletRequest walletRequest);

    Wallet updateWallet(UpdateWalletRequest walletRequest) ;

}
