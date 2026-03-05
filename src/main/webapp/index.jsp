<%@ page import="com.svalero.classroom.dao.Database" %>
<%@ page import="com.svalero.classroom.dao.StudentDao" %>
<%@ page import="com.svalero.classroom.model.Student" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>San Valero Classroom</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
</head>
<body>
<%
    List<Student> studentList = new ArrayList<>();

    try {
        Database.connect();
        studentList.addAll(StudentDao.getAll());
    } catch (SQLException sqle) {
        sqle.printStackTrace();
    } catch (ClassNotFoundException cnfe) {
        cnfe.printStackTrace();
    }
%>
<div class="container">
    <h1>San Valero Classroom</h1>
    <button type="button" class="btn btn-info">Matricular alumno</button>

    <p>Hay <%= studentList.size() %> alumnos matriculados</p>

    <ul class="list-group">
        <%
            for (Student student : studentList) {
        %>
        <li class="list-group-item"><%= student.getFullName() %></li>
        <%
            }
        %>
    </ul>
</div>
</body>
</html>