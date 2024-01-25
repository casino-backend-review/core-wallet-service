package com.core.walletservice.services;

import com.core.walletservice.dto.TransactionRequest;
import com.core.walletservice.dto.TransactionResponse;
;

public interface TransactionService {

    TransactionResponse deposit(TransactionRequest transactionRequest);

    TransactionResponse withdraw(TransactionRequest transactionRequest) ;


}
