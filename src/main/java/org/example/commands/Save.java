package org.example.commands;

import org.example.managers.CollectionManager;

public class Save extends Command{
    CollectionManager collectionManager;

    public Save(CollectionManager collectionManager){
        super("save");
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) {
        try{
            collectionManager.saveCollection();
            System.out.println("Коллекция успешна сохранена");
        } catch (Exception e){
            System.out.println("Произошла ошибка");
        }
    }
}
