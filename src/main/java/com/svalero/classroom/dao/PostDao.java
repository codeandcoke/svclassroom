package com.svalero.classroom.dao;

import com.svalero.classroom.model.Post;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.statement.UseRowMapper;

import java.util.Date;
import java.util.List;

public interface PostDao {
    @SqlUpdate("INSERT INTO posts (title, message, date, classroom_id) VALUES (?, ?, ?, ?)")
    void add(String title, String message, Date date, int classroomId);

    @SqlUpdate("DELETE FROM posts WHERE id = ?")
    void delete(int id);

    @SqlUpdate("UPDATE posts SET title = ?, message = ? WHERE id = ?")
    void modify(String title, String message, int id);

    @SqlQuery("SELECT * FROM posts WHERE classroom_id = ? ORDER BY date DESC")
    @UseRowMapper(PostMapper.class)
    List<Post> getAll(int classroomId);
}
