package com.ego.casino.dto;

import com.ego.casino.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class DepositRequestDto {
    private BigDecimal amount;

    public DepositRequestDto(BigDecimal amount) {
        this.amount = amount;
    }

    public DepositRequestDto() {
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
