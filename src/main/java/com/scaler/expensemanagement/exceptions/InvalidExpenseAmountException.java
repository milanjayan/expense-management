package com.scaler.expensemanagement.exceptions;

public class InvalidExpenseAmountException extends RuntimeException {
    public InvalidExpenseAmountException(String message) {
        super(message);
    }
}
