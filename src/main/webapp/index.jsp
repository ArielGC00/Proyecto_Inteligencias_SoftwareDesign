<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Inicio</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            padding: 20px;
            text-align: center;
        }

        h1 {
            color: #007bff;
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
            font-weight: bold;
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
    </style>
</head>
<body>
    <h1>Inicio de sesión</h1>
<%-- Mostrar mensaje de error si está presente --%>
    <% String mensajeError = (String) request.getAttribute("mensajeError"); %>
    <% if (mensajeError != null && !mensajeError.isEmpty()) { %>
        <div><%= mensajeError %></div>
    <% } %>
    <!-- Formulario con un campo para ingresar la cédula -->
    <form action="SvUsuario" method="POST">
        <label for="cedula">Ingrese el número de cédula:</label>
        <input type="text" id="cedula" name="cedula" required>

        <!-- Botón para enviar el formulario -->
        <button type="submit">Validar</button>
    </form>
</body>
</html>
