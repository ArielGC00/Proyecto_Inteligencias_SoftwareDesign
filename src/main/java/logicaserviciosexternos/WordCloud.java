
package logicaserviciosexternos;

import com.kennycason.kumo.CollisionMode;
import com.kennycason.kumo.WordFrequency;
import com.kennycason.kumo.bg.RectangleBackground;
import com.kennycason.kumo.font.scale.LinearFontScalar;
import com.kennycason.kumo.nlp.FrequencyAnalyzer;
import com.kennycason.kumo.palette.ColorPalette;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


/**
 *
 * @author josea
 */
public class WordCloud {
    private static final String RUTA_ARCHIVO_TXT = "/textFile.txt";
    private static final String RUTA_IMAGEN_WORDCLOUD = "/wordCloudImage.png";
    public WordCloud() {

    }
    public byte[] generarWordCloud() throws IOException {
        // Cargar el archivo de texto y analizar las frecuencias de palabras
        final FrequencyAnalyzer frequencyAnalyzer = new FrequencyAnalyzer();
        final List<WordFrequency> wordFrequencies = frequencyAnalyzer.load(getClass().getResource(RUTA_ARCHIVO_TXT).getPath());
        final Dimension dimension = new Dimension(600, 600);
        final com.kennycason.kumo.WordCloud wordCloud = new com.kennycason.kumo.WordCloud(dimension, CollisionMode.RECTANGLE);
        wordCloud.setPadding(0);
        wordCloud.setBackground(new RectangleBackground(dimension));
        wordCloud.setColorPalette(new ColorPalette(Color.RED, Color.GREEN, Color.YELLOW, Color.BLUE));
        wordCloud.setFontScalar(new LinearFontScalar(10, 40));
        wordCloud.build(wordFrequencies);

        // Obtener la ruta del archivo de imagen del WordCloud
        Path imagePath;
        try {
            imagePath = Paths.get(getClass().getResource(RUTA_IMAGEN_WORDCLOUD).toURI());
        } catch (URISyntaxException e) {
            System.out.println("Error al obtener la URI de la imagen del WordCloud: " + e.getMessage());
            return null;
        }

        // Eliminar la imagen existente si ya existe
        try {
            Files.deleteIfExists(imagePath);
        } catch (IOException e) {
            System.out.println("Error al eliminar la imagen existente del WordCloud: " + e.getMessage());
        }

        // Guardar la nueva imagen del WordCloud en la ruta obtenida
        wordCloud.writeToFile(imagePath.toFile().getAbsolutePath());

        // Convertir la imagen del WordCloud a un arreglo de bytes para devolverlo como resultado
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        wordCloud.writeToStreamAsPNG(byteArrayOutputStream);

        return byteArrayOutputStream.toByteArray();
    }
    
    
}
