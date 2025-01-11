package com.ego.casino.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlayGameRequestDto {

    private Long id;
    private double betAmount;

    public PlayGameRequestDto(Long id, double betAmount) {
        this.id = id;
        this.betAmount = betAmount;
    }

    public PlayGameRequestDto() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public double getBetAmount() {
        return betAmount;
    }

    public void setBetAmount(double betAmount) {
        this.betAmount = betAmount;
    }



}
