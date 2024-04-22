package logicadeaccesoadatos;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.UUID; // Importar UUID para generar IDs únicos

/**
 * DAO para la entidad Texto.
 */
public class DAOTexto implements IDAOTexto {

    private String rutaArchivo; // Ruta del archivo JSON para almacenar textos

    public DAOTexto() {
        // Ruta del archivo JSON donde se guardarán los textos
        this.rutaArchivo = "C:\\Users\\josea\\OneDrive\\Documentos\\TEC\\TEC\\TEC\\V semestre\\diseño\\proyecto1Diseno\\src\\main\\java\\datosProyecto\\textos.json";
    }

    @Override
    public boolean registrarTexto(String infoTexto, String fechaHora, String tematica, String sentimiento) {
        // Leer el contenido del archivo JSON existente (si existe)
        List<Map<String, String>> textos = new ArrayList<>();

        Gson gson = new Gson();

        if (Files.exists(Paths.get(rutaArchivo))) {
            try {
                String contenidoExistente = new String(Files.readAllBytes(Paths.get(rutaArchivo)));
                textos = gson.fromJson(contenidoExistente, new TypeToken<List<Map<String, String>>>() {}.getType());
            } catch (IOException e) {
                System.out.println("Error al leer el archivo existente: " + e.getMessage());
                return false; // Registro fallido
            }
        }

        // Crear un mapa para almacenar los datos del nuevo texto
        Map<String, String> nuevoTexto = new HashMap<>();
        nuevoTexto.put("id", UUID.randomUUID().toString()); // Generar un ID único usando UUID
        nuevoTexto.put("texto", infoTexto);
        nuevoTexto.put("fechaHora", fechaHora);
        nuevoTexto.put("idTematica", tematica);
        nuevoTexto.put("sentimiento", sentimiento);

        // Agregar el nuevo texto a la lista de textos
        textos.add(nuevoTexto);

        // Convertir la lista de textos a JSON
        String jsonTextos = gson.toJson(textos);

        // Escribir el JSON actualizado de textos al archivo
        try {
            Files.write(Paths.get(rutaArchivo), jsonTextos.getBytes());
            return true; // Registro exitoso
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
            return false; // Registro fallido
        }
    }
    public ArrayList<Map<String, Object>> obtenerTodosTextos() {
        ArrayList<Map<String, Object>> textos = new ArrayList<>();

        // Leer el contenido del archivo JSON
        try {
            String contenidoExistente = new String(Files.readAllBytes(Paths.get(rutaArchivo)));
            Gson gson = new Gson();
            textos = gson.fromJson(contenidoExistente, new TypeToken<ArrayList<Map<String, Object>>>(){}.getType());
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }

        return textos;
    }
}
