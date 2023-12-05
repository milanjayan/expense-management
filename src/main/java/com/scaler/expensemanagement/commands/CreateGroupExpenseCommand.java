package com.scaler.expensemanagement.commands;

import com.scaler.expensemanagement.controllers.GroupExpenseController;
import com.scaler.expensemanagement.dtos.CreateGroupExpenseRequest;
import com.scaler.expensemanagement.exceptions.InvalidCredentialsException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@AllArgsConstructor
public class CreateGroupExpenseCommand implements Command {

    private GroupExpenseController groupExpenseController;
    @Override
    public Boolean match(String input) {
        return getCommand(input).equals(Commands.CREATE_GROUP_EXPENSE_COMMAND);
    }

    @Override
    public void execute(String input) {
        //Command >> create-group-expense {groupId} {descri} {amount} {creatorId} {usersIds,.,.}
        List<String> tokens = getTokens(input);
        validate(tokens);
        CreateGroupExpenseRequest request = CreateGroupExpenseRequest.builder()
                .groupId(Long.valueOf(tokens.get(1)))
                .description(tokens.get(2))
                .amount(Double.valueOf(tokens.get(3)))
                .creatorId(Long.valueOf(tokens.get(4)))
                .usersIds(Arrays.stream(tokens.get(5).split(",")).map(Long::valueOf).toList())
                .build();
        groupExpenseController.createGroupExpense(request);
        System.out.println("Group expense created");
    }

    private void validate(List<String> tokens) {
        if(tokens.size() != 6) {
            throw new InvalidCredentialsException("Credentials missing for create-group-expense command");
        }
    }
}
