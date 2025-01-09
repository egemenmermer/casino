package com.ego.casino.controller;

import com.ego.casino.dto.TransactionDto;
import com.ego.casino.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user/")
public class TransactionController {

    @Autowired
    private  TransactionService transactionService;


    @PutMapping("/{username}/balance/topup")
    public ResponseEntity<TransactionDto> deposit(@PathVariable String username, @RequestBody TransactionDto transactionDto) {

        TransactionDto topUpBalance = transactionService.deposit(username, transactionDto.getDepositAmount().doubleValue()).getBody();
        return ResponseEntity.ok(topUpBalance);

    }
}
