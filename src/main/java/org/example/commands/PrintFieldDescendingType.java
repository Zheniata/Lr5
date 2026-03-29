package org.example.commands;

import org.example.managers.CollectionManager;
import org.example.models.HasType;
import org.example.models.Identifiable;
import org.example.models.Organization;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * Команда вывода значений поля {@code type} всех организаций в порядке убывания.
 * Сортировка выполняется по ID (благодаря {@link Comparable} в {@link Organization}),
 * но выводятся только значения типа.
 */
public class PrintFieldDescendingType<T extends Comparable<T> & Identifiable & HasType> extends Command{
    CollectionManager<T> collectionManager;

    public PrintFieldDescendingType(CollectionManager<T> collectionManager){
        super("print_field_descending_type");
        this.collectionManager = collectionManager;
    }

    /**
     * Выводит тип каждой организации в порядке убывания ID.
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
            System.out.println(item.getTypeName());
        }
    }
}
