package org.example.commands;

import org.example.managers.CollectionManager;
import org.example.models.Identifiable;
import org.example.models.Organization;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * Команда вывода всех организаций в порядке убывания их ID.
 */
public class PrintDescending<T extends Comparable<T> & Identifiable> extends Command{
    CollectionManager<T> collectionManager;

    public PrintDescending(CollectionManager<T> collectionManager){
        super("print_descending");
        this.collectionManager = collectionManager;
    }

    /**
     * Выводит все организации в порядке убывания ID.
     * Если коллекция пуста — выводит соответствующее сообщение.
     *
     * @param args аргументы команды (игнорируются)
     */

    @Override
    public void execute(String[] args) {
        List<T> list = new ArrayList<>(collectionManager.getCollection());
        if (list.isEmpty()){
            System.out.println("Коллекция пуста");
            return;
        }
        list.sort(Collections.reverseOrder());
        for (T item: list){
            System.out.println(item);
        }
    }
}
