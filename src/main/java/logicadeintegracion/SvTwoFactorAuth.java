/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package logicadeintegracion;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logicadeaccesoadatos.DAOUsuario;
import logicadenegocios.Usuario;
import logicadevalidaciones.TwoFactoAuth;
import logicadevalidaciones.ValidarCodigo;

/**
 *
 * @author josea
 */
@WebServlet(name = "SvTwoFactorAuth", urlPatterns = {"/SvTwoFactorAuth"})
public class SvTwoFactorAuth extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String codigo = request.getParameter("autenticador");
        
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        String idUsuario=usuario.getId();
        if(ValidarCodigo.validarCodigo(codigo,idUsuario)){
            response.sendRedirect("registro_exitoso.jsp");
        }else{
            request.setAttribute("mensajeError", "El código proporcionado no es correcto, intente de nuevo");
            RequestDispatcher dispatcher = request.getRequestDispatcher("AutenticacionHtml.jsp");
            dispatcher.forward(request, response);
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
