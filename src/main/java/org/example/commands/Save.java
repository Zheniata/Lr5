package org.example.commands;

import org.example.managers.CollectionManager;
/**
 * Команда сохранения текущей коллекции в XML-файл.
 * Использует {@link org.example.managers.XmlFileManager} для сериализации данных.
 */
public class Save extends Command{
    CollectionManager collectionManager;

    public Save(CollectionManager collectionManager){
        super("save");
        this.collectionManager = collectionManager;
    }

    /**
     * Сохраняет коллекцию в файл.
     * Выводит сообщение об успехе или ошибке.
     *
     * @param args аргументы команды (игнорируются)
     */

    @Override
    public void execute(String[] args) {
        try{
            collectionManager.saveCollection();
        } catch (Exception e){
            System.out.println("Произошла ошибка");
        }
    }
}
