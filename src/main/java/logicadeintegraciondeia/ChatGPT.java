package logicadeintegraciondeia;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class ChatGPT {
  private String key = "";
  
  public ChatGPT(){
      
  }
  
  public String generarRespuesta(String pCadenaTexto) {
    String url = "https://api.openai.com/v1/chat/completions";
    String model = "gpt-3.5-turbo";
    try {
      URL obj = new URL(url);
      HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
      connection.setRequestMethod("POST");
      connection.setRequestProperty("Authorization", "Bearer " + key);
      connection.setRequestProperty("Content-Type", "application/json");
      String body = "{\"model\": \"" + model + "\", \"messages\": [{\"role\": \"user\", \"content\": \"" + 
              pCadenaTexto + "\"}]}"; //USAR SOLO pCadenaTexto
      connection.setDoOutput(true);
      OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
      writer.write(body);
      writer.flush();
      writer.close();
      BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
      String line;
      StringBuilder response = new StringBuilder();
      while ((line = br.readLine()) != null) {
        response.append(line);
      }
      br.close();
      int start = response.toString().indexOf("content")+ 11;
      int end = response.toString().indexOf("\"", start);
      return response.toString().substring(start, end);

    } catch (IOException e) {
      throw new RuntimeException(e);
    }    
  }
  
  public String generarRespuesta(ArrayList<String> pCadenaTexto) {
    String url = "https://api.openai.com/v1/chat/completions";
    String model = "gpt-3.5-turbo";
    try {
      URL obj = new URL(url);
      HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
      connection.setRequestMethod("POST");
      connection.setRequestProperty("Authorization", "Bearer " + key);
      connection.setRequestProperty("Content-Type", "application/json");
      String body = "{\"model\": \"" + model + "\", \"messages\": [{\"role\": \"user\", \"content\": \"" + 
              "¿qué tienes que decir al respecto sobre estas palabras clave? " + pCadenaTexto + "\"}]}";  //USAR SOLO pCadenaTexto
      connection.setDoOutput(true);
      OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
      writer.write(body);
      writer.flush();
      writer.close();
      BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
      String line;
      StringBuilder response = new StringBuilder();
      while ((line = br.readLine()) != null) {
        response.append(line);
      }
      br.close();
      int start = response.toString().indexOf("content")+ 11;
      int end = response.toString().indexOf("\"", start);
      return response.toString().substring(start, end);

    } catch (IOException e) {
      throw new RuntimeException(e);
    }    
  }
}
