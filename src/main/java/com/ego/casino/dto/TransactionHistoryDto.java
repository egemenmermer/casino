package com.ego.casino.dto;

import com.ego.casino.entity.AccountEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class TransactionHistoryDto {

    private Long id;
    private AccountEntity account;
    private BigDecimal amount;
    private BigDecimal finalBalance;
    private String kind;
    private Timestamp createdAt;

    public TransactionHistoryDto(Long id, AccountEntity account, BigDecimal amount, BigDecimal finalBalance, String kind, Timestamp createdAt) {
        this.id = id;
        this.account = account;
        this.amount = amount;
        this.finalBalance = finalBalance;
        this.kind = kind;
        this.createdAt = createdAt;
    }

    public TransactionHistoryDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AccountEntity getAccount() {
        return account;
    }

    public void setAccount(AccountEntity account) {
        this.account = account;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getFinalBalance() {
        return finalBalance;
    }

    public void setFinalBalance(BigDecimal finalBalance) {
        this.finalBalance = finalBalance;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
