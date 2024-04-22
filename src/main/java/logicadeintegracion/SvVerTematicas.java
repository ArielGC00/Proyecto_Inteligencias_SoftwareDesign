
package logicadeintegracion;


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

/**
 *
 * @author josea
 */
@WebServlet(name = "SvVerTematicas", urlPatterns = {"/SvVerTematicas"})
public class SvVerTematicas extends HttpServlet {
    
    Usuario usuario;
    private static final long serialVersionUID = 1L;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    } 

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

      HttpSession session = request.getSession();
      ArrayList<String> listaNombreTematicas = new ArrayList<>();
      ArrayList<String> listIdTematicas = new ArrayList<>();
      ArrayList<String> listDescripcionTematicas = new ArrayList<>();
      ArrayList<String> listFotoTematicas = new ArrayList<>();

      
      usuario = (Usuario) session.getAttribute("usuario");
      List<Tematica> tematicasRegistradas = usuario.getTematicasRegistradas();
      for (Tematica tematica:tematicasRegistradas){
          listaNombreTematicas.add(tematica.getNombre());
          listIdTematicas.add(tematica.getIdTematica());
          listDescripcionTematicas.add(tematica.getDscripcionTematica());
          listFotoTematicas.add(tematica.getFotoTematica());

      }
      session.setAttribute("nombresTematicas", listaNombreTematicas);
      session.setAttribute("listIdTematicas", listIdTematicas);
      session.setAttribute("listDescripcionTematicas", listDescripcionTematicas);
      session.setAttribute("listFotoTematicas", listFotoTematicas);
      response.sendRedirect("verTematicas.jsp");
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
