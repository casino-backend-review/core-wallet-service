package com.core.walletservice.services.impl;

import com.core.walletservice.dto.CreateWalletRequest;
import com.core.walletservice.dto.GetWalletRequest;
import com.core.walletservice.dto.UpdateWalletRequest;
import com.core.walletservice.dto.WalletResponse;
import com.core.walletservice.entity.Wallet;
import com.core.walletservice.exceptions.EntityNotFoundException;
import com.core.walletservice.repositories.WalletRepository;
import com.core.walletservice.services.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;

    @Autowired
    public WalletServiceImpl(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Override
    public Wallet getWallet(GetWalletRequest walletRequest) throws Exception {
        Wallet response = walletRepository.findByUsername(walletRequest.getUsername());
        if (response == null) {
            throw new Exception("Wallet not found");
        }

        return response;
    }

    @Override
    @Transactional
    public Wallet updateWallet(UpdateWalletRequest walletRequest) throws Exception {
        Wallet response = walletRepository.updateBalanceByUsername(walletRequest.getUsername(), walletRequest.getAmountAfter());
        if (response == null) {
            throw new Exception("Wallet not found");
        }

        return response;
    }

    @Override
    public Wallet createWallet(CreateWalletRequest walletRequest) throws Exception {
        Wallet newWallet = Wallet.builder().type(walletRequest.getType()).token(walletRequest.getToken()).
                username(walletRequest.getUsername()).upline(walletRequest.getUpline())
                .createdAt(new Date()).balance(walletRequest.getBalance()).refSale(walletRequest.getRefSale()).build();

        Wallet response = walletRepository.save(newWallet);
        if (response == null) {
            throw new Exception("Failed to create wallet");
        }
        return response;
    }

    @Override
    public WalletResponse getBalance(GetWalletRequest balanceRequest) throws EntityNotFoundException {
        Wallet wallet = walletRepository.findByUsername(balanceRequest.getUsername());
        if (wallet == null) {
            throw new EntityNotFoundException("Wallet not found for username: " + balanceRequest.getUsername());
        }

        WalletResponse response = new WalletResponse();
        response.setUsername(wallet.getUsername());
        response.setBalance(wallet.getBalance());

        // You can add other fields to the response as needed
        // ...

        return response;
    }
}
