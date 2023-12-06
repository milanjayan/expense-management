package com.scaler.expensemanagement.services;

import com.scaler.expensemanagement.dtos.CreateUserRequest;
import com.scaler.expensemanagement.exceptions.UserAlreadyExistsException;
import com.scaler.expensemanagement.exceptions.UserNotFoundException;
import com.scaler.expensemanagement.models.User;
import com.scaler.expensemanagement.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;
    private BcryptEncoder bcryptEncoder;
    public User getUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id: "+id+" not found"));
    }

    public List<User> getAllUsers(List<Long> ids) {
        return userRepository.findAllById(ids);
    }

    public User createUser(CreateUserRequest request) {
        validate(request);
        String encodedPassword = bcryptEncoder.encode(request.getPassword());
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .password(encodedPassword)
                .build();
        return userRepository.save(user);
    }

    private void validate(CreateUserRequest request) {
        Optional<User> existingUser = userRepository.findByNameOrEmailOrPhoneNumber(request.getName(), request.getEmail(), request.getPhoneNumber());
        if(existingUser.isPresent()) {
            if(existingUser.get().getName().equals(request.getName())) {
                throw new UserAlreadyExistsException("User with name: "+request.getName()+" already present");
            }
            if(existingUser.get().getEmail().equals(request.getEmail())) {
                throw new UserAlreadyExistsException("User with email: "+request.getEmail()+" already present");
            }
            if(existingUser.get().getPhoneNumber().equals(request.getPhoneNumber())) {
                throw new UserAlreadyExistsException("User with phone number : "+request.getPhoneNumber()+" already present");
            }
        }
    }


}
