package com.scaler.expensemanagement.models;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "expenses")
public class Expense extends BaseModel {
    private String description;
    private Double amount;
    @ManyToOne
    private User createdBy;
    @ManyToMany
    private List<User> users;
    @OneToMany(mappedBy = "expense")
    private List<UserExpense> lenders;
    @OneToMany(mappedBy = "expense")
    private List<UserExpense> owers;
}
