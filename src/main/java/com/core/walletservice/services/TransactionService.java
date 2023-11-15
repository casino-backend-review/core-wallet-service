package com.core.walletservice.services;

import com.core.walletservice.dto.TransactionRequest;
import com.core.walletservice.dto.TransactionResponse;
import com.core.walletservice.exceptions.EntityNotFoundException;
import com.core.walletservice.exceptions.InsufficientFundsException;

public interface TransactionService {

    TransactionResponse deposit(TransactionRequest transactionRequest) throws EntityNotFoundException;

    TransactionResponse withdraw(TransactionRequest transactionRequest) throws EntityNotFoundException, InsufficientFundsException;


}
