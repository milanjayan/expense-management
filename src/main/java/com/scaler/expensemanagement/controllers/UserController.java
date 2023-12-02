package com.scaler.expensemanagement.controllers;

import com.scaler.expensemanagement.dtos.CreateUserRequest;
import com.scaler.expensemanagement.models.User;
import com.scaler.expensemanagement.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class UserController {
    private UserService userService;

    public User createUser(CreateUserRequest request) {
        return userService.createUser(request);
    }
}
