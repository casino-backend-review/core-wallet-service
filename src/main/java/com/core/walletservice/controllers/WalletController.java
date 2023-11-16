package com.core.walletservice.controllers;

import com.core.walletservice.dto.CreateWalletRequest;
import com.core.walletservice.dto.GetWalletRequest;
import com.core.walletservice.dto.UpdateWalletRequest;
import com.core.walletservice.dto.WalletResponse;
import com.core.walletservice.entity.Wallet;
import com.core.walletservice.exceptions.AppException;
import com.core.walletservice.exceptions.EntityNotFoundException;
import com.core.walletservice.exceptions.InternalErrorException;
import com.core.walletservice.exceptions.NotFoundException;
import com.core.walletservice.services.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/wallet")
public class WalletController {

    private final WalletService walletService;

    @Autowired
    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @GetMapping("/get/wallet/{username}")
    public ResponseEntity<Wallet> getWallet(@PathVariable String username) throws Exception {
        Wallet wallet = walletService.getWallet(new GetWalletRequest(username));
        if (wallet == null) {
            throw new NotFoundException("Wallet not found for username: " + username);
        }
        return ResponseEntity.ok(wallet);
    }

    @PutMapping("/update/balance")
    public ResponseEntity<Wallet> updateWallet(@RequestBody UpdateWalletRequest walletRequest) throws Exception {
        Wallet updatedWallet = walletService.updateWallet(walletRequest);
        if (updatedWallet == null) {
            throw new AppException("Failed to update wallet balance.");
        }
        return ResponseEntity.ok(updatedWallet);
    }

    @PostMapping("/create")
    public ResponseEntity<Wallet> createWallet(@RequestBody CreateWalletRequest walletRequest) throws Exception {
        Wallet createdWallet = walletService.createWallet(walletRequest);
        if (createdWallet == null) {
            throw new InternalErrorException("Error creating wallet.");
        }
        return new ResponseEntity<>(createdWallet, HttpStatus.CREATED);
    }

    @PostMapping("/balance")
    public ResponseEntity<WalletResponse> getBalance(@RequestBody GetWalletRequest balanceRequest) throws EntityNotFoundException {
        WalletResponse response = walletService.getBalance(balanceRequest);
        if (response == null) {
            throw new AppException("Unable to retrieve wallet balance.");
        }
        return ResponseEntity.ok(response);
    }
}