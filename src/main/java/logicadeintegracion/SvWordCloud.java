/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package logicadeintegracion;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logicaserviciosexternos.WordCloud;

/**
 *
 * @author josea
 */
@WebServlet(name = "SvWordCloud", urlPatterns = {"/SvWordCloud"})
public class SvWordCloud extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        WordCloud wordCloud = new WordCloud();

        try {
            byte[] imageBytes = wordCloud.generarWordCloud();

            // Establecer el tipo de contenido de la respuesta como imagen PNG
             HttpSession session = request.getSession();
            session.setAttribute("wordCloudImage", imageBytes);

            // Redireccionar a WordCloudHtml.jsp después de configurar la sesión
            request.getRequestDispatcher("WordCloudHtml.jsp").forward(request, response);
            
        } catch (IOException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        
        
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
