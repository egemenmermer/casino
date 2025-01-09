package com.ego.casino.controller;

import com.ego.casino.dto.TransactionDto;
import com.ego.casino.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/account/")
public class AccountController {

    @Autowired
    private  TransactionService transactionService;


    @PutMapping("/{account_id}/deposit")
    public ResponseEntity<TransactionDto> deposit(@RequestHeader("X-USER-ID") Long id, @PathVariable Long accountId, @RequestBody TransactionDto transactionDto) {

        TransactionDto topUpBalance = transactionService.deposit(id, transactionDto.getAmount().doubleValue()).getBody();
        return ResponseEntity.ok(topUpBalance);
    }
}
