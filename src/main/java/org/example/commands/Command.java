package org.example.commands;

import java.util.Objects;

public abstract class Command {
    private String name;

    public Command(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

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
