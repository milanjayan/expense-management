package com.scaler.expensemanagement.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.scaler.expensemanagement.enums.ExpenseType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@Entity
@Table(name = "user_expenses")
@Builder
@AllArgsConstructor
public class UserExpense extends BaseModel {
    @ManyToOne
    private User user;
    private Double amount;
    @Enumerated(EnumType.STRING)
    private ExpenseType expenseType;
    @ManyToOne
    @JoinColumn(name = "expense_id")
    @JsonBackReference
    private Expense expense;
}
