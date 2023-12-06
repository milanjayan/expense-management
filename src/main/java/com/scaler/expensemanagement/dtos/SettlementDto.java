package com.scaler.expensemanagement.dtos;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SettlementDto {
    private Long payedById;
    private Long owedById;
    private Double amount;
}
