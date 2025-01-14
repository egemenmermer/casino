package com.ego.casino.dto;

public class ActivationResponseDto {

    private String message;

    public ActivationResponseDto(String message) {
        this.message = message;
    }

    public ActivationResponseDto() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
