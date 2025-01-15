package com.ego.casino.service;

import com.ego.casino.dto.AccountDto;
import com.ego.casino.dto.DepositResponseDto;
import com.ego.casino.dto.UserDto;
import com.ego.casino.dto.WithdrawResponseDto;
import com.ego.casino.enums.TransactionType;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

public interface AccountService {

    //public ResponseEntity<UserDto> retrieveAccounts(Long id);
    public AccountDto getBalance(Long id);
    //public ResponseEntity<AccountDto> updateBalance(Long id, AccountDto accountDto);
    public DepositResponseDto deposit(Long id, BigDecimal amount, TransactionType transactionType);
    public WithdrawResponseDto withdraw(Long id, BigDecimal amount, TransactionType transactionType);

}
