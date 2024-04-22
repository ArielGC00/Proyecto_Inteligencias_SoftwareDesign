
package logicadeintegracion;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logicadeaccesoadatos.DAOUsuario;
import logicadenegocios.Usuario;

/**
 *
 * @author josea
 */
@WebServlet(name = "SvUsuario", urlPatterns = {"/SvUsuario"})
public class SvUsuario extends HttpServlet {

    String cedula;
    String nombreCompleto;
    String fotoUsuario;
    Usuario usuario;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("usuario", usuario);
        String nombreCompletoUsario=usuario.getNombre();
        String fotoRegistradaUsuario=usuario.getFotoUsuario();
            

            // Agregar el nombre del usuario como un atributo a la solicitud
            
        
        session.setAttribute("nombreUsuario", nombreCompletoUsario);
        session.setAttribute("fotoUsuario", fotoRegistradaUsuario);
        response.sendRedirect("registro_exitoso.jsp");
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.cedula = request.getParameter("cedula");
        DAOUsuario daoUsuario=new DAOUsuario();
        ArrayList<String> datosUsuario=daoUsuario.obtenerUsuarioPorCedula(cedula);
        if (!datosUsuario.isEmpty()) {
            // Extraer los datos del ArrayList
            String id = datosUsuario.get(0);
            nombreCompleto = datosUsuario.get(1);
            String correoElectronico = datosUsuario.get(2);
            String numeroTelefono = datosUsuario.get(3);
            fotoUsuario = datosUsuario.get(4);

            // Crear una nueva instancia de Usuario con los datos obtenidos
            usuario = new Usuario(id, nombreCompleto, correoElectronico, numeroTelefono, fotoUsuario);
            HttpSession session = request.getSession();
            session.setAttribute("usuario", usuario);
            

            // Agregar el nombre del usuario como un atributo a la solicitud
        
            session.setAttribute("nombreUsuario", nombreCompleto);
            session.setAttribute("fotoUsuario", fotoUsuario);
            
            response.sendRedirect("AutenticacionHtml.jsp");

            
        } else {
            // No se encontró un usuario con la cédula especificada
            // Redirigir a una página de error o realizar otra acción
            response.sendRedirect("index.jsp");
        }
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    public Usuario getUsuario(){
        return usuario;
    }
}
