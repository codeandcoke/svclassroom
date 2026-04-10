package com.svalero.classroom.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Post {

    private int id;
    private String title;
    private String message;
    private Date date;
    private int classroomId;
}
