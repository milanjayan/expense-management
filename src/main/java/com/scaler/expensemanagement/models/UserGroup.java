package com.scaler.expensemanagement.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "user_groups")
public class UserGroup extends BaseModel {
    private String name;
    @ManyToOne
    private User createdBy;
    @ManyToMany
    private List<User> members;
    @ManyToMany
    private List<User> admins;
}
