package org.example.commands;

import org.example.managers.CollectionManager;
import org.example.models.Organization;

import java.util.PriorityQueue;

public class RemoveHead extends Command{
    CollectionManager collectionManager;

    public RemoveHead(CollectionManager collectionManager){
        super("remove_head");
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) {
        PriorityQueue<Organization> collection = collectionManager.getCollection();
        if (collection.isEmpty()){
            System.out.println("Коллекция пуста");
            return;
        }
        Organization head = collection.poll();
        System.out.println(head);
    }
}
