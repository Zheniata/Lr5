package org.example.commands;

import org.example.managers.CommandManager;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class ExecuteScript extends Command{
    CommandManager commandManager;

    public ExecuteScript(CommandManager commandManager){
        super("execute_script");
        this.commandManager = commandManager;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 1){
            System.out.println("Неверный аргумент, имя файла должно быть одно");
            return;
        }
        String fileName = args[0].trim();
        if (fileName.isEmpty()){
            System.out.println("Введите название файла");
            return;
        }
        Path pathScript = Paths.get(fileName).toAbsolutePath().normalize();
        if (commandManager.isExecuteScript(pathScript)){
            System.out.println("Ошибка, произошла рекурсия");
            return;
        }
        commandManager.addToExecuteScript(pathScript);

        try (BufferedReader reader = Files.newBufferedReader(pathScript)) {
            String line;
            while ((line = reader.readLine()) != null){
                String trimmedLine = line.trim();
                if (trimmedLine.isEmpty()){
                    continue;
                }
                String[] parts = line.split(" ", 2);
                String commandName = parts[0];
                String[] cmdArgs;

                if (parts.length > 1){
                    cmdArgs = new String[]{parts[1]};
                }
                else {
                    cmdArgs = new String[0];
                }
                Command command = commandManager.getCommand(commandName);
                if (command == null){
                    System.out.println("Команда " + command + " не сущетсвует");
                    continue;
                }
                try {
                    command.execute(cmdArgs);
                } catch (Exception e){
                    System.out.println("Произошла ошибка во время выполнения команды" + command);
                }
            }

        } catch (IOException e) {
            String message = e.getMessage();

            if (message != null && message.contains("No such file")) {
                System.out.println("Файл" + pathScript + "не найден");
            }
            else if (message != null && message.contains("Permission denied")) {
                System.out.println("Нет доступа к файлу: " + pathScript);
            } else {
                System.out.println("Ошибка при чтении файла: " + message);
            }
        } finally {
            commandManager.removeFromExecuteScript(pathScript);
        }


    }
}
