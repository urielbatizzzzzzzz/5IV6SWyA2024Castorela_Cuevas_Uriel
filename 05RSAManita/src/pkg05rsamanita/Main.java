/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pkg05rsamanita;

import java.math.BigInteger;
import java.util.*;
import java.io.*;

public class Main {

    /**
     * @param args the command line arguments
     */
    int tamprimo;
        BigInteger p,q,n;
        BigInteger fi;
        BigInteger e,d;
    public Main(int tamprimo){
            this.tamprimo=tamprimo;
        }
        public void generarPrimos(){
            p = new BigInteger(tamprimo,10,new Random ());
            do q = new BigInteger(tamprimo,10,new Random ());
                while(q.compareTo(p)== 0);
        }
        public void generarClaves(){
            n=p.multiply(q);
            fi= p.subtract(BigInteger.valueOf(1));
            
            fi=fi.multiply(q.subtract(BigInteger.valueOf(1)));
            
            do e= new BigInteger(2*tamprimo, new Random());
            while((e.compareTo(fi) !=1 )  || (e.gcd(fi).compareTo(BigInteger.valueOf(1))) != 0);
            
            d=e.modInverse(fi);
        }
        
        public BigInteger[] cifrar(String mensaje){
            int i;
            byte[] temp= new byte[1];
            byte[] digitos= mensaje.getBytes();
            
            BigInteger[] bigdigitos= new BigInteger[digitos.length];
            
            //Iteracion del mensaje
            for(i=0;i<bigdigitos.length;i++){
                temp[0] = digitos[i];
                bigdigitos[i]= new  BigInteger(temp);
            }
            //Cifrado del big
            BigInteger[] cifrado = new BigInteger[bigdigitos.length];
            
            for(i=0;i<bigdigitos.length;i++){
                //Formula C=M^e mod n
                cifrado[i]=bigdigitos[i].modPow(e, n);
            }
            return cifrado;
        }
        public String descifrar(BigInteger[] cifrado){
            int i;
            BigInteger[] descifrado=new BigInteger[cifrado.length];
            
            
            for(i=0;i<descifrado.length;i++){
                descifrado[i]= cifrado[i].modPow(d,n);
            }
            
            char[] charArray= new char[descifrado.length];
             for(i=0;i<charArray.length;i++){
                charArray[i] = (char)(descifrado[i].intValue());
            }
            return (new String(charArray));
        }
    public static void main(String[] args) {
    Main rsa = new Main(1024); // Puedes ajustar el tamaño del primo según tus necesidades
    rsa.generarPrimos();
    rsa.generarClaves();

    String mensajeOriginal = "Hola, este es un mensaje de prueba.";
    BigInteger[] cifrado = rsa.cifrar(mensajeOriginal);
    System.out.println("Mensaje cifrado: " + Arrays.toString(cifrado));

    String mensajeDescifrado = rsa.descifrar(cifrado);
    System.out.println("Mensaje descifrado: " + mensajeDescifrado);
}

}
