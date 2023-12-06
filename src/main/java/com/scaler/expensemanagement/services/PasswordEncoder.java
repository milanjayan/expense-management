package com.scaler.expensemanagement.services;

public interface PasswordEncoder {
    String encode(String rawPassword);
    boolean matches(String password, String hashedPassword);
}
