package com.ego.casino.dto;

public class RegisterResponseDto {

    private String message;
    private String token;


    public RegisterResponseDto(String token, String message) {
        this.token = token;
        this.message = message;
    }

    public RegisterResponseDto(String message){
        this.message = message;
    }

    public RegisterResponseDto() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
