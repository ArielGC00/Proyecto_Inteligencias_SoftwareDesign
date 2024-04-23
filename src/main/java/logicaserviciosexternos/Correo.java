package logicaserviciosexternos;

import java.io.IOException;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class Correo {
    private static final String RUTA_OUTPUT_PDF = "/outputPdf.pdf";
    
    public void enviarCorreo(String pDestinatario) throws MessagingException {
        // Configuración de las propiedades del servidor SMTP
        String host = "smtp.gmail.com";
        String usuario = "jojoar04@gmail.com"; 
        
        String contraseña = "bidn dxoq prdt ylwz"; 
        // Destinatario y asunto del correo
        String asunto = "Este es el texto y el resultado de sus respectivas funcionalidades";

        // Ruta del archivo PDF que deseas adjuntar
        String archivoPDF = getClass().getResource(RUTA_OUTPUT_PDF).getPath();

        // Configuración de propiedades para la sesión de correo
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", "587");

        // Autenticación del usuario con el servidor SMTP
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(usuario, contraseña);
            }
        });

        try {
            // Crear mensaje de correo
            Message mensaje = new MimeMessage(session);
            mensaje.setFrom(new InternetAddress(usuario));
            mensaje.setRecipients(Message.RecipientType.TO, InternetAddress.parse(pDestinatario));
            mensaje.setSubject(asunto);

            // Crear cuerpo del mensaje (opcional)
            mensaje.setText("Adjunto encontrarás un documento PDF.");

            // Adjuntar archivo PDF al mensaje
            MimeBodyPart adjunto = new MimeBodyPart();
            adjunto.attachFile(archivoPDF);

            // Combinar mensaje con archivo adjunto
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(adjunto);

            mensaje.setContent(multipart);

            // Enviar mensaje
            Transport.send(mensaje);

            System.out.println("Correo con PDF enviado exitosamente a " + pDestinatario);
        } catch (IOException e) {
            System.err.println("Error al adjuntar el archivo PDF: " + e.getMessage());
        }
    }
}
