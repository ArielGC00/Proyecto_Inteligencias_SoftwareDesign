
package logicadenegocios;

import java.util.ArrayList;
import paqueteclasesutilitarias.IComparable;

public interface IUsuario extends IComparable{
    ArrayList<IUsuario> mostrarInformacionUsuario();
    ArrayList<Tematica> getTematicasRegistradas();
    
}
