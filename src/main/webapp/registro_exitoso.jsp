<%@page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Registro Exitoso</title>
    <link rel="stylesheet" href="styles.css">
    <link rel="stylesheet" href="styleRegistroExitoso.css">
</head>
<body>
    <h1>¡Ingreso exitoso!</h1>
    <% String nombre = (String) request.getSession().getAttribute("nombreUsuario"); %>
    <% String foto = (String) request.getSession().getAttribute("fotoUsuario"); %>
    <h1>Bienvenido, <%= nombre %>!</h1>
    <!-- Mostrar la imagen del usuario -->
    <img src="imagenes/<%= foto %>" alt="Foto de Usuario" style="width: 300px; height: 350px;">
    
    
    <!-- Otros contenidos de la página de registro exitoso -->

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
    
    
    <form action="SvTematica" memthod="GET">
        <button type="submit">
           Realizar funcionalidades
        </button>
            
    </form>
    <form action="SvVerTematicas" memthod="GET">
        <button type="submit">
           Ver mis temáticas
        </button>
            
    </form>
    <form action="SvVerUsuarios" memthod="GET">
        <button type="submit">
           Ver usuarios registrados
        </button>
            
    </form>
    
    <a href="index.jsp">Inicio</a> 


</body>
</html>
