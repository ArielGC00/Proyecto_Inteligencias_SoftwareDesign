package logicadenegocios;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;
import logicadeaccesoadatos.DAOTexto;
import logicadeintegraciondeia.Sentimiento;
/**
 *
 * @author Celeste
 */
public class Texto implements ITexto{
    private String infoTexto;
    private String fechaHora;
    private int cantidadPalabras;
    private int idTexto;
    private static int cantTextos = 0;
    private String sentimientoAsignado;
    
    
    //Método constructor
    public Texto (String infoTexto){
        this.infoTexto = infoTexto;
        this.fechaHora = asignarFechaHora();
        this.cantidadPalabras = registrarConteoPalabras(infoTexto);
        this.idTexto = cantTextos;
        
        cantTextos++;
    }
    
    public Texto(){
        
    }
    
private String asignarFechaHora(){
        Date fechaHoraActual = new Date();

        // Establecer la zona horaria a Costa Rica
        TimeZone zonaHorariaCR = TimeZone.getTimeZone("America/Costa_Rica");

        // Crear un formato para la fecha y hora y establecer la zona horaria
        SimpleDateFormat formatoFechaHora = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        formatoFechaHora.setTimeZone(zonaHorariaCR);

        // Formatear la fecha y hora actual como una cadena
        fechaHora = formatoFechaHora.format(fechaHoraActual);

        return fechaHora;
    }
    
    // Método para dividir el texto en palabras
    private String[] dividirEnPalabras(String infoTexto) {
        return infoTexto.split("\\s+");
    }
    
    // Método para contar las palabras de un texto
    private int registrarConteoPalabras(String infoTexto){
        String[] palabras = dividirEnPalabras(infoTexto);
        cantidadPalabras = palabras.length;
        return cantidadPalabras;
    }
    
    //Método para mostrar las primeras 30 palabras de un texto
    @Override
    public String mostrarTexto(){
        int palabras = registrarConteoPalabras (infoTexto);
        // Si hay menos de 30 palabras en el texto, devolver el texto completo
        if (palabras <= 30) {
            return infoTexto;
        }
        
        String[] arregloPalabras = dividirEnPalabras(infoTexto);
        
        // Crea una cadena con las primeras 30 palabras
        StringBuilder primerasPalabras = new StringBuilder();
        for (int i = 0; i < 30; i++) {
            primerasPalabras.append(arregloPalabras[i]).append(" ");
        }
        
        return primerasPalabras.toString().trim();
    }
    @Override
    public void guardarTexto(String pTematica){
        DAOTexto daoTexto = new DAOTexto();
        Sentimiento sentimiento=new Sentimiento();
        sentimientoAsignado=sentimiento.generarSentimiento(infoTexto);
        System.out.println("Este es el sentimiento: "+sentimientoAsignado);
        daoTexto.registrarTexto(infoTexto, fechaHora,pTematica,sentimientoAsignado);
    }
    
    
    @Override
    public String getFechaHora(){
        return fechaHora;
    }
    
    @Override
    public int getCantidadPalabras(){
        return cantidadPalabras;
    }
    
    @Override
    public int getID(){
        return idTexto;
    }
    
    public ArrayList<Map<String, Object>> getTextosDAO(){
        DAOTexto daoTexto = new DAOTexto();
        return daoTexto.obtenerTodosTextos();
    }
    
}