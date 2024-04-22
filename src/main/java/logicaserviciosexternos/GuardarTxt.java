package logicaserviciosexternos;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GuardarTxt {
     private static final String RUTA_ARCHIVO = "/textFile.txt";

    public GuardarTxt() {

    }

    // Método para guardar un String en un archivo .txt
    public void guardarStringEnArchivo(String texto) throws IOException {
        // Crear un objeto FileWriter para escribir en el archivo indicado
        try (InputStream inputStream = getClass().getResourceAsStream(RUTA_ARCHIVO);
             BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(getClass().getResource(RUTA_ARCHIVO).getPath()))) {

            // Escribir el nuevo texto en el archivo
            bufferedWriter.write(texto);
            System.out.println("Guardado");
        }
    }
}
