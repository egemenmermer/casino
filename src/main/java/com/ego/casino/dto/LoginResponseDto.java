package com.ego.casino.dto;

public class LoginResponseDto {

    private String message;
    private String token;

    public LoginResponseDto(String message,  String token) {
        this.message = message;
        this.token = token;
    }

    public LoginResponseDto(String message){
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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
