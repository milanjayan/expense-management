package com.scaler.expensemanagement.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "group_expenses")
@Builder
@AllArgsConstructor
public class GroupExpense extends BaseModel {
    @ManyToOne
    private UserGroup userGroup;
    @OneToOne
    private Expense expense;
}
