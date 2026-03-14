package org.example.commands;

import org.example.managers.CollectionManager;
import org.example.models.Organization;

import java.util.*;
/**
 * Команда удаления всех организаций, чей ID больше заданного значения.
 */
public class RemoveGreater extends Command{
    CollectionManager collectionManager;

    public RemoveGreater(CollectionManager collectionManager){
        super("remove_greater");
        this.collectionManager = collectionManager;
    }

    /**
     * Удаляет все организации с ID > указанного значения.
     * Требует один числовой аргумент.
     *
     * @param args массив аргументов, где args[0] — строковое представление порогового ID
     */

    @Override
    public void execute(String[] args) {
        if (args.length != 1){
            System.out.println("Неверный аргумент");
            return;
        }

        if (args[0] == null){
            System.out.println("Укажите id");
            return;
        }

        long idMax;
        try {
            idMax = Long.parseLong(args[0]);
        } catch (NumberFormatException e){
            System.out.println("id должен быть целым числом");
            return;
        }
        Collection<Organization> collection = collectionManager.getCollection();
        if (collection.isEmpty()){
            System.out.println("Коллекция пуста");
            return;
        }

        Iterator<Organization> iterator = collection.iterator();
        int removedCount = 0;
        while (iterator.hasNext()){
            Organization organization = iterator.next();
            if(organization.getId() > idMax){
                iterator.remove();
                removedCount++;
            }
        }
        if (removedCount == 0){
            System.out.println("Нет элементов, которые превышают id = " + idMax);
        }
        else {
            System.out.println("Элементы, превышающие id = " + idMax + " удалены");
        }
    }
}
