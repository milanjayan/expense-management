package com.scaler.expensemanagement.services;

import com.scaler.expensemanagement.dtos.CreateUserRequest;
import com.scaler.expensemanagement.models.User;
import com.scaler.expensemanagement.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    public User createUser(CreateUserRequest request) {
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .password(request.getPassword())
                .build();
        return userRepository.save(user);
    }
}
