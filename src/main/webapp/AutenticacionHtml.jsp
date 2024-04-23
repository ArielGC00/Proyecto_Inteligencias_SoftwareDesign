<%-- 
    Document   : AutenticacionHtml
    Created on : 19 abr 2024, 20:26:03
    Author     : josea
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Autentique su usuario</title>
        <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            padding: 20px;
            text-align: center;
        }

        form {
            max-width: 400px;
            margin: 0 auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }

        label {
            display: block;
            margin-bottom: 10px;
        }

        input[type="text"] {
            width: 100%;
            padding: 10px;
            font-size: 16px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
            margin-bottom: 20px;
        }

        button[type="submit"] {
            background-color: #007bff;
            color: #fff;
            padding: 12px 20px;
            font-size: 16px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        button[type="submit"]:hover {
            background-color: #0056b3;
        }
        a.button-link {
        text-decoration: none;
        display: inline-block;
        margin-top: 20px;
    }

    a.button-link button {
        background-color: #007bff;
        color: #fff;
        padding: 12px 20px;
        font-size: 16px;
        border: none;
        border-radius: 4px;
        cursor: pointer;
        transition: background-color 0.3s ease;
    }

    a.button-link button:hover {
        background-color: #218838;
    }
    </style>
    </head>
    <body>
        <%-- Mostrar mensaje de error si está presente --%>
    <% String mensajeError = (String) request.getAttribute("mensajeError"); %>
    <% if (mensajeError != null && !mensajeError.isEmpty()) { %>
        <div><%= mensajeError %></div>
    <% } %>
        <form action="SvTwoFactorAuth" method="POST">
            <!-- Campo para ingresar la cédula -->
            <label for="cedula">Ingrese el código númerico que le aparece en Google Authenticator:</label>
            <input type="text" id="cedula" name="autenticador" required>

            <!-- Botón para enviar el formulario -->
            <button type="submit">validar</button>
        </form>
        <a href="index.jsp" class="button-link"><button type="button">Inicio</button></a>
    </body>
</html>
