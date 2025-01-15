package com.ego.casino.dto;

public class AccountCreateRequestDto {

    private String username;

    public AccountCreateRequestDto(String username) {
        this.username = username;
    }

    public AccountCreateRequestDto() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
