package org.example.util;

import org.example.managers.CommandManager;

import java.util.Scanner;

public class Runner {
    private CommandManager commandManager;
    private Scanner scanner;

    public Runner(CommandManager commandManager, Scanner scanner){
        this.commandManager = commandManager;
        this.scanner = scanner;
    }

    public void interactiveMode(){
        while (true){
            System.out.println("> ");
            String line = scanner.nextLine().trim();
            if (line.isEmpty()){
                continue;
            }

            String[] parts = line.split(" ", 2);
            String commandName = parts[0];
            String[] args;

            if (parts.length > 1){
                args = new String[]{parts[1]};
            }
            else {
                args = new String[0];
            }
            if ("exit".equals(commandName)){
                System.out.println("Работа завершена");
                break;
            }

            try{
                commandManager.execute(commandName, args);
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }

        }
    }
}
