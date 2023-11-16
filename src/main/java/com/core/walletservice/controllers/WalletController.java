package com.core.walletservice.controllers;

import com.core.walletservice.dto.CreateWalletRequest;
import com.core.walletservice.dto.GetWalletRequest;
import com.core.walletservice.dto.UpdateWalletRequest;
import com.core.walletservice.dto.WalletResponse;
import com.core.walletservice.entity.Wallet;
import com.core.walletservice.exceptions.AppException;
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
    public ResponseEntity<?> getWallet(@PathVariable String username) {
        try {
            Wallet wallet = walletService.getWallet(new GetWalletRequest(username));
            return ResponseEntity.ok(wallet);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/update/balance")
    public ResponseEntity<?> updateWallet(@RequestBody UpdateWalletRequest walletRequest) {
        try {
            Wallet updatedWallet = walletService.updateWallet(walletRequest);
            return ResponseEntity.ok(updatedWallet);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createWallet(@RequestBody CreateWalletRequest walletRequest) {
        try {
            Wallet createdWallet = walletService.createWallet(walletRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdWallet);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/balance")
    public ResponseEntity<?> getBalance(@RequestBody GetWalletRequest balanceRequest) {
        try {
            WalletResponse response = walletService.getBalance(balanceRequest);
            return ResponseEntity.ok(response);
        } catch (AppException e) {
            // Assuming AppException is a custom exception that you handle accordingly
            return ResponseEntity
                    .status(e.getHttpStatus())
                    .body(e.getMessage());
        } catch (Exception e) {
            // Handle unexpected exceptions
            return ResponseEntity
                    .internalServerError()
                    .body("An unexpected error occurred");
        }
    }
}
