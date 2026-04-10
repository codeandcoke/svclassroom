<%@ page import="com.svalero.classroom.model.Classroom" %>
<%@ page import="com.svalero.classroom.dao.Database" %>
<%@ page import="com.svalero.classroom.dao.ClassroomDao" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="includes/header.jsp" %>

<script>
    $(document).ready(function () {
        $("#new-button").click(function (event) {
            event.preventDefault();
            const form = $("#new-form")[0];
            const data = new FormData(form);

            $("#new-button").prop("disabled", true);
            $.ajax({
                type: "POST",
                enctype: "multipart/form-data",
                url: "edit-classroom",
                data: data,
                processData: false,
                contentType: false,
                cache: false,
                timeout: 600000,
                success: function (data) {
                    $("#result").html(data);
                    $("#new-button").prop("disabled", false);
                },
                error: function (error) {
                    $("#result").html(error.responseText);
                    $("#new-button").prop("disabled", false);
                }
            });
        });
    });
</script>

<%
    String id = request.getParameter("id");
    String action = "Registrar";
    Classroom classroom = null;
    if (id != null) {
        action = "Editar";
        Database.connect();
        ClassroomDao classroomDao = Database.jdbi.onDemand(ClassroomDao.class);
        classroom = classroomDao.getById(Integer.parseInt(id));
    }
%>

<main>
    <div class="container">
        <form id="new-form" class="row g-3" method="post" enctype="multipart/form-data">
            <div class="col-12">
                <label class="form-label">Nombre</label>
                <input type="text" class="form-control" name="name" value="<%= classroom != null ? classroom.getName() : "" %>">
            </div>
            <div class="col-12">
                <label class="form-label">Descripción</label>
                <textarea class="form-control" name="description"><%= classroom != null ? classroom.getDescription() : "" %></textarea>
            </div>
            <div class="col-12">
                <label class="form-label">Imagen</label>
                <input type="file" class="form-control" name="image">
            </div>
            <div class="col-12">
                <label class="form-label">Categoria</label>
                <input type="text" class="form-control" name="category" value="<%= classroom != null ? classroom.getCategory() : "" %>">
            </div>
            <div class="col-12">
                <button id="new-button" class="btn btn-primary"><%= action %></button>
            </div>
            <input type="hidden" name="id" value="<%= id %>"/>
            <input type="hidden" name="action" value="<%= action %>"/>
        </form>
        <br/>
        <div id="result"></div>
    </div>
</main>

<%@ include file="includes/footer.jsp" %>
