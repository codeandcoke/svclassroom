<%@ page contentType="text/html;charset=UTF-8" language="java" %>



<%@ include file="includes/header.jsp" %>

<main>
    <div class="container">
        <form class="row g-3" method="post" action="add-classroom">
            <div class="col-12">
                <label class="form-label">Nombre</label>
                <input type="text" class="form-control" name="name">
            </div>
            <div class="col-12">
                <label class="form-label">Descripción</label>
                <textarea class="form-control" name="description"></textarea>
            </div>
<%--            <div class="col-12">--%>
<%--                <label class="form-label">Imagen</label>--%>
<%--                <input type="file" class="form-control" name="image">--%>
<%--            </div>--%>
            <div class="col-12">
                <label class="form-label">Categoria</label>
                <input type="text" class="form-control" name="category">
            </div>
            <div class="col-12">
                <input type="submit" value="Registrar" class="btn btn-primary"/>
            </div>
        </form>
    </div>
</main>

<%@ include file="includes/footer.jsp" %>
