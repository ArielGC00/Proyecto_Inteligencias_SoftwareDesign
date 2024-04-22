
package logicaserviciosexternos;


import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.text_to_speech.v1.TextToSpeech;
import com.ibm.watson.text_to_speech.v1.model.SynthesizeOptions;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

public class TextoToSpeech1 {
    
  public static void hablar(String pTexto){
    IamAuthenticator authenticator = new IamAuthenticator("lv_y3MfXyccr8_-h0DCoYv_yUXXLRDQGHtl1iw8asdG0");
    TextToSpeech textToSpeech = new TextToSpeech(authenticator);
    textToSpeech.setServiceUrl("https://api.au-syd.text-to-speech.watson.cloud.ibm.com/instances/5837d746-9e90-4f97-a2d5-0eec27c58639");
    int idioma_id = Traductor.detectarIdioma(pTexto);
    if(idioma_id == 0){
      try {
        SynthesizeOptions synthesizeOptions =
          new SynthesizeOptions.Builder()
            .text(pTexto)
            .accept("audio/wav")
            .voice("es-LA_SofiaVoice")
            .build();
        InputStream bufferedInputStream;
        try (InputStream inputStream = textToSpeech.synthesize(synthesizeOptions).execute().getResult()) {
          bufferedInputStream = new BufferedInputStream(inputStream);
          crear(bufferedInputStream);
        }
        bufferedInputStream.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    } else if(idioma_id == 1){
      try {
        SynthesizeOptions synthesizeOptions =
          new SynthesizeOptions.Builder()
            .text(pTexto)
            .accept("audio/wav")
            .voice("en-US_AllisonExpressive")
            .build();
        InputStream bufferedInputStream;
        try (InputStream inputStream = textToSpeech.synthesize(synthesizeOptions).execute().getResult()) {
          bufferedInputStream = new BufferedInputStream(inputStream);
          crear(bufferedInputStream);
        }
        bufferedInputStream.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
  
  public static void main(String[] args){
      System.out.println("Hola mundo");
    hablar("Exploring the vast cosmos, scientists seek answers to cosmic mysteries through powerful telescopes and satellites.");
  }
    
  private static void crear(InputStream inputStream) {
    AudioInputStream audioStream = null;
    try {
      audioStream = AudioSystem.getAudioInputStream(inputStream);
      AudioFormat format = audioStream.getFormat();
      DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
      SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info);
      line.open(format);
      line.start();
      byte[] buffer = new byte[2048];
      int length;
      while ((length = inputStream.read(buffer)) > 0) {
        if (length % 2 != 0) {
          buffer[length] = 0;
          length++;
        }
        line.write(buffer, 0, length);
      } line.drain();
      line.close();
    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
      Logger.getLogger(TextoToSpeech1.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      try {
        audioStream.close();
      } catch (IOException ex) {
        Logger.getLogger(TextoToSpeech1.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }
}

