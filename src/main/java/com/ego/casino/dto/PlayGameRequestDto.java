package com.ego.casino.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Builder
@Log4j2
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlayGameRequestDto {

    private String username;
    private String gameName;
    private double betAmount;
    private String message;

    public PlayGameRequestDto(String username, String gameName, double betAmount, String message) {
        this.username = username;
        this.gameName = gameName;
        this.betAmount = betAmount;
        this.message = message;
    }

    public PlayGameRequestDto() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public double getBetAmount() {
        return betAmount;
    }

    public void setBetAmount(double betAmount) {
        this.betAmount = betAmount;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
