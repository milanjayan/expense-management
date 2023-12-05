package com.scaler.expensemanagement.services;

import com.scaler.expensemanagement.dtos.CreateExpenseRequest;
import com.scaler.expensemanagement.dtos.CreateUserExpenseRequest;
import com.scaler.expensemanagement.enums.ExpenseType;
import com.scaler.expensemanagement.enums.SettleStatus;
import com.scaler.expensemanagement.exceptions.*;
import com.scaler.expensemanagement.models.Expense;
import com.scaler.expensemanagement.models.User;
import com.scaler.expensemanagement.models.UserExpense;
import com.scaler.expensemanagement.repositories.ExpenseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@AllArgsConstructor
public class ExpenseService {
    private ExpenseRepository expenseRepository;
    private UserService userService;

    public Expense getExpense(Long id) {
        return expenseRepository.findById(id)
                .orElseThrow(() -> new ExpenseNotFoundException("Expense with id: "+id+" not found"));
    }
    public Expense createExpense(CreateExpenseRequest request) {
        User creator = userService.getUser(request.getCreatorId());
        if(request.getAmount() <= 0.0) {
            throw new InvalidExpenseAmountException("Expense amount should be greater than 0");
        }
        List<User> users = userService.getAllUsers(request.getUsersIds());
        //validating if users exists
        if(request.getUsersIds().size() != users.size()) {
            HashSet<Long> usersIds = new HashSet<>(users.stream().map(
                    user -> user.getId()
                    ).toList());
            for(Long id : request.getUsersIds()) {
                if(!usersIds.contains(id)) {
                    throw new UserNotFoundException("User with id: "+id+" not found");
                }
            }
        }
        users.add(creator);
        Expense expense = Expense.builder()
                .description(request.getDescription())
                .amount(request.getAmount())
                .createdBy(creator)
                .users(users)
                .status(SettleStatus.PENDING)
                .build();
        return expenseRepository.save(expense);
    }

    public Expense save(Expense expense) {
        return expenseRepository.save(expense);
    }
}
