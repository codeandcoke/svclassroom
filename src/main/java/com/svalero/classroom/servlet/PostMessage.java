package com.svalero.classroom.servlet;

import com.svalero.classroom.dao.Database;
import com.svalero.classroom.dao.PostDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Date;

import static com.svalero.classroom.dao.Database.jdbi;

@WebServlet("/post-message")
public class PostMessage extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String message = request.getParameter("message");
        String classroomId = request.getParameter("classroomId");

        try {
            Database.connect();
            PostDao postDao = jdbi.onDemand(PostDao.class);
            postDao.add(title, message, new Date(), Integer.parseInt(classroomId));

            response.sendRedirect("/classroom/view-classroom.jsp?id=" + classroomId);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
