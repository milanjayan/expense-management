package com.scaler.expensemanagement.exceptions;

public class UserNotPartOfExpenseException extends RuntimeException {
    public UserNotPartOfExpenseException(String message) {
        super(message);
    }
}
