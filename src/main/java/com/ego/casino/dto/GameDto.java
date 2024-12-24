package com.ego.casino.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Log4j2
public class GameDto {

    private Long id;
    private String gameName;
    private BigDecimal winChance;
    private BigDecimal minAmount;
}
