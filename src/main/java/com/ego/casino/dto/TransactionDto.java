package com.ego.casino.dto;

import com.ego.casino.entity.AccountEntity;
import com.ego.casino.enums.TransactionType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.extern.log4j.Log4j2;


import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionDto {

    private Long id;
    private BigDecimal amount;
    private TransactionType transactionType;
    private LocalDateTime createdAt;
    private AccountEntity account;

    public TransactionDto(Long id, BigDecimal amount, TransactionType transactionType, BigDecimal balance , LocalDateTime createdAt) {
        this.id = id;
        this.amount = amount;
        this.account.setBalance(balance);
        this.transactionType = transactionType;
        this.createdAt = createdAt;
    }

    public AccountEntity getAccount() {
        return account;
    }

    public void setAccount(AccountEntity account) {
        this.account = account;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public TransactionDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }
}
