package com.ego.casino.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidBetAmountException extends RuntimeException {
    public InvalidBetAmountException(String message) {
        super(message);
    }
}
