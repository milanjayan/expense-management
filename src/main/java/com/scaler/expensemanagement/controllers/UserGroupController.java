package com.scaler.expensemanagement.controllers;

import com.scaler.expensemanagement.dtos.AddGroupUsersRequest;
import com.scaler.expensemanagement.dtos.CreateUserGroupRequest;
import com.scaler.expensemanagement.models.UserGroup;
import com.scaler.expensemanagement.services.UserGroupService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class UserGroupController {
    private UserGroupService userGroupService;

    public UserGroup createGroup(CreateUserGroupRequest request) {
        return userGroupService.createGroup(request);
    }

    public UserGroup addGroupMembers(AddGroupUsersRequest request) {
        return userGroupService.addGroupMembers(request);
    }

    public UserGroup addGroupAdmins(AddGroupUsersRequest request) {
        return userGroupService.addGroupAdmins(request);
    }
}
