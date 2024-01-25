package com.core.walletservice.controller;

import com.core.walletservice.dto.TransactionRequest;
import com.core.walletservice.dto.TransactionResponse;
import com.core.walletservice.exception.ApiResponseMessage;
import com.core.walletservice.services.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/deposit")
    public ResponseEntity<ApiResponseMessage<TransactionResponse>> deposit(@RequestBody TransactionRequest transactionRequest) {
        try {
            TransactionResponse response = transactionService.deposit(transactionRequest);
            return ResponseEntity.ok(ApiResponseMessage.<TransactionResponse>builder().data(response).code(ApiResponseMessage.OK).type("ok").build());
        } catch (Exception exception) {
            ApiResponseMessage apiResponseMessage = new ApiResponseMessage(ApiResponseMessage.ERROR, exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponseMessage);
        }
    }

    @PostMapping("/withdraw")
    public ResponseEntity<ApiResponseMessage<TransactionResponse>> withdraw(@RequestBody TransactionRequest transactionRequest) {
        try {
            TransactionResponse response = transactionService.withdraw(transactionRequest);
            return ResponseEntity.ok(ApiResponseMessage.<TransactionResponse>builder().data(response).code(ApiResponseMessage.OK).type("ok").build());
        } catch (Exception exception) {
            ApiResponseMessage apiResponseMessage = new ApiResponseMessage(ApiResponseMessage.ERROR, exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponseMessage);
        }
    }
}
