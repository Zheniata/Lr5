package org.example.models;

import java.time.LocalDate;

public interface ObjectCreator<T> {
    T create(long id, LocalDate creationDate, String name, Coordinates coordinates,
             float annualTurnover, OrganizationType type, Address address);
}
