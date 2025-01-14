package com.ego.casino.dto;

public class ActivationRequestDto {

    private String token;
    private String email;

    public ActivationRequestDto(String token, String email) {
        this.token = token;
        this.email = email;
    }

    public ActivationRequestDto() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
