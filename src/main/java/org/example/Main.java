package org.example;

import org.example.commands.*;
import org.example.managers.CollectionManager;
import org.example.managers.CommandManager;
import org.example.util.Runner;

import java.util.Scanner;

public class Main {
    public static void main (String[] args){
        Scanner scanner = new Scanner(System.in);
        CollectionManager collectionManager = new CollectionManager();

        CommandManager commandManager = new CommandManager(){{
            register("help", new Help(this));
            register("add", new Add(collectionManager, scanner));
            register("clear", new Clear(collectionManager));
            register("show", new Show(collectionManager));
            register("info", new Info(collectionManager));
        }};

        new Runner(commandManager, scanner).interactiveMode();
        scanner.close();


    }
}