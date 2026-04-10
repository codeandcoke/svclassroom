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

@WebServlet("/edit-classroom")
@MultipartConfig
public class EditClassroom extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String id = request.getParameter("id");

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
            ClassroomDao classroomDao = jdbi.onDemand(ClassroomDao.class);
            if (action.equals("Registrar")) {
                // Comprueba si ya existe un classroom con ese nombre
                if (classroomDao.getByName(name) != null) {
                    sendError(response, "Ya existe un classroom con ese nombre");
                    return;
                }
            }

            String filename = "no-image.png";
            // Procesar la imagen enviada (si se ha enviado -> getSize() > 0)
            if (image.getSize() > 0) {
                filename = UUID.randomUUID() + ".png";
                String imagePath = "/home/astable/apache-tomcat-11.0.18/webapps/classroom_images";
                InputStream inputStream = image.getInputStream();
                Files.copy(inputStream, Paths.get(imagePath + "/" + filename));
            }

            if (action.equals("Registrar")) {
                classroomDao.add(name, description, filename, UUID.randomUUID().toString(), category);
                sendSuccess(response, "Se ha añadido el classroom correctamente");
            } else {
                if (image.getSize() == 0) {
                    // El usuario no modifica la imagen (getSize() == 0)
                    classroomDao.modify(name, description, UUID.randomUUID().toString(), category, Integer.parseInt(id));
                } else {
                    // La i
                    classroomDao.modify(name, description, filename, UUID.randomUUID().toString(), category, Integer.parseInt(id));
                }
                sendSuccess(response, "Se ha modificado el classroom correctamente");
            }
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
