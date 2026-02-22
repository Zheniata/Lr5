package org.example.managers;

import org.example.models.Organization;

import java.time.LocalDateTime;
import java.util.PriorityQueue;

public class CollectionManager {
    private PriorityQueue<Organization> collection = new PriorityQueue<Organization>();
    private final LocalDateTime initTime = LocalDateTime.now();


    public Organization getById(long id){
        for (Organization element: collection){
            if(element.getId() == id){
                return element;
            }
        }
        return null;
    }

    public void addToCollection(Organization element){
        collection.add(element);
        Organization.touchNextId();
    }

    public void removeFromCollection(Organization element){
        collection.remove(element);
    }

    public boolean checkExist(long id){
        for (Organization element: collection){
            if (element.getId() == id){
                return true;
            }
        }
        return false;
    }

     public void clearCollection(){
        collection.clear();
     }

    public int size() {
        return collection.size();
    }

    public boolean isEmpty() {
        return collection.isEmpty();
    }

    public void clear(){
        collection.clear();
    }

    public PriorityQueue<Organization> getCollection() {
        return collection;
    }

    public LocalDateTime getTime(){
        return initTime;
    }
}
