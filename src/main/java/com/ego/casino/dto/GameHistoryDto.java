package com.ego.casino.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GameHistoryDto {

    private Long id;
    private String gameName;
    private Timestamp playDate;
    private BigDecimal betAmount;
    private BigDecimal oldBalance;
    private BigDecimal newBalance;

}
