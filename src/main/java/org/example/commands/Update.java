package org.example.commands;

import org.example.exceptions.MustBeNotEmptyException;
import org.example.exceptions.ValidationException;
import org.example.managers.CollectionManager;
import org.example.models.Address;
import org.example.models.Coordinates;
import org.example.models.Organization;
import org.example.models.OrganizationType;

import java.util.Scanner;

/**
 * Команда обновления существующей организации по её ID.
 * Запрашивает у пользователя новые данные (имя, координаты, годовой оборот, тип, адрес)
 * и заменяет старую организацию на новую с тем же ID и датой создания.
 */

public class Update extends Command{
    CollectionManager collectionManager;
    private Scanner scanner;

    public Update(CollectionManager collectionManager, Scanner scanner){
        super("update");
        this.collectionManager = collectionManager;
        this.scanner = scanner;
    }

    /**
     * Выполняет обновление организации.
     * Требует один аргумент — ID существующей организации.
     * После валидации ID запрашивает новые данные и заменяет объект в коллекции.
     *
     * @param args массив аргументов, где args[0] — строковое представление ID
     */

    @Override
    public void execute(String[] args) {
        if (args.length != 1){
            System.out.println("Неверный id, попробуйте еще раз");
            return;
        }
        if (args[0] == null){
            System.out.println("Id не может быть равен null, попробуйте еще раз");
            return;
        }
        long id = 0;
        try{
            id = Long.parseLong(args[0]);
        }
        catch (NumberFormatException e){
            System.out.println("Неверный формат id");
        }
        if (!collectionManager.checkExist(id)){
            System.out.println("Организации с id = " + id + " не сущетсвует");
        }
        else {
            Organization org = collectionManager.getById(id);
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
                System.out.print("Введите тип [TRUST, PRIVATE_LIMITED_COMPANY, OPEN_JOINT_STOCK_COMPANY]: ");
                String typeStr = scanner.nextLine().trim().toUpperCase();
                try {
                    type = OrganizationType.valueOf(typeStr);
                    break;
                }
                catch (IllegalArgumentException e) {
                    System.out.println("Такого type не сущетсвует, введите еще раз");
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
                new Organization(name, coordinates, annualTurnover, type, address,true);
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
            Organization updated = new Organization(org.getId(), org.getCreationDate(),
                    name, coordinates, annualTurnover, type, address);
            collectionManager.removeFromCollection(org);
            collectionManager.addToCollection(updated);

            System.out.println("Организация с id = " + id + " обновлена");
        }
    }
}
