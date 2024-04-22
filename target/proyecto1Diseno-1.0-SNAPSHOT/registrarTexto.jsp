<%-- 
    Esto es de prueba BORRAR
--%>

<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="styles.css">
    </head>
    <body>
        <% List<String> tematicas = (List<String>) request.getSession().getAttribute("nombresTematicas"); %> <!<!-- esta lista es del get del SvTematica -->
        <% List<String> idTemtaticas = (List<String>) request.getSession().getAttribute("listIdTematicas"); %> <!<!-- esta lista es del get del SvTematica -->
        
        <h2>Registrar Nuevo texto</h2>
        <form  action="SvTexto" method="POST">
            <label>Texto nuevo:</label><br>
            <textarea name="descripcionTexto" rows="4" cols="50"></textarea><br><br>
            <label for="tematicasSelect">Selecciona una temática:</label>
            <select id="tematicasSelect" name="tematicaSeleccionada">
                <option value="" disabled selected>Selecciona una temática</option>
                <%-- Iterar sobre la lista de temáticas y agregar opciones --%>
                <% for (int i = 0; i < tematicas.size(); i++) { %>
                    <option value="<%= idTemtaticas.get(i) %>"><%= tematicas.get(i) %></option>
                <% } %>
            </select>
            <button type="submit" >Registrar texto</button>
        </form>
            <h2>Registrar Nueva Temática:</h2>
    <form id="tematicaForm" action="SvTematica" method="POST">
        <label>Nombre:</label>
        <input type="text" name="nombreTematica"><br><br>
        <label>Descripción:</label><br>
        <textarea name="descripcionTematica" rows="4" cols="50"></textarea><br><br>
        <label>Foto (URL):</label>
        <input type="text" name="fotoTematica"><br><br>
        <button type="submit" >Registrar Temática</button>
    </form>
            
        <a href="tematicasRegistradas.jsp">Volver</a><!-- Enlace para volver a la página anterior -->
    </body>
</html>
