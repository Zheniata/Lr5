package org.example.managers;

import org.example.commands.Command;

import java.nio.file.Path;
import java.util.*;

public class CommandManager {
    private Map<String, Command> commands = new HashMap<>();
    private List<String> commandHistory = new ArrayList<>();
    private final Set<Path> executingScripts = new HashSet<>();

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

    public Command getCommand(String name) {
        return commands.get(name);
    }

    public Map<String, Command> getCommands(){
        return commands;
    }

    public List<String> getCommandHistory(){
        return commandHistory;
    }

    public void addCommandHistory(String command){
        commandHistory.add(command);
        if (commandHistory.size() > 6){
            commandHistory.remove(0);
        }
    }

    public boolean isExecuteScript(Path path){
        return executingScripts.contains(path);
    }

    public void addToExecuteScript(Path path){
        executingScripts.add(path);
    }

    public void removeFromExecuteScript(Path path){
        executingScripts.remove(path);
    }


}
