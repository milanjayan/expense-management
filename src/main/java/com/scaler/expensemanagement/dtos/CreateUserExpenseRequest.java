package com.scaler.expensemanagement.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class CreateUserExpenseRequest {
    private Long userId;
    private Double amount;
    private String expenseType;
    private Long expenseId;
}
