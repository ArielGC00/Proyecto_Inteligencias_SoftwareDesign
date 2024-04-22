package logicadeaccesoadatos;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import logicadenegocios.Tematica;

/**
 *
 * @author Celeste
 */
public interface IDAOTematica {
    boolean registrarTematica (String nombreTematica, String descripcionTematica, String fotoTematica, ArrayList<String> textosMostrados, ArrayList<String> textos, String idUsuario);
    void actualizarTematicaData(ArrayList<String> textosMostrados, ArrayList<String> textos);
    ArrayList<Map<String, Object>> consultarTematicasPorNombres(ArrayList<String> nombresTematicas);
    ArrayList<String> consultarTextosMostradosPorTematica(String nombreTematica);
    String consultarTextoEspecífico(String nombreTematica, int posicion);
}