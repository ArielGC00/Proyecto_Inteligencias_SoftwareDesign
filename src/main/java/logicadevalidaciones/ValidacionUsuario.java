/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicadevalidaciones;

import java.util.ArrayList;
import logicadeaccesoadatos.DAOUsuario;

/**
 *
 * @author Celeste
 */
public class ValidacionUsuario {
    public static boolean validarCedula(String cedula) {
        DAOUsuario daoUsuario=new DAOUsuario();
        ArrayList<String> datosUsuario=daoUsuario.obtenerUsuarioPorCedula(cedula);
        return !datosUsuario.isEmpty();
        
    }
}
