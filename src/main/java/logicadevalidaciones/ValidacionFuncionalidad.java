/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicadevalidaciones;

/**
 *
 * @author Celeste
 */
public class ValidacionFuncionalidad {
    public static boolean validarAtributos(Object... atributos) {
        for (Object atributo : atributos) {
            if (atributo == null) {
                return false;
            }
        }
        return true;
    }
}
