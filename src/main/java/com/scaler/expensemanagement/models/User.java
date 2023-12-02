package com.scaler.expensemanagement.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User extends BaseModel {
    private String name;
    private String email;
    private String phoneNumber;
    private String password;
    private Double totalOwed;
    private Double totalLend;
}
