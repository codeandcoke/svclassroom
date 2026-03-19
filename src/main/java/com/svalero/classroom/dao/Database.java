package com.svalero.classroom.dao;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

public class Database {

    public static Jdbi jdbi;
    public static Handle db;

    public static void connect() throws ClassNotFoundException {
        Class.forName("org.mariadb.jdbc.Driver");
        // TODO Utilizar fichero de configuración en lugar de hardcodear contraseñas aqui
        jdbi = Jdbi.create("jdbc:mariadb://localhost:3306/svclassroom", "classroom", "password");
        jdbi.installPlugin(new SqlObjectPlugin());
        db = jdbi.open();
    }

    public static void close() {
        db.close();
    }
}
