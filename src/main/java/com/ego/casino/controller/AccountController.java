package com.ego.casino.controller;

import com.ego.casino.dto.*;
import com.ego.casino.enums.TransactionType;
import com.ego.casino.exception.*;
import com.ego.casino.security.CurrentUser;
import com.ego.casino.security.CustomUserDetails;
import com.ego.casino.service.Impl.AccountServiceImpl;
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
    private AccountServiceImpl accountService;

    @PostMapping("/{account_id}/deposit")
    @Operation(summary = "Deposit", description = "Deposit by AccountID")
    public ResponseEntity<DepositResponseDto> deposit(
            @CurrentUser CustomUserDetails currentUser,
            @PathVariable Long account_id,
            @RequestBody DepositRequestDto depositRequestDto) {
        try {
            DepositResponseDto depositResponseDto = accountService.deposit(currentUser, account_id , BigDecimal.valueOf(depositRequestDto.getAmount().doubleValue()), TransactionType.DEPOSIT);
            return ResponseEntity.ok(depositResponseDto);
        } catch (Exception e) {
            throw new DepositException("Failed to deposit: " + e.getMessage());
        }
    }

    @PostMapping("/{account_id}/withdraw")
    @Operation(summary = "Withdraw", description = "Withdraw by AccountID")
    public ResponseEntity<WithdrawResponseDto> withdraw(
            @CurrentUser CustomUserDetails currentUser,
            @PathVariable Long account_id,
            @RequestBody WithdrawRequestDto withdrawRequestDto) {
        try {
            WithdrawResponseDto withdrawResponseDto = accountService.withdraw(currentUser, account_id, BigDecimal.valueOf(withdrawRequestDto.getAmount().doubleValue()), TransactionType.WITHDRAW);
            return ResponseEntity.ok(withdrawResponseDto);
        } catch (Exception e) {
            throw new WithdrawException("Failed to withdraw: " + e.getMessage());
        }
    }

    @GetMapping("/{account_id}")
    @Operation(summary = "Get Account")
    public ResponseEntity<AccountDto> getBalance(
            @CurrentUser CustomUserDetails userDetails,
            @PathVariable Long account_id) {
        try {
            return ResponseEntity.ok(accountService.getBalance(userDetails, account_id));
        } catch (Exception e) {
            throw new AccountNotFoundException("Account not found for ID: " + account_id);
        }
    }

    @GetMapping("/{account_id}/transactions")
    @Operation(summary = "Get Transaction History")
    public ResponseEntity<List<TransactionHistoryDto>> getTransactionHistory(
            @CurrentUser CustomUserDetails userDetails,
            @PathVariable Long account_id) {
        try {
            return ResponseEntity.ok(accountService.getTransactionHistory(userDetails, account_id));
        } catch (Exception e) {
            throw new TransactionHistoryException("Failed to fetch transaction history: " + e.getMessage());
        }
    }

    @PostMapping("/create")
    @Operation(summary = "Create Account")
    public ResponseEntity<AccountCreateResponseDto> createAccount(
            @CurrentUser CustomUserDetails userDetails) {
        try {
            return ResponseEntity.ok(accountService.createAccount(userDetails));
        } catch (Exception e) {
            throw new AccountCreationException("Failed to create account: " + e.getMessage());
        }
    }

    @GetMapping("/")
    @Operation(summary = "Get All Accounts")
    public ResponseEntity<List<AccountDto>> getAllAccounts(
            @CurrentUser CustomUserDetails currentUser) {
        try {
            return ResponseEntity.ok(accountService.getAllAccounts(currentUser));
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve accounts: " + e.getMessage());
        }
    }
}
