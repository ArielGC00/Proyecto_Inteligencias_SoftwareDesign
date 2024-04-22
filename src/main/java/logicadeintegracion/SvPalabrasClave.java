/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicadeintegracion;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logicaserviciosexternos.PalabrasClave;

/**
 *
 * @author Celeste
 */
@WebServlet(name = "SvPalabrasClave", urlPatterns = {"/SvPalabrasClave"})
public class SvPalabrasClave extends HttpServlet{
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String texto = (String) session.getAttribute("descripcionTextoCompleto");
        PalabrasClave palabrasClave = new PalabrasClave();
        String palabrasClaveTexto = palabrasClave.obtenerResultado(texto);
        
        session.setAttribute("palabrasClaveTexto", palabrasClaveTexto);
        response.sendRedirect("palabrasClaveHtml.jsp");
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
