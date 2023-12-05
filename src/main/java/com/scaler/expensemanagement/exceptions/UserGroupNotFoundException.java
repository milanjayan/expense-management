package com.scaler.expensemanagement.exceptions;

public class UserGroupNotFoundException extends RuntimeException {
    public UserGroupNotFoundException(String message) {
        super(message);
    }
}
