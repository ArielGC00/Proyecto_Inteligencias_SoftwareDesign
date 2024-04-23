package logicadeintegracion;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logicadenegocios.Usuario;
import logicadevalidaciones.ValidacionFuncionalidad;
import logicaserviciosexternos.Correo;
import logicaserviciosexternos.Pdf;

/**
 *
 * @author josea
 */
@WebServlet(name = "SvCorreoPdf", urlPatterns = {"/SvCorreoPdf"})
public class SvCorreoPdf extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        String correoUsuario=usuario.getCorreoElectronico();
        String cedula=usuario.getId();
        String telefonoUsuario=usuario.getNumeroTelefono();
        
        String nombreUsuario=(String) session.getAttribute("nombreUsuario");
        String texto=(String) session.getAttribute("descripcionTextoCompleto");
        String sentimiento=(String) session.getAttribute("sentimientoTextoCompleto");
        String fecha=(String) session.getAttribute("fechaTextoSeleccionadoCompleto");
        String ideaPrincipal=(String) session.getAttribute("ideaPrincipalTexto");
        String palabrasClave = (String) request.getSession().getAttribute("palabrasClaveTexto");
        String analisisIdea = (String) request.getSession().getAttribute("opinionIdea");
        String analisisPalabrasClave = (String) request.getSession().getAttribute("opinionPalabras");
        
        // Verificar que ninguno de los atributos adicionales sea null utilizando la clase ValidacionFuncionalidad
        if (!ValidacionFuncionalidad.validarAtributos(sentimiento, ideaPrincipal, palabrasClave, analisisIdea, analisisPalabrasClave)) {
            // Si alguno de los atributos adicionales es null, enviar un mensaje de error
            request.setAttribute("mensajeError", "ERROR: Asegúrese de ejecutar todas las funcionalidades antes de generar el pdf.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("tematicasRegistradas.jsp");
            dispatcher.forward(request, response);
            return;
        }
        
        Pdf pdf=new Pdf();
        pdf.crearHtml(nombreUsuario,cedula,correoUsuario,telefonoUsuario,ideaPrincipal,texto,sentimiento,analisisIdea,analisisPalabrasClave,null,palabrasClave,fecha);
        pdf.generarPdf();
        
        Correo correo=new Correo();
        try {
            correo.enviarCorreo(correoUsuario);
            response.sendRedirect("pdfCorreoHtml.jsp");
            
        } catch (MessagingException ex) {
            Logger.getLogger(SvCorreoPdf.class.getName()).log(Level.SEVERE, null, ex);
            response.sendRedirect("tematicasRegistradas.jsp");
        }
        
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}