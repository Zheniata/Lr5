package org.example.managers;

import org.example.commands.Command;

import java.util.*;

public class CommandManager {
    private Map<String, Command> commands = new HashMap<>();
    private List<String> commandHistory = new ArrayList<>();

    public void execute(String commandName, String[] args){
        Command command = commands.get(commandName);
        if (command == null){
            throw new IllegalArgumentException("Данная команды не найдена");
        }
        command.execute(args);
    }

    public void register(String name, Command command){
        commands.put(name, command);
    }

    public Map<String, Command> getCommands(){
        return commands;
    }

    public List<String> getCommandHistory(){
        return commandHistory;
    }

    public void addCommandHistory(String command){
        commandHistory.add(command);
    }


}
