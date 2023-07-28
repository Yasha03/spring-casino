package ru.itis.springbackend.exception;

public class NotEnoughFundsToPlaySlotsGameException extends NotEnoughFundsException{
    public NotEnoughFundsToPlaySlotsGameException(long difference) {
        super(String.format("Not enough funds, you need %d", difference));
    }
}
