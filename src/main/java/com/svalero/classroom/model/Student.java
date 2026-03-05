package com.svalero.classroom.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Student {

    private long id;
    private String name;
    private String surname;
    private String phoneNumber;
    private String address;
    private LocalDate birthDate;

    public String getFullName() {
        return name + " " + surname;
    }
}
