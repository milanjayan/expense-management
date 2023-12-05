package com.scaler.expensemanagement.commands;

import com.scaler.expensemanagement.controllers.UserGroupController;
import com.scaler.expensemanagement.dtos.AddGroupUsersRequest;
import com.scaler.expensemanagement.exceptions.InvalidCredentialsException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@AllArgsConstructor
public class AddGroupMembersCommand implements Command {

    private UserGroupController userGroupController;

    @Override
    public Boolean match(String input) {
        return getCommand(input).equals(Commands.ADD_GROUP_MEMBERS_COMMAND);
    }

    @Override
    public void execute(String input) {
        //Command >> add-group-members {group_id} {mem_id},{mem_id},{mem_id}
        List<String> tokens = getTokens(input);
        validate(tokens);
        AddGroupUsersRequest request = AddGroupUsersRequest.builder()
                .groupId(Long.valueOf(tokens.get(1)))
                .userIds(Arrays.stream(tokens.get(2).split(",")).mapToLong(Long::valueOf).boxed().toList())
                .build();
        userGroupController.addGroupMembers(request);
        System.out.println("Group members added");
    }

    private void validate(List<String> tokens) {
        if(tokens.size() != 3) {
            throw new InvalidCredentialsException("Credentials missing for add-group-members command");
        }
    }
}
