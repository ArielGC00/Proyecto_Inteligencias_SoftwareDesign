
package logicadeaccesoadatos;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import logicadenegocios.Tematica;
import java.util.UUID; // Importar UUID para generar IDs únicos
/**
 *
 * @author Celeste
 */
public class DAOTematica implements IDAOTematica{
    // Crear un mapa para almacenar los datos de la temática
    private Map<String, Object> tematicaData = new HashMap<>();
    private static final String RUTA_ARCHIVO = "/tematicas.json";
    
    //Metodo agregar Tematicas
    @Override
    public boolean registrarTematica(String nombreTematica, String descripcionTematica, String fotoTematica, ArrayList<String> textosMostrados, ArrayList<String> textos, String idUsuario) {
        try (InputStream inputStream = getClass().getResourceAsStream(RUTA_ARCHIVO)) {
            // Crear un mapa para almacenar los datos de la temática
            Map<String, Object> tematicaData = new HashMap<>();
            tematicaData.put("nombreTematica", nombreTematica);
            tematicaData.put("descripcionTematica", descripcionTematica);
            tematicaData.put("fotoTematica", fotoTematica);
            tematicaData.put("idTematica", UUID.randomUUID().toString()); 
            tematicaData.put("textosMostrados", textosMostrados);
            tematicaData.put("textos", textos);
            tematicaData.put("idUsuario", idUsuario);

            // Convertir el mapa a una cadena de texto JSON
            Gson gson = new Gson();
            String jsonTematica = gson.toJson(tematicaData);

            List<Map<String, Object>> tematicas;

            if (inputStream != null) {
                // Leer el contenido del archivo JSON existente (si existe)
                String contenidoExistente = new String(inputStream.readAllBytes());
                // Convertir el contenido existente a una lista de mapas
                tematicas = gson.fromJson(contenidoExistente, new TypeToken<List<Map<String, Object>>>(){}.getType());
            } else {
                tematicas = new ArrayList<>();
            }

            // Agregar la nueva temática al listado de temáticas
            tematicas.add(tematicaData);

            // Escribir la lista de temáticas al archivo JSON
            try {
                URI uri = getClass().getResource(RUTA_ARCHIVO).toURI();
                try (FileOutputStream outputStream = new FileOutputStream(new File(uri))) {
                    String jsonTematicas = gson.toJson(tematicas);
                    outputStream.write(jsonTematicas.getBytes());
                }
            } catch (URISyntaxException e) {
                System.out.println("Error al convertir la ruta del archivo a URI: " + e.getMessage());
                return false; // Registro fallido
            } catch (IOException e) {
                System.out.println("Error al escribir el archivo: " + e.getMessage());
                return false; // Registro fallido
            }

            return true; // Registro exitoso
        } catch (IOException e) {
            System.out.println("Error al leer el archivo existente: " + e.getMessage());
            return false; // Registro fallido
        }
    }
      
    @Override
    public void actualizarTematicaData(ArrayList<String> textosMostrados, ArrayList<String> textos) {
        tematicaData.put("textosMostrados", textosMostrados);
        tematicaData.put("textos", textos);
    }
    
    //Método muestra la información de las temáticas registradas (por el usuario)
    //Recibe como parámetro la lista de las temáticas del usuario
    @Override
    public ArrayList<Map<String, Object>> consultarTematicasPorNombres(ArrayList<String> nombresTematicas) {
        ArrayList<Map<String, Object>> tematicas = obtenerTodasTematicas();

        // Filtrar las temáticas por nombres proporcionados
        ArrayList<Map<String, Object>> tematicasConsultadas = filtrarTematicasPorNombres(tematicas, nombresTematicas);

        return tematicasConsultadas;
    }
    
    // Método publico para obtener todas las temáticas del archivo JSON
    public ArrayList<Map<String, Object>> obtenerTodasTematicas() {
        
        try (InputStream inputStream = getClass().getResourceAsStream(RUTA_ARCHIVO)) {
            if (inputStream != null) {
                // Leer el contenido del archivo JSON
                String contenidoExistente = new String(inputStream.readAllBytes());
                Gson gson = new Gson();
                return gson.fromJson(contenidoExistente, new TypeToken<ArrayList<Map<String, Object>>>(){}.getType());
            } else {
                System.out.println("No se pudo cargar el archivo " + RUTA_ARCHIVO);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo JSON: " + e.getMessage());
        }

        // Si hay algún error o el archivo no se puede cargar, devolver una lista vacía
        return new ArrayList<>();
    }
    
    
    // Método para filtrar las temáticas por nombres proporcionados
    private ArrayList<Map<String, Object>> filtrarTematicasPorNombres(ArrayList<Map<String, Object>> tematicas, ArrayList<String> nombresTematicas) {
        ArrayList<Map<String, Object>> tematicasFiltradas = new ArrayList<>();

        // Iterar sobre las temáticas y agregar las que coincidan con los nombres proporcionados
        for (Map<String, Object> tematica : tematicas) {
            String nombreTematica = (String) tematica.get("nombreTematica");
            if (nombresTematicas.contains(nombreTematica)) {
                tematicasFiltradas.add(tematica);
            }
        }

        return tematicasFiltradas;
    }
    
    
    // Método para consultar los textos (solo 30 palabras) de una temática específica
    @Override
    public ArrayList<String> consultarTextosMostradosPorTematica(String nombreTematica) {
        ArrayList<Map<String, Object>> tematicas = obtenerTodasTematicas();

        // Buscar la temática por nombre
        Map<String, Object> tematicaEncontrada = buscarTematicaPorNombre(tematicas, nombreTematica);

        // Obtener los textosMostrados si se encontró la temática
        if (tematicaEncontrada != null) {
            return (ArrayList<String>) tematicaEncontrada.get("textosMostrados");
        } else {
            System.out.println("No se encontró la temática con el nombre especificado.");
            return new ArrayList<>(); // Retorna una lista vacía si no se encuentra la temática
        }
    }
    
    //Método para buscar una temática específica
    private Map<String, Object> buscarTematicaPorNombre(ArrayList<Map<String, Object>> tematicas, String nombreTematica) {
        // Iterar sobre todas las temáticas
        for (Map<String, Object> tematica : tematicas) {
            String nombre = (String) tematica.get("nombreTematica");
            // Si se encuentra la temática con el nombre especificado, devolverla
            if (nombre.equals(nombreTematica)) {
                return tematica;
            }
        }
        // Si no se encuentra ninguna temática con el nombre especificado, devolver null
        return null;
    }

    //Método para consultar info de un texto específico
    @Override
    public String consultarTextoEspecífico(String nombreTematica, int posicion) {
        // Obtener los textos mostrados de la temática especificada
        ArrayList<String> textosMostrados = consultarTextosMostradosPorTematica(nombreTematica);

        // Verificar si la posición proporcionada es válida
        if (posicion >= 0 && posicion < textosMostrados.size()) {
            // Obtener el texto mostrado en la posición especificada
            return textosMostrados.get(posicion);
        } else {
            // Si la posición no es válida, devolver un mensaje de error
            return "La posición especificada no es válida.";
        }
    }
}
