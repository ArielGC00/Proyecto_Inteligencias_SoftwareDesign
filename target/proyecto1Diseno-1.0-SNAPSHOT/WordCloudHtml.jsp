
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="styleWordCloudHtml.css">
    </head>
    <body>
        <h2>WordCloud</h2>
        <% 
        // Obtener la imagen de la sesión
        byte[] imageBytes = (byte[]) session.getAttribute("wordCloudImage");
        if (imageBytes != null) {
            // Mostrar la imagen como un recurso base64 en la etiqueta <img>
            String imageData = "data:image/png;base64," + java.util.Base64.getEncoder().encodeToString(imageBytes);
            %>
            <img src="<%=imageData%>" alt="Word Cloud" style="width: 600px; height: 600px;">
         <% } else { %>
            <p>La imagen no está disponible.</p>
        <% } %>
    
        <br><br>
        <button onclick="goBack()">Volver</button>

        <script>
            function goBack() {
                window.history.back();
            }
        </script>
    </body>
</html>
