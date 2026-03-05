package com.svalero.classroom.dao;

import lombok.Getter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    @Getter
    private static Connection connection;

    public static void connect() throws ClassNotFoundException, SQLException {
        if (connection == null) {
            Class.forName("org.mariadb.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/svclassroom", "classroom", "password");
        }
    }

    public static void close() throws SQLException {
        connection.close();
    }
}
