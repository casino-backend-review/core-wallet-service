package com.core.walletservice.controllers;

import com.core.walletservice.dto.GetWalletRequest;
import com.core.walletservice.dto.WalletResponse;
import com.core.walletservice.exceptions.AppException;
import com.core.walletservice.services.impl.WalletServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wallet")
public class WalletController {

    private final WalletServiceImpl walletServiceImpl;

    @Autowired
    public WalletController(WalletServiceImpl walletServiceImpl) {
        this.walletServiceImpl = walletServiceImpl;
    }

    @PostMapping("/balance")
    public ResponseEntity<?> getBalance(@RequestBody GetWalletRequest balanceRequest) {
        try {
            WalletResponse response = walletServiceImpl.getBalance(balanceRequest);
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
