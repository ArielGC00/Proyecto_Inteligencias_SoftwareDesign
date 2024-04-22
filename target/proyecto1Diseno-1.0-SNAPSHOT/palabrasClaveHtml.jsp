<%-- 
    Document   : palabrasClaveHtml
    Created on : 19 abr 2024, 14:31:02
    Author     : Celeste
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Palabras Clave</title>
        <style>
            .container {
                margin: 50px auto;
                padding: 20px;
                width: 80%;
                max-width: 600px;
                text-align: center;
                border: 2px solid #ccc;
                border-radius: 10px;
                background-color: #f9f9f9;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }

            h2 {
                color: #333;
            }

            h1 {
                color: #007bff;
                margin-top: 20px;
            }

            a {
                color: #007bff;
                text-decoration: none;
            }

            a:hover {
                text-decoration: underline;
            }
        </style>
    </head>
    <body>
        <% String nombre = (String) request.getSession().getAttribute("nombreUsuario"); %>
    <div class="container">
        <h2>Hola <%=nombre%></h2>
        <% String palabrasClaveTexto = (String) request.getSession().getAttribute("palabrasClaveTexto"); %>
        <h1>"<%= palabrasClaveTexto  %>"</h1>
        <br>
        <a href="#" onclick="history.back();">Volver</a>
    </div>
    </body>
</html>
