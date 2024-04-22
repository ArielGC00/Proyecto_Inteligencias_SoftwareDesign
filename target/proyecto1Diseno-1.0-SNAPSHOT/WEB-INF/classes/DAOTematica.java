
package logicadeaccesoadatos;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileWriter;
import java.io.IOException;
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
    private String rutaArchivo; //Cambiar la ruta por la de ustedes

    public DAOTematica(){
        this.rutaArchivo = "C:\\Users\\josea\\OneDrive\\Documentos\\TEC\\TEC\\TEC\\V semestre\\diseño\\proyecto1Diseno\\src\\main\\java\\datosProyecto\\tematicas.json";
    }
    
    //Metodo agregar Tematicas
    @Override
    public boolean registrarTematica(String nombreTematica, String descripcionTematica, String fotoTematica, ArrayList<String> textosMostrados, ArrayList<String> textos, String idUsuario) {
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

        

        // Crear una lista para almacenar los datos de las temáticas
        List<Map<String, Object>> tematicas = new ArrayList<>();

        // Leer el contenido del archivo JSON existente (si existe)
        if (Files.exists(Paths.get(rutaArchivo))) {
            try {
                String contenidoExistente = new String(Files.readAllBytes(Paths.get(rutaArchivo)));
                // Convertir el contenido existente a una lista de mapas
                tematicas = gson.fromJson(contenidoExistente, new TypeToken<List<Map<String, Object>>>(){}.getType());
            } catch (IOException e) {
                System.out.println("Error al leer el archivo existente: " + e.getMessage());
                return false; // Registro fallido
            }
        }

        // Agregar la nueva temática al listado de temáticas
        tematicas.add(tematicaData);

        // Escribir la lista de temáticas al archivo JSON
        String jsonTematicas = gson.toJson(tematicas);
        try {
            Files.write(Paths.get(rutaArchivo), jsonTematicas.getBytes());
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo: " + e.getMessage());
            return false; // Registro fallido
        }

        return true; // Registro exitoso
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
        ArrayList<Map<String, Object>> tematicas = new ArrayList<>();

        // Leer el contenido del archivo JSON
        try {
            String contenidoExistente = new String(Files.readAllBytes(Paths.get(rutaArchivo)));
            Gson gson = new Gson();
            tematicas = gson.fromJson(contenidoExistente, new TypeToken<ArrayList<Map<String, Object>>>(){}.getType());
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }

        return tematicas;
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
