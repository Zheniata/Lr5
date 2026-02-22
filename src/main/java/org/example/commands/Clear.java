package org.example.commands;

import org.example.managers.CollectionManager;

public class Clear extends Command{
    CollectionManager collectionManager;

    public Clear(CollectionManager collectionManager){
        super("clear");
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) {
        collectionManager.clearCollection();
        System.out.println("Коллекция очищена");
    }
}
