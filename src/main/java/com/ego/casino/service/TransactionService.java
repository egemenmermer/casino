package com.ego.casino.service;

import com.ego.casino.dto.DepositDto;
import com.ego.casino.dto.UserDto;
import com.ego.casino.entity.UserEntity;
import org.springframework.http.ResponseEntity;

public interface TransactionService {

    public ResponseEntity<DepositDto> topUpBalance(String username, Double amount);
}
