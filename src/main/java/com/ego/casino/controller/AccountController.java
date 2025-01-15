package com.ego.casino.controller;

import com.ego.casino.dto.*;
import com.ego.casino.enums.TransactionType;
import com.ego.casino.service.Impl.AccountServiceImpl;
import com.ego.casino.service.Impl.TransactionServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/account/")
public class AccountController {

    @Autowired
    private TransactionServiceImpl transactionService;

    @Autowired
    private AccountServiceImpl accountService;

    @Operation(summary = "Deposit", description = "Deposit by AccountID")
    @PostMapping("/{account_id}/deposit")
    public ResponseEntity<DepositResponseDto> deposit(@RequestHeader("X-USER-ID") Long userId, @PathVariable Long accountID, @RequestBody DepositRequestDto depositRequestDto) {

        DepositResponseDto depositResponseDto = accountService.deposit(accountID, BigDecimal.valueOf(depositRequestDto.getAmount().doubleValue()), TransactionType.DEPOSIT);
        return ResponseEntity.ok(depositResponseDto);
    }

    @Operation(summary = "Withdraw", description = "Withdraw by AccountID")
    @PostMapping("/{account_id}/withdraw")
    public ResponseEntity<WithdrawResponseDto> withdraw(@RequestHeader("X-USER-ID") Long userId, @PathVariable Long accountId, @RequestBody WithdrawRequestDto withdrawRequestDto) {

        WithdrawResponseDto withdrawResponseDto = accountService.withdraw(accountId, BigDecimal.valueOf(withdrawRequestDto.getAmount().doubleValue()), TransactionType.WITHDRAW);
        return ResponseEntity.ok(withdrawResponseDto);
    }

    @Operation(summary = "Get Account")
    @GetMapping("/{account_id}")
    public ResponseEntity<AccountDto> getAccount(@RequestHeader("X-USER-ID") Long userId, @PathVariable Long account_id) {

        return ResponseEntity.ok(accountService.getBalance(account_id));
    }

    @Operation(summary = "Get Transaction History")
    @GetMapping("/{account_id}/transactions")
    public ResponseEntity<List<TransactionHistoryDto>> getTransactionHistory(@RequestHeader("X-USER-ID") Long userId, @PathVariable Long account_id) {

        return ResponseEntity.ok(transactionService.getHistory(account_id));
    }
}
