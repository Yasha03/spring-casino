package ru.itis.springbackend.exception;

import org.springframework.http.HttpStatus;

public class NotEnoughFundsException extends ServiceException {
    public NotEnoughFundsException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
