package com.ego.casino.service.Impl;

import com.ego.casino.dto.AccountCreateResponseDto;
import com.ego.casino.dto.AccountDto;
import com.ego.casino.dto.DepositResponseDto;
import com.ego.casino.dto.WithdrawResponseDto;
import com.ego.casino.entity.AccountEntity;
import com.ego.casino.entity.UserEntity;
import com.ego.casino.enums.TransactionType;
import com.ego.casino.exception.ResourceNotFoundException;
import com.ego.casino.repository.AccountRepository;
import com.ego.casino.security.CustomUserDetails;
import com.ego.casino.service.AccountService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private TransactionServiceImpl transactionService;

    @Override
    public AccountDto getBalance(Long id) {
        AccountEntity account = accountRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Account not found!")
        );
        AccountDto accountDto = new AccountDto(account.getId(), account.getBalance());

        return accountDto;
    }

    public Optional<AccountEntity> findAccount(Long id) {
        return accountRepository.findById(id);
    }

    public void updateAccount(AccountEntity account) {
        accountRepository.save(account);
    }

    @Override
    @Transactional
    public DepositResponseDto deposit(Long accountId, BigDecimal amount, TransactionType transactionType) {
        AccountEntity account = findAccount(accountId).orElseThrow(
                () -> new ResourceNotFoundException("Account not found!")
        );
        if(amount.compareTo(BigDecimal.ZERO) < 0){
            return (DepositResponseDto) ResponseEntity.status(HttpStatus.BAD_REQUEST);
        }

        account.setBalance(account.getBalance().add(amount));
        account.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        updateAccount(account);
        transactionService.createTransaction(account, amount, account.getBalance(), transactionType, LocalDateTime.now());
        return new DepositResponseDto(account.getId(),transactionType, LocalDateTime.now(),account.getBalance());

    }
    @Override
    @Transactional
    public WithdrawResponseDto withdraw(Long accountId, BigDecimal amount, TransactionType transactionType) {
        AccountEntity account = findAccount(accountId).orElseThrow(
                () -> new ResourceNotFoundException("Account not found!")
        );
        if(amount.compareTo(BigDecimal.ZERO) < 0){
            return (WithdrawResponseDto) ResponseEntity.status(HttpStatus.BAD_REQUEST);
        }

        account.setBalance(account.getBalance().subtract(amount));
        account.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        updateAccount(account);
        transactionService.createTransaction(account, amount, account.getBalance(), transactionType, LocalDateTime.now());
        return new WithdrawResponseDto(account.getId(),transactionType, LocalDateTime.now(),account.getBalance());

    }

    /*
    @Override
    public ResponseEntity<UserDto> retrieveAccounts(Long id) {
        return null;
    }
     */

    public void updateBalance(AccountEntity accountEntity, BigDecimal newBalance){
        accountEntity.setBalance(newBalance);
        accountRepository.save(accountEntity);
    }


    public AccountCreateResponseDto createAccount(CustomUserDetails user) {
        UserEntity userEntity = userService.findByEmail(user.getEmail());
        AccountEntity account = new AccountEntity();
        account.setUserId(userEntity);
        account.setBalance(BigDecimal.ZERO);
        account.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));

        userService.createUser(userEntity);
        accountRepository.save(account);

        return new AccountCreateResponseDto("Account Created");
    }
}
