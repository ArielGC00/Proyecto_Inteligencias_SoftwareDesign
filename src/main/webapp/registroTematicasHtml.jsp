<%-- 
    Document   : registroTematicasHtml
    Created on : 23 abr 2024, 19:32:20
    Author     : josea
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="styles.css">
        <title>JSP Page</title>
    </head>
    <body>

    <h2>Registrar Nueva Temática:</h2>
    <form id="tematicaForm" action="SvTematica" method="POST" enctype="multipart/form-data">
        <label>Nombre:</label>
        <input type="text" name="nombreTematica" required><br><br>
        <label>Descripción:</label><br>
        <textarea name="descripcionTematica" required rows="4" cols="50"></textarea><br><br>
        <label>Imagen:</label>
        <input type="file" name="fotoTematica" required accept="image/*"><br><br>
        <button type="submit">Registrar Temática</button>
    </form>
    <%-- Mostrar mensaje de error si está presente --%>
    <% String mensajeError = (String) request.getAttribute("mensajeError"); %>
    <% if (mensajeError != null && !mensajeError.isEmpty()) { %>
        <div><%= mensajeError %></div>
    <% } %>
    <a href="registro_exitoso.jsp"><button type="button">Volver al menú principal</button></a>
    <a href="registrarTexto.jsp"><button type="button">Registrar textos</button></a>
    </body>
</html>
