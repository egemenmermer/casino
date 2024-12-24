package com.ego.casino.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.extern.log4j.Log4j2;


import java.math.BigDecimal;

@Log4j2
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DepositDto {

    private Long id;
    private String username;
    private BigDecimal balance;
    private BigDecimal depositAmount;


    public DepositDto(String username, BigDecimal balance, BigDecimal depositAmount) {
        this.username = username;
        this.balance = balance;
        this.depositAmount = depositAmount;
    }

    public DepositDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(BigDecimal depositAmount) {
        this.depositAmount = depositAmount;
    }
}
