package com.core.walletservice.controller;

import com.core.walletservice.dto.*;
import com.core.walletservice.entity.Wallet;
import com.core.walletservice.exceptions.ApiResponseMessage;
import com.core.walletservice.services.WalletService;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wallet")
public class WalletController {

    private final WalletService walletService;

    @Autowired
    public WalletController(WalletService walletService) {
        this.walletService = walletService;

    }

    @PostMapping("/create")
    public ResponseEntity<?> createWallet(@RequestBody CreateWalletRequest walletRequest) {
        try {
            Wallet createdWallet = walletService.createWallet(walletRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdWallet);
        } catch (Exception exception) {
            ApiResponseMessage apiResponseMessage = new ApiResponseMessage(ApiResponseMessage.ERROR, exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponseMessage);
        }
    }

    @GetMapping("/get/wallet/{username}")
    public ResponseEntity<?> getWallet(@PathVariable @NotBlank String username) {
        try {
            Wallet wallet = walletService.getWallet(new GetWalletRequest(username));
            return ResponseEntity.ok(wallet);
        } catch (Exception exception) {
            ApiResponseMessage apiResponseMessage = new ApiResponseMessage(ApiResponseMessage.ERROR, exception.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponseMessage);
        }
    }

    @PostMapping("/get/all/byUpline")
    public ResponseEntity<?> getWalletByUpline(@RequestBody GetWalletsByUplineRequest walletsByUplineRequest) {
        try {
            List<Wallet> createdWallet = walletService.getWalletByUpline(walletsByUplineRequest);
            return ResponseEntity.status(HttpStatus.OK).body(createdWallet);
        } catch (Exception exception) {
            ApiResponseMessage apiResponseMessage = new ApiResponseMessage(ApiResponseMessage.ERROR, exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponseMessage);
        }
    }
    @PutMapping("/update/balance")
    public ResponseEntity<?> updateWallet(@RequestBody UpdateWalletRequest walletRequest) {
        try {
            Wallet updatedWallet = walletService.updateWallet(walletRequest);
            return ResponseEntity.ok(updatedWallet);
        } catch (Exception exception) {
            ApiResponseMessage apiResponseMessage = new ApiResponseMessage(ApiResponseMessage.ERROR, exception.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponseMessage);
        }
    }

    @PostMapping("/balance")
    public ResponseEntity<?> getBalance(@RequestBody GetWalletRequest balanceRequest) {
        try {
            WalletResponse response = walletService.getBalance(balanceRequest);
            return ResponseEntity.ok(response);
        } catch (Exception exception) {
            ApiResponseMessage apiResponseMessage = new ApiResponseMessage(ApiResponseMessage.ERROR, exception.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponseMessage);
        }
    }
}
