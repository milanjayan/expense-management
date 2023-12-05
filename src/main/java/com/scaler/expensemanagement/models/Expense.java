package com.scaler.expensemanagement.models;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.scaler.expensemanagement.enums.SettleStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Where;

import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "expenses")
@SuperBuilder(toBuilder = true)
public class Expense extends BaseModel {
    private String description;
    private Double amount;
    @ManyToOne
    private User createdBy;
    @ManyToMany(fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<User> users;
    @JsonManagedReference
    @OneToMany(mappedBy = "expense")
    @Where(clause = "expense_type = 'LEND'")
    private List<UserExpense> lenders;
    @OneToMany(mappedBy = "expense")
    @JsonManagedReference
    @Where(clause = "expense_type = 'OWE'")
    private List<UserExpense> owers;
    @Enumerated(EnumType.STRING)
    private SettleStatus status;
}
