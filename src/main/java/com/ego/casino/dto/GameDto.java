package com.ego.casino.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.math.BigDecimal;

@Builder
@Log4j2
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GameDto {

    private Long id;
    private String name;
    private BigDecimal winChance;
    private BigDecimal minAmount;

    public GameDto(Long id, String name, BigDecimal winChance, BigDecimal minAmount) {
        this.id = id;
        this.name = name;
        this.winChance = winChance;
        this.minAmount = minAmount;
    }

    public GameDto() {
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
