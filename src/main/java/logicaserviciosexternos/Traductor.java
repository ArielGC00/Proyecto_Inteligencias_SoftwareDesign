
package logicaserviciosexternos;

import com.detectlanguage.DetectLanguage;
import com.detectlanguage.errors.APIError;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Traductor {
  public static int detectarIdioma(String pTexto){
    int codigo_idioma = 0;
    DetectLanguage.apiKey = "981a515338ad42ce186253a42ecbf9fd";
    String idioma = null;
    try{
      idioma = DetectLanguage.simpleDetect(pTexto);
    } catch (APIError ex) {
      Logger.getLogger(TextoToSpeech1.class.getName()).log(Level.SEVERE, null, ex);
    }
    if(idioma.equals("es")){
      codigo_idioma = 0;
    } else if(idioma.equals("en")){
      codigo_idioma = 1;
    }
    return codigo_idioma;
  }
  


  public static String traducirTexto(String pTexto) {
    String key="sk-proj-q4Chv08pOs5IwY8XQAPDT3BlbkFJTsGQX8r0N0oHejha4A3r";
    String url = "https://api.openai.com/v1/chat/completions";
    String model = "gpt-3.5-turbo";
    try {
      URL obj = new URL(url);
      HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
      connection.setRequestMethod("POST");
      connection.setRequestProperty("Authorization", "Bearer " + key);
      connection.setRequestProperty("Content-Type", "application/json");
      String body = "{\"model\": \"" + model + "\", \"messages\": [{\"role\": \"user\", \"content\": \"" + 
              "Traduce esto a ingles: "+ pTexto + "\"}]}";
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
