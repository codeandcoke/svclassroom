package com.svalero.classroom.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class User {

    private long id;
    private String name;
    private String surname;
    private String emailAddress;
    private String role;

    public String getFullName() {
        return name + " " + surname;
    }
}
