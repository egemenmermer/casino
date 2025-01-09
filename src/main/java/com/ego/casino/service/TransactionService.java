package com.ego.casino.service;

import com.ego.casino.dto.AccountDto;
import com.ego.casino.dto.TransactionDto;
import com.ego.casino.enums.TransactionType;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public interface TransactionService {

    public ResponseEntity<TransactionDto> transaction(Long id, BigDecimal amount, TransactionType transactionType);

}
