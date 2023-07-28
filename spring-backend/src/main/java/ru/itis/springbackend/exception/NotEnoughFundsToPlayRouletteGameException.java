package ru.itis.springbackend.exception;

public class NotEnoughFundsToPlayRouletteGameException extends NotEnoughFundsException{
    public NotEnoughFundsToPlayRouletteGameException(long difference) {
        super(String.format("Not enough funds, you need %d", difference));
    }
}
