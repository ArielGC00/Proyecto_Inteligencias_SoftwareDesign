/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicadevalidaciones;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import de.taimos.totp.TOTP;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.util.Scanner;
import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.Hex;


public class TwoFactoAuth {
    
    public static String generateSecretKey() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[20];
        random.nextBytes(bytes);
        Base32 base32 = new Base32();
        return base32.encodeToString(bytes);
    }
   
    public static String getTOTPCode(String secretKey) {
        Base32 base32 = new Base32();
        byte[] bytes = base32.decode(secretKey);
        String hexKey = Hex.encodeHexString(bytes);
        return TOTP.getOTP(hexKey);
    }
    
    public static void validar(){
        String keyBase32="CYG6NVSYLVX4QNSM6BLSWIHJXS4FVS43";
        String lastCode = null;
        while (true) {
            String code = getTOTPCode(keyBase32);
            if (!code.equals(lastCode)) {
                System.out.println(code);
            }
            lastCode = code;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {};
        }
    }
    public String getCodigo(String pKeyBase32){ 
        String code = getTOTPCode(pKeyBase32);
        return code;
    }
    
    public static String getGoogleAuthenticatorBarCode(String secretKey, String account, String issuer) {
        try {
            return "otpauth://totp/"
                    + URLEncoder.encode(issuer + ":" + account, "UTF-8").replace("+", "%20")
                    + "?secret=" + URLEncoder.encode(secretKey, "UTF-8").replace("+", "%20")
                    + "&issuer=" + URLEncoder.encode(issuer, "UTF-8").replace("+", "%20");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e);
        }
    }
    public static void createQRCode(String barCodeData, String filePath, int height, int width)
            throws WriterException, IOException {
            BitMatrix matrix = new MultiFormatWriter().encode(barCodeData, BarcodeFormat.QR_CODE,
                    width, height);
            try (FileOutputStream out = new FileOutputStream(filePath)) {
                MatrixToImageWriter.writeToStream(matrix, "png", out);
            }
        }
    
    
    
   // public static void main(String[] args) throws WriterException, IOException {
        
        //TwoFactoAuth secretKey=new TwoFactoAuth();
        //System.out.println(secretKey.generateSecretKey());
        //String path="C:\\Users\\josea\\OneDrive\\Documentos\\TEC\\TEC\\TEC\\V semestre\\diseño\\HolaMundoWeb\\src\\main\\java\\logicadenegocios\\QR.png";
        //String qrKey=secretKey.getGoogleAuthenticatorBarCode("CYG6NVSYLVX4QNSM6BLSWIHJXS4FVS43","vegal7770@gmail.com@","Proyecto 1");
       //secretKey.createQRCode(qrKey,path,450,450);
       //secretKey.getCodigo();
       //secretKey.validar();
   // }
    //qr key:otpauth://totp/Tecnologico%20de%20costa%20rica%3Ajosearielgc.040%40gmail.com?secret=USZHQ6U37OPB75QILRMMZY2VLDYFEA5C&issuer=Tecnologico%20de%20costa%20rica ariel
}