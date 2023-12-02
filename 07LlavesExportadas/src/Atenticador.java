import java.io.FileInputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

public class Atenticador {

    private static Signature signature;

    public static void main(String[] args) throws Exception {
        PublicKey publicKey;
        PrivateKey privateKey;
        String cargarArchivo = loadFile("Firma.txt");
        publicKey = loadpublicKey("public.key");
        privateKey = loadprivateKey("private.key");
        signature = Signature.getInstance("SHA256withRSA");
        if(cargarArchivo.length() == 0) {
            System.out.println("El archivo esta vacio. No se pudo leer");
            return;
        }

        String[] partes = cargarArchivo.split("\n");
        if (partes.length != 2) {
            System.out.println("El archivo Se ha modificado. No se pudo leer");
            return;
        }

        String contenidoLeido = partes[0];
        String firmaLeida = partes[1];
        if (contenidoLeido.length() > firmaLeida.length()) {
            System.out.println("El archivo Se ha modificado. No se pudo leer");
            return;
        }

        if (firmaLeida.length() != 256) {
            System.out.println("El archivo Se ha modificado. No se pudo leer");
            return;
        }
        System.out.println("Contenido leído: " + contenidoLeido);
        System.out.println("Firma leída: " + firmaLeida);

        // Verificación de la firma
        signature.initVerify(publicKey);
        signature.update(contenidoLeido.getBytes());

        byte[] firmaBytesLeida = hexStringToByteArray(firmaLeida);

            if (signature.verify(firmaBytesLeida)) {
                System.out.println("La firma es válida. El documento no ha sido modificado.");
            } else {
                System.out.println("La firma no es válida. El documento puede haber sido modificado.");
            }
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
