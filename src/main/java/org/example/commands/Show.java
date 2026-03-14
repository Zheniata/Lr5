package org.example.commands;

import org.example.managers.CollectionManager;
import org.example.models.Organization;

import java.util.PriorityQueue;

/**
 * Команда вывода всех организаций из коллекции.
 */

public class Show extends Command{
    CollectionManager collectionManager;

    public Show(CollectionManager collectionManager){
        super("show");
        this.collectionManager = collectionManager;
    }

    /**
     * Выводит все организации из коллекции.
     * Если коллекция пуста — выводит соответствующее сообщение.
     *
     * @param args аргументы команды (игнорируются)
     */

    @Override
    public void execute(String[] args) {
        PriorityQueue<Organization> collection = collectionManager.getCollection();
        if (collection.isEmpty()){
            System.out.println("Коллекцияя пуста");
        }
        for (Organization organization: collection){
            System.out.println(organization);
        }
    }
}
