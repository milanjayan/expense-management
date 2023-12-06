package com.scaler.expensemanagement.strategies.settlementstrategies;

import com.scaler.expensemanagement.dtos.SettlementDto;
import com.scaler.expensemanagement.models.Expense;

import java.util.List;

public interface SettlementStrategy {
    List<SettlementDto> settle(List<Expense>expenses);
}
