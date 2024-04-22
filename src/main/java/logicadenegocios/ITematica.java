
package logicadenegocios;

import java.util.ArrayList;
import java.util.Map;
import paqueteclasesutilitarias.IComparable;

/**
 *
 * @author Celeste
 */
public interface ITematica extends IComparable{
    void crearTexto(String pTexto,String pIdTematica);
    String getNombre();
    Map<String, String> getTextosMostrados();
}
