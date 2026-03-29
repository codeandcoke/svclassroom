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
                url: "add-classroom",
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

<main>
    <div class="container">
        <form id="new-form" class="row g-3" method="post" enctype="multipart/form-data">
            <div class="col-12">
                <label class="form-label">Nombre</label>
                <input type="text" class="form-control" name="name">
            </div>
            <div class="col-12">
                <label class="form-label">Descripción</label>
                <textarea class="form-control" name="description"></textarea>
            </div>
            <div class="col-12">
                <label class="form-label">Imagen</label>
                <input type="file" class="form-control" name="image">
            </div>
            <div class="col-12">
                <label class="form-label">Categoria</label>
                <input type="text" class="form-control" name="category">
            </div>
            <div class="col-12">
                <button id="new-button" class="btn btn-primary">Registrar</button>
            </div>
        </form>
        <br/>
        <div id="result"></div>
    </div>
</main>

<%@ include file="includes/footer.jsp" %>
