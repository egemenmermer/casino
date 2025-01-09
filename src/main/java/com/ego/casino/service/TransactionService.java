package com.ego.casino.service;

import com.ego.casino.dto.AccountDto;
import com.ego.casino.dto.TransactionDto;
import org.springframework.http.ResponseEntity;

public interface TransactionService {

    public ResponseEntity<AccountDto> deposit(Long id, Double amount);
    public ResponseEntity<AccountDto> withdraw(Long id, Double amount);
}
