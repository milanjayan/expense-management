package com.scaler.expensemanagement.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class AddGroupUsersRequest {
    private Long groupId;
    private List<Long> userIds;
}
