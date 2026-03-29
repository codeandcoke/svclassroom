package com.svalero.classroom.servlet;

import com.svalero.classroom.dao.ClassroomDao;
import com.svalero.classroom.dao.Database;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import static com.svalero.classroom.dao.Database.jdbi;

@WebServlet("/add-classroom")
@MultipartConfig
public class AddClassroom extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        if (name.isEmpty()) {
            sendError(response, "El classroom debe tener nombre");
            return;
        }
        String description = request.getParameter("description");
        Part image = request.getPart("image");
        String category = request.getParameter("category");

        try {
            Database.connect();
            ClassroomDao classrommDao = jdbi.onDemand(ClassroomDao.class);
            // Comprueba si ya existe un classroom con ese nombre
            if (classrommDao.getByName(name) != null) {
                sendError(response, "Ya existe un classroom con ese nombre");
                return;
            }

            // Procesar la imagen enviada
            String filename = "";
            if (image.getSize() > 0) {
                filename = UUID.randomUUID() + ".png";
                String imagePath = "/home/astable/apache-tomcat-11.0.18/webapps/classroom_images";
                InputStream inputStream = image.getInputStream();
                Files.copy(inputStream, Paths.get(imagePath + "/" + filename));
            }

            classrommDao.add(name, description, filename, UUID.randomUUID().toString(), category);
            sendSuccess(response, "Se ha añadido el classroom correctamente");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void sendSuccess(HttpServletResponse response, String message) throws IOException {
        sendMessage(response, "success", message);
    }

    private void sendError(HttpServletResponse response, String message) throws IOException {
        sendMessage(response, "danger", message);
    }

    private void sendMessage(HttpServletResponse response, String type, String message) throws IOException {
        response.getWriter().println("<div class=\"alert alert-" + type + "\" role=\"alert\">\n" + message + "</div>");
    }
}
