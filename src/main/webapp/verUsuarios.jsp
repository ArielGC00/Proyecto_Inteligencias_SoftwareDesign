<%-- 
    Document   : verUsuarios
    Created on : 20 abr 2024, 23:04:29
    Author     : Celeste
--%>

<%@page import="java.util.Collections"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Ver Usuarios</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            padding: 20px;
            margin: 0;
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
        }
        .usuario {
            background-color: #ffffff;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
            padding: 20px;
            margin: 20px;
            width: 300px;
            text-align: center;
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        h3 {
            color: #007bff;
            margin-bottom: 10px;
        }
        p {
            color: #555;
            margin-bottom: 10px;
        }
        img {
            max-width: 100%;
            height: auto;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
            margin-top: 10px;
        }
    </style>
</head>
<body>
    <% ArrayList<ArrayList<String>> listaUsuarios = (ArrayList<ArrayList<String>>) request.getSession().getAttribute("listaUsuarios"); %>
   
        <% for (ArrayList<String> usuario : listaUsuarios) { %>
        <div class="usuario">
            <h3>Información de Usuario</h3>
            <%-- Mostrar los campos de información del usuario --%>
            <p><strong>ID:</strong> <%= usuario.get(0) %></p>
            <p><strong>Nombre Completo:</strong> <%= usuario.get(1) %></p>
            <p><strong>Correo Electrónico:</strong> <%= usuario.get(2) %></p>
            <p><strong>Número Telefónico:</strong> <%= usuario.get(3) %></p>
            <%-- Mostrar la foto de perfil del usuario --%>
            <img src="imagenes/<%= usuario.get(4) %>" alt="Foto de Usuario">
        </div>
    <% } %>
</body>
</html>
