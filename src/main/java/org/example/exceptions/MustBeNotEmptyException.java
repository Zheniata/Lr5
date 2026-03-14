package org.example.exceptions;
/**
 * Исключение, выбрасываемое при попытке установить пустое или {@code null} значение
 * в поле, которое не может быть пустым (например, имя организации).
 * Является unchecked-исключением (наследует {@link RuntimeException}).
 */
public class MustBeNotEmptyException extends RuntimeException {
    public MustBeNotEmptyException() {
        super("Поле не может быть пустым");
    }
}
