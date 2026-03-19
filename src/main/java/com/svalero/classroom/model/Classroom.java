package com.svalero.classroom.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Classroom {
    private int id;
    private String name;
    private String description;
    private String image;
    private String meetURL;
    private String category;
}
