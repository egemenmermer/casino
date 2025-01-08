package com.ego.casino.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.math.BigDecimal;

@Builder
@Log4j2
@Entity
@Table(name = "game")
public class GameEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false, name = "name")
    private String name;

    @Column(nullable = false, name = "win_chance")
    private BigDecimal winChance;

    @Column(nullable = false, name = "min_amount")
    private BigDecimal minAmount;

    public GameEntity() {
    }

    public GameEntity(Long id, String gameName, BigDecimal winChance, BigDecimal minAmount) {
        this.id = id;
        this.name = gameName;
        this.winChance = winChance;
        this.minAmount = minAmount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getWinChance() {
        return winChance;
    }

    public void setWinChance(BigDecimal winChance) {
        this.winChance = winChance;
    }

    public BigDecimal getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(BigDecimal minAmount) {
        this.minAmount = minAmount;
    }
}
