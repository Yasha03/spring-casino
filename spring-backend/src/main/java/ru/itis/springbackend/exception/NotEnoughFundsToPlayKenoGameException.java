package ru.itis.springbackend.exception;

public class NotEnoughFundsToPlayKenoGameException extends NotEnoughFundsException {
    public NotEnoughFundsToPlayKenoGameException(long difference) {
        super(String.format("Not enough funds, you need %d", difference));
    }
}
