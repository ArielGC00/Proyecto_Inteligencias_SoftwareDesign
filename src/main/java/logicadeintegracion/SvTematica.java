/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package logicadeintegracion;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logicadenegocios.Tematica;
import logicadenegocios.Usuario;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author josea
 */
@WebServlet(name = "SvTematica", urlPatterns = {"/SvTematica"})

public class SvTematica extends HttpServlet {
    
    Usuario usuario;
    private static final long serialVersionUID = 1L;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    } 


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        usuario = (Usuario) session.getAttribute("usuario");
        ArrayList<String> listaNombreTematicas = new ArrayList<>();
        ArrayList<String> listIdTematicas = new ArrayList<>();


            
            List<Tematica> tematicasRegistradas = usuario.getTematicasRegistradas();
            
            
            for (Tematica tematica:tematicasRegistradas){
                listaNombreTematicas.add(tematica.getNombre());
                listIdTematicas.add(tematica.getIdTematica());
                
            }
            
            // Pasar la lista de temáticas registradas al request
            session.setAttribute("nombresTematicas", listaNombreTematicas);
            session.setAttribute("listIdTematicas", listIdTematicas);
            session.setAttribute("nombreUsuario", usuario.getNombre());
            response.sendRedirect("tematicasRegistradas.jsp");
            // Redirigir a la vista para mostrar las temáticas registradas
            //request.getRequestDispatcher("tematicasRegistradas.jsp").forward(request, response);

        
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8"); // Configurar codificación de caracteres

        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        String nombreTematica = null;
        String descripcionTematica = null;

        // Obtener la ruta del directorio donde se almacenan los archivos JSON
        String jsonDirectory = getServletContext().getRealPath("/src/classes/");

        if (ServletFileUpload.isMultipartContent(request)) {
            try {
                List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);

                for (FileItem item : items) {
                    if (item.isFormField()) {
                        if (item.getFieldName().equals("nombreTematica")) {
                            nombreTematica = new String(item.getString().getBytes("ISO-8859-1"), "UTF-8");
                        } else if (item.getFieldName().equals("descripcionTematica")) {
                            descripcionTematica = new String(item.getString().getBytes("ISO-8859-1"), "UTF-8");
                        }
                    } else {
                        // Obtener el nombre del archivo
                        String fileName = new File(item.getName()).getName();
                        // Construir la ruta completa del archivo
                        String filePath = jsonDirectory + File.separator + fileName;

                        // Guardar el archivo en el directorio
                        File uploadedFile = new File(filePath);
                        item.write(uploadedFile);

                        // Crear la nueva temática y guardarla
                        Tematica nuevaTematica = new Tematica(null, nombreTematica, descripcionTematica, fileName);
                        nuevaTematica.guardarTematica(usuario.getId());

                        // Redireccionar después de guardar exitosamente
                        response.sendRedirect("registro_exitoso.jsp");
                        return;
                    }
                }
            } catch (Exception ex) {
                throw new ServletException("Error al procesar la carga de archivos", ex);
            }
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
