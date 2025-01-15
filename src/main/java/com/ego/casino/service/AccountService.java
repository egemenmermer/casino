package com.ego.casino.service;

import com.ego.casino.dto.*;
import com.ego.casino.entity.UserEntity;
import com.ego.casino.enums.TransactionType;
import com.ego.casino.security.CustomUserDetails;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {

    //public ResponseEntity<UserDto> retrieveAccounts(Long id);
    public AccountDto getBalance(CustomUserDetails userDetails, Long id);
    //public ResponseEntity<AccountDto> updateBalance(Long id, AccountDto accountDto);
    public DepositResponseDto deposit(CustomUserDetails userDetails, Long id, BigDecimal amount, TransactionType transactionType);
    public WithdrawResponseDto withdraw(CustomUserDetails userDetails, Long id, BigDecimal amount, TransactionType transactionType);
    public List<AccountDto> getAllAccounts(CustomUserDetails userEntity);
    AccountCreateResponseDto createAccount(CustomUserDetails user);
}
