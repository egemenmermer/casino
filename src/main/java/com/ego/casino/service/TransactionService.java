package com.ego.casino.service;

import com.ego.casino.dto.*;
import com.ego.casino.entity.AccountEntity;
import com.ego.casino.enums.TransactionType;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface TransactionService {

    public void createTransaction(AccountEntity account, BigDecimal amount, BigDecimal finalBalance, TransactionType transactionType , LocalDateTime created_at);
    public ResponseEntity<TransactionDto> transaction(Long id, BigDecimal amount, TransactionType transactionType);
    public List<TransactionHistoryDto> getHistory(Long id);

}
