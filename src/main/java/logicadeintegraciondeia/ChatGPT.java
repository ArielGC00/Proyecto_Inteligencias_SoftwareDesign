package logicadeintegraciondeia;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class ChatGPT {
    //sk-proj-xMLX90T8da3jSKfcfD5iT3BlbkFJbigyGy1pn4inhK7s8gWb
    private String key = "";
  
    public ChatGPT(){
      
    }
  
    public String generarRespuesta(String pCadenaTexto) {
        String url = "https://api.openai.com/v1/chat/completions";
        String model = "gpt-3.5-turbo";
        String body = buildRequestBody(pCadenaTexto, model);
        String respuesta = sendRequest(url, body);
        return processResponse(respuesta);
    }

    private String buildRequestBody(String pCadenaTexto, String model) {
        return "{\"model\": \"" + model + "\", \"messages\": [{\"role\": \"user\", \"content\": \"" + pCadenaTexto + "\"}]}";
    }

    private String sendRequest(String url, String body) {
        try {
            URL obj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Bearer " + key);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(body);
            writer.flush();
            writer.close();
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                response.append(line);
            }
            br.close();
            return response.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String processResponse(String response) {
        int start = response.indexOf("content") + 11;
        int end = response.indexOf("\"", start);
        return response.substring(start, end);
    }
  
  
}
