package com.scaler.expensemanagement.dtos;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateUserGroupRequest {
    private String groupName;
    private Long creatorId;
}
