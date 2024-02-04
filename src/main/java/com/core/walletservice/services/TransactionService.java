package com.core.walletservice.services;

import com.core.walletservice.dto.TransactionRequest;
import com.core.walletservice.dto.TransactionResponse;
import com.core.walletservice.exception.ApiException;
;

public interface TransactionService {

    TransactionResponse deposit(TransactionRequest transactionRequest) throws ApiException;

    TransactionResponse withdraw(TransactionRequest transactionRequest) throws ApiException;


}
