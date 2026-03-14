package org.example.commands;

import org.example.managers.CollectionManager;
import org.example.managers.CommandManager;
import org.example.models.Address;
import org.example.models.Coordinates;
import org.example.models.Organization;
import org.example.models.OrganizationType;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

/**
 * Команда выполнения скрипта из файла.
 * Поддерживает два формата строк:
 * Обычные команды: {@code show}, {@code save}, {@code remove_by_id 5}</li>
 * Специальный формат добавления: {@code add {name='...', x=..., y=..., ...}}</li>
 * Предотвращает рекурсивное выполнение одного и того же файла.
 */
public class ExecuteScript extends Command{
    CommandManager commandManager;
    CollectionManager collectionManager;

    public ExecuteScript(CommandManager commandManager, CollectionManager collectionManager){
        super("execute_script");
        this.commandManager = commandManager;
        this.collectionManager = collectionManager;
    }

    /**
     * Выполянет команды из указаного файла по строчно
     * Пропускает пустые строки
     * @param args - массив аргументов, где первый элемент - путь к файлу
     */

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
            int lineNumber = 0;
            while ((line = reader.readLine()) != null){
                lineNumber++;
                String trimmedLine = line.trim();
                if (trimmedLine.isEmpty()){
                    continue;
                }
                if (trimmedLine.startsWith("add") && trimmedLine.contains("{") && trimmedLine.endsWith("}")) {
                    parseAndAdd(trimmedLine, lineNumber);
                } else {
                    String[] parts = trimmedLine.split(" ", 2);
                    String commandName = parts[0];
                    String[] cmdArgs;

                    if (parts.length > 1) {
                        cmdArgs = new String[]{parts[1]};
                    } else {
                        cmdArgs = new String[0];
                    }
                    Command command = commandManager.getCommand(commandName);
                    if (command == null) {
                        System.out.println("Команда в строке " + lineNumber + "не существует");
                        continue;
                    }
                    try {
                        command.execute(cmdArgs);
                    } catch (Exception e) {
                        System.out.println("Произошла ошибка во время выполнения команды" + command);
                    }
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
    private void parseAndAdd(String line, int lineNum) {
        int startBrace = line.indexOf('{');
        int endBrace = line.lastIndexOf('}');
        if (startBrace == -1 || endBrace == -1 || endBrace <= startBrace) {
            System.err.println("Строка " + lineNum + ": неверный формат скобок");
            return;
        }
        String content = line.substring(startBrace + 1, endBrace).trim();

        String[] pairs = content.split(",(?=([^\']*\'[^\']*\')*[^\']*$)");

        String name = null;
        Double x = null;
        Long y = null;
        Float annualTurnover = null;
        OrganizationType type = null;
        String street = null;
        String zipCode = null;

        for (String pair : pairs) {
            pair = pair.trim();
            if (!pair.contains("=")) {
                System.err.println("Строка " + lineNum + ": некорректная пара: " + pair);
                return;
            }
            String[] kv = pair.split("=", 2);
            if (kv.length < 2) {
                System.err.println("Строка " + lineNum + ": не хватает значения в паре: " + pair);
                return;
            }
            String key = kv[0].trim();
            String value = kv[1].trim();

            if (value.startsWith("'") && value.endsWith("'")) {
                value = value.substring(1, value.length() - 1);
            }

            try {
                switch (key) {
                    case "name" -> name = value;
                    case "x" -> x = Double.parseDouble(value);
                    case "y" -> y = Long.parseLong(value);
                    case "annualTurnover" -> annualTurnover = Float.parseFloat(value);
                    case "type" -> type = OrganizationType.valueOf(value);
                    case "street" -> street = value;
                    case "zipCode" -> zipCode = value;
                    case "id" -> {
                        System.out.println("Предупреждение (строка " + lineNum + "): поле 'id' игнорируется");
                    }
                    default -> {
                        System.err.println("Строка " + lineNum + ": неизвестное поле '" + key + "'");
                        return;
                    }
                }
            } catch (Exception e) {
                System.err.println("Строка " + lineNum + ": ошибка в поле '" + key + "': " + e.getMessage());
                return;
            }
        }
        if (name == null || name.isEmpty()) {
            System.err.println("Строка " + lineNum + ": имя обязательно");
            return;
        }
        if (x == null || y == null) {
            System.err.println("Строка " + lineNum + ": координаты x и y обязательны");
            return;
        }
        if (annualTurnover == null || annualTurnover <= 0) {
            System.err.println("Строка " + lineNum + ": annualTurnover должен быть > 0");
            return;
        }
        if (type == null) {
            System.err.println("Строка " + lineNum + ": тип обязателен");
            return;
        }

        Coordinates coordinates = new Coordinates(x, y);
        Address address = (street != null || zipCode != null) ? new Address(street, zipCode) : null;

        try {
            long newId = collectionManager.getNextAvailableId();
            LocalDate creationDate = LocalDate.now();
            Organization org = new Organization(
                    newId,
                    creationDate,
                    name,
                    coordinates,
                    annualTurnover,
                    type,
                    address
            );
            collectionManager.addToCollection(org);
            System.out.println("Добавлена организация из скрипта: " + name);
        } catch (Exception e) {
            System.err.println("Строка " + lineNum + ": ошибка создания: " + e.getMessage());
        }
    }
}
