package com.scaler.expensemanagement.controllers;

import com.scaler.expensemanagement.dtos.CreateUserExpenseRequest;
import com.scaler.expensemanagement.models.UserExpense;
import com.scaler.expensemanagement.services.UserExpenseService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user-expense")
@AllArgsConstructor
public class UserExpenseController {
    private UserExpenseService userExpenseService;

    public UserExpense createUserExpense(CreateUserExpenseRequest request) {
        return userExpenseService.createUserExpense(request);
    }
}
