package com.scaler.expensemanagement.repositories;

import com.scaler.expensemanagement.models.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserGroupRepository extends JpaRepository<UserGroup, Long> {

}
