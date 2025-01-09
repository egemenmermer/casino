package com.ego.casino.service.Impl;

import com.ego.casino.dto.AccountDto;
import com.ego.casino.dto.TransactionDto;
import com.ego.casino.entity.AccountEntity;
import com.ego.casino.entity.UserEntity;
import com.ego.casino.exception.ResourceNotFoundException;
import com.ego.casino.repository.AccountRepository;
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
    private AccountRepository accountRepository;

    @Override
    public ResponseEntity<AccountDto> deposit(Long id, Double amount) {
        AccountEntity account = accountRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Account not found!")
        );
        if(amount > 0){
            AccountDto accountDto = new AccountDto();
            accountDto.setId(id);
            accountDto.setBalance(account.getBalance().add(BigDecimal.valueOf(amount)));
            account.setBalance(account.getBalance().add(BigDecimal.valueOf(amount)));
            accountRepository.save(account);
            return ResponseEntity.ok(accountDto);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(null);
        }
    }

    @Override
    public ResponseEntity<AccountDto> withdraw(Long id, Double amount) {
        AccountEntity account = accountRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Account not found!")
        );
        if(amount > 0){
            AccountDto accountDto = new AccountDto();
            accountDto.setId(id);
            accountDto.setBalance(account.getBalance().subtract(BigDecimal.valueOf(amount)));
            account.setBalance(account.getBalance().subtract(BigDecimal.valueOf(amount)));
            accountRepository.save(account);
            return ResponseEntity.ok(accountDto);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(null);
        }
    }
}
