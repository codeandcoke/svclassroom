<%@ page import="com.svalero.classroom.dao.Database" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.svalero.classroom.model.Classroom" %>
<%@ page import="java.util.List" %>
<%@ page import="static com.svalero.classroom.dao.Database.jdbi" %>
<%@ page import="com.svalero.classroom.dao.ClassroomDao" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<html lang="en" data-bs-theme="auto">
  <head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta name="description" content="" />
    <title>San Valero Classroom</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
  </head>
  <body>
    <main>
      <section class="py-5 text-center container">
        <div class="row py-lg-5">
          <div class="col-lg-6 col-md-8 mx-auto">
            <h1 class="fw-light">Classrooms</h1>
            <p class="lead text-body-secondary">
              Estos son tus classrooms activos
            </p>
            <p>
              <a href="#" class="btn btn-primary my-2">Crear un classroom</a>
            </p>
          </div>
        </div>
      </section>
      <div class="album py-5 bg-body-tertiary">
        <div class="container">
          <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
            <%
              List<Classroom> allClassrooms = new ArrayList<>();

              try {
                Database.connect();
                ClassroomDao classrommDao = jdbi.onDemand(ClassroomDao.class);
                allClassrooms.addAll(classrommDao.getAll());
              } catch (ClassNotFoundException cnfe) {
                cnfe.printStackTrace();
              }

              for (Classroom classroom : allClassrooms) {
            %>
            <div class="col">
              <div class="card shadow-sm">
                <a href="#">
                  <img src="images/<%= classroom.getImage() %>" width="400" height="250">
                </a>
                <div class="card-body">
                  <p class="card-text">
                    <%= classroom.getDescription() %>
                  </p>
                  <div
                    class="d-flex justify-content-between align-items-center"
                  >
                    <div class="btn-group">
                      <button type="button" class="btn btn-sm btn-outline-warning">
                        Editar
                      </button>
                      <button type="button" class="btn btn-sm btn-outline-danger">
                        Eliminar
                      </button>
                    </div>
                    <small class="text-body-secondary"><%= classroom.getCategory() %></small>
                  </div>
                </div>
              </div>
            </div>
            <%
              }
            %>
          </div>
        </div>
      </div>
    </main>
    <footer class="text-body-secondary py-5">
      <div class="container">
        <p class="mb-1">
          San Valero Classroom
        </p>
        <p class="mb-0">
          (c) 2025 - Clase de Programación de 1º DAM-DAW
        </p>
      </div>
    </footer>
  </body>
</html>
