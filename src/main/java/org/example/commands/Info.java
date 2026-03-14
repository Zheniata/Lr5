package org.example.commands;

import org.example.managers.CollectionManager;
/**
 * Команда вывода общей информации о коллекции.
 * Включает тип коллекции, дату инициализации и количество элементов.
 */
public class Info extends Command{
    CollectionManager collectionManager;

    public Info(CollectionManager collectionManager){
        super("info");
        this.collectionManager = collectionManager;
    }

    /**
     * Выводит информацию о коллекции:
     * @param args аргументы команды (игнорируются)
     */

    @Override
    public void execute(String[] args) {
        System.out.println("Тип коллекции: " + collectionManager.getCollection().getClass().getSimpleName());
        System.out.println("Дата инициализации: " + collectionManager.getTime());
        System.out.println("Колличество элементов: " + collectionManager.size());
    }
}
