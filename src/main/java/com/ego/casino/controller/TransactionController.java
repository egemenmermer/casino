package com.ego.casino.controller;

import com.ego.casino.dto.DepositDto;
import com.ego.casino.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user/")
public class TransactionController {

    @Autowired
    private  TransactionService transactionService;


    @PutMapping("/{username}/balance/topup")
    public ResponseEntity<DepositDto> deposit(@PathVariable String username, @RequestBody DepositDto depositDto ) {

        DepositDto topUpBalance = transactionService.topUpBalance(username, depositDto.getDepositAmount().doubleValue()).getBody();
        return ResponseEntity.ok(topUpBalance);

    }
}
