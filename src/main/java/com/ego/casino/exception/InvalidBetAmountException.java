package com.ego.casino.exception;

public class InvalidBetAmountException extends RuntimeException {
    public InvalidBetAmountException(String message) {
        super(message);
    }
}
