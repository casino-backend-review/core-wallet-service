package com.core.walletservice.services.impl;

import com.core.walletservice.dto.TransactionDTO;
import com.core.walletservice.dto.TransactionRequest;
import com.core.walletservice.dto.TransactionResponse;
import com.core.walletservice.entity.Transaction;
import com.core.walletservice.entity.Wallet;
import com.core.walletservice.enums.TransactionType;
import com.core.walletservice.exception.ApiException;
import com.core.walletservice.repositories.TransactionRepository;
import com.core.walletservice.repositories.WalletRepository;
import com.core.walletservice.services.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepo;

    private final WalletRepository walletRepo;

    private final MongoTemplate mongoTemplate;

    private final KafkaTemplate<String, TransactionDTO> kafkaTemplate;

    public TransactionServiceImpl(TransactionRepository transactionRepo, WalletRepository walletRepo, MongoTemplate mongoTemplate, KafkaTemplate<String, TransactionDTO> kafkaTemplate) {
        this.transactionRepo = transactionRepo;
        this.walletRepo = walletRepo;
        this.mongoTemplate = mongoTemplate;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    @Transactional
    public TransactionResponse deposit(TransactionRequest transactionRequest) throws ApiException {

        try {
            Instant start = Instant.now();

            Wallet userWallet = walletRepo.findByUsername(transactionRequest.getUsername());
            if (userWallet == null) {
                // throw new NotFoundException("Wallet not found");
                throw new ApiException("Wallet not found",1,HttpStatus.FORBIDDEN);
            }
            double walletAmountBefore = userWallet.getBalance();
            double walletAmountAfter = walletAmountBefore + transactionRequest.getAmount();

            checkRecentTransaction(transactionRequest.getUsername(), "deposit", transactionRequest.getAmount());

       /*     Transaction financeTransaction = createAndSaveTransaction(TransactionType.DEPOSIT, transactionRequest,
                    walletAmountBefore, userWallet);*/

            processTransaction(transactionRequest.getRefId(),TransactionType.deposit, walletAmountBefore, transactionRequest.getAmount(),
                    walletAmountAfter, "", "", "", userWallet.getRefSale(),
                    userWallet.getUsername(), userWallet.getUpline(), "", transactionRequest.getRemark(), false, true,transactionRequest.getUpdatedBy());

            userWallet.setBalance(walletAmountAfter);
            walletRepo.save(userWallet);

            Instant end = Instant.now();
            System.out.println("Deposit transaction time: " + Duration.between(start, end));

            return createTransactionResponse(userWallet, walletAmountBefore, walletAmountAfter, transactionRequest.getRefId());
        } catch (Exception exception) {
            //throw new InternalErrorException("Failed to deposit :", exception);
            throw new ApiException("Failed to deposit :" + exception.getMessage(),1,HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @Override
    @Transactional
    public TransactionResponse withdraw(TransactionRequest transactionRequest) throws ApiException {
        if (transactionRequest.getAmount() <= 0) {
            // throw new BadRequestException("Invalid amount");
            throw new ApiException("Invalid amount",1,HttpStatus.FORBIDDEN);

        }

        try {
            Instant start = Instant.now();

            Wallet userWallet = walletRepo.findByUsername(transactionRequest.getUsername());
            if (userWallet == null) {
                //   throw new NotFoundException("Wallet not found");
                throw new ApiException("Wallet not found",1,HttpStatus.FORBIDDEN);

            }

            double walletAmountBefore = userWallet.getBalance();
            double walletAmountAfter = walletAmountBefore - transactionRequest.getAmount();

            if (walletAmountBefore < transactionRequest.getAmount()) {
                throw new ApiException("Insufficient Funds",1,HttpStatus.FORBIDDEN);
            }

            checkRecentTransaction(transactionRequest.getUsername(), "withdraw", transactionRequest.getAmount());

         /*   Transaction financeTransaction = createAndSaveTransaction(TransactionType.WITHDRAW, transactionRequest,
                    walletAmountBefore, userWallet);
*/
            processTransaction(transactionRequest.getRefId(),TransactionType.withdraw, walletAmountBefore, transactionRequest.getAmount(),
                    walletAmountAfter, "", "", "", userWallet.getRefSale(),
                    userWallet.getUsername(), userWallet.getUpline(), "", transactionRequest.getRemark(), false, true, transactionRequest.getUpdatedBy());

            userWallet.setBalance(walletAmountAfter);
            walletRepo.save(userWallet);

            Instant end = Instant.now();
            System.out.println("Withdraw transaction time: " + Duration.between(start, end));

            return createTransactionResponse(userWallet, walletAmountBefore, walletAmountAfter, transactionRequest.getRefId());
        } catch (Exception e) {
            throw new ApiException("Failed to withdraw :" + e.getMessage(),1,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void checkRecentTransaction(String username, String action, double amount) throws ApiException {
        Transaction recentTx = transactionRepo.findRecentTransaction(username, action, amount);
        if (recentTx != null) {
            Duration diff = Duration.between(recentTx.getCreatedAt(), Instant.now());
            if (diff.toMinutes() < 2) {
                throw new ApiException("Please wait 2 minutes between transactions",1,HttpStatus.FORBIDDEN);
            }
        }
    }

    private Transaction createAndSaveTransaction(TransactionType type, TransactionRequest transactionRequest, double walletAmountBefore, Wallet userWallet) {
        Transaction transaction1 = new Transaction();
        transaction1.setAction(type.toString());
        transaction1.setAmount(transactionRequest.getAmount());
        transaction1.setAfterBalance(walletAmountBefore);
        transaction1.setUsername(userWallet.getUsername());
        transaction1.setUpline(userWallet.getUpline());
        transaction1.setCreatedBy(userWallet.getUpline());
        transaction1.setDescription(transactionRequest.getRemark());
        transaction1.setTransactionId(transactionRequest.getRefId());
        transaction1.setCreatedAt(LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()));
        transaction1.setUpdatedAt(LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()));
        return transactionRepo.save(transaction1);
    }

    private void processTransaction(String refId, TransactionType actionType, double beforeBalance, double amount, double afterBalance,
                                    String roundId, String gameId, String gameName, String refSale, String username,
                                    String upline, String productId, String description, boolean isFeatureBuy,
                                    boolean isEndRound, String updatedBy) throws ExecutionException, InterruptedException {
        Transaction transactionDTO = new Transaction();
        transactionDTO.setTransactionId(refId);
        transactionDTO.setActionType(actionType);
        transactionDTO.setAction(String.valueOf(actionType));

        if (isEndRound && actionType.equals(TransactionType.payIn)) {
            transactionDTO.setEndRound(true);
        }

        if (productId.equals("withdrawal") || actionType.equals(TransactionType.transferOut) || actionType.equals(TransactionType.transferIn)) {
            transactionDTO.setProductName(productId);
            transactionDTO.setGameCategory("");
            transactionDTO.setProvider(productId);
            transactionDTO.setPercentage(100.0);
        }

        transactionDTO.setBeforeBalance(beforeBalance);
        transactionDTO.setAmount(amount);
        transactionDTO.setAfterBalance(afterBalance);
        transactionDTO.setUsername(username);
        transactionDTO.setRoundId(roundId);
        transactionDTO.setGameId(null);
        transactionDTO.setGameName(gameName);
        transactionDTO.setFeatureBuy(isFeatureBuy);
        transactionDTO.setEndRound(isEndRound);
        transactionDTO.setUpline(upline);
        transactionDTO.setRefSale(refSale);
        transactionDTO.setDescription(description);

        transactionDTO.setProductId(productId);
        transactionDTO.setCreatedAtIso(LocalDateTime.now());
        transactionDTO.setCreatedAt(LocalDateTime.now());

        transactionDTO.setCreated(LocalDateTime.now());
        transactionDTO.setFRunning(false);
        transactionDTO.setFRunningDate("");
        transactionDTO.setCreatedBy(updatedBy);


//        kafkaTemplate.send("transaction", transactionDTO);
    }

    private TransactionResponse createTransactionResponse(Wallet userWallet, double walletAmountBefore,
                                                          double walletAmountAfter, String  refId) {
        return new TransactionResponse(
                userWallet.getToken(),
                userWallet.getUsername(),
                walletAmountAfter,
                walletAmountBefore,
                walletAmountAfter,
                refId
        );
    }
}
