
package logicaserviciosexternos;


/**
 *
 * @author Celeste
 */
public class PalabrasClave extends AnalizadorTexto{

    public PalabrasClave() {
    }

    @Override
    public String obtenerResultado(String texto) {
         String consulta = ("Deme en una linea cuáles son las palabras claves del siguiente texto: "+texto);
        respuesta = chatGPT.generarRespuesta(consulta);
        respuesta = respuesta.replace("[\r\\n]+", " ");

        return respuesta;
    }
}
