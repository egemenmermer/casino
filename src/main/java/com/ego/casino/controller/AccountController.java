package com.ego.casino.controller;

import com.ego.casino.dto.AccountDto;
import com.ego.casino.dto.TransactionDto;
import com.ego.casino.dto.TransactionHistoryDto;
import com.ego.casino.entity.AccountEntity;
import com.ego.casino.enums.TransactionType;
import com.ego.casino.service.AccountService;
import com.ego.casino.service.Impl.AccountServiceImpl;
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
    private  TransactionService transactionService;

    @Autowired
    private AccountServiceImpl accountService;


    @PostMapping("/{account_id}/deposit")
    public ResponseEntity<TransactionDto> deposit(@RequestHeader("X-USER-ID") Long id, @PathVariable Long account_id, @RequestBody TransactionDto transactionDto) {

        TransactionDto topUpBalance = transactionService.transaction(id, BigDecimal.valueOf(transactionDto.getAmount().doubleValue()), TransactionType.DEPOSIT).getBody();
        return ResponseEntity.ok(topUpBalance);
    }

    @PostMapping("/{account_id}/withdraw")
    public ResponseEntity<TransactionDto> withdraw(@RequestHeader("X-USER-ID") Long id, @PathVariable Long account_id, @RequestBody TransactionDto transactionDto) {

        TransactionDto topUpBalance = transactionService.transaction(id, BigDecimal.valueOf(transactionDto.getAmount().doubleValue()), TransactionType.WITHDRAW).getBody();
        return ResponseEntity.ok(topUpBalance);
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
