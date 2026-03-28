package com.svalero.classroom.servlet;

import com.svalero.classroom.dao.ClassroomDao;
import com.svalero.classroom.dao.Database;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

import static com.svalero.classroom.dao.Database.jdbi;

@WebServlet("/add-classroom")
public class AddClassroom extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String category = request.getParameter("category");

        try {
            Database.connect();
            ClassroomDao classrommDao = jdbi.onDemand(ClassroomDao.class);
            classrommDao.add(name, description, "", UUID.randomUUID().toString(), category);

            response.sendRedirect("/classroom");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
