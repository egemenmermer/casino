package com.ego.casino.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Log4j2
public class PlayGameRequestDto {

    private String username;
    private String gameName;
    private double betAmount;
    private String message;
}
