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

    public UserDto getUser(Long id);
    public UserEntity findByEmail(String email);
    public void createUser(UserEntity userEntity);
    CustomUserDetails getUserDetailsByEmail(String email);
    UserEntity getUserByEmail(String email);

}
