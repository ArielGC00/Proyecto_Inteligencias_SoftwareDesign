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
public class OpinionChatGPT {
    protected ChatGPT chatGPT;
    public OpinionChatGPT(){
        this.chatGPT = new ChatGPT();
    }
    
    public String obtenerOpinion(String pTexto) {
        String consulta = ("En un párrefo, que opinas de esto, empieza la opinión con la oración, mi opinión es: "+pTexto);
        String respuesta = chatGPT.generarRespuesta(consulta);

        return respuesta;
    }
}
