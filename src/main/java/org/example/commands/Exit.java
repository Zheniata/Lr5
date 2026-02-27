package org.example.commands;

public class Exit extends Command{
    public Exit(){
        super("exit");
    }

    @Override
    public void execute(String[] args) {
        System.out.println("Работа завершена");
    }
}
