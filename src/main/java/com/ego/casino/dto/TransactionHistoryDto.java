package com.ego.casino.dto;

import com.ego.casino.entity.AccountEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionHistoryDto {

    private Long id;
    private AccountEntity account;
    private BigDecimal profit;
    private String username;
    private Long accountId;
    private String kind;
    private Timestamp createdAt;

    public TransactionHistoryDto(Long id, AccountEntity account, BigDecimal profit, String kind, Timestamp createdAt) {
        this.id = id;
        this.accountId = account.getId();
        this.username = account.getUserId().getUsername();
        this.profit = profit;
        this.kind = kind;
        this.createdAt = createdAt;
    }

    public TransactionHistoryDto() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
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



    public BigDecimal getProfit() {
        return profit;
    }

    public void setProfit(BigDecimal finalBalance) {
        this.profit = profit;
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
