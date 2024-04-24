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
    
    
    
    <a href="registroTematicasHtml.jsp"><button type="button">Registrar temática</button></a>
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
