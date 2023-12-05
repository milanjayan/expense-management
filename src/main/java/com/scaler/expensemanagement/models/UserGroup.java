package com.scaler.expensemanagement.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "user_groups")
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
public class UserGroup extends BaseModel {
    private String name;
    @ManyToOne
    private User createdBy;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<User> members = new ArrayList<>();
    @ManyToMany(fetch = FetchType.EAGER)
    private List<User> admins = new ArrayList<>();
}
