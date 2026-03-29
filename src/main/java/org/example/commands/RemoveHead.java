package org.example.commands;

import org.example.managers.CollectionManager;
import org.example.models.Identifiable;
import org.example.models.Organization;

import java.util.PriorityQueue;
/**
 * Команда удаления и вывода головного элемента коллекции.
 * Головной элемент — организация с наименьшим ID (благодаря {@link PriorityQueue}).
 */
public class RemoveHead <T extends Comparable<T> & Identifiable> extends Command{
    CollectionManager<T> collectionManager;

    public RemoveHead(CollectionManager<T> collectionManager){
        super("remove_head");
        this.collectionManager = collectionManager;
    }

    /**
     * Удаляет и выводит первую организацию из коллекции.
     * Если коллекция пуста — выводит соответствующее сообщение.
     *
     * @param args аргументы команды (игнорируются)
     */

    @Override
    public void execute(String[] args) {
        PriorityQueue<T> collection = collectionManager.getCollection();
        if (collection.isEmpty()){
            System.out.println("Коллекция пуста");
            return;
        }
        T head = collection.poll();
        System.out.println(head);
    }
}
