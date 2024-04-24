
package logicadenegocios;

import java.util.ArrayList;
import java.util.Map;
import logicadeaccesoadatos.DAOUsuario;
import logicadeaccesoadatos.DAOTematica;
import java.util.Collections;

/**
 *
 * @author josea
 */
public class Usuario implements IUsuario{
    private String id;
    private String nombreCompleto;
    private String correoElectronico;
    private String numeroTelefono;
    private String fotoUsuario;
    private DAOUsuario daoUsuario=new DAOUsuario();
    private ArrayList<Tematica> tematicasRegistradas=new ArrayList<>();
    private  ArrayList<IUsuario> listaUsuarios= new ArrayList<>();;
    
    public Usuario(String id, String nombreCompleto,String correoElectronico, String numeroTelefonico, String fotoUsuario){
        this.id=id;
        this.nombreCompleto=nombreCompleto;
        this.correoElectronico=correoElectronico;
        this.numeroTelefono=numeroTelefonico;
        this.fotoUsuario=fotoUsuario;
        // Agregar este usuario a la lista de usuarios
        listaUsuarios.add(this);
    }
    public Usuario(){
    }
    
    @Override
    public ArrayList<IUsuario> mostrarInformacionUsuario(){
        return listaUsuarios;
    }
   
    @Override
     public  ArrayList<Tematica> getTematicasRegistradas() {
         asignarTematicaUsuario();
         return tematicasRegistradas;
     }

    private Tematica crearTematicaDesdeJson(Map<String, Object> tematicaData) {
        String idTeamatica=(String) tematicaData.get("idTematica");
        String nombreTematica = (String) tematicaData.get("nombreTematica");
        String descripcionTematica = (String) tematicaData.get("descripcionTematica");
        String fotoTematica = (String) tematicaData.get("fotoTematica");
       
        // Otros atributos de Tematica según tu implementación

        // Crear y retornar un nuevo objeto Tematica
        return new Tematica(idTeamatica,nombreTematica, descripcionTematica, fotoTematica);
    }
    
    @Override
    public boolean menorQue(Comparable obj){
        return this.nombreCompleto.compareTo(((Usuario)obj).getNombre()) < 0;
    }
    
    public void guardarUsuario(){
        daoUsuario.registrarUsuario(id, nombreCompleto, correoElectronico, numeroTelefono, fotoUsuario);
    }
    
    private void asignarTematicaUsuario(){
        DAOTematica daoTematica=new DAOTematica();
        ArrayList<Map<String, Object>> tematicasJSON=daoTematica.obtenerTodasTematicas();
        tematicasRegistradas.clear();
        // Iterar sobre las temáticas del JSON y filtrar por idUsuario
        for (Map<String, Object> tematicaData : tematicasJSON) {
            String idUsuarioTematica = (String) tematicaData.get("idUsuario");

            // Si el idUsuario de la temática coincide con el id del usuario actual
            if (idUsuarioTematica != null && idUsuarioTematica.equals(id)) {
                // Crear un objeto Tematica y agregarlo a tematicasRegistradas
                Tematica tematica = crearTematicaDesdeJson(tematicaData);
                tematicasRegistradas.add(tematica);
            }
        }
        // Ordenar las temáticas registradas en orden alfabético por el nombre
        
        Collections.sort(tematicasRegistradas, (t1, t2) -> t1.getNombre().compareTo(t2.getNombre()));
    }
    public String getNombre(){
        return nombreCompleto;
    }
    public String getId(){
        return id;
    }
    
    public String getCorreoElectronico(){
        return correoElectronico;
    }
    
    public String getNumeroTelefono(){
        return numeroTelefono;
    }
    
    public String getFotoUsuario(){
        return fotoUsuario;
    }
    public void setCorreo(String pCorreo){
        this.correoElectronico=pCorreo;
    }
    public void setId(String pId){
        this.id=pId;
    }
    public void setNombreCompleto(String pNombreCompleto){
        this.nombreCompleto=pNombreCompleto;
    }
    public void setNumeroTelefono(String pNumeroTelefono){
        this.numeroTelefono=pNumeroTelefono;
    }
    public void setFotoUsuario(String pFotoUsuario){
        this.fotoUsuario=pFotoUsuario;
    }
    
    

}
