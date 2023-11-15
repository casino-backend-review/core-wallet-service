package com.core.walletservice.repositories;

import com.core.walletservice.entity.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface TransactionRepository extends MongoRepository<Transaction, String> ,CustomTransactionRepository {

}
