import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;


import javax.crypto.Cipher;

public class Creador {

    private static Cipher rsa;
    private static Signature signature;

    public static void main(String[] args) throws Exception{
        generateKey();
        generateFirma();
    }

    // Metodos Para los archivos

    private static void generateKey() throws Exception {
        KeyPairGenerator generadorllaves = KeyPairGenerator.getInstance("RSA");
        KeyPair llaversa = generadorllaves.generateKeyPair();

        PublicKey llavepublica = llaversa.getPublic();
        PrivateKey llaveprivada = llaversa.getPrivate();

        saveKey(llavepublica, "public.key");

        saveKey(llaveprivada, "private.key");

    }

    private static void generateFirma() throws Exception {
        PrivateKey llaveprivada = loadprivateKey("private.key");
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
        System.out.println(archivo);
        saveFile(archivo);
    }

    private static void saveFile(String file) throws Exception {

        byte[] fileByte = file.getBytes();
        FileOutputStream fos = new FileOutputStream("Firma.txt");
        fos.write(fileByte);
        fos.close();
    }

    private static void saveKey(Key llave, String archivo) throws Exception {
        byte[] llavesPubPriv = llave.getEncoded();

        FileOutputStream fos = new FileOutputStream(archivo);
        fos.write(llavesPubPriv);
        fos.close();
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

    public static String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X", b));
        }
        return sb.toString();
    }

}
