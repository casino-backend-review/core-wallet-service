package com.core.walletservice.controllers;

// ... other imports ...
import com.core.walletservice.dto.TransactionRequest;
import com.core.walletservice.dto.TransactionResponse;
import com.core.walletservice.exceptions.*;
import com.core.walletservice.services.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/deposit")
    public ResponseEntity<TransactionResponse> deposit(@RequestBody TransactionRequest transactionRequest) {
        if (transactionRequest.getAmount() <= 0) {
            throw new BadRequestException("The deposit amount must be greater than zero.");
        }

        try {
            TransactionResponse response = transactionService.deposit(transactionRequest);
            if (response == null) {
                throw new AppException("There are no deposits available.");
            }
            return ResponseEntity.ok(response);
        } catch (BadRequestException e) {
            throw e;
        } catch (InsufficientFundsException | EntityNotFoundException e) {
            // Handle specific business logic exceptions with custom messages
            throw new AppException(e.getMessage());
        } catch (Exception e) {
            throw new InternalErrorException("Unexpected error occurred during deposit.", e);
        }
    }

    @PostMapping("/withdraw")
    public ResponseEntity<TransactionResponse> withdraw(@RequestBody TransactionRequest transactionRequest) {
        if (transactionRequest.getAmount() <= 0) {
            throw new BadRequestException("The withdrawal amount must be greater than zero.");
        }

        try {
            TransactionResponse response = transactionService.withdraw(transactionRequest);
            if (response == null) {
                throw new NotFoundException("The account for withdrawal was not found.");
            }
            return ResponseEntity.ok(response);
        } catch (BadRequestException e) {
            throw e;
        } catch (InsufficientFundsException e) {
            throw new InsufficientFundsException("Insufficient funds for the requested withdrawal.");
        } catch (NotFoundException e) {
            throw new NotFoundException("The entity involved in the transaction does not exist.");
        } catch (Exception e) {
            throw new InternalErrorException("Unexpected error occurred during withdrawal.", e);
        }
    }
}
