package com.ego.casino.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
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
