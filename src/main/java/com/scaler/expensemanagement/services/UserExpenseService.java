package com.scaler.expensemanagement.services;

import com.scaler.expensemanagement.dtos.CreateUserExpenseRequest;
import com.scaler.expensemanagement.enums.ExpenseType;
import com.scaler.expensemanagement.exceptions.UserNotPartOfExpenseException;
import com.scaler.expensemanagement.models.Expense;
import com.scaler.expensemanagement.models.User;
import com.scaler.expensemanagement.models.UserExpense;
import com.scaler.expensemanagement.repositories.UserExpenseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
@AllArgsConstructor
public class UserExpenseService {
    private UserExpenseRepository userExpenseRepository;
    private ExpenseService expenseService;

    public UserExpense createUserExpense(CreateUserExpenseRequest request) {
        Expense expense  = expenseService.getExpense(request.getExpenseId());
        HashSet<Long> usersIds = new HashSet<>(expense.getUsers().stream().map(
                user -> user.getId()
        ).toList());
        //validate if user part of expense users
        if(!usersIds.contains(request.getUserId())) {
            throw new UserNotPartOfExpenseException("User with id: "+request.getUserId()+" not part of Expense with id: "+expense.getId());
        }
        User user = null;
        for(User u: expense.getUsers()) {
            if(u.getId().equals(request.getUserId())) {
                user = u;
                break;
            }
        }
        String type = request.getExpenseType().toUpperCase();

        UserExpense userExpense = UserExpense.builder()
                .user(user)
                .amount(request.getAmount())
                .expenseType(ExpenseType.valueOf(type))
                .expense(expense)
                .build();
        return userExpenseRepository.save(userExpense);
    }
}
