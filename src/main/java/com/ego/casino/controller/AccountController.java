package com.ego.casino.controller;

import com.ego.casino.dto.TransactionDto;
import com.ego.casino.enums.TransactionType;
import com.ego.casino.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/account/")
public class AccountController {

    @Autowired
    private  TransactionService transactionService;


    @PutMapping("/{account_id}/deposit")
    public ResponseEntity<TransactionDto> deposit(@RequestHeader("X-USER-ID") Long id, @PathVariable Long account_id, @RequestBody TransactionDto transactionDto) {

        TransactionDto topUpBalance = transactionService.transaction(id, BigDecimal.valueOf(transactionDto.getAmount().doubleValue()), TransactionType.DEPOSIT).getBody();
        return ResponseEntity.ok(topUpBalance);
    }

    @PutMapping("/{account_id}/withdraw")
    public ResponseEntity<TransactionDto> withdraw(@RequestHeader("X-USER-ID") Long id, @PathVariable Long account_id, @RequestBody TransactionDto transactionDto) {

        TransactionDto topUpBalance = transactionService.transaction(id, BigDecimal.valueOf(transactionDto.getAmount().doubleValue()), TransactionType.WITHDRAW).getBody();
        return ResponseEntity.ok(topUpBalance);
    }
}
