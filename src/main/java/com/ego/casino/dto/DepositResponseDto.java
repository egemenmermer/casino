package com.ego.casino.dto;

import com.ego.casino.entity.AccountEntity;
import com.ego.casino.enums.TransactionType;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DepositResponseDto {
    private Long id;
    private TransactionType transactionType;
    private LocalDateTime createdAt;
    private BigDecimal finalBalance;
    private AccountEntity account;

    public DepositResponseDto(Long id, TransactionType transactionType, LocalDateTime createdAt, BigDecimal finalBalance) {
        this.id = id;
        this.transactionType = transactionType;
        this.createdAt = createdAt;
        this.finalBalance = finalBalance;
    }

    public DepositResponseDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public BigDecimal getFinalBalance() {
        return finalBalance;
    }

    public void setFinalBalance(BigDecimal finalBalance) {
        this.finalBalance = finalBalance;
    }

    public AccountEntity getAccount() {
        return account;
    }

    public void setAccount(AccountEntity account) {
        this.account = account;
    }
}
