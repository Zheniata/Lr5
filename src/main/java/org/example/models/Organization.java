package org.example.models;

import java.time.LocalDate;
import java.util.Objects;
import org.example.util.Validator;

public class Organization implements Comparable<Organization>{
    private long id;
    private String name;
    private Coordinates coordinates;
    private LocalDate creationDate;
    private float annualTurnover;
    private OrganizationType type;
    private Address officialAddress;

    private static int nextId = 1;

    public Organization(String name, Coordinates coordinates, float annualTurnover, OrganizationType type, Address officialAddress){
        Validator.validateName(name);
        Validator.validateAnnualTurnover(annualTurnover);
        Validator.validateType(type);
        Validator.validateCoordinates(coordinates);

        this.id = nextId;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = LocalDate.now();
        this.annualTurnover = annualTurnover;
        this.type = type;
        this.officialAddress = officialAddress;
    }

    public long getId() {
        return id;
    }

    public static void touchNextId() {
        nextId++;
    }

    @Override
    public int compareTo(Organization o) {
        return Long.compare(this.id, o.id);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return id == that.id && Float.compare(annualTurnover, that.annualTurnover) == 0 && Objects.equals(name, that.name)
                && Objects.equals(coordinates, that.coordinates) && Objects.equals(creationDate, that.creationDate)
                && type == that.type && Objects.equals(officialAddress, that.officialAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate, annualTurnover, type, officialAddress);
    }

    @Override
    public String toString() {
        return "Organization{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", annualTurnover=" + annualTurnover +
                ", type=" + type +
                ", officialAddress=" + officialAddress +
                '}';
    }
}
