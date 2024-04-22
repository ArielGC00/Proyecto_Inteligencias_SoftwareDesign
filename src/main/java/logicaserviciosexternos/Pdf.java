package logicaserviciosexternos;


import com.itextpdf.html2pdf.HtmlConverter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class Pdf {
    private static final String RUTA_FUENTE_HTML = "/htmlPdf.html";
    private static final String RUTA_OUTPUT_PDF = "/outputPdf.pdf";
    private static final String RUTA_IMAGEN_WORDCLOUD = "/wordCloudImage.png";
    
    public Pdf() {
    
    }
    
    public void generarPdf() throws FileNotFoundException, IOException {
        String src = getClass().getResource(RUTA_FUENTE_HTML).getPath();
        String dest = getClass().getResource(RUTA_OUTPUT_PDF).getPath();
        HtmlConverter.convertToPdf(new File(src), new File(dest));
    }
    
    public void crearHtml(String pNombre,String pCedula,String pCorreo,String pTelefono, String ideaPrincipal, String pTexto, String pSentimiento, String pAnalisisChatGPTIdeaPrincipal,String pAnalisisChatGPTPalabrasClave, String pRuta, String pPalabrasClave, String pFecha) {
        String rutaImagen = "wordCloudImage.png"; // Ruta relativa para acceder a la imagen

        String rutaCompleta = getClass().getResource(RUTA_FUENTE_HTML).getPath();

        try (PrintWriter writer = new PrintWriter(new FileWriter(rutaCompleta))) {
            // Escribir el contenido HTML en el archivo
            writer.println("<!DOCTYPE html>");
            writer.println("<html>");
            writer.println("<head>");
            writer.println("<meta charset=\"UTF-8\">");
            writer.println("<title>Informe de Análisis</title>");
            writer.println("<style>");
            writer.println("body { font-family: 'Arial', sans-serif; background-color: #f0f0f0; color: #333; padding: 20px; }");
            writer.println("h1, h2, h3 { color: #1a1a1a; }"); // Color oscuro para títulos
            writer.println("p { color: #333; }"); // Color negro para texto principal
            writer.println("ul { margin-left: 20px; }"); // Añadir sangría a la lista
            writer.println("img { max-width: 60%; height: auto; display: block; margin: 20px auto; }"); // Estilo para la imagen
            writer.println("</style>");
            writer.println("</head>");
            writer.println("<body>");
            writer.println("<h1>Hola " + pNombre + "!</h1>");
             writer.println("<h3>Cédula " + pCedula + "</h3>");
              writer.println("<h3>N° de teléfono " + pTelefono + "</h3>");
              writer.println("<h3>Correo electrónico " + pCorreo + "</h3>");
            writer.println("<h2>Texto seleccionado:</h2>");
            writer.println("<p>" + pTexto + "</p>");
            writer.println("<h3>Fecha y hora cuando fue creado el texto:</h3>");
            writer.println("<p>" + pFecha + "</p>");
            writer.println("<h3>Sentimiento del texto:</h3>");
            writer.println("<p>" + pSentimiento + "</p>");
            writer.println("<h3>Idea principal:</h3>");
            writer.println("<p>" + ideaPrincipal + "</p>");
            writer.println("<h3>Análisis de ChatGpt la idea principal:</h3>");
            writer.println("<p>" + pAnalisisChatGPTIdeaPrincipal + "</p>");
            writer.println("<h3>Palabras clave:</h3>");
            writer.println("<p>" + pPalabrasClave + "</p>");
            writer.println("<h3>Análisis realizado por ChatGPT de las palabras clave:</h3>");
            writer.println("<p>" + pAnalisisChatGPTPalabrasClave + "</p>");
            writer.println("<h3>WordCloud:</h3>");
            writer.println("<img src=\"" + rutaImagen + "\" alt=\"WordCloud\">");
            writer.println("</body>");
            writer.println("</html>");

            System.out.println("Archivo HTML con estilos creado exitosamente: " + rutaCompleta);
        } catch (IOException e) {
            System.err.println("Error al crear el archivo HTML: " + e.getMessage());
        }
    }
}
