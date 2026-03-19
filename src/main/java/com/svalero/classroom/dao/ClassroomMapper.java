package com.svalero.classroom.dao;

import com.svalero.classroom.model.Classroom;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClassroomMapper implements RowMapper<Classroom> {
    @Override
    public Classroom map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new Classroom(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("description"),
                rs.getString("image"),
                rs.getString("meet_url"),
                rs.getString("category")
        );
    }
}
