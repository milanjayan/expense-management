package com.scaler.expensemanagement.controllers;

import com.scaler.expensemanagement.dtos.CreateExpenseRequest;
import com.scaler.expensemanagement.dtos.CreateGroupExpenseRequest;
import com.scaler.expensemanagement.models.GroupExpense;
import com.scaler.expensemanagement.services.ExpenseService;
import com.scaler.expensemanagement.services.GroupExpenseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class GroupExpenseController {
    private GroupExpenseService groupExpenseService;

    public GroupExpense createGroupExpense(CreateGroupExpenseRequest request) {
        return groupExpenseService.createGroupExpense(request);
    }
}
