package com.ego.casino.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GameHistoryDto {

    private Long id;
    private String gameName;
    private Timestamp playDate;
    private BigDecimal betAmount;
    private BigDecimal oldBalance;
    private BigDecimal newBalance;


    public GameHistoryDto() {
    }

    public GameHistoryDto(Long id, String gameName, Timestamp playDate, BigDecimal betAmount, BigDecimal oldBalance, BigDecimal newBalance) {
        this.id = id;
        this.gameName = gameName;
        this.playDate = playDate;
        this.betAmount = betAmount;
        this.oldBalance = oldBalance;
        this.newBalance = newBalance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public Timestamp getPlayDate() {
        return playDate;
    }

    public void setPlayDate(Timestamp playDate) {
        this.playDate = playDate;
    }

    public BigDecimal getBetAmount() {
        return betAmount;
    }

    public void setBetAmount(BigDecimal betAmount) {
        this.betAmount = betAmount;
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
