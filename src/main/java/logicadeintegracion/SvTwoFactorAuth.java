/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package logicadeintegracion;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logicadeaccesoadatos.DAOUsuario;
import logicadenegocios.Usuario;
import logicavalidacionentrada.TwoFactoAuth;

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
        if(validarCodigo(codigo,idUsuario)){
            response.sendRedirect("registro_exitoso.jsp");
        }else{
            response.sendRedirect("AutenticacionHtml.jsp");
        }
        
    }
    private boolean validarCodigo(String pCodigo,String pId){
        TwoFactoAuth secretKey=new TwoFactoAuth();
        DAOUsuario daoUsuario=new DAOUsuario();
        
        String keyGoogle=daoUsuario.consultarKeyTwoFactorAuth(pId);
        
        String tempCodigo=secretKey.getCodigo(keyGoogle);
        
        
        if(tempCodigo.equals(pCodigo)){
            return true;
        }else{
            return false;
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
