package com.ego.casino.dto;

public class AccountCreateResponseDto {

    private String message;


    public AccountCreateResponseDto(String message) {
        this.message = message;
    }

    public AccountCreateResponseDto() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
