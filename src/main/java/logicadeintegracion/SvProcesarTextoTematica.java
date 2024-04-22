
package logicadeintegracion;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logicadenegocios.Tematica;
import logicaserviciosexternos.GuardarTxt;
import logicaserviciosexternos.Traductor;

/**
 *
 * @author josea
 */
@WebServlet(name = "SvProcesarTextoTematica", urlPatterns = {"/SvProcesarTextoTematica"})
public class SvProcesarTextoTematica extends HttpServlet {
    String idTextoSeleccionado;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Tematica tematica = (Tematica) session.getAttribute("tematicaSeleccionadaSv");
        System.out.println("Id del TEXTO: "+ idTextoSeleccionado);
        tematica.asignarTextoCompleto(); // Llamar al método para asignar textos
        String idTexto="";
        String fechaHora="";
        String descripcionTexto="";
        String sentimientoTexto="";
        // Obtener el mapa de textos mostrados
        List<Map<String, Object>> textosCompletos = tematica.getTextos();
        
        
        for (Map<String, Object> textoData : textosCompletos) {
            String idTextoJson = (String) textoData.get("id");
            System.out.println("Id del TEXTOJSON: "+ idTextoJson);
            // Si el idTematica del texto coincide con el idTematica actual
            if (idTextoJson != null && idTextoJson.equals(idTextoSeleccionado)) {
                idTexto = (String) textoData.get("id");
                descripcionTexto = (String)textoData.get("texto");
                fechaHora=(String) textoData.get("fechaHora");
                sentimientoTexto=(String) textoData.get("sentimiento");
                
                
            }
        }
        System.out.println("Fecha: "+fechaHora);
        System.out.println("Descripcion: "+descripcionTexto);
        //Guarda el texto seleccionado para el wordcloud
        GuardarTxt guardarTxt=new GuardarTxt();
        guardarTxt.guardarStringEnArchivo(descripcionTexto);
        // Guardar el JSON del texto seleccionado en la sesión
        session.setAttribute("idTextoSeleccionadoCompleto", idTexto);
        session.setAttribute("fechaTextoSeleccionadoCompleto", fechaHora);
        session.setAttribute("descripcionTextoCompleto", descripcionTexto);
        asignarSentimientoText0(sentimientoTexto,session,descripcionTexto);
        //session.setAttribute("sentimientoTextoCompleto", sentimientoTexto);

        // Redireccionar a la página para mostrar el texto seleccionado
        response.sendRedirect("tematicasRegistradas.jsp"); // Guardar el json en la sesión
        
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         idTextoSeleccionado = request.getParameter("textoSeleccionado");
        
        System.out.println("Id del texto: "+ idTextoSeleccionado);
        response.sendRedirect("tematicasRegistradas.jsp");
    }
    private int setIdiomaFuncionalidades(String pTexto){
        Traductor traductor=new Traductor();
        return traductor.detectarIdioma(pTexto);
    }
    private void asignarSentimientoText0(String pSentimiento,HttpSession session,String pTexto){
        Traductor traductor=new Traductor();
        int idioma=setIdiomaFuncionalidades(pTexto);
        String sentimiento=pSentimiento;
        if(idioma==1){
            sentimiento=traductor.traducirTexto(pSentimiento);
            session.setAttribute("sentimientoTextoCompleto", sentimiento);
        }else{
            session.setAttribute("sentimientoTextoCompleto", sentimiento);
        }
        
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
