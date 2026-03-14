package org.example.commands;

import org.example.managers.CommandManager;

import java.util.List;
/**
 * Команда вывода истории последних 6 выполненных команд.
 * История хранится в {@link CommandManager} и обновляется при каждом вызове команды.
 */
public class History extends Command{
    CommandManager commandManager;

    public History(CommandManager commandManager){
        super("history");
        this.commandManager = commandManager;
    }

    /**
     * Выводит список последних выполненных команд (максимум 6).
     * Если история пуста — выводит соответствующее сообщение.
     *
     * @param args аргументы команды (игнорируются)
     */

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
