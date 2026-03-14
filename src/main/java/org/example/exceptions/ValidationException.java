package org.example.exceptions;
/**
 * Общее исключение, используемое для сигнализации о нарушении правил валидации данных.
 * Например: недопустимые координаты, отрицательный годовой оборот и т.д.
 * Является unchecked-исключением (наследует {@link RuntimeException}).
 */
public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}
