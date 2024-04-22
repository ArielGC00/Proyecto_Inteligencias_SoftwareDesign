
package logicadeaccesoadatos;

import java.util.ArrayList;
import logicadenegocios.Usuario;

/**
 *
 * @author josea
 */
public  interface IDAOUsuario {
    boolean registrarUsuario(String id, String nombreCompleto, String correoElectronico, String numeroTelefonico, String fotoUsuario);
    String consultarCorreo(String id);
    String consultarTelefono(String id);
    ArrayList<String> consultarInformacionUsuarios();
    ArrayList<String> obtenerUsuarioPorCedula(String cedula);
    //ArrayList<Tematica> consultarTematica();
}
