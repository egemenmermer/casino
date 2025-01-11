package com.ego.casino.service.Impl;

import com.ego.casino.dto.AccountDto;
import com.ego.casino.dto.DepositResponseDto;
import com.ego.casino.dto.TransactionDto;
import com.ego.casino.dto.WithdrawResponseDto;
import com.ego.casino.entity.AccountEntity;
import com.ego.casino.enums.TransactionType;
import com.ego.casino.exception.ResourceNotFoundException;
import com.ego.casino.repository.AccountRepository;
import com.ego.casino.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionServiceImpl transactionService;

    @Override
    public ResponseEntity<AccountDto> getBalance(Long id) {
        AccountEntity account = accountRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Account not found!")
        );
        AccountDto accountDto = new AccountDto(account.getId(), account.getBalance());

        return ResponseEntity.ok(accountDto);
    }


    public Optional<AccountEntity> findAccount(Long id) {
        return accountRepository.findById(id);
    }

    public void updateAccount(AccountEntity account) {
        accountRepository.save(account);
    }

    public ResponseEntity<DepositResponseDto> deposit(Long id, BigDecimal amount, TransactionType transactionType) {
        AccountEntity account = findAccount(id).orElseThrow(
                () -> new ResourceNotFoundException("Account not found!")
        );
        if(amount.compareTo(BigDecimal.ZERO) < 0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        account.setBalance(account.getBalance().add(amount));
        updateAccount(account);
        transactionService.createTransaction(account, amount, account.getBalance(), transactionType, LocalDateTime.now());
        return ResponseEntity.ok(new DepositResponseDto(account.getId(),transactionType, LocalDateTime.now(),account.getBalance()));

    }

    public ResponseEntity<WithdrawResponseDto> withdraw(Long id, BigDecimal amount, TransactionType transactionType) {
        AccountEntity account = findAccount(id).orElseThrow(
                () -> new ResourceNotFoundException("Account not found!")
        );
        if(amount.compareTo(BigDecimal.ZERO) < 0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        account.setBalance(account.getBalance().subtract(amount));
        updateAccount(account);
        transactionService.createTransaction(account, amount, account.getBalance(), transactionType, LocalDateTime.now());
        return ResponseEntity.ok(new WithdrawResponseDto(account.getId(),transactionType, LocalDateTime.now(),account.getBalance()));

    }
    /*
    @Override
    public ResponseEntity<UserDto> retrieveAccounts(Long id) {
        return null;
    }
     */

    public void updateUserBalance(AccountEntity accountEntity, BigDecimal newBalance){
        accountEntity.setBalance(newBalance);
        accountRepository.save(accountEntity);
    }



}
