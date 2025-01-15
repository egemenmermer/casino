package com.ego.casino.controller;

import com.ego.casino.dto.*;
import com.ego.casino.enums.TransactionType;
import com.ego.casino.security.CurrentUser;
import com.ego.casino.security.CustomUserDetails;
import com.ego.casino.service.Impl.AccountServiceImpl;
import com.ego.casino.service.Impl.TransactionServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/account")
public class AccountController {

    @Autowired
    private TransactionServiceImpl transactionService;

    @Autowired
    private AccountServiceImpl accountService;

    @Operation(summary = "Deposit", description = "Deposit by AccountID")
    @PostMapping("/{account_id}/deposit")
    public ResponseEntity<DepositResponseDto> deposit(@CurrentUser CustomUserDetails currentUser, @PathVariable Long account_id, @RequestBody DepositRequestDto depositRequestDto) {

        DepositResponseDto depositResponseDto = accountService.deposit(currentUser, account_id , BigDecimal.valueOf(depositRequestDto.getAmount().doubleValue()), TransactionType.DEPOSIT);
        return ResponseEntity.ok(depositResponseDto);
    }

    @Operation(summary = "Withdraw", description = "Withdraw by AccountID")
    @PostMapping("/{account_id}/withdraw")
    public ResponseEntity<WithdrawResponseDto> withdraw(@CurrentUser CustomUserDetails currentUser, @PathVariable Long account_id, @RequestBody WithdrawRequestDto withdrawRequestDto) {

        WithdrawResponseDto withdrawResponseDto = accountService.withdraw(currentUser, account_id, BigDecimal.valueOf(withdrawRequestDto.getAmount().doubleValue()), TransactionType.WITHDRAW);
        return ResponseEntity.ok(withdrawResponseDto);
    }

    @Operation(summary = "Get Account")
    @GetMapping("/{account_id}")
    public ResponseEntity<AccountDto> getBalance(@CurrentUser CustomUserDetails userDetails, @PathVariable Long account_id) {

        return ResponseEntity.ok(accountService.getBalance(userDetails, account_id));
    }

    @Operation(summary = "Get Transaction History")
    @GetMapping("/{account_id}/transactions")
    public ResponseEntity<List<TransactionHistoryDto>> getTransactionHistory(@CurrentUser CustomUserDetails userDetails, @PathVariable Long account_id) {

        return ResponseEntity.ok(accountService.getTransactionHistory(userDetails, account_id));
    }
    @Operation(summary = "Create Account")
    @PostMapping("/create")
    public ResponseEntity<AccountCreateResponseDto> createAccount(@CurrentUser CustomUserDetails userDetails) {
        return ResponseEntity.ok(accountService.createAccount(userDetails));
    }

    @GetMapping("/")
    @Operation(summary = "Get All Accounts")
    public ResponseEntity<List<AccountDto>> getAllAccounts(@CurrentUser CustomUserDetails currentUser) {
        return ResponseEntity.ok(accountService.getAllAccounts(currentUser));
    }
}
