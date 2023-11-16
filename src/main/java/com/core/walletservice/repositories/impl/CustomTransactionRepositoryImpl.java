package com.core.walletservice.repositories.impl;

import com.core.walletservice.entity.Transaction;
import com.core.walletservice.repositories.CustomTransactionRepository;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class CustomTransactionRepositoryImpl implements CustomTransactionRepository {

    MongoTemplate mongoTemplate;

    public CustomTransactionRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Transaction findRecentTransaction(String username, String action, double amount) {
        Criteria criteria = Criteria.where("username").is(username).and("type").is(action).and("amount").is(amount);
        Query query = new Query();
        query.addCriteria(criteria);
        query.with(Sort.by(Sort.Order.desc("created_at")));
        return mongoTemplate.findOne(query, Transaction.class);
    }
}
