/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicadeintegracion;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logicadeaccesoadatos.DAOUsuario;


/**
 *
 * @author Celeste
 */
@WebServlet(name = "SvVerUsuarios", urlPatterns = {"/SvVerUsuarios"})
public class SvVerUsuarios extends HttpServlet{
    private static final long serialVersionUID = 1L;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    } 

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

      HttpSession session = request.getSession();
      
        DAOUsuario daoUsuario = new DAOUsuario();
        ArrayList<String> listaInformacionUsuarios = daoUsuario.consultarInformacionUsuarios();

        ArrayList<ArrayList<String>> usuarios = new ArrayList<>();
        for (String infoUsuario : listaInformacionUsuarios) {
            // Separar la información del usuario por comas y obtener los campos individuales
            String[] datosUsuario = infoUsuario.split(",");

            // Crear una lista para almacenar los campos del usuario actual
            ArrayList<String> usuario = new ArrayList<>();
            for (String dato : datosUsuario) {
                // Obtener los valores de cada campo eliminando los encabezados
                String valor = dato.substring(dato.indexOf(":") + 2);
                usuario.add(valor.trim());
            }

            usuarios.add(usuario);
        }

        // Ordenar la lista de usuarios por nombre
        Collections.sort(usuarios, (ArrayList<String> u1, ArrayList<String> u2) -> u1.get(1).compareToIgnoreCase(u2.get(1)) // Comparar los nombres de los usuarios
     
      );

        session.setAttribute("listaUsuarios", usuarios);

        response.sendRedirect("verUsuarios.jsp");
        
    
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
