
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
import java.security.*;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

/**
 *
 * @author Alumno
 */
public class ExportarLlaves {

    private static Cipher rsa;
    private static Signature signature;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        try {
            KeyPairGenerator generadorllaves = KeyPairGenerator.getInstance("RSA");
            KeyPair llaversa = generadorllaves.generateKeyPair();

            PublicKey llavepublica = llaversa.getPublic();
            PrivateKey llaveprivada = llaversa.getPrivate();

            saveKey(llavepublica, "public.key");
            llavepublica = loadpublicKey("public.key");

            saveKey(llaveprivada, "private.key");
            llaveprivada = loadprivateKey("private.key");

            rsa = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            signature = Signature.getInstance("SHA256withRSA");

            String texto = "AAAA00000 NOMBRE CDMX";

            // Firma digital del contenido
            signature.initSign(llaveprivada);
            signature.update(texto.getBytes(StandardCharsets.UTF_8));
            byte[] firma = signature.sign();
            String firmatexto = bytesToHexString(firma);
            ;
            String archivo = texto + "\n" + firmatexto;
            saveFile(archivo);

            // Hasta aqui llega el cifrado

            String cargarArchivo = loadFile("Firma.txt");

            String[] partes = cargarArchivo.split("\n");
            String contenidoLeido = partes[0];
            String firmaLeida = partes[1];

            System.out.println("Contenido leído: " + contenidoLeido);
            System.out.println("Firma leída: " + firmaLeida);

            // Verificación de la firma
            signature.initVerify(llavepublica);
            signature.update(contenidoLeido.getBytes());

            // Convertir la firma leída de hexadecimal a bytes
            byte[] firmaBytesLeida = hexStringToByteArray(firmaLeida);

            if (signature.verify(firmaBytesLeida)) {
                System.out.println("La firma es válida. El documento no ha sido modificado.");
            } else {
                System.out.println("La firma no es válida. El documento puede haber sido modificado.");
            }

            rsa.init(Cipher.ENCRYPT_MODE, llavepublica);

            byte[] cifrado = rsa.doFinal(texto.getBytes());

            rsa.init(Cipher.DECRYPT_MODE, llaveprivada);

            byte[] bytesdescifrados = rsa.doFinal(cifrado);

            String textodescifrado = new String(bytesdescifrados);

            System.out.println("Texto descifrado: " + textodescifrado);
        } catch (IOException e) {
            System.err.println("Error: " + e);
        }
    }

    private static void saveFile(String file) throws Exception {

        byte[] fileByte = file.getBytes();
        FileOutputStream fos = new FileOutputStream("Firma.txt");
        fos.write(fileByte);
        fos.close();
    }

    private static String loadFile(String archivo) throws Exception {

        FileInputStream fis = new FileInputStream(archivo);
        int numBytes = fis.available();
        byte[] bytes = new byte[numBytes];
        fis.read(bytes);
        fis.close();

        String contenido = new String(bytes, "UTF-8");
        return contenido;

    }

    private static void saveKey(Key llave, String archivo) throws Exception {
        byte[] llavesPubPriv = llave.getEncoded();

        FileOutputStream fos = new FileOutputStream(archivo);
        fos.write(llavesPubPriv);
        fos.close();
    }

    private static PublicKey loadpublicKey(String archivo) throws Exception {
        FileInputStream fis = new FileInputStream(archivo);
        int numBytes = fis.available();
        byte[] bytes = new byte[numBytes];
        fis.read(bytes);
        fis.close();

        KeyFactory fabricadeLlaves = KeyFactory.getInstance("RSA");

        KeySpec keyspec = new X509EncodedKeySpec(bytes);
        PublicKey llavesdeArchivos = fabricadeLlaves.generatePublic(keyspec);

        return llavesdeArchivos;
    }

    private static PrivateKey loadprivateKey(String archivo) throws Exception {
        FileInputStream fis = new FileInputStream(archivo);
        int numBytes = fis.available();
        byte[] bytes = new byte[numBytes];
        fis.read(bytes);
        fis.close();

        KeyFactory fabricadeLlaves = KeyFactory.getInstance("RSA");

        KeySpec keyspec = new PKCS8EncodedKeySpec(bytes);
        PrivateKey llavesdeArchivos = fabricadeLlaves.generatePrivate(keyspec);

        return llavesdeArchivos;
    }

    public static byte[] hexStringToByteArray(String hexString) {
        if (hexString == null || hexString.isEmpty()) {
            return new byte[0];
        }

        int len = hexString.length();
        // Asegurarse de que la longitud sea par
        if (len % 2 != 0) {
            hexString = "0" + hexString;
            len++;
        }

        byte[] data = new byte[len / 2];

        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4)
                    + Character.digit(hexString.charAt(i + 1), 16));
        }

        return data;
    }

    public static String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X", b));
        }
        return sb.toString();
    }

}
