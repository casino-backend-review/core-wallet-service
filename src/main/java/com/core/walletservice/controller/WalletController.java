package com.core.walletservice.controller;

import com.core.walletservice.dto.*;
import com.core.walletservice.entity.Wallet;
import com.core.walletservice.exception.ApiResponseMessage;
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
    public ResponseEntity<ApiResponseMessage<Wallet>> createWallet(@RequestBody CreateWalletRequest walletRequest) {
        try {
            Wallet createdWallet = walletService.createWallet(walletRequest);
            return ResponseEntity.ok(ApiResponseMessage.<Wallet>builder().data(createdWallet).code(ApiResponseMessage.OK).type("ok").build());
        } catch (Exception exception) {
            ApiResponseMessage apiResponseMessage = new ApiResponseMessage(ApiResponseMessage.ERROR, exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponseMessage);
        }
    }

    @GetMapping("/get-balance/username/{username}")
    public ResponseEntity<ApiResponseMessage<Wallet>> getWallet(@PathVariable @NotBlank String username) throws Exception {
        try {
            Wallet wallet = walletService.getWallet(new GetWalletRequest(username));
            return ResponseEntity.ok(ApiResponseMessage.<Wallet>builder().data(wallet).code(ApiResponseMessage.OK).type("ok").build());
        } catch (Exception exception) {
            ApiResponseMessage apiResponseMessage = new ApiResponseMessage(ApiResponseMessage.ERROR, exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponseMessage);
        }
    }

    @PostMapping("/get-all-wallet-details/by-upline")
    public ResponseEntity<ApiResponseMessage<List<Wallet>>> getWalletByUpline(@RequestBody GetWalletsByUplineRequest walletsByUplineRequest) {
        try {
            List<Wallet> createdWallet = walletService.getWalletByUpline(walletsByUplineRequest);
            return ResponseEntity.ok(ApiResponseMessage.<List<Wallet>>builder().data(createdWallet).code(ApiResponseMessage.OK).type("ok").build());
        } catch (Exception exception) {
            ApiResponseMessage apiResponseMessage = new ApiResponseMessage(ApiResponseMessage.ERROR, exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponseMessage);
        }
    }
    @PutMapping("/update/balance")
    public ResponseEntity<ApiResponseMessage<Wallet>> updateWallet(@RequestBody UpdateWalletRequest walletRequest) {
        try {
            Wallet updatedWallet = walletService.updateWallet(walletRequest);
            return ResponseEntity.ok(ApiResponseMessage.<Wallet>builder().data(updatedWallet).code(ApiResponseMessage.OK).type("ok").build());
        } catch (Exception exception) {
            ApiResponseMessage apiResponseMessage = new ApiResponseMessage(ApiResponseMessage.ERROR, exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponseMessage);
        }
    }

}
