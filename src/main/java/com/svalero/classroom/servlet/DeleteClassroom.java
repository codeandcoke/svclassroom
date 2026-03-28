package com.svalero.classroom.servlet;

import com.svalero.classroom.dao.ClassroomDao;
import com.svalero.classroom.dao.Database;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.svalero.classroom.dao.Database.jdbi;

@WebServlet("/remove-classroom")
public class DeleteClassroom extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        try {
            Database.connect();
            ClassroomDao classrommDao = jdbi.onDemand(ClassroomDao.class);
            classrommDao.delete(id);

            response.sendRedirect("/classroom");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
