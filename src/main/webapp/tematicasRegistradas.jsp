<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="logicadenegocios.Tematica"%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Tematicas Registradas</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <% String nombre = (String) request.getSession().getAttribute("nombreUsuario"); %>
    <h1>Tematicas registradas por <%= nombre %></h1>

    <%-- Obtener la lista de temáticas registradas --%>
    <% List<String> tematicas = (List<String>) request.getSession().getAttribute("nombresTematicas"); %>
    <% List<String> idTemtaticas = (List<String>) request.getSession().getAttribute("listIdTematicas"); %> <!-- esta lista es del get del SvTematica -->

    <%-- Crear un formulario para seleccionar una temática --%>
    <form action="SvMostrarTextosTematica" method="POST">
        <label for="tematicasSelect">Selecciona una temática:</label>
        <select id="tematicasSelect" name="tematicaSeleccionada">
            <option value="" disabled selected>Selecciona una temática</option>
            <%-- Iterar sobre la lista de temáticas y agregar opciones --%>
                <% for (int i = 0; i < tematicas.size(); i++) { %>
                    <option value="<%= idTemtaticas.get(i) %>"><%= tematicas.get(i) %></option>
                <% } %>
        </select>
        <button type="submit">Seleccionar tematica</button>
    </form>
        
        
        
    <form action="SvMostrarTextosTematica" memthod="GET" >
            <button type="submit">
               Ver textos registrados
            </button>     
    </form>
        
    <%-- Mostrar la tabla si hay textos para mostrar --%>
    <% Map<String, String> textosMostrados = (Map<String, String>) request.getSession().getAttribute("textosMostrados"); %>
    <% if (textosMostrados != null && !textosMostrados.isEmpty()) { %>
        <h2>Textos registrados:</h2>
        <form action="SvProcesarTextoTematica" method="POST">
            <table border="1">
                <thead>
                    <tr>
                        <th>#</th>
                        <th>Texto</th>
                        <th>Seleccionar</th> <!-- Nueva columna para botones de selección -->
                    </tr>
                </thead>
                <tbody>
                    <% int i = 1; %>
                    <% for (Map.Entry<String, String> entry : textosMostrados.entrySet()) { %>
                        <tr>
                            <td><%= i %></td>
                            <td><%= entry.getValue() %></td>
                            <td>
                                <input type="radio" name="textoSeleccionado" value="<%= entry.getKey() %>">
                            </td>
                        </tr>
                        <% i++; %>
                    <% } %>

                </tbody>
            </table>
            <br>

           <button type="submit">Seleccionar Texto</button>
        </form>

            <form action="SvProcesarTextoTematica" memthod="GET">
                <button type="submit">
                    Mostrar Texto seleccionado
                </button>
               <%-- Mostrar información del texto seleccionado --%>
                <% String fechaHoraTexto = (String) request.getSession().getAttribute("fechaTextoSeleccionadoCompleto"); %>
                <% String descripcionTexto = (String) request.getSession().getAttribute("descripcionTextoCompleto"); %>
                
                
                <% if (descripcionTexto != null && !descripcionTexto.isEmpty()) { %>
                    <% if (fechaHoraTexto != null && !fechaHoraTexto.isEmpty()) { %>
                        <h1>Fecha y hora en la que se registró:</h1>
                        <p><%= fechaHoraTexto %></p>
                        <h1>Texto completo:</h1>
                        <p><%= descripcionTexto %></p>
                        
                    <% } else { %>
                        <p>No hay información disponible para el texto seleccionado.</p>
                    <% } %>
                    
                    
            </form> 
                    <a href="sentimientoTextoHtml.jsp"><button type="submit">Generar analisis de sentimiento</button></a>  
                    <form action="SvPalabrasClave" memthod="GET">
                        <button type="submit">
                            Generar Palabras clave
                        </button>
                        
                    </form> 
                    <form action="SvIdeaPrincipal" memthod="GET">
                        <button type="submit">
                            Generar Idea principal
                        </button>
                    </form> 
                    
                    <form action="SvOpinionPalabras" memthod="GET">
                        <button type="submit">
                            Generar Opinión de ChatGPT con las palabras clave
                        </button>
                    </form>
                    
                    <form action="SvOpinionIdea" memthod="GET">
                        <button type="submit">
                            Generar Opinión de ChatGPT con la idea principal
                        </button>
                    </form>
                    
                    <form action="SvWordCloud" memthod="GET">
                        <button type="submit">
                            Generar WordCloud
                        </button>
                    </form>
                    <form action="SvCorreoPdf" memthod="GET">
                        <button type="submit">
                            Generar pdf
                        </button>
                    </form> 
                    
                    <form action="SvAudio" memthod="GET">
                        <button type="submit">
                            Generar Audio
                        </button>
                    </form> 
                <% } %> 
            
        <% } %>
        
    <a href="registro_exitoso.jsp">Volver</a><!-- Enlace para volver a la página anterior -->
    <a href="registrarTexto.jsp"><button type="button">Registrar nuevo texto</button></a>
</body>
</html>
