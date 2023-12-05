package com.scaler.expensemanagement.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class CreateExpenseRequest {
    private String description;
    private Double amount;
    private Long creatorId;
    private List<Long> usersIds;
}
