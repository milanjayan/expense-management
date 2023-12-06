package com.scaler.expensemanagement.services;

import com.scaler.expensemanagement.dtos.CreateGroupExpenseRequest;
import com.scaler.expensemanagement.dtos.SettlementDto;
import com.scaler.expensemanagement.enums.SettleStatus;
import com.scaler.expensemanagement.exceptions.UserNotGroupMemberException;
import com.scaler.expensemanagement.models.Expense;
import com.scaler.expensemanagement.models.GroupExpense;
import com.scaler.expensemanagement.models.User;
import com.scaler.expensemanagement.models.UserGroup;
import com.scaler.expensemanagement.repositories.GroupExpenseRepository;
import com.scaler.expensemanagement.strategies.settlementstrategies.SettlementStrategy;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
@AllArgsConstructor
public class GroupExpenseService {
    private GroupExpenseRepository groupExpenseRepository;
    private UserGroupService userGroupService;
    private ExpenseService expenseService;
    @Autowired
    @Qualifier("greedySettlementStrategy")
    private SettlementStrategy settlementStrategy;

    public GroupExpense createGroupExpense(CreateGroupExpenseRequest request) {
        UserGroup group = userGroupService.getUserGroup(request.getGroupId());
        HashSet<Long> groupMembersIds = new HashSet<>(group.getMembers().stream().map(
                member -> member.getId()
        ).toList());
        //validate creator a group member
        if(!groupMembersIds.contains(request.getCreatorId())) {
            throw new UserNotGroupMemberException("User with id: "+request.getCreatorId()+" not memeber of group "+group.getName());
        }
        User creator = null;

        //validate users members of group
        for(Long id : request.getUsersIds()) {
            if(!groupMembersIds.contains(id)) {
                throw new UserNotGroupMemberException("User with id: "+id+" not memeber of group "+group.getName());
            }
        }
        HashSet<Long> usersIds = new HashSet<>(request.getUsersIds());
        List<User> users = new ArrayList<>();
        for(User user : group.getMembers()) {
            if(usersIds.contains(user.getId())) {
                users.add(user);
            }
            if(creator == null && user.getId().equals(request.getCreatorId())) {
                creator = user;
            }
        }
        users.add(creator);
        Expense expense = Expense.builder()
                .description(request.getDescription())
                .amount(request.getAmount())
                .createdBy(creator)
                .users(users)
                .status(SettleStatus.PENDING)
                .build();
        expense = expenseService.save(expense);
        GroupExpense groupExpense = GroupExpense.builder()
                .expense(expense)
                .userGroup(group)
                .build();
        return groupExpenseRepository.save(groupExpense);
    }

    public List<SettlementDto> settleUp(Long groupId) {
        List<GroupExpense> groupExpenses = groupExpenseRepository.findGroupExpenseByUserGroupId(groupId);
        List<Expense> expenses = groupExpenses.stream().map(GroupExpense::getExpense).toList();
        return settlementStrategy.settle(expenses);
    }
}
