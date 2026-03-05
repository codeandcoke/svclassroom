package com.svalero.classroom.dao;

import com.svalero.classroom.model.Student;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {

    public static void add(Student student) throws SQLException {
        String sql = "INSERT INTO students (name, surname, phone_number, address, birth_date) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement preparedStatement = Database.getConnection().prepareStatement(sql);
        preparedStatement.setString(1, student.getName());
        preparedStatement.setString(2, student.getSurname());
        preparedStatement.setString(3, student.getPhoneNumber());
        preparedStatement.setString(4, student.getAddress());
        // FIXME preparedStatement.setDate(5, student.getBirthDate());

        // TODO Comprobar si realmente se ha insertado el dato
        preparedStatement.executeUpdate();
    }

    public static void remove(int id) {

    }

    public static List<Student> getAll() throws SQLException {
        String sql = "SELECT * FROM students";

        PreparedStatement preparedStatement = Database.getConnection().prepareStatement(sql);
        ResultSet result = preparedStatement.executeQuery();

        List<Student> studentList = new ArrayList<>();
        while (result.next()) {
            Student student = new Student();
            student.setId(result.getLong("id"));
            student.setName(result.getString("name"));
            student.setSurname(result.getString("surname"));
            student.setPhoneNumber(result.getString("phone_number"));
            student.setAddress(result.getString("address"));
            // FIXME student.setBirthDate(result.getDate("birth_date"));

            studentList.add(student);
        }

        return studentList;
    }

    public static void update(int id, Student student) {

    }
}
