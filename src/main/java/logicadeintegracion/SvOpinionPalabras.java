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
import logicaserviciosexternos.OpinionChatGPT;
import logicaserviciosexternos.Traductor;
/**
 *
 * @author Celeste
 */


@WebServlet(name = "SvOpinionPalabras", urlPatterns = {"/SvOpinionPalabras"})
public class SvOpinionPalabras extends HttpServlet{
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        String textoCompleto = (String) session.getAttribute("descripcionTextoCompleto");
        String texto = (String) session.getAttribute("palabrasClaveTexto");
        OpinionChatGPT opinion = new OpinionChatGPT();
        String ideaPrincipalTexto = opinion.obtenerOpinion(texto);
        asignarOpinionPalabrasClaveTexto(ideaPrincipalTexto,session,textoCompleto);
        //session.setAttribute("opinionPalabras", ideaPrincipalTexto);
        response.sendRedirect("opinionPalabras.jsp");
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    private int setIdiomaFuncionalidades(String pTexto){
        Traductor traductor=new Traductor();
        return traductor.detectarIdioma(pTexto);
    }
    private void asignarOpinionPalabrasClaveTexto(String pTextoFuncionalidad,HttpSession session,String pTexto){
        Traductor traductor=new Traductor();
        int idioma=setIdiomaFuncionalidades(pTexto);
        String textoFuncionalidad=pTextoFuncionalidad;
        if(idioma==1){
            textoFuncionalidad=traductor.traducirTexto(pTextoFuncionalidad);
            session.setAttribute("opinionPalabras", textoFuncionalidad);
        }else{
            session.setAttribute("opinionPalabras", textoFuncionalidad);
        }
        
    }
    
    
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
