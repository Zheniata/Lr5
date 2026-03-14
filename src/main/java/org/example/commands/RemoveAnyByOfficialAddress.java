package org.example.commands;

import org.example.managers.CollectionManager;
import org.example.models.Address;
import org.example.models.Organization;

import java.util.Collection;
import java.util.Iterator;
/**
 * Команда удаления одной организации, у которой официальный адрес содержит указанную улицу.
 * Удаляется только первый найденный элемент.
 */
public class RemoveAnyByOfficialAddress extends Command{
    CollectionManager collectionManager;

    public RemoveAnyByOfficialAddress(CollectionManager collectionManager){
        super("remove_any_by_official_address");
        this.collectionManager = collectionManager;
    }

    /**
     * Удаляет первую организацию, у которой улица в официальном адресе совпадает с заданной.
     * Требует один аргумент — название улицы.
     *
     * @param args массив аргументов, где args[0] — название улицы
     */

    @Override
    public void execute(String[] args) {
        if (args.length != 1){
            System.out.println("Неверный аргумент, укажите улицу");
            return;
        }
        String street = args[0].trim();
        Collection<Organization> collection = collectionManager.getCollection();
        boolean found = false;

        Iterator<Organization> iterator = collection.iterator();
        while (iterator.hasNext()) {
            Organization org = iterator.next();
            Address addr = org.getOfficialAddress();
            if (addr != null && street.equals(addr.getStreet())) {
                iterator.remove();
                System.out.println("Элемент с адресом: " + street + " удалён");
                found = true;
                break;
            }
        }
        if (!found){
            System.out.println("Элемент с улицей: " + street + " не найден");
        }
    }
}
