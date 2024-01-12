package com.core.walletservice.repositories;

import com.core.walletservice.entity.Wallet;
import com.core.walletservice.exceptions.EntityNotFoundException;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface WalletRepository extends MongoRepository<Wallet, UUID>, CustomWalletRepository {

    Wallet findByUsername(String username) throws EntityNotFoundException;

}
