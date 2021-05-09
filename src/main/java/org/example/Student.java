package org.example;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;

//Данный класс используется для отображения списка студентов

public class Student {
    private SimpleIntegerProperty id;
    private SimpleStringProperty firstName;
    private SimpleStringProperty lastName;
    private SimpleObjectProperty<LocalDate> birthDate;
    private SimpleStringProperty group;

    public Student(int id, String firstName, String lastName, LocalDate birthDate, String group) {
        this.id = new SimpleIntegerProperty(id);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.birthDate = new SimpleObjectProperty<>(birthDate);
        this.group = new SimpleStringProperty(group);
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public String getFirstName() {
        return firstName.get();
    }

    public SimpleStringProperty firstNameProperty() {
        return firstName;
    }

    public String getLastName() {
        return lastName.get();
    }

    public SimpleStringProperty lastNameProperty() {
        return lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate.get();
    }

    public SimpleObjectProperty<LocalDate> birthDateProperty() {
        return birthDate;
    }

    public String getGroup() {
        return group.get();
    }

    public SimpleStringProperty groupProperty() {
        return group;
    }
}
