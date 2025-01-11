package com.ego.casino.service.Impl;

import com.ego.casino.dto.TransactionDto;
import com.ego.casino.dto.TransactionHistoryDto;
import com.ego.casino.entity.AccountEntity;
import com.ego.casino.entity.TransactionHistoryEntity;
import com.ego.casino.enums.TransactionType;
import com.ego.casino.exception.ResourceNotFoundException;
import com.ego.casino.repository.TransactionHistoryRepository;
import com.ego.casino.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private AccountServiceImpl accountService;

    @Autowired
    private TransactionHistoryRepository transactionHistoryRepository;

    @Override
    public void createTransaction(AccountEntity account, BigDecimal amount, BigDecimal finalBalance, TransactionType transactionType, LocalDateTime created_at) {
        TransactionHistoryEntity transactionHistoryEntity = new TransactionHistoryEntity();
        transactionHistoryEntity.setAccount(account);
        transactionHistoryEntity.setAmount(amount);
        transactionHistoryEntity.setFinalBalance(finalBalance);
        transactionHistoryEntity.setKind(transactionType.toString());
        transactionHistoryEntity.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        transactionHistoryRepository.save(transactionHistoryEntity);
    }

    @Override
    public ResponseEntity<TransactionDto> transaction(Long id, BigDecimal amount, TransactionType transactionType) {
        AccountEntity account = accountService.findAccount(id).orElseThrow(
                () -> new ResourceNotFoundException("Account not found!")
        );

        if(amount.compareTo(BigDecimal.ZERO) < 0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        if(transactionType == TransactionType.DEPOSIT){
            account.setBalance(account.getBalance().add(amount));
        }else if(transactionType == TransactionType.WITHDRAW){
            account.setBalance(account.getBalance().subtract(amount));
        }
        accountService.updateAccount(account);
        createTransaction(account, amount, account.getBalance(), transactionType, LocalDateTime.now());
        return ResponseEntity.ok(new TransactionDto(account.getId(),amount, transactionType,account.getBalance()));
    }


    @Override
    public List<TransactionHistoryDto> getHistory(Long id) {
        AccountEntity accountEntity = accountService.findAccount(id).orElseThrow(
                () -> new ResourceNotFoundException("Account not found!")
        );
        return transactionHistoryRepository
                .findByAccountId(accountEntity.getId())
                .stream()
                .map(transactionHistoryEntity -> new TransactionHistoryDto(
                        transactionHistoryEntity.getId(),
                        transactionHistoryEntity.getAccount(),
                        transactionHistoryEntity.getAmount(),
                        transactionHistoryEntity.getFinalBalance(),
                        transactionHistoryEntity.getKind(),
                        transactionHistoryEntity.getCreatedAt()
                ))
                .collect(Collectors.toList());

    }

    public double calculateWinAmount(double betAmount, double winRate){
        return betAmount * (1 / winRate);
    }

    public BigDecimal calculateNewbalance(BigDecimal currentBalance, double betAmount, double winRate, boolean isWinner){
        if(isWinner){
            double winAmount = calculateWinAmount(betAmount, winRate);
            return currentBalance.add(BigDecimal.valueOf(winAmount));
        }else{
            return currentBalance.subtract(BigDecimal.valueOf(betAmount));
        }
    }

}
