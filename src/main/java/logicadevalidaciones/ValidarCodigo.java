/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicadevalidaciones;

import logicadeaccesoadatos.DAOUsuario;

/**
 *
 * @author Celeste
 */
public class ValidarCodigo {
    public static boolean validarCodigo(String pCodigo,String pId){
        
        TwoFactoAuth secretKey=new TwoFactoAuth();
     
        DAOUsuario daoUsuario=new DAOUsuario();
        
        String keyGoogle=daoUsuario.consultarKeyTwoFactorAuth(pId);
        
        String tempCodigo=secretKey.getCodigo(keyGoogle);
        
        
        return tempCodigo.equals(pCodigo);
        
    }
}
