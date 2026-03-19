package com.svalero.classroom.dao;

import com.svalero.classroom.model.Classroom;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.statement.UseRowMapper;

import java.util.List;

public interface ClassroomDao {

    @SqlUpdate("INSERT INTO classrooms (name, description, image, meet_url, category) VALUES (?, ?, ?, ?, ?)")
    void add(String name, String description, String image, String meetURL, String category);

    @SqlUpdate("DELETE classrooms WHERE id = ?")
    void delete(int id);

    @SqlUpdate("UPDATE classrooms SET name = ?, description = ?, image = ?, meet_url = ?, categorya = ? WHERE id = ?")
    void modify(String name, String description, String image, String meetURL, String category, int id);

    @SqlQuery("SELECT * FROM classrooms")
    @UseRowMapper(ClassroomMapper.class)
    List<Classroom> getAll();

    @SqlQuery("SELECT * FROM classrooms WHERE categorya = ?")
    @UseRowMapper(ClassroomMapper.class)
    List<Classroom> getByCategory(String category);
}
