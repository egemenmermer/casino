package com.ego.casino.dto;

import java.math.BigDecimal;

public class WithdrawRequestDto {
    private BigDecimal amount;

    public WithdrawRequestDto(BigDecimal amount) {
        this.amount = amount;
    }

    public WithdrawRequestDto() {
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
