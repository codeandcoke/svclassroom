<%@ page import="com.svalero.classroom.dao.Database" %>
<%@ page import="com.svalero.classroom.dao.ClassroomDao" %>
<%@ page import="static com.svalero.classroom.dao.Database.jdbi" %>
<%@ page import="com.svalero.classroom.model.Classroom" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="includes/header.jsp" %>

<%
    int id = Integer.parseInt(request.getParameter("id"));
    Classroom classroom = null;

    try {
        Database.connect();
        ClassroomDao classroomDao = jdbi.onDemand(ClassroomDao.class);
        classroom = classroomDao.getById(id);
    } catch (ClassNotFoundException cnfe) {
        cnfe.printStackTrace();
    }
%>
<main>
    <div class="container">
        <h1>Bienvenido a <%= classroom.getName() %></h1>
        <img width="600" height="400" alt="<%= classroom.getName() %>" src="../classroom_images/<%= classroom.getImage() %>" />
    </div>
</main>

<%@ include file="includes/footer.jsp" %>
