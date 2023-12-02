package com.scaler.expensemanagement.commands;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommandExecutor {

    private List<Command> commands = new ArrayList<>();
    public void execute(String input) {
        for(Command command : commands) {
            if(command.match(input)) {
                command.execute(input);
                return;
            }
        }
    }

    public void addCommands(List<Command> commands) {
        this.commands.addAll(commands);
    }
}
