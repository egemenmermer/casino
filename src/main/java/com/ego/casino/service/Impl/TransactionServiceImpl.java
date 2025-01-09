package com.ego.casino.service.Impl;

import com.ego.casino.dto.TransactionDto;
import com.ego.casino.entity.AccountEntity;
import com.ego.casino.enums.TransactionType;
import com.ego.casino.exception.ResourceNotFoundException;
import com.ego.casino.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private AccountServiceImpl accountService;

    @Override
    public ResponseEntity<TransactionDto> transaction(Long id, BigDecimal amount, TransactionType transactionType) {
        AccountEntity account = accountService.searchAccount(id).orElseThrow(
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
        accountService.saveAccount(account);

        return ResponseEntity.ok(new TransactionDto(account.getId(),amount, transactionType, account.getBalance(), LocalDateTime.now()));
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
