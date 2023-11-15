package com.core.walletservice.repositories.impl;

import com.core.walletservice.entity.Wallet;
import com.core.walletservice.exceptions.EntityNotFoundException;
import com.core.walletservice.repositories.CustomWalletRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;


@Repository
public class CustomWalletRepositoryImpl implements CustomWalletRepository {

    MongoTemplate mongoTemplate;

    public CustomWalletRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Wallet updateBalanceByUsername(String username, double newBalance) throws EntityNotFoundException {
        Criteria filterCriteria = Criteria.where("username").is(username);
        Query query = Query.query(filterCriteria);
        Update update = new Update().set("balance", newBalance);
        Wallet wallet = mongoTemplate.findAndModify(query, update, Wallet.class);
        return wallet;
    }

}
