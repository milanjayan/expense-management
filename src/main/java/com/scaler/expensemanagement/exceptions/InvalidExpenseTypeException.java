package com.scaler.expensemanagement.exceptions;

public class InvalidExpenseTypeException extends RuntimeException {
    public InvalidExpenseTypeException(String message) {
        super(message);
    }
}
