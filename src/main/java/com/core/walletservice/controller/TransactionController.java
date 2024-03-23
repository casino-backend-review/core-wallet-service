package com.core.walletservice.controller;

import com.core.walletservice.dto.TransactionRequest;
import com.core.walletservice.dto.TransactionResponse;
import com.core.walletservice.exception.ApiException;
import com.core.walletservice.exception.ApiResponseMessage;
import com.core.walletservice.exception.Error;
import com.core.walletservice.services.TransactionService;
import org.springframework.beans.factory.annotation.Value;
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
            return ResponseEntity.ok(ApiResponseMessage.<TransactionResponse>builder().data(response).build());
        }catch (ApiException exception) {
            return getFailureResponseEntity(exception);
        }
    }

    @PostMapping("/withdraw")
    public ResponseEntity<ApiResponseMessage<TransactionResponse>> withdraw(@RequestBody TransactionRequest transactionRequest) {
        try {
            TransactionResponse response = transactionService.withdraw(transactionRequest);
            return ResponseEntity.ok(ApiResponseMessage.<TransactionResponse>builder().data(response).build());
        } catch (ApiException exception) {
            return getFailureResponseEntity(exception);
        }
    }

    private static ResponseEntity getFailureResponseEntity(ApiException exception) {
        HttpStatus status = exception.getStatus() != null ? exception.getStatus() : HttpStatus.INTERNAL_SERVER_ERROR;
        ApiResponseMessage apiResponseMessage = new ApiResponseMessage(null, Error.builder().code(exception.getErrorCode()).message(exception.getMessage()).build());
        return ResponseEntity.status(status).body(apiResponseMessage);
    }
}
