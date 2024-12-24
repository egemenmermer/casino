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
public class HistoryDto {

    private Long id;
    private String game_name;
    private Timestamp play_date;
    private BigDecimal bet_amount;
    private BigDecimal old_balance;
    private BigDecimal new_balance;

}
