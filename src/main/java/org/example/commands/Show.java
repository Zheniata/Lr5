package org.example.commands;

import org.example.managers.CollectionManager;
import org.example.models.Identifiable;
import org.example.models.Organization;

import java.util.PriorityQueue;

/**
 * Команда вывода всех организаций из коллекции.
 */

public class Show<T extends Comparable<T> & Identifiable> extends Command{
    CollectionManager<T> collectionManager;

    public Show(CollectionManager<T> collectionManager){
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
        PriorityQueue<T> collection = collectionManager.getCollection();
        if (collection.isEmpty()){
            System.out.println("Коллекцияя пуста");
        }
        for (T item: collection){
            System.out.println(item);
        }
    }
}
