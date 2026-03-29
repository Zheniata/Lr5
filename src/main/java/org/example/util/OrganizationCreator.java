package org.example.util;

import org.example.models.*;

import java.time.LocalDate;

public class OrganizationCreator implements ObjectCreator<Organization> {
    @Override
    public Organization create(long id, LocalDate creationDate, String name, Coordinates coordinates, float annualTurnover, OrganizationType type, Address address) {
        return new Organization(id, creationDate, name, coordinates, annualTurnover, type, address);
    }
}
