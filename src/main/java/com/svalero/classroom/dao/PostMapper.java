package com.svalero.classroom.dao;

import com.svalero.classroom.model.Post;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PostMapper implements RowMapper<Post> {
    @Override
    public Post map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new Post(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("message"),
                rs.getDate("date"),
                rs.getInt("classroom_id")
        );
    }
}
