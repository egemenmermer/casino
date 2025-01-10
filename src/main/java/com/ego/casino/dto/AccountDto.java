package com.ego.casino.dto;

import com.ego.casino.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountDto {
    private Long id;
    private BigDecimal balance;
    private UserEntity user;


    public AccountDto(Long id, BigDecimal balance) {
        this.id = id;
        this.balance = balance;
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
