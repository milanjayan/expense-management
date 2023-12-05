package com.scaler.expensemanagement.commands;

import com.scaler.expensemanagement.controllers.UserController;
import com.scaler.expensemanagement.dtos.CreateUserRequest;
import com.scaler.expensemanagement.exceptions.InvalidCredentialsException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class RegisterCommand implements Command {
    private UserController userController;
    @Override
    public Boolean match(String input) {
        return getCommand(input).equals(Commands.REGISTER_USER_COMMAND);
    }

    @Override
    public void execute(String input) {
        //command >> REGISTER milan milan@gmail.com 123542 9870280809
        List<String> tokens = getTokens(input);
        validate(tokens);
        CreateUserRequest request = CreateUserRequest.builder()
                .name(tokens.get(1))
                .email(tokens.get(2))
                .password(tokens.get(3))
                .phoneNumber(tokens.get(4))
                .build();
        userController.createUser(request);
        System.out.println("user created");
    }

    private void validate(List<String> tokens) {
        // validations
        if(tokens.size() != 5) {
            throw new InvalidCredentialsException("Credentials missing for REGISTER command");
        }
    }
}
