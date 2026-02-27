package org.example.commands;

import org.example.managers.CommandManager;

import java.util.List;

public class History extends Command{
    CommandManager commandManager;

    public History(CommandManager commandManager){
        super("history");
        this.commandManager = commandManager;
    }

    @Override
    public void execute(String[] args) {
        List<String> history = commandManager.getCommandHistory();
        if (history.isEmpty()){
            System.out.println("История пуста");
        } else {
            for (int i = 0; i < history.size(); i++){
                System.out.println(i + 1 + "." + history.get(i));
            }
        }
    }
}
