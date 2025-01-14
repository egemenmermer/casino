package com.ego.casino.dto;

public class LoginResponseDto {

    private String message;

    public LoginResponseDto(String message) {
        this.message = message;
    }

    public LoginResponseDto() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String token) {
        this.message = token;
    }
}
