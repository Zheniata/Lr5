package org.example.exceptions;

public class InvalidEnumException extends RuntimeException {
    public InvalidEnumException() {
        super("Неверное значение. Доступны: TRUST, PRIVATE_LIMITED_COMPANY, OPEN_JOINT_STOCK_COMPANY");
    }
}
