package com.ego.casino.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class WithdrawException extends RuntimeException {
    public WithdrawException(String message) {
        super(message);
    }
}
