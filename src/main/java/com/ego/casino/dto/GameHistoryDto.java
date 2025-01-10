package com.ego.casino.dto;

import com.ego.casino.entity.AccountEntity;
import com.ego.casino.entity.GameEntity;
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
    private Timestamp playDate;
    private BigDecimal betAmount;
    private BigDecimal oldBalance;
    private BigDecimal newBalance;
    private String status;
    private String gameName;
    private GameEntity game;
    private AccountEntity account;

    public GameHistoryDto(Long id, Timestamp playDate, BigDecimal betAmount, BigDecimal oldBalance, BigDecimal newBalance, String status, GameEntity game) {
        this.id = id;
        this.playDate = playDate;
        this.betAmount = betAmount;
        this.oldBalance = oldBalance;
        this.newBalance = newBalance;
        this.status = status;
        this.gameName = game.getName();
    }

    public GameHistoryDto() {
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public GameEntity getGame() {
        return game;
    }

    public void setGame(GameEntity game) {
        this.game = game;
    }

    public AccountEntity getAccount() {
        return account;
    }

    public void setAccount(AccountEntity account) {
        this.account = account;
    }
}
