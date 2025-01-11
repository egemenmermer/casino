package com.ego.casino.dto;

import com.ego.casino.enums.TransactionType;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@JsonInclude(JsonInclude.Include.NON_NULL)

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
