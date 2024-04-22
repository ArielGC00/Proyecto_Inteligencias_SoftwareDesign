package logicadeaccesoadatos;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileOutputStream;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.UUID; // Importar UUID para generar IDs únicos

/**
 * DAO para la entidad Texto.
 */
public class DAOTexto implements IDAOTexto {

    private static final String RUTA_ARCHIVO = "/textos.json";

    public DAOTexto() {
        // Ruta del archivo JSON donde se guardarán los textos
    }

    @Override
    public boolean registrarTexto(String infoTexto, String fechaHora, String tematica, String sentimiento) {
        try (InputStream inputStream = getClass().getResourceAsStream(RUTA_ARCHIVO)) {
            // Crear un mapa para almacenar los datos del nuevo texto
            Map<String, String> nuevoTexto = new HashMap<>();
            nuevoTexto.put("id", UUID.randomUUID().toString()); // Generar un ID único usando UUID
            nuevoTexto.put("texto", infoTexto);
            nuevoTexto.put("fechaHora", fechaHora);
            nuevoTexto.put("idTematica", tematica);
            nuevoTexto.put("sentimiento", sentimiento);

            Gson gson = new Gson();
            List<Map<String, String>> textos;

            if (inputStream != null) {
                // Leer el contenido del archivo JSON existente (si existe)
                String contenidoExistente = new String(inputStream.readAllBytes());
                // Convertir el contenido existente a una lista de mapas
                textos = gson.fromJson(contenidoExistente, new TypeToken<List<Map<String, String>>>() {}.getType());
            } else {
                textos = new ArrayList<>();
            }

            // Agregar el nuevo texto a la lista de textos
            textos.add(nuevoTexto);

            // Escribir la lista de textos al archivo JSON
            try {
                URI uri = getClass().getResource(RUTA_ARCHIVO).toURI();
                try (FileOutputStream outputStream = new FileOutputStream(new File(uri))) {
                    String jsonTextos = gson.toJson(textos);
                    outputStream.write(jsonTextos.getBytes());
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


    
    public ArrayList<Map<String, Object>> obtenerTodosTextos() {
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
}
