package com.ego.casino.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Log4j2
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DepositDto {

    private Long id;
    private String username;
    private BigDecimal balance;
    private BigDecimal depositAmount;
}
