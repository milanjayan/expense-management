package com.scaler.expensemanagement.services;

import com.scaler.expensemanagement.dtos.AddGroupUsersRequest;
import com.scaler.expensemanagement.dtos.CreateUserGroupRequest;
import com.scaler.expensemanagement.exceptions.*;
import com.scaler.expensemanagement.models.User;
import com.scaler.expensemanagement.models.UserGroup;
import com.scaler.expensemanagement.repositories.UserGroupRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@AllArgsConstructor
public class UserGroupService {
    private UserGroupRepository userGroupRepository;
    private UserService userService;


    public UserGroup getUserGroup(Long id) {
        return userGroupRepository.findById(id)
                .orElseThrow(() -> new UserGroupNotFoundException("User Group with id: "+id+" not found"));
    }
    public UserGroup createGroup(CreateUserGroupRequest request) {
        User user = userService.getUser(request.getCreatorId());
        UserGroup userGroup = UserGroup.builder()
                .createdBy(user)
                .name(request.getGroupName())
                .members(List.of(user))
                .admins(List.of(user))
                .build();
        return userGroupRepository.save(userGroup);
    }

    public UserGroup addGroupMembers(AddGroupUsersRequest request) {
        UserGroup userGroup = getUserGroup(request.getGroupId());
        List<User> members = userService.getAllUsers(request.getUserIds());
        //validating user exists
        if(members.size() != request.getUserIds().size()) {
            HashSet<Long> ids = new HashSet<>(request.getUserIds());
            for(User member : members) {
                ids.remove(member.getId());
            }
            for(Long id : ids) {
                throw new UserNotFoundException("User with id: "+id+" not found");
            }
        }
        //validating member already present in group
        HashSet<Long> existingIds = new HashSet<>(userGroup.getMembers().stream().map(
                member -> member.getId()
        ).toList());
        for(Long id : request.getUserIds()) {
            if(existingIds.contains(id)) {
                throw new UserAlreadyMemberException("User with id: "+id+" already member of group "+userGroup.getName());
            }
        }
        userGroup.getMembers().addAll(members);
        return userGroupRepository.save(userGroup);
    }

    public UserGroup addGroupAdmins(AddGroupUsersRequest request) {
        UserGroup userGroup = getUserGroup(request.getGroupId());
        List<User> admins = userService.getAllUsers(request.getUserIds());
        //validating user exists in group
        HashSet<Long> existingMemberIds = new HashSet<>(userGroup.getMembers().stream().map(
                member -> member.getId()
        ).toList());
        for(Long id : request.getUserIds()) {
            if(!existingMemberIds.contains(id)) {
                throw new UserNotGroupMemberException("User with id: "+id+" not member of group "+userGroup.getName());
            }
        }
        //validating admin already present in group
        HashSet<Long> existingIds = new HashSet<>(userGroup.getAdmins().stream().map(
                admin -> admin.getId()
        ).toList());
        for(Long id : request.getUserIds()) {
            if(existingIds.contains(id)) {
                throw new UserAlreadyAdminException("User with id: "+id+" already admin of group "+userGroup.getName());
            }
        }
        userGroup.getAdmins().addAll(admins);
        return userGroupRepository.save(userGroup);
    }
}
