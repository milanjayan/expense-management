package com.scaler.expensemanagement.commands;

import java.util.Arrays;
import java.util.List;

public interface Command {
    Boolean match(String input);
    void execute(String input);

    default String getCommand(String input) {
        return getTokens(input).get(0);
    }
    default List<String> getTokens(String input) {
        return Arrays.stream(input.split(" ")).toList();
    }
}
