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
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
        if (transactionRequest.getAmount() <= 0) {
            //  throw new BadRequestException("Invalid amount");
            throw new ApiException("Invalid amount",1, HttpStatus.FORBIDDEN);

        }

        try {
            Instant start = Instant.now();

            Wallet userWallet = walletRepo.findByUsername(transactionRequest.getUsername());
            if (userWallet == null) {
                // throw new NotFoundException("Wallet not found");
                throw new ApiException("Wallet not found",1,HttpStatus.NO_CONTENT);
            }
            double walletAmountBefore = userWallet.getBalance();
            double walletAmountAfter = walletAmountBefore + transactionRequest.getAmount();

            checkRecentTransaction(transactionRequest.getUsername(), "deposit", transactionRequest.getAmount());

       /*     Transaction financeTransaction = createAndSaveTransaction(TransactionType.DEPOSIT, transactionRequest,
                    walletAmountBefore, userWallet);*/

            processTransaction(transactionRequest.getRefId(),"deposit", walletAmountBefore, transactionRequest.getAmount(),
                    walletAmountAfter, "", "", "", userWallet.getRefSale(),
                    userWallet.getUsername(), userWallet.getUpline(), "withdrawal", transactionRequest.getRemark(), false, true);

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
                throw new ApiException("Wallet not found",1,HttpStatus.NO_CONTENT);

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
            processTransaction(transactionRequest.getRefId(),"withdraw", walletAmountBefore, transactionRequest.getAmount(),
                    walletAmountAfter, "", "", "", userWallet.getRefSale(),
                    userWallet.getUsername(), userWallet.getUpline(), "withdrawal", transactionRequest.getRemark(), false, true);

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
        Transaction transaction = new Transaction();
        transaction.setType(type);
        transaction.setAmount(transactionRequest.getAmount());
        transaction.setAfterBalance(walletAmountBefore);
        transaction.setUsername(userWallet.getUsername());
        transaction.setUpline(userWallet.getUpline());
        transaction.setCreatedBy(userWallet.getUpline());
        transaction.setRemark(transactionRequest.getRemark());
        transaction.setRefID(transactionRequest.getRefId());
        transaction.setCreatedAt(LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()));
        transaction.setUpdatedAt(LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()));
        return transactionRepo.save(transaction);
    }

    private void processTransaction(String refId,String actionType, double beforeBalance, double amount, double afterBalance,
                                    String roundId, String gameId, String gameName, String refSale, String username,
                                    String upline, String productId, String description, boolean isFeatureBuy,
                                    boolean isEndRound) throws ExecutionException, InterruptedException {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setId(refId);
        transactionDTO.setActionType(actionType);
        transactionDTO.setAction(actionType);

        if (isEndRound && actionType.equals("payIn")) {
            transactionDTO.setEndRound(true);
        }

        if (productId.equals("withdrawal") || actionType.equals("transferOut") || actionType.equals("transferIn")) {
            transactionDTO.setProductName(productId);
            transactionDTO.setGameCategory("");
            transactionDTO.setProvider(productId);
            transactionDTO.setPercentage(100.0);
        }

        transactionDTO.setBeforeBalance(beforeBalance);
        transactionDTO.setAmount(amount);
        transactionDTO.setAfterBalance(afterBalance);
        transactionDTO.setUsername(username);
        transactionDTO.setRoundID(roundId);
        transactionDTO.setGameID(gameId);
        transactionDTO.setGameName(gameName);
        transactionDTO.setFeatureBuy(isFeatureBuy);
        transactionDTO.setEndRound(isEndRound);
        transactionDTO.setUpline(upline);
        transactionDTO.setRefSale(refSale);
        transactionDTO.setDescription(description);

        transactionDTO.setProductID(productId);
        transactionDTO.setCreatedAtIso(Instant.now().toString());
        transactionDTO.setCreatedAt(Instant.now().toString());

        transactionDTO.setCreated(Instant.now().toString());
        transactionDTO.setFRunning(false);
        transactionDTO.setFRunningDate("");

     //   kafkaTemplate.send("transaction", transactionDTO);

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
