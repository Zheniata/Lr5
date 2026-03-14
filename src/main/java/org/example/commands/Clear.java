package org.example.commands;

import org.example.managers.CollectionManager;
/**
 * Команда очистки всей коллекции организаций.
 * Удаляет все элементы из коллекции без подтверждения.
 */
public class Clear extends Command{
    CollectionManager collectionManager;

    public Clear(CollectionManager collectionManager){
        super("clear");
        this.collectionManager = collectionManager;
    }

    /**
     * Очищает всю коллекцию организаций.
     * Выводит сообщение об успешной очистке.
     *
     * @param args аргументы команды (не используются)
     */

    @Override
    public void execute(String[] args) {
        collectionManager.clearCollection();
        System.out.println("Коллекция очищена");
    }
}
