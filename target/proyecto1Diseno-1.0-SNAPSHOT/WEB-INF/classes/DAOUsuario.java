
package logicadeaccesoadatos;
import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import logicadenegocios.Usuario;
import com.google.gson.reflect.TypeToken;


/**
 *
 * @author josea
 */
public class DAOUsuario implements IDAOUsuario {
    String rutaArchivo = "..\\..\\resources\\usuarios.json";
    public DAOUsuario(){
    }

    @Override
    public boolean registrarUsuario(String id, String nombreCompleto, String correoElectronico, String numeroTelefonico, String fotoUsuario) {

    // Crear un mapa para almacenar los datos del usuario
    Map<String, String> usuarioData = new HashMap<>();
    usuarioData.put("id", id);
    usuarioData.put("nombreCompleto", nombreCompleto);
    usuarioData.put("correoElectronico", correoElectronico);
    usuarioData.put("numeroTelefonico", numeroTelefonico);
    usuarioData.put("fotoUsuario", fotoUsuario);

    // Convertir el mapa a una cadena de texto JSON
    Gson gson = new Gson();
    String jsonUsuario = gson.toJson(usuarioData);


    // Crear una lista para almacenar los datos de los usuarios
    List<Map<String, String>> usuarios = new ArrayList<>();

    // Leer el contenido del archivo JSON existente (si existe)
    if (Files.exists(Paths.get(rutaArchivo))) {
        try {
            String contenidoExistente = new String(Files.readAllBytes(Paths.get(rutaArchivo)));
            usuarios = gson.fromJson(contenidoExistente, List.class);
        } catch (IOException e) {
            System.out.println("Error al leer el archivo existente: " + e.getMessage());
            return false; // Registro fallido
        }
    }

    // Agregar el nuevo usuario a la lista
    usuarios.add(usuarioData);

    // Escribir la lista de usuarios al archivo JSON
    String jsonUsuarios = gson.toJson(usuarios);
    try {
        Files.write(Paths.get(rutaArchivo), jsonUsuarios.getBytes());
    } catch (IOException e) {
        System.out.println("Error al escribir el archivo: " + e.getMessage());
        return false; // Registro fallido
    }

    return true; // Registro exitoso
    }

    @Override
    public String consultarCorreo(String id) {

        // Leer el contenido del archivo JSON
        String contenidoJson = "";
        try {
            contenidoJson = new String(Files.readAllBytes(Paths.get(rutaArchivo)));
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
            return null;
        }

        // Convertir el contenido JSON a una lista de objetos
        Gson gson = new Gson();
        List<Map<String, String>> usuarios = gson.fromJson(contenidoJson, List.class);

        // Buscar el usuario con el ID especificado
        for (Map<String, String> usuario : usuarios) {
            if (usuario.get("id").equals(id)) {
                return usuario.get("correoElectronico");
            }
        }

        // Si no se encuentra el usuario, retornar null
        return null;
    }
    
    public String consultarKeyTwoFactorAuth(String id) {

        // Leer el contenido del archivo JSON
        String contenidoJson = "";
        try {
            contenidoJson = new String(Files.readAllBytes(Paths.get(rutaArchivo)));
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
            return null;
        }

        // Convertir el contenido JSON a una lista de objetos
        Gson gson = new Gson();
        List<Map<String, String>> usuarios = gson.fromJson(contenidoJson, List.class);

        // Buscar el usuario con el ID especificado
        for (Map<String, String> usuario : usuarios) {
            if (usuario.get("id").equals(id)) {
                return usuario.get("keyTwoFactorAuth");
            }
        }

        // Si no se encuentra el usuario, retornar null
        return null;
    }

    @Override
    public String consultarTelefono(String id) {
        // Leer el contenido del archivo JSON
        String contenidoJson = "";
        try {
            contenidoJson = new String(Files.readAllBytes(Paths.get(rutaArchivo)));
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
            return null;
        }

        // Convertir el contenido JSON a una lista de objetos
        Gson gson = new Gson();
        List<Map<String, String>> usuarios = gson.fromJson(contenidoJson, List.class);

        // Buscar el usuario con el ID especificado
        for (Map<String, String> usuario : usuarios) {
            if (usuario.get("id").equals(id)) {
                return usuario.get("numeroTelefonico");
            }
        }
        // Si no se encuentra el usuario, retornar null
        return null;
    }

    
    @Override
    public ArrayList<String> consultarInformacionUsuarios() {

        ArrayList<String> listaUsuarios = new ArrayList<>();

        try {
            // Leer el contenido del archivo JSON
            String contenidoJson = new String(Files.readAllBytes(Paths.get(rutaArchivo)));

            // Convertir el contenido JSON a una lista de mapas
            Gson gson = new Gson();
            List<Map<String, String>> usuarios = gson.fromJson(contenidoJson, List.class);

            // Iterar sobre la lista de usuarios y obtener información relevante
            for (Map<String, String> usuario : usuarios) {
                String id = usuario.get("id");
                String nombreCompleto = usuario.get("nombreCompleto");
                String correoElectronico = usuario.get("correoElectronico");
                String numeroTelefono = usuario.get("numeroTelefonico");
                String fotoUsuario = usuario.get("fotoUsuario");

                // Crear una cadena con la información del usuario
                String infoUsuario = "ID: " + id + ", Nombre: " + nombreCompleto + ", Correo: " + correoElectronico + ", Teléfono: " + numeroTelefono + ", Foto: " + fotoUsuario;

                // Agregar la información del usuario a la lista
                listaUsuarios.add(infoUsuario);
            }

        } catch (IOException e) {
            System.out.println("Error al leer el archivo JSON: " + e.getMessage());
        }

        return listaUsuarios;
    }
    
    @Override
    public ArrayList<String> obtenerUsuarioPorCedula(String cedula) {
        // Especificar la ruta del archivo JSON


        try {
            // Leer el contenido del archivo JSON
            String contenidoJson = new String(Files.readAllBytes(Paths.get(rutaArchivo)));

            // Convertir el contenido JSON a una lista de mapas
            Gson gson = new Gson();
            List<Map<String, String>> usuarios = gson.fromJson(contenidoJson, new TypeToken<List<Map<String, String>>>(){}.getType());

            // Buscar el usuario con la cédula especificada
            for (Map<String, String> usuario : usuarios) {
                if (usuario.containsKey("id") && usuario.get("id").equals(cedula)) {
                    // Si se encuentra el usuario, construir una lista con sus datos
                    ArrayList<String> datosUsuario = new ArrayList<>();
                    datosUsuario.add(usuario.get("id"));
                    datosUsuario.add(usuario.get("nombreCompleto"));
                    datosUsuario.add(usuario.get("correoElectronico"));
                    datosUsuario.add(usuario.get("numeroTelefonico"));
                    datosUsuario.add(usuario.get("fotoUsuario"));

                    return datosUsuario;
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo JSON: " + e.getMessage());
        }

        // Si no se encuentra el usuario, retornar una lista vacía o null
        return new ArrayList<>(); // o puedes retornar null
    }
    
}
