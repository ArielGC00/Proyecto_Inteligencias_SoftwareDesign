/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicaserviciosexternos;

/**
 *
 * @author Celeste
 */
public class IdeaPrincipal extends AnalizadorTexto{
    public IdeaPrincipal() {
        
    }

    @Override
    public String obtenerResultado(String texto) {
        String consulta = ("Deme en un párrafo la idea principal del siguiente texto: " + texto);
        respuesta = chatGPT.generarRespuesta(consulta);
       // respuesta = respuesta.replace("[\r\\n]+", " ");

        return respuesta;
    }
}
