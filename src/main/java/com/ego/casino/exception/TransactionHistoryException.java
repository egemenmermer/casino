package com.ego.casino.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class TransactionHistoryException extends RuntimeException {
    public TransactionHistoryException(String message) {
        super(message);
    }
}
