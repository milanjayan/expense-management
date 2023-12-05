package com.scaler.expensemanagement.commands;

import com.scaler.expensemanagement.controllers.ExpenseController;
import com.scaler.expensemanagement.dtos.CreateExpenseRequest;
import com.scaler.expensemanagement.exceptions.InvalidCredentialsException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@AllArgsConstructor
public class CreateExpenseCommand implements Command {

    private ExpenseController expenseController;

    @Override
    public Boolean match(String input) {
        return getCommand(input).equals(Commands.CREATE_EXPENSE_COMMAND);
    }

    @Override
    public void execute(String input) {
        //command >> create-expense dinner 1000 3 1,2
        List<String> tokens = getTokens(input);
        validate(tokens);
        CreateExpenseRequest request = CreateExpenseRequest.builder()
                .description(tokens.get(1))
                .amount(Double.valueOf(tokens.get(2)))
                .creatorId(Long.valueOf(tokens.get(3)))
                .usersIds(Arrays.stream(tokens.get(4).split(",")).map(Long::valueOf).toList())
                .build();
        expenseController.createExpense(request);
        System.out.println("Expense created");
    }

    private void validate(List<String> tokens) {
        if(tokens.size() != 5) {
            throw new InvalidCredentialsException("Credentials missing for create-expense command");
        }
    }
}
