package com.core.walletservice.repositories;

import com.core.walletservice.entity.Wallet;
import com.core.walletservice.exceptions.EntityNotFoundException;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.UUID;

public interface WalletRepository extends MongoRepository<Wallet, UUID>, CustomWalletRepository {

    Wallet findByUsername(String username) throws EntityNotFoundException;

    @Query("""
{"username": {$in: ?0}}""")
    List< Wallet> findAllByUpline(List<String> usernameIds) ;


}
