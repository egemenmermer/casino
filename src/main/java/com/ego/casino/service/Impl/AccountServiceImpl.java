package com.ego.casino.service.Impl;

import com.ego.casino.dto.AccountDto;
import com.ego.casino.dto.TransactionDto;
import com.ego.casino.entity.AccountEntity;
import com.ego.casino.enums.TransactionType;
import com.ego.casino.repository.AccountRepository;
import com.ego.casino.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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



/*
    public ResponseEntity<TransactionDto> createTransaction(Long id, BigDecimal amount, TransactionType transactionType) {
        transactionService.transaction(id, amount, transactionType);
       return ResponseEntity.ok(new TransactionDto(account.getId(),amount, transactionType,account.getBalance()));
    }

 */

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
