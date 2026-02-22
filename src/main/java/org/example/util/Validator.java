package org.example.util;

import org.example.models.Coordinates;
import org.example.models.OrganizationType;

public class Validator {

    public static void validateId(long id){
        if (id <= 0){
            throw new IllegalArgumentException("ID должно быть большее нуля");
        }
    }

    public static void validateName(String name){
        if (name == null || name.isEmpty()){
            throw new IllegalArgumentException("Имя пользователя не должно быть путсым или null");
        }
    }

    public static void validateAnnualTurnover(double turnover) {
        if (turnover <= 0) {
            throw new IllegalArgumentException("Годовой оборот должен быть больше 0");
        }
    }

    public static void validateType(OrganizationType type) {
        if (type == null) {
            throw new IllegalArgumentException("Тип организации не может быть null");
        }
    }

    public static void validateX(double x){
        if (x > 606){
            throw new IllegalArgumentException("X не должен быть больше 606");
        }
    }

    public static void validateY(long y){
        if (y <= -992){
            throw new IllegalArgumentException("Y должен быть больше -992");
        }
    }

    public static void validateCoordinates(Coordinates coordinates) {
        if (coordinates == null) {
            throw new IllegalArgumentException("Координаты не могут быть null");
        }
        Validator.validateX(coordinates.getX());
        Validator.validateY(coordinates.getY());
    }
}