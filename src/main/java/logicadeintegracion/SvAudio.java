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
import logicaserviciosexternos.TextoToSpeech1;

/**
 *
 * @author josea
 */
@WebServlet(name = "SvAudio", urlPatterns = {"/SvAudio"})
public class SvAudio extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }


    @Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    HttpSession session = request.getSession();
    String texto = (String) session.getAttribute("descripcionTextoCompleto");
    TextoToSpeech1 textToSpeech = new TextoToSpeech1();
    byte[] audioBytes = textToSpeech.text_to_speech(texto);
    
    // Configurar la respuesta para enviar el audio
    response.setContentType("audio/mpeg");
    response.setContentLength(audioBytes.length);
    
    // Enviar los datos del audio como un flujo de salida
    OutputStream out = response.getOutputStream();
    out.write(audioBytes);
    out.flush();
    out.close();
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
