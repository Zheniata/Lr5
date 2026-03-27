package org.example.commands;

import org.example.managers.CollectionManager;
import org.example.models.Identifiable;
import org.example.models.Organization;

import java.util.Scanner;
/**
 * Команда удаления организации по её уникальному идентификатору (ID).
 */
public class RemoveById <T extends Comparable<T> & Identifiable> extends Command {
    CollectionManager<T> collectionManager;

    public RemoveById(CollectionManager collectionManager) {
        super("remove_by_id");
        this.collectionManager = collectionManager;
    }

    /**
     * Удаляет организацию с указанным ID из коллекции.
     * Требует один аргумент — строковое представление ID.
     *
     * @param args массив аргументов, где args[0] — ID организации
     */

    @Override
    public void execute(String[] args) {
        String strId = args[0].trim();
        long id;
        try {
            id = Long.parseLong(strId);
        } catch (NumberFormatException e) {
            System.out.println("Неверный фомат id");
            return;
        }
        T element = collectionManager.getById(id);
        if (element == null) {
            System.out.println("Организация с id=" + id + " не найдена");
            return;
        }
        collectionManager.removeFromCollection(element);
        System.out.println("Организация с id=" + id + " удалена");
    }
}