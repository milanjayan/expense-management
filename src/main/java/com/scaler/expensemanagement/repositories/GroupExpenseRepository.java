package com.scaler.expensemanagement.repositories;

import com.scaler.expensemanagement.models.Expense;
import com.scaler.expensemanagement.models.GroupExpense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupExpenseRepository extends JpaRepository<GroupExpense, Long> {

    List<GroupExpense> findGroupExpenseByUserGroupId(Long id);
}
