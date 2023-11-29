package com.scaler.expensemanagement.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "group_expenses")
public class GroupExpense extends BaseModel {
    @ManyToOne
    private UserGroup userGroup;
    @OneToOne
    private Expense expense;
}
