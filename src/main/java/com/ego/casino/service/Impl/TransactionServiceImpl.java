package com.ego.casino.service.Impl;

import com.ego.casino.dto.TransactionDto;
import com.ego.casino.entity.UserEntity;
import com.ego.casino.repository.UserRepository;
import com.ego.casino.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    UserRepository userRepository;

    @Override
    public ResponseEntity<TransactionDto> topUpBalance(String username, Double amount) {
        UserEntity user = userRepository.findByUsername(username).orElseThrow(
                () -> new RuntimeException("User not found!")
        );

        if(amount > 0){
            TransactionDto transactionDto = new TransactionDto();
            transactionDto.setUsername(user.getUsername());
            transactionDto.setBalance(user.getBalance().add(BigDecimal.valueOf(amount)));
            user.setBalance(user.getBalance().add(BigDecimal.valueOf(amount)));
            userRepository.save(user);
            return ResponseEntity.ok(transactionDto);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new TransactionDto("Topup amount can't be negative!"));
        }
    }
}
