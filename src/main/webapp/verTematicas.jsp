<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Ver Temáticas</title>
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
        .tematica {
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
    <% List<String> tematicas = (List<String>) request.getSession().getAttribute("nombresTematicas"); %>
    <% List<String> idTemtaticas = (List<String>) request.getSession().getAttribute("listIdTematicas"); %>
    <% List<String> listDescripcionTematicas = (List<String>) request.getSession().getAttribute("listDescripcionTematicas"); %>
    <% List<String> listFotoTematicas = (List<String>) request.getSession().getAttribute("listFotoTematicas"); %>

    <% for (int i = 0; i < tematicas.size(); i++) { %>
        <div class="tematica">
            <h3>Temática</h3>
            <p><strong>Nombre:</strong> <%= tematicas.get(i) %></p>
            <p><strong>Descripción:</strong> <%= listDescripcionTematicas.get(i) %></p>
            <img src="src/classes/<%= listFotoTematicas.get(i) %>" alt="Foto de temática">
        </div>
    <% } %>
</body>
</html>
