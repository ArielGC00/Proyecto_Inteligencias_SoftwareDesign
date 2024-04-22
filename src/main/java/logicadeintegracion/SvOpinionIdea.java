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
@WebServlet(name = "SvOpinionIdea", urlPatterns = {"/SvOpinionIdea"})
public class SvOpinionIdea extends HttpServlet{
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String texto = (String) session.getAttribute("ideaPrincipalTexto");
        OpinionChatGPT opinion = new OpinionChatGPT();
        String ideaPrincipalTexto = opinion.obtenerOpinion(texto);
        asignarOpinionIdeaPrincipalTexto(ideaPrincipalTexto,session,texto);
        //session.setAttribute("opinionIdea", ideaPrincipalTexto);
        response.sendRedirect("opinionIdea.jsp");
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
    private void asignarOpinionIdeaPrincipalTexto(String pTextoFuncionalidad,HttpSession session,String pTexto){
        Traductor traductor=new Traductor();
        int idioma=setIdiomaFuncionalidades(pTexto);
        String textoFuncionalidad=pTextoFuncionalidad;
        if(idioma==1){
            textoFuncionalidad=traductor.traducirTexto(pTextoFuncionalidad);
            session.setAttribute("opinionIdea", textoFuncionalidad);
        }else{
            session.setAttribute("opinionIdea", textoFuncionalidad);
        }
        
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
