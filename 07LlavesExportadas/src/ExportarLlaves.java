import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;

public class ExportarLlaves {

    private KeyPairGenerator generadorllaves;
    private KeyPair llaversa;
    private PublicKey llavepublica;
    private PrivateKey llaveprivada;
    private Cipher rsa;
    static Signature signature;
     String cargarArchivo;
    public ExportarLlaves() {
        try {
            signature = Signature.getInstance("SHA256withRSA");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ExportarLlaves.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public PublicKey getLlavepublica() {
        return llavepublica;
    }

    public void setLlaveprivada(PrivateKey llaveprivada) {
        this.llaveprivada = llaveprivada;
    }

    public void setLlavepublica(PublicKey llavepublica) {
        this.llavepublica = llavepublica;
    }

    public PrivateKey getLlaveprivada() {
        return llaveprivada;
    }

    public void generarLlaves() {
        try {
            generadorllaves = KeyPairGenerator.getInstance("RSA");
            llaversa = generadorllaves.generateKeyPair();
            llavepublica = llaversa.getPublic();
            llaveprivada = llaversa.getPrivate();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ExportarLlaves.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getCargarArchivo() {
        return cargarArchivo;
    }

    public void setCargarArchivo(String cargarArchivo) {
        this.cargarArchivo = cargarArchivo;
    }
    
    public void cargarLlaveDesdeArchivo(String archivo) {
        try {
            
            FileInputStream fis = new FileInputStream(archivo);
            int numBytes = fis.available();
            byte[] bytes = new byte[numBytes];
            fis.read(bytes);
            fis.close();

            KeyFactory fabricaDeLlaves = KeyFactory.getInstance("RSA");
            KeySpec keySpec = new PKCS8EncodedKeySpec(bytes);
            llaveprivada = fabricaDeLlaves.generatePrivate(keySpec);
            setLlaveprivada(llaveprivada);
        } catch (Exception ex) {
            Logger.getLogger(ExportarLlaves.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String generarCifrado(String texto, String nombreArchivo) {
        try {
            rsa = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            rsa.init(Cipher.ENCRYPT_MODE, llavepublica);

             signature.initSign(llaveprivada);
            signature.update(texto.getBytes(StandardCharsets.UTF_8));
            byte[] firma = signature.sign();
            String firmatexto = bytesToHexString(firma);
            String archivo = texto + "\n" + firmatexto;
            saveFile(archivo);
            
            return nombreArchivo;
        } catch (Exception ex) {
            Logger.getLogger(ExportarLlaves.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

     static void saveKey(Key llave, String archivo) throws Exception{
    byte[] llavesPubPriv = llave.getEncoded();
    
        FileOutputStream fos = new FileOutputStream(archivo);
        fos.write(llavesPubPriv);
        fos.close();
    }

     static PublicKey loadpublicKey(String archivo) throws Exception{
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

     static PrivateKey loadprivateKey(String archivo) throws Exception{
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
     
    public boolean verifyFirma(String cargarArchivo) throws InvalidKeyException, SignatureException, Exception{
        
        String[] partes = cargarArchivo.split("\n");
            
            String contenidoLeido = partes[0];
            String firmaLeida = partes[1];
            boolean validacion;
           signature.initVerify(loadpublicKey("public.key"));
            signature.update(contenidoLeido.getBytes());

            // Convertir la firma leída de hexadecimal a bytes
            byte[] firmaBytesLeida = hexStringToByteArray(firmaLeida);

            if (signature.verify(firmaBytesLeida)) {
                System.out.println("La firma es válida. El documento no ha sido modificado.");
                validacion=true;
            } else {
                System.out.println("La firma no es valida. El documento puede haber sido modificado.");
                validacion=false;
            }
    return validacion;
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
     private static void saveFile(String file) throws Exception {
        byte[] fileByte = file.getBytes();
        FileOutputStream fos = new FileOutputStream("Firma.txt");
        fos.write(fileByte);
        fos.close();
    }

     static String loadFile(String archivo) throws Exception {

        FileInputStream fis = new FileInputStream(archivo);
        int numBytes = fis.available();
        byte[] bytes = new byte[numBytes];
        fis.read(bytes);
        fis.close();
        
        String contenido = new String(bytes, "UTF-8"); 
       
        return contenido;

    }
}
