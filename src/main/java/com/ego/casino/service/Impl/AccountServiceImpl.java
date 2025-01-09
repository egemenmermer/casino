package com.ego.casino.service.Impl;

import com.ego.casino.dto.AccountDto;
import com.ego.casino.dto.UserDto;
import com.ego.casino.entity.AccountEntity;
import com.ego.casino.entity.UserEntity;
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

    @Override
    public ResponseEntity<AccountDto> getBalance(Long id) {
        AccountEntity account = accountRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Account not found!")
        );
        AccountDto accountDto = new AccountDto(account.getId(), account.getBalance());

        return ResponseEntity.ok(accountDto);
    }


    public Optional<AccountEntity> searchAccount(Long id) {
        return accountRepository.findById(id);
    }

    public void saveAccount(AccountEntity account) {
        accountRepository.save(account);
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
