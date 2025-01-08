package com.ego.casino.service;

import com.ego.casino.dto.TransactionDto;
import org.springframework.http.ResponseEntity;

public interface TransactionService {

    public ResponseEntity<TransactionDto> topUpBalance(String username, Double amount);
}
