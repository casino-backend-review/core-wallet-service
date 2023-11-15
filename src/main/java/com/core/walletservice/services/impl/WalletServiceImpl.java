package com.core.walletservice.services.impl;

import com.core.walletservice.dto.GetWalletRequest;
import com.core.walletservice.dto.WalletResponse;
import com.core.walletservice.entity.Wallet;
import com.core.walletservice.exceptions.EntityNotFoundException;
import com.core.walletservice.repositories.WalletRepository;
import com.core.walletservice.services.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;

    @Autowired
    public WalletServiceImpl(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Transactional
    public Wallet updateWalletBalance(String username, double newBalance) throws EntityNotFoundException {
        Wallet wallet = walletRepository.findByUsername(username);
        if (wallet == null) {
            throw new EntityNotFoundException("Wallet not found for username: " + username);
        }

        wallet.setBalance(newBalance);
        return walletRepository.save(wallet);
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
