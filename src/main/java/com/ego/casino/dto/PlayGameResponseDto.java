package com.ego.casino.dto;

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
public class PlayGameResponseDto {

    private String message;
    private BigDecimal oldBalance;
    private BigDecimal newBalance;
}
