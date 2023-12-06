package com.scaler.expensemanagement.strategies.settlementstrategies;

import org.springframework.data.util.Pair;
import com.scaler.expensemanagement.dtos.SettlementDto;
import com.scaler.expensemanagement.models.Expense;
import com.scaler.expensemanagement.models.UserExpense;
import org.springframework.stereotype.Component;

import java.util.*;

import static java.lang.Math.abs;

@Component("greedySettlementStrategy")
public class GreedySettlementStrategy implements SettlementStrategy {

    private static Map<Long, Double> condenseUserExpenses(List<Expense> expenses) {
        Map<Long, Double> hashMap = new HashMap<>();
        for(Expense expense : expenses) {
            //for lenders list
            for(UserExpense userExpense : expense.getLenders()) {
                Long userId = userExpense.getUser().getId();
                if(hashMap.containsKey(userId)) {
                    hashMap.put(userId, hashMap.get(userId)+userExpense.getAmount());
                } else {
                    hashMap.put(userId, userExpense.getAmount());
                }
            }
            //for owers list
            for(UserExpense userExpense : expense.getOwers()) {
                Long userId = userExpense.getUser().getId();
                if(hashMap.containsKey(userId)) {
                    hashMap.put(userId, hashMap.get(userId)-userExpense.getAmount());
                } else {
                    hashMap.put(userId, -1*userExpense.getAmount());
                }
            }
        }
        return hashMap;
    }
    @Override
    public List<SettlementDto> settle(List<Expense> expenses) {
        Map<Long, Double> condensedUserExpense = condenseUserExpenses(expenses);
        List<SettlementDto> settlement = new ArrayList<>();
        TreeSet<Pair<Long, Double>> treeSet = new TreeSet<>((left, right) -> (int)(left.getSecond() - right.getSecond()));
        for(Map.Entry<Long, Double> entry : condensedUserExpense.entrySet()) {
            treeSet.add(Pair.of(entry.getKey(), entry.getValue()));
        }
        while(treeSet.size() > 1) {
            Long payerId = treeSet.first().getFirst();
            Double payerAmount = treeSet.first().getSecond();
            Long owerId = treeSet.last().getFirst();
            Double owerAmount = treeSet.last().getSecond();
            treeSet.pollFirst();
            treeSet.pollLast();
            Double transaction =  payerAmount + owerAmount;
            Double settleAmount = 0.0;
            if(transaction == 0) {
                settleAmount = abs(payerAmount);

            } else if(transaction > 0) {
                settleAmount = abs(payerAmount);
                treeSet.add(Pair.of(owerId, transaction));
            } else {
                settleAmount = owerAmount;
                treeSet.add(Pair.of(payerId, transaction));
            }
            settlement.add(SettlementDto.builder()
                    .payedById(payerId)
                    .owedById(owerId)
                    .amount(settleAmount)
                    .build());
        }
        return settlement;
    }
}
