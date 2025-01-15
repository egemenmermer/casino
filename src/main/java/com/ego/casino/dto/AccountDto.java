package com.ego.casino.dto;

import com.ego.casino.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.sql.Timestamp;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountDto {
    private Long id;
    private UserEntity user;
    private Long userId;
    private BigDecimal balance;
    private Timestamp createdAt;
    private Timestamp updatedAt;


    public AccountDto(Long id, BigDecimal balance) {
        this.id = id;
        this.balance = balance;
    }

    public AccountDto(Long id, UserEntity user, BigDecimal balance, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.userId = user.getId();
        this.balance = balance;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public AccountDto() {
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
