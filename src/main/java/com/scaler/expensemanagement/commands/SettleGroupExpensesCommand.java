package com.scaler.expensemanagement.commands;

import com.scaler.expensemanagement.controllers.GroupExpenseController;
import com.scaler.expensemanagement.dtos.SettlementDto;
import com.scaler.expensemanagement.exceptions.InvalidCredentialsException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class SettleGroupExpensesCommand implements Command {

    private GroupExpenseController groupExpenseController;

    @Override
    public Boolean match(String input) {
        return getCommand(input).matches(Commands.SETTLE_GROUP_EXPENSE_COMMAND);
    }

    @Override
    public void execute(String input) {
        //Command >> settle-group {groupId}
        List<String> tokens = getTokens(input);
        validate(tokens);
        List<SettlementDto> settlements = groupExpenseController.settleUp(Long.valueOf(tokens.get(1)));
        for(SettlementDto settlement : settlements) {
            System.out.println(settlement.getPayedById()+" -> "+settlement.getOwedById()+" "+settlement.getAmount());
        }
    }

    private void validate(List<String> tokens) {
        if(tokens.size() != 2) {
            throw new InvalidCredentialsException("Credentials missing for settle-group command");
        }
    }
}
