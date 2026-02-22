package org.example.commands;

import org.example.managers.CollectionManager;
import org.example.managers.CommandManager;
import java.util.Scanner;
import org.example.exceptions.*;
import org.example.models.Address;
import org.example.models.Coordinates;
import org.example.models.Organization;
import org.example.models.OrganizationType;

public class Add extends Command {
    private CollectionManager collectionManager;
    private Scanner scanner;

    public Add(CollectionManager collectionManager, Scanner scanner){
        super("add");
        this.collectionManager = collectionManager;
        this.scanner = scanner;
    }

    @Override
    public void execute(String[] args) {

        String name;
        while (true){
            try {
                System.out.println("Введите имя:");
                name = scanner.nextLine().trim();
                if (name.isEmpty()) {
                    throw new MustBeNotEmptyException();
                }
                break;
            }
            catch (MustBeNotEmptyException e){
                System.out.println(e.getMessage());
            }
        }

        double x;
        while (true) {
            System.out.println("Введите координуту X:");
            String strX = scanner.nextLine().trim();
            try {
                x = Double.parseDouble(strX);
                if (x > 660) {
                    throw new ValidationException("x не может быть больше 660");
                }
                break;
            } catch (NumberFormatException e) {
            System.out.println("Неверный формат. Введите число");
            } catch (ValidationException e) {
            System.out.println(e.getMessage());
            }
        }

        long y;
        while (true) {
            System.out.println("Введите координату Y:");
            String strY = scanner.nextLine().trim();
            try {
                y = Long.parseLong(strY);
                if (y <= -992) {
                    throw new ValidationException("y должен быть больше -992");
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Неверный формат. Введите число");
            } catch (ValidationException e) {
                System.out.println(e.getMessage());
            }
        }
        Coordinates coordinates = new Coordinates(x, y);


        float annualTurnover;
        while (true) {
            System.out.println("Введите значение годового оборота:");
            String strAnnualTurnover = scanner.nextLine().trim();
            try {
                annualTurnover = Float.parseFloat(strAnnualTurnover);
                if (annualTurnover <= 0) {
                    throw new ValidationException("Значение должно быть больше нуля");
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Неверный формат. Введите число");
            } catch (ValidationException e) {
                System.out.println(e.getMessage());
            }
        }

        OrganizationType type;
        while (true) {
            System.out.print("Тип [TRUST, PRIVATE_LIMITED_COMPANY, OPEN_JOINT_STOCK_COMPANY]: ");
            String typeStr = scanner.nextLine().trim().toUpperCase();
            try {
                type = OrganizationType.valueOf(typeStr);
                break;
            }
            catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("Введите улицу:");
        String street = scanner.nextLine().trim();
        if (street.isEmpty()){
            street = null;
        }
        System.out.println("Введите индекс:");
        String zipCode = scanner.nextLine().trim();
        if (zipCode.isEmpty()){
            zipCode = null;
        }
        Address address;
        if (street != null || zipCode != null){
            address = new Address(street, zipCode);
        } else {
            address = null;
        }

        try {
            Organization organization = new Organization(name, coordinates, annualTurnover, type, address);
            collectionManager.addToCollection(organization);
            System.out.println("Организация добавлена");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

}

