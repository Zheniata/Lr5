package org.example.commands;

import org.example.managers.CollectionManager;
import org.example.models.Organization;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PrintFieldDescendingType extends Command{
    CollectionManager collectionManager;

    public PrintFieldDescendingType(CollectionManager collectionManager){
        super("print_field_descending_type");
        this.collectionManager = collectionManager;
    }

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
