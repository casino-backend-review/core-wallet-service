package com.core.walletservice.repositories;

import com.core.walletservice.entity.Wallet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.UUID;

public interface WalletRepository extends MongoRepository<Wallet, String>, CustomWalletRepository {

    Wallet findByUsername(String username) ;

    @Query("""
{"username": {$in: ?0}}""")
    List< Wallet> findAllByUpline(List<String> usernameIds) ;


    void deleteByUsername(String username);
}
