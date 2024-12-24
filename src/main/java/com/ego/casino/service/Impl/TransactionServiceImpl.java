package com.ego.casino.service.Impl;

import com.ego.casino.dto.UserDto;
import com.ego.casino.entity.UserEntity;
import com.ego.casino.repository.UserRepository;
import com.ego.casino.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    UserRepository userRepository;

    @Override
    public ResponseEntity<UserDto> topUpBalance(String username, Double amount) {
        UserEntity user = userRepository.findByUsername(username).orElseThrow(
                () -> new RuntimeException("User not found!")
        );
        UserDto userDto = new UserDto();
        userDto.setUsername(user.getUsername());
        userDto.setBalance(user.getBalance().add(BigDecimal.valueOf(amount)));

        return ResponseEntity.ok(userDto);
    }
}
