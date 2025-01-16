package com.ego.casino.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.EXPECTATION_FAILED)
public class AccountCreationException extends RuntimeException {
    public AccountCreationException(String message) {
        super(message);
    }
}
