package com.ego.casino.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.math.BigDecimal;


@Builder
@Log4j2
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlayGameResponseDto {

    private String message;
    private BigDecimal oldBalance;
    private BigDecimal newBalance;

    public PlayGameResponseDto(String message, BigDecimal oldBalance, BigDecimal newBalance) {
        this.message = message;
        this.oldBalance = oldBalance;
        this.newBalance = newBalance;
    }

    public PlayGameResponseDto(String message){
        this.message = message;
    }

    public PlayGameResponseDto() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BigDecimal getOldBalance() {
        return oldBalance;
    }

    public void setOldBalance(BigDecimal oldBalance) {
        this.oldBalance = oldBalance;
    }

    public BigDecimal getNewBalance() {
        return newBalance;
    }

    public void setNewBalance(BigDecimal newBalance) {
        this.newBalance = newBalance;
    }
}
