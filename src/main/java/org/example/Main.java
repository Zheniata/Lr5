package org.example;

import org.example.commands.*;
import org.example.managers.CollectionManager;
import org.example.managers.CommandManager;
import org.example.managers.XmlFileManager;
import org.example.models.ObjectCreator;
import org.example.models.Organization;
import org.example.util.OrganizationCreator;
import org.example.util.Runner;

import java.util.Scanner;
/**
 * Точка входа в приложение.
 * Инициализирует менеджеры, регистрирует команды и запускает интерактивный режим.
 */
public class Main {
    private static final String fileName = System.getenv("XML_FILENAME");
    public static void main (String[] args){
        Scanner scanner = new Scanner(System.in);
        XmlFileManager xmlFileManager = new XmlFileManager(fileName);
        CollectionManager<Organization> collectionManager = new CollectionManager<>(xmlFileManager);
        ObjectCreator<Organization> creator = new OrganizationCreator();

        CommandManager commandManager = new CommandManager(){{
            register("help", new Help(this));
            register("add", new Add(collectionManager, scanner, creator));
            register("clear", new Clear(collectionManager));
            register("show", new Show(collectionManager));
            register("info", new Info(collectionManager));
            register("remove_by_id", new RemoveById(collectionManager));
            register("remove_head", new RemoveHead(collectionManager));
            register("history", new History(this));
            register("print_descending", new PrintDescending(collectionManager));
            register("print_field_descending_type", new PrintFieldDescendingType(collectionManager));
            register("remove_any_by_official_address", new RemoveAnyByOfficialAddress(collectionManager));
            register("remove_greater", new RemoveGreater(collectionManager));
            register("update", new Update(collectionManager, scanner, creator));
            register("execute_script", new ExecuteScript(this, collectionManager, creator));
            register("save", new Save(collectionManager));
            register("exit", new Exit());
        }};

        new Runner(commandManager, scanner).interactiveMode();
        scanner.close();

    }
}