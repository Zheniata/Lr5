package org.example.managers;

import org.example.models.Organization;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class CollectionManager {
    private PriorityQueue<Organization> collection = new PriorityQueue<Organization>();
    private LocalDateTime lastInitTime;
    private LocalDateTime lastSaveTime;
    private final XmlFileManager fileManager;

    public CollectionManager(XmlFileManager fileManager){
        this.fileManager = fileManager;
        loadCollection();
    }

    private void loadCollection(){
        try {
            List<Organization> loaded = fileManager.readCollection();
            for(Organization org : loaded) {
                Organization.touchNextId();
                org.setId(Organization.getNextId());
            }
            collection.clear();
            collection.addAll(loaded);
            lastInitTime = LocalDateTime.now();
            System.out.println("Загружены данные из файла " + System.getenv("XML_FILENAME") + ". В коллекции " + loaded.size() + " объект(а/ов).");
        }
        catch (Exception e) {
            System.err.println("Ошибка при загрузке (или файл отсутсвует)");
            collection.clear();
        }
    }

    public void saveCollection(){
        try {
            fileManager.writeCollection(new ArrayList<>(collection));
            lastSaveTime = LocalDateTime.now();
            System.out.println("Коллекция успешна завершена");
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении");
        }
    }

    public Organization getById(long id){
        for (Organization element: collection){
            if(element.getId() == id){
                return element;
            }
        }
        return null;
    }

    public void addToCollection(Organization element){
        //Organization.touchNextId();
        collection.add(element);
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
        return lastInitTime;
    }
    public LocalDateTime getLastSaveTime() {
        return lastSaveTime;
    }
}
