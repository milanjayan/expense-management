package com.scaler.expensemanagement.commands;

import com.scaler.expensemanagement.controllers.ExpenseController;
import com.scaler.expensemanagement.controllers.UserExpenseController;
import com.scaler.expensemanagement.dtos.CreateUserExpenseRequest;
import com.scaler.expensemanagement.exceptions.InvalidCredentialsException;
import com.scaler.expensemanagement.services.UserExpenseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class CreateUserExpenseCommand implements Command{

    private UserExpenseController userExpenseController;

    @Override
    public Boolean match(String input) {
        return getCommand(input).equals(Commands.CREATE_USER_EXPENSE_COMMAND);
    }

    @Override
    public void execute(String input) {
        //command >> create-user-expense {userId} {amount} {lend/owe} {expenseId}
        List<String> tokens = getTokens(input);
        validate(tokens);
        CreateUserExpenseRequest request = CreateUserExpenseRequest.builder()
                .userId(Long.valueOf(tokens.get(1)))
                .amount(Double.valueOf(tokens.get(2)))
                .expenseType(tokens.get(3))
                .expenseId(Long.valueOf(tokens.get(4)))
                .build();
        userExpenseController.createUserExpense(request);
        System.out.println("User Expense added");
    }

    private void validate(List<String> tokens) {
        if(tokens.size() != 5) {
            throw new InvalidCredentialsException("Credentials missing for add-user-expense command");
        }
    }
}
