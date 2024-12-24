package com.ego.casino.service;

import com.ego.casino.dto.UserDto;
import com.ego.casino.entity.UserEntity;
import org.springframework.http.ResponseEntity;

public interface TransactionService {

    public ResponseEntity<UserDto> topUpBalance(String username, Double amount);
}
