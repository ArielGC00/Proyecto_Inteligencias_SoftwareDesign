package logicadenegocios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import logicadeaccesoadatos.DAOTematica;
/**
 *
 * @author Celeste
 */
public class Tematica implements ITematica{
    private String idUsuario;
    private String nombreTematica;
    private String descripcionTematica;
    private String fotoTematica;
    private String idTematica;
    private Map<String, String> textosMostrados; // Mapa para almacenar texto con su ID
    private List<Map<String, Object>> textos;
    
    private ArrayList<String> textosA=new ArrayList<>();//NO QUITAR NO FUNCIONA PERO SE CAE 
    
    private DAOTematica daoTematica = new DAOTematica();
    
    public Tematica(String pId,String nombreTematica, String descripcionTematica, String fotoTematica){
        this.nombreTematica = nombreTematica;
        this.descripcionTematica = descripcionTematica;
        this.fotoTematica = fotoTematica;
        this.idTematica = pId;
        this.textos = new ArrayList<>();
        this.textosMostrados = new HashMap<>();
        
    }
    public Tematica(){
        this.textos = new ArrayList<>();
        this.textosMostrados = new HashMap<>();
    }
    
    
    @Override
    public boolean menorQue(Comparable obj){
        return this.nombreTematica.compareTo(((Tematica)obj).getNombre()) < 0;
    }
    
    public String getIdUsuario(){
      return this.idUsuario;
    }
    
    public void setIdUsuario(String pIdUsuario){
      this.idUsuario = pIdUsuario;
    }
    
    // Método para crear un texto
    @Override
    public void crearTexto(String pTexto,String pIdTematica){
        // Crea una nueva instancia
        Texto nuevoTexto = new Texto(pTexto);
        nuevoTexto.guardarTexto(pIdTematica);
        

    }
    
    public void guardarTematica(String idUsario){
        System.out.println("Se ejeutaa");
        boolean estado=daoTematica.registrarTematica(nombreTematica, descripcionTematica, fotoTematica, null, textosA,idUsario);
        System.out.println("Resultado: " +estado);
    }
    
    @Override
    public String getNombre(){
        return nombreTematica;
    }
    
    @Override
    public Map<String, String> getTextosMostrados(){
        asignarTexto();
        return textosMostrados;
    }
    
    public List<Map<String, Object>> getTextos(){
        asignarTextoCompleto();
        return textos;
    }
    
    public void construirTematica(String pIdTematica){
        
        ArrayList<Map<String, Object>> tematicasJSON=daoTematica.obtenerTodasTematicas();
        for (Map<String, Object> tematicaData : tematicasJSON) {
            String idTematicaJson = (String) tematicaData.get("idTematica");

            
            if (idTematicaJson != null && idTematicaJson.equals(pIdTematica)) {
                // Crear un objeto Tematica y agregarlo a tematicasRegistradas
                setIdTematica((String)tematicaData.get("idTematica"));
                setNombre((String)tematicaData.get("nombreTematica"));
                setDescripcion((String)tematicaData.get("descripcionTematica"));
                setFotoTematica((String)tematicaData.get("fotoTematica"));
            }
        }
    }
    public void asignarTextoCompleto() {
        Texto textoTemp = new Texto();
        textos.clear();
        textos = textoTemp.getTextosDAO();


    }
    public void asignarTexto(){
        Texto textoTemp = new Texto();
        List<Map<String, Object>> textosJSON = textoTemp.getTextosDAO();
        textosMostrados.clear(); // Limpiar el mapa antes de agregar nuevos textos

        // Iterar sobre los textos del JSON y filtrar por idTematica
        for (Map<String, Object> textoData : textosJSON) {
            String idTematicaTexto = (String) textoData.get("idTematica");

            // Si el idTematica del texto coincide con el idTematica actual
            if (idTematicaTexto != null && idTematicaTexto.equals(idTematica)) {
                String idTexto = (String) textoData.get("id");
                String descripcionTexto = crearTextoDesdeMapa(textoData);

                // Agregar el texto al mapa usando el idTexto como clave
                textosMostrados.put(idTexto, descripcionTexto);
            }
        }
    }
    
    private String crearTextoDesdeMapa(Map<String, Object> pTextoData) {
        String descripcionTexto = (String) pTextoData.get("texto");
        Texto texto=new Texto(descripcionTexto);
        descripcionTexto=texto.mostrarTexto();
        // Crear y retornar un nuevo objeto Tematica
        return descripcionTexto;
    }
    
    public String getDscripcionTematica(){
        return descripcionTematica;
    }
    public String getIdTematica(){
        return idTematica;
    }
    public String getFotoTematica(){
        return fotoTematica;
    }
    public void setIdTematica(String pId){
        this.idTematica=pId;
    }
    public void setNombre(String pNombre){
        this.nombreTematica=pNombre;
    }
    public void setDescripcion(String pDescripcion){
        this.descripcionTematica=pDescripcion;
    }
    public void setFotoTematica(String foto){
        this.fotoTematica=foto;
    }
    
}
