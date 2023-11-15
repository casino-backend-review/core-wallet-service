package com.core.walletservice.controllers;

import com.core.walletservice.dto.TransactionRequest;
import com.core.walletservice.dto.TransactionResponse;
import com.core.walletservice.exceptions.BadRequestException;
import com.core.walletservice.exceptions.InternalErrorException;
import com.core.walletservice.services.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/wallet")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/deposit")
    public ResponseEntity<TransactionResponse> deposit(@RequestBody TransactionRequest transactionRequest) {
        try {
            TransactionResponse response = transactionService.deposit(transactionRequest);
            return ResponseEntity.ok(response);
        } catch (BadRequestException e) {
            // This assumes you have a global exception handler to handle BadRequestException
            throw e;
        } catch (Exception e) {
            // This assumes you have a global exception handler to handle InternalErrorException
            throw new InternalErrorException("Unexpected error occurred", e);
        }
    }

    @PostMapping("/withdraw")
    public ResponseEntity<TransactionResponse> withdraw(@RequestBody TransactionRequest transactionRequest) {
        try {
            TransactionResponse response = transactionService.withdraw(transactionRequest);
            return ResponseEntity.ok(response);
        } catch (BadRequestException e) {
            throw e;
        } catch (Exception e) {
            throw new InternalErrorException("Unexpected error occurred", e);
        }
    }
}
