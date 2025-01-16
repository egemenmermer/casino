package com.ego.casino.service.Impl;

import com.ego.casino.dto.*;
import com.ego.casino.entity.AccountEntity;
import com.ego.casino.entity.UserEntity;
import com.ego.casino.enums.TransactionType;
import com.ego.casino.exception.AccountNotFoundException;
import com.ego.casino.exception.InvalidDepositAmountException;
import com.ego.casino.exception.ResourceNotFoundException;
import com.ego.casino.repository.AccountRepository;
import com.ego.casino.security.CustomUserDetails;
import com.ego.casino.service.AccountService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private TransactionServiceImpl transactionService;

    @Override
    public AccountDto getBalance(CustomUserDetails userDetails, Long accountId) {
        UserEntity userEntity = userService.getUserByEmail(userDetails.getEmail());
        AccountEntity account = findAccountByUserId(userEntity, accountId)
                .orElseThrow(() -> new AccountNotFoundException("Account not found for this user"));

        return new AccountDto(account.getId(), account.getBalance());
    }

    @Override
    @Transactional
    public DepositResponseDto deposit(CustomUserDetails userDetails, Long accountId, BigDecimal amount, TransactionType transactionType) {
        if(amount.compareTo(BigDecimal.ZERO) < 0){
            throw new InvalidDepositAmountException("Deposit amount must be greater than zero");
        }

        UserEntity userEntity = userService.getUserByEmail(userDetails.getEmail());
        AccountEntity account = findAccountByUserId(userEntity, accountId)
                .orElseThrow(() -> new AccountNotFoundException("Account not found for this user"));

        account.setBalance(account.getBalance().add(amount));
        account.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        accountRepository.save(account);

        transactionService.createTransaction(account, amount, account.getBalance(), transactionType, LocalDateTime.now());
        return new DepositResponseDto(account.getId(),transactionType, LocalDateTime.now(),account.getBalance());
    }

    @Override
    @Transactional
    public WithdrawResponseDto withdraw(CustomUserDetails userDetails ,Long accountId, BigDecimal amount, TransactionType transactionType) {
        if(amount.compareTo(BigDecimal.ZERO) < 0){
            throw new InvalidDepositAmountException("Withdraw amount must be greater than zero");
        }

        UserEntity userEntity = userService.getUserByEmail(userDetails.getEmail());
        AccountEntity account = findAccountByUserId(userEntity, accountId)
                .orElseThrow(() -> new AccountNotFoundException("Account not found for this user"));

        account.setBalance(account.getBalance().subtract(amount));
        account.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        transactionService.createTransaction(account, amount, account.getBalance(), transactionType, LocalDateTime.now());
        return new WithdrawResponseDto(account.getId(),transactionType, LocalDateTime.now(),account.getBalance());

    }

    @Override
    public List<AccountDto> getAllAccounts(CustomUserDetails userEntity) {
        UserEntity user = userService.getUserByEmail(userEntity.getEmail());
        return accountRepository
                .findAccountEntitiesByUserId(user)
                .stream()
                .map(accountEntity -> new AccountDto(
                        accountEntity.getId(),
                        user,
                        accountEntity.getBalance(),
                        accountEntity.getCreatedAt(),
                        accountEntity.getUpdatedAt()
                ))
                .collect(Collectors.toList());
    }

    @Override
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


    public Optional<AccountEntity> findAccountByUserId(UserEntity userId, Long accountId) {
        return accountRepository.findAccountEntitiesByUserIdAndId(userId, accountId);
    }

    public void updateBalance(AccountEntity accountEntity, BigDecimal newBalance){
        accountEntity.setBalance(newBalance);
        accountRepository.save(accountEntity);
    }

    public List<TransactionHistoryDto> getTransactionHistory(CustomUserDetails userEntity, Long accountId) {
        return transactionService.getHistory(userEntity, accountId);
    }


}
