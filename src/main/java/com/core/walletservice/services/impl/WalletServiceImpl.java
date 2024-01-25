package com.core.walletservice.services.impl;

import com.core.walletservice.dto.*;
import com.core.walletservice.entity.Wallet;
import com.core.walletservice.exception.ApiException;
import com.core.walletservice.repositories.WalletRepository;
import com.core.walletservice.services.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;

    @Autowired
    public WalletServiceImpl(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Override
    public Wallet getWallet(GetWalletRequest walletRequest){
        Wallet response = walletRepository.findByUsername(walletRequest.getUsername());
        if (response == null) {
            throw new ApiException("Wallet not found");
        }

        return response;
    }

    @Override
    public Wallet updateWallet(UpdateWalletRequest walletRequest)  {
        Wallet response = walletRepository.updateBalanceByUsername(walletRequest.getUsername(), walletRequest.getAmountAfter());
        if (response == null) {
            throw new ApiException("Wallet not found");
        }
        return response;
    }

    @Override
    public Wallet createWallet(CreateWalletRequest walletRequest) {
        Wallet response;
        try {
            Wallet newWallet = Wallet.builder().type(walletRequest.getType()).token(walletRequest.getToken()).
                    username(walletRequest.getUsername()).upline(walletRequest.getUpline())
                    .createdAt(new Date()).balance(walletRequest.getBalance()).refSale(walletRequest.getRefSale()).build();

            response = walletRepository.save(newWallet);
        } catch (Exception exception) {
            throw new ApiException("Failed to create wallet");
        }
        return response;
    }


    @Override
    public List<Wallet> getWalletByUpline(GetWalletsByUplineRequest walletsByUplineRequest) {
        List<Wallet> allByUpline = walletRepository.findAllByUpline(walletsByUplineRequest.getUserNames());
        return allByUpline;
    }
}
