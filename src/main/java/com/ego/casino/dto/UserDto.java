package com.ego.casino.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Log4j2
public class UserDto {

    private long id;
    private String username;
    private BigDecimal balance;

    public UserDto(String username, BigDecimal balance) {
        this.username = username;
        this.balance = balance;
    }

}
