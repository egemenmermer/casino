package com.ego.casino.service;

import com.ego.casino.dto.UserDto;
import com.ego.casino.entity.AccountEntity;
import com.ego.casino.entity.UserEntity;
import com.ego.casino.enums.TransactionType;
import com.ego.casino.security.CustomUserDetails;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface UserService {

    UserDto getUser(Long id);
    UserEntity findByEmail(String email);
    UserEntity findByUsername(String username);
    void createUser(UserEntity userEntity);
    CustomUserDetails getUserDetailsByEmail(String email);
    UserEntity getUserByEmail(String email);

}
