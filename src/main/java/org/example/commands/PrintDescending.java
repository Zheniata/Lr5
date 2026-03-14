package org.example.commands;

import org.example.managers.CollectionManager;
import org.example.models.Organization;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * Команда вывода всех организаций в порядке убывания их ID.
 */
public class PrintDescending extends Command{
    CollectionManager collectionManager;

    public PrintDescending(CollectionManager collectionManager){
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
        List<Organization> list = new ArrayList<>(collectionManager.getCollection());
        if (list.isEmpty()){
            System.out.println("Коллекция пуста");
            return;
        }
        list.sort(Collections.reverseOrder());
        for (Organization organization: list){
            System.out.println(organization);
        }
    }
}
