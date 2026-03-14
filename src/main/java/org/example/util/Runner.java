package org.example.util;

import org.example.commands.Command;
import org.example.commands.Exit;
import org.example.managers.CommandManager;

import java.util.Scanner;
/**
 * Класс, отвечающий за запуск и управление интерактивным режимом работы программы.
 * Обрабатывает ввод пользователя, разбирает команды и передаёт их на выполнение.
 */
public class Runner {
    private CommandManager commandManager;
    private Scanner scanner;

    public Runner(CommandManager commandManager, Scanner scanner){
        this.commandManager = commandManager;
        this.scanner = scanner;
    }

    /**
     * Запускает бесконечный цикл интерактивного режима.
     * Программа завершается только при выполнении команды {@code exit}.
     */

    public void interactiveMode(){
        System.out.println("Ввведите команду");
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
            Command command = commandManager.getCommand(commandName);
            if (command instanceof Exit){
                command.execute(args);
                break;
            }

            try{
                commandManager.execute(commandName, args);
                commandManager.addCommandHistory(commandName);
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }

        }
    }
}
