package org.example.commands;

import org.example.managers.CollectionManager;
import org.example.models.Address;
import org.example.models.Organization;

import java.util.Collection;
import java.util.PriorityQueue;

public class RemoveAnyByOfficialAddress extends Command{
    CollectionManager collectionManager;

    public RemoveAnyByOfficialAddress(CollectionManager collectionManager){
        super("remove_any_by_official_address");
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 1){
            System.out.println("Неверный аргумент, укажите улицу");
            return;
        }
        String street = args[0].trim();
        Collection<Organization> collection = collectionManager.getCollection();
        boolean found = false;

        for(Organization organization: collection){
            Address adress = organization.getOfficialAddress();
            if (adress != null && adress.getStreet() != null && adress.getStreet().equals(street)){
                collectionManager.removeFromCollection(organization);
                System.out.println("Элемент с адресом: " + street + " удален");
                found = true;
                break;
            }
        }
        if (!found){
            System.out.println("Элемент с улицей: " + street + " не найден");
        }
    }
}
