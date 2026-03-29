<%@ page import="com.svalero.classroom.dao.Database" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.svalero.classroom.model.Classroom" %>
<%@ page import="java.util.List" %>
<%@ page import="static com.svalero.classroom.dao.Database.jdbi" %>
<%@ page import="com.svalero.classroom.dao.ClassroomDao" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="includes/header.jsp" %>
    <main>
      <section class="py-5 text-center container">
        <div class="row py-lg-5">
          <div class="col-lg-6 col-md-8 mx-auto">
            <h1 class="fw-light">Classrooms</h1>
            <p class="lead text-body-secondary">
              Estos son tus classrooms activos
            </p>
            <p>
              <a href="new-classroom.jsp" class="btn btn-primary my-2">Crear un classroom</a>
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
                <a href="view-classroom.jsp?id=<%= classroom.getId() %>">
                  <img src="../classroom_images/<%= classroom.getImage() %>" width="400" height="250">
                </a>
                <div class="card-body">
                  <p class="card-text">
                    <%= classroom.getDescription() %>
                  </p>
                  <div
                    class="d-flex justify-content-between align-items-center"
                  >
                    <div class="btn-group">
                      <a href="#" type="button" class="btn btn-sm btn-outline-warning">
                        Editar
                      </a>
                      <a href="remove-classroom?id=<%= classroom.getId() %>" type="button" class="btn btn-sm btn-outline-danger"
                        onclick="return confirm('¿Estás seguro?')">
                        Eliminar
                      </a>
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

<%@ include file="includes/footer.jsp" %>
