package com.scaler.expensemanagement.commands;

import com.scaler.expensemanagement.controllers.UserGroupController;
import com.scaler.expensemanagement.dtos.CreateUserGroupRequest;
import com.scaler.expensemanagement.exceptions.InvalidCredentialsException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class CreateGroupCommand implements Command {

    private UserGroupController userGroupController;
    @Override
    public Boolean match(String input) {
        return getCommand(input).equals(Commands.CREATE_GROUP_COMMAND);
    }

    @Override
    public void execute(String input) {
        //command >> create-group trip {userId}
        List<String> tokens = getTokens(input);
        validate(tokens);
        CreateUserGroupRequest request = CreateUserGroupRequest.builder()
                .groupName(tokens.get(1))
                .creatorId(Long.valueOf(tokens.get(2)))
                .build();
        userGroupController.createGroup(request);
        System.out.println("Group created");
    }

    private void validate(List<String> tokens) {
        if(tokens.size() != 3) {
            throw new InvalidCredentialsException("Credentials missing for GROUP command");
        }
    }
}
