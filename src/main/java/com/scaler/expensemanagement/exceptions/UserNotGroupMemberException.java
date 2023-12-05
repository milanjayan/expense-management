package com.scaler.expensemanagement.exceptions;

public class UserNotGroupMemberException extends RuntimeException {
    public UserNotGroupMemberException(String message) {
        super(message);
    }
}
