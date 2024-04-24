package logicaserviciosexternos;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;


public class TextoToSpeech1 {
  private static final String apiKey = "sk-proj-q4Chv08pOs5IwY8XQAPDT3BlbkFJTsGQX8r0N0oHejha4A3r";
  
  public static byte[] text_to_speech(String texto) {
    try {
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
            InputStream inputStream = connection.getInputStream();
            byte[] audioBytes = obtenerBytesDesdeInputStream(inputStream);
            guardarMP3(audioBytes);
            connection.disconnect();
            return audioBytes;
        } else {
            System.out.println("Error: " + connection.getResponseCode() + " - " + connection.getResponseMessage());
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    return null;
}

private static byte[] obtenerBytesDesdeInputStream(InputStream inputStream) throws IOException {
    ByteArrayOutputStream buffer = new ByteArrayOutputStream();
    byte[] data = new byte[4096];
    int bytesRead;
    while ((bytesRead = inputStream.read(data, 0, data.length)) != -1) {
        buffer.write(data, 0, bytesRead);
    }
    buffer.flush();
    return buffer.toByteArray();
}

private static void guardarMP3(byte[] audioBytes) throws IOException {
    Path speechFilePath = Paths.get("text_to_speech.mp3");
    FileOutputStream fileOutputStream = new FileOutputStream(speechFilePath.toFile());
    fileOutputStream.write(audioBytes);
    fileOutputStream.close();
    System.out.println("El archivo MP3 se ha guardado correctamente en: " + speechFilePath);
}

  private static void reproducirAudio(InputStream inputStream) {
    try {
      Player player = new Player(inputStream);
      player.play();
    } catch (JavaLayerException ex) {
      Logger.getLogger(TextoToSpeech1.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  public static void main(String[] args) {
        // Texto de prueba
        String texto = "Emotional intelligence is the ability to recognize, understand, and manage one's own emotions and the emotions of others effectively. It encompasses skills like empathy, self-awareness, self-control, and social skills, and plays a key role in personal well-being and successful interpersonal relationships..";

        // Llamada al método para convertir texto a voz y reproducirlo
        TextoToSpeech1.text_to_speech(texto);
    }
}
