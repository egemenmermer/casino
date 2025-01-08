package com.ego.casino.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.extern.log4j.Log4j2;


import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionDto {

    private Long id;
    private BigDecimal amount;
    private String transactionType;

    public TransactionDto(Long id, BigDecimal amount, String transactionType) {
        this.id = id;
        this.amount = amount;
        this.transactionType = transactionType;
    }

    public TransactionDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
}
