package com.ego.casino.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.extern.log4j.Log4j2;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Builder
@Log4j2
@Entity
@Table(name = "game_history_log")
public class GameHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false, name = "play_date")
    private Timestamp playDate;

    @Column(nullable = false, name = "bet_amount")
    private BigDecimal betAmount;

    @Column(nullable = false, name = "old_balance")
    private BigDecimal oldBalance;

    @Column(nullable = false, name = "new_balance")
    private BigDecimal newBalance;

    @Column(nullable = false, name = "status")
    private String status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id", nullable = false)
    private AccountEntity account;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "game_id", nullable = false)
    private GameEntity game;


    public GameHistoryEntity(Long id, Timestamp playDate, BigDecimal betAmount, BigDecimal oldBalance, BigDecimal newBalance, String status, AccountEntity account, GameEntity game) {
        this.id = id;
        this.playDate = playDate;
        this.betAmount = betAmount;
        this.oldBalance = oldBalance;
        this.newBalance = newBalance;
        this.status = status;
        this.account = account;
        this.game = game;
    }

    public GameHistoryEntity() {
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

    public AccountEntity getAccount() {
        return account;
    }

    public void setAccount(AccountEntity account) {
        this.account = account;
    }

    public GameEntity getGame() {
        return game;
    }

    public void setGame(GameEntity game) {
        this.game = game;
    }
}