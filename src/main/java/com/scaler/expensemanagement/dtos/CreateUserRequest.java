package com.scaler.expensemanagement.dtos;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateUserRequest {
    private String name;
    private String email;
    private String phoneNumber;
    private String password;
}
