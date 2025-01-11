package com.ego.casino.controller;

import com.ego.casino.dto.*;
import com.ego.casino.entity.AccountEntity;
import com.ego.casino.enums.TransactionType;
import com.ego.casino.service.AccountService;
import com.ego.casino.service.Impl.AccountServiceImpl;
import com.ego.casino.service.Impl.TransactionServiceImpl;
import com.ego.casino.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/account/")
public class AccountController {

    @Autowired
    private TransactionServiceImpl transactionService;

    @Autowired
    private AccountServiceImpl accountService;


    @PostMapping("/{account_id}/deposit")
    public ResponseEntity<DepositResponseDto> deposit(@RequestHeader("X-USER-ID") Long id, @PathVariable Long account_id, @RequestBody DepositRequestDto depositRequestDto) {

        DepositResponseDto depositResponseDto = accountService.deposit(id, BigDecimal.valueOf(depositRequestDto.getAmount().doubleValue()), TransactionType.DEPOSIT).getBody();
        return ResponseEntity.ok(depositResponseDto);
    }

    @PostMapping("/{account_id}/withdraw")
    public ResponseEntity<WithdrawResponseDto> withdraw(@RequestHeader("X-USER-ID") Long id, @PathVariable Long account_id, @RequestBody WithdrawRequestDto withdrawRequestDto) {

        WithdrawResponseDto withdrawResponseDto = accountService.withdraw(id, BigDecimal.valueOf(withdrawRequestDto.getAmount().doubleValue()), TransactionType.WITHDRAW).getBody();
        return ResponseEntity.ok(withdrawResponseDto);
    }

    @GetMapping("/{account_id}")
    public ResponseEntity<AccountDto> getAccount(@RequestHeader("X-USER-ID") Long id, @PathVariable Long account_id) {

        return ResponseEntity.ok(accountService.getBalance(account_id).getBody());
    }

    @GetMapping("/{account_id}/transactions")
    public ResponseEntity<List<TransactionHistoryDto>> getTransactionHistory(@RequestHeader("X-USER-ID") Long id, @PathVariable Long account_id) {

        return ResponseEntity.ok(transactionService.getHistory(account_id));
    }
}
