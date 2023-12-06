package com.scaler.expensemanagement.controllers;

import com.scaler.expensemanagement.dtos.CreateExpenseRequest;
import com.scaler.expensemanagement.dtos.CreateUserExpenseRequest;
import com.scaler.expensemanagement.dtos.SettlementDto;
import com.scaler.expensemanagement.models.Expense;
import com.scaler.expensemanagement.models.UserExpense;
import com.scaler.expensemanagement.services.ExpenseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("expense")
@AllArgsConstructor
public class ExpenseController {
    private ExpenseService expenseService;

    @GetMapping("{id}")
    public Expense getExpense(@PathVariable Long id) {
        return expenseService.getExpense(id);
    }
    public Expense createExpense(CreateExpenseRequest request) {
        return expenseService.createExpense(request);
    }
    public List<SettlementDto> settleUp(Long expenseId) {
        return expenseService.settleUp(expenseId);
    }
}
