/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package logicadeintegracion;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logicadenegocios.Tematica;

/**
 *
 * @author josea
 */
@WebServlet(name = "SvMostrarTextosTematica", urlPatterns = {"/SvMostrarTextosTematica"})
public class SvMostrarTextosTematica extends HttpServlet {

    String idTematica;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       Tematica tematica = new Tematica();
        tematica.construirTematica(idTematica);
        tematica.asignarTexto(); // Llamar al método para asignar textos

        // Obtener el mapa de textos mostrados
        Map<String, String> textosMostrados = tematica.getTextosMostrados();

        HttpSession session = request.getSession();
        session.setAttribute("textosMostrados", textosMostrados); // Guardar el mapa en la sesión
        session.setAttribute("tematicaSeleccionadaSv", tematica);//Esta tematica es la seleccionada y se guarda en la sesion para otros Serverlets

        response.sendRedirect("tematicasRegistradas.jsp");

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        idTematica = request.getParameter("tematicaSeleccionada");
        response.sendRedirect("tematicasRegistradas.jsp");
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
