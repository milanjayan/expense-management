package com.scaler.expensemanagement;

import com.scaler.expensemanagement.commands.Command;
import com.scaler.expensemanagement.commands.CommandExecutor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
@EnableJpaAuditing
@Component
public class ExpenseManagementApplication implements CommandLineRunner {
    private CommandExecutor commandExecutor;
    private Scanner scanner;

    public ExpenseManagementApplication(CommandExecutor commandExecutor, List<Command> commands) {
        this.scanner = new Scanner(System.in);
        this.commandExecutor =commandExecutor;
        commandExecutor.addCommands(commands);
    }
    public static void main(String[] args) {
        SpringApplication.run(ExpenseManagementApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        while(true) {
            System.out.println("Enter input: ");
            String input = scanner.nextLine();
            commandExecutor.execute(input);
        }
    }
}
