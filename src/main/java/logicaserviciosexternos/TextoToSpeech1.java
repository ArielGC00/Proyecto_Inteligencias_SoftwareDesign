package logicaserviciosexternos;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;


public class TextToSpeech1 {
  private static final String apiKey = "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";
  
  public static void text_to_speech(String texto){
    try {
      // Realizar la solicitud HTTP para obtener el audio
      URL url = new URL("https://api.openai.com/v1/audio/speech");
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("POST");
      connection.setRequestProperty("Content-Type", "application/json");
      connection.setRequestProperty("Authorization", "Bearer " + apiKey);
      connection.setDoOutput(true);

      String postData = "{\"model\":\"tts-1\",\"voice\":\"alloy\",\"input\":\"" + texto + "\"}";
      byte[] postDataBytes = postData.getBytes(StandardCharsets.UTF_8);

      OutputStream outputStream = connection.getOutputStream();
      outputStream.write(postDataBytes);
      outputStream.flush();

      if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
          // Obtener el flujo de entrada del audio de la respuesta HTTP
          InputStream inputStream = connection.getInputStream();

          // Reproducir el audio directamente desde el flujo de entrada
          reproducirAudio(inputStream);
      } else {
          System.out.println("Error: " + connection.getResponseCode() + " - " + connection.getResponseMessage());
      }

      connection.disconnect();
    } catch (IOException e) {
        e.printStackTrace();
    }
  }

  private static void reproducirAudio(InputStream inputStream) {
    try {
      Player player = new Player(inputStream);
      player.play();
    } catch (JavaLayerException ex) {
      Logger.getLogger(TextToSpeech.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
}
