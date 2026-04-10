<%@ page import="com.svalero.classroom.dao.Database" %>
<%@ page import="com.svalero.classroom.dao.ClassroomDao" %>
<%@ page import="static com.svalero.classroom.dao.Database.jdbi" %>
<%@ page import="com.svalero.classroom.model.Classroom" %>
<%@ page import="com.svalero.classroom.dao.PostDao" %>
<%@ page import="com.svalero.classroom.model.Post" %>
<%@ page import="java.util.List" %>
<%@ page import="com.svalero.classroom.util.DateUtils" %>
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

        <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
            <form id="new-form" class="row g-3" method="post" action="post-message">
                <div class="col-12">
                    <label class="form-label">Nombre</label>
                    <input type="text" class="form-control" name="title" value="">
                </div>
                <div class="col-12">
                    <label class="form-label">Descripción</label>
                    <textarea class="form-control" name="message"></textarea>
                </div>
                <div class="col-12">
                    <button id="new-button" class="btn btn-primary">Enviar</button>
                </div>
                <input type="hidden" name="classroomId" value="<%= classroom.getId() %>"/>
            </form>
            <br/>
            <div id="result"></div>
        </div>
        <br/>
        <!-- Carga los mensajes del classroom -->
        <%
            PostDao postDao = Database.jdbi.onDemand(PostDao.class);
            List<Post> postList = postDao.getAll(classroom.getId());
            if (postList.isEmpty()) {
        %>
        <div class="alert alert-info" role="alert">
            No hay ningún mensaje publicado
        </div>
        <%
            } else {
                for (Post post : postList) {
        %>
        <h3><%= post.getTitle() %></h3>
        <h6><i><%= DateUtils.printDate(post.getDate()) %></i></h6>
        <p>
            <%= post.getMessage() %>
        </p>
        <%
                }
            }
        %>
    </div>
</main>

<%@ include file="includes/footer.jsp" %>
