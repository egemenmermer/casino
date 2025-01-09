package com.ego.casino.service;

import com.ego.casino.dto.TransactionDto;
import org.springframework.http.ResponseEntity;

public interface TransactionService {

    public ResponseEntity<TransactionDto> deposit(Long id, Double amount);
    public ResponseEntity<TransactionDto> withdraw(Long id, Double amount);
}
