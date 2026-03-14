package org.example.commands;

import org.example.managers.CollectionManager;
import org.example.models.Organization;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * Команда вывода значений поля {@code type} всех организаций в порядке убывания.
 * Сортировка выполняется по ID (благодаря {@link Comparable} в {@link Organization}),
 * но выводятся только значения типа.
 */
public class PrintFieldDescendingType extends Command{
    CollectionManager collectionManager;

    public PrintFieldDescendingType(CollectionManager collectionManager){
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
        List<Organization> list = new ArrayList<>(collectionManager.getCollection());
        if (list.isEmpty()){
            System.out.println("Коллекция пуста");
            return;
        }
        list.sort(Collections.reverseOrder());
        for (Organization organization: list){
            System.out.println(organization.getType());
        }
    }
}
