package org.example.commands;

import java.util.Objects;
/**
 * Абстрактный базовый класс для всех команд системы.
 * Определяет общее поведение: имя команды и метод выполнения.
 */
public abstract class Command {
    private String name;

    public Command(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
    /**
     * Абстрактный метод выполнения команды.
     * Должен быть реализован в подклассах.
     *
     * @param args массив аргументов команды
     */
    public abstract void execute(String[] args);

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Command command = (Command) o;
        return Objects.equals(name, command.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
