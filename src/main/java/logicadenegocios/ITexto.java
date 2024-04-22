package logicadenegocios;

/**
 *
 * @author Celeste
 */
public interface ITexto {
    String mostrarTexto();
    String getFechaHora();
    int getCantidadPalabras();
    int getID();
    void guardarTexto(String pTematica);
}
