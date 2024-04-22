<%-- 
    Document   : sentimientoTextoHtml
    Created on : 15 abr 2024, 21:37:28
    Author     : josea
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="styleSentimientoHtml.css">
    </head>
    <body>
        <% String nombre = (String) request.getSession().getAttribute("nombreUsuario"); %>
    <div class="container">
        <h2>Hola <%=nombre%></h2>
        <% String sentimientoTexto = (String) request.getSession().getAttribute("sentimientoTextoCompleto"); %>
        <h2>Sentimiento del texto:</h2>
        <h1>"<%= sentimientoTexto %>"</h1>
        <br>
        <a href="#" onclick="history.back();">Volver</a>
    </div>
    </body>
</html>
