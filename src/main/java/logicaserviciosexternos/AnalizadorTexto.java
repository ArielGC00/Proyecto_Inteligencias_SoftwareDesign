/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicaserviciosexternos;

import logicadeintegraciondeia.ChatGPT;

/**
 *
 * @author Celeste
 */
public abstract class AnalizadorTexto {
    protected ChatGPT chatGPT;
    public String respuesta;

    public AnalizadorTexto() {
        this.chatGPT = new ChatGPT();
    }

    public abstract String obtenerResultado(String texto);
    
}
