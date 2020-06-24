
/**
 * Write a description of CaesarCipherDecrypt here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;

public class CaesarCipherDecrypt {

    public String decrypt(String encrypted, int key1, int key2){
        StringBuilder e = new StringBuilder(encrypted);
        String Alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String Decrypt1 = Alpha.substring(key1) + Alpha.substring(0, key1);
        String Decrypt2 = Alpha.substring(key2) + Alpha.substring(0, key2);
        String alpha = Alpha.toLowerCase();
        String decrypt1 = Decrypt1.toLowerCase();
        String decrypt2 = Decrypt2.toLowerCase();
        for(int i=0; i<e.length(); i++)
        {
            char ch = e.charAt(i);
            int IDX1 = Decrypt1.indexOf(ch);
            int IDX2 = Decrypt2.indexOf(ch);
            if(i % 2 == 0 && IDX1 > -1 )
                e.setCharAt(i, Alpha.charAt(IDX1));
            else if(i % 2 == 1 && IDX2 > -1)
                e.setCharAt(i, Alpha.charAt(IDX2));
            int idx1 = decrypt1.indexOf(ch);
            int idx2 = decrypt2.indexOf(ch);
            if(i % 2 == 0 && idx1 > -1)
                e.setCharAt(i, alpha.charAt(idx1));
            else if(i % 2 == 1 && idx2 > -1)
                e.setCharAt(i, alpha.charAt(idx2));
        }
        return e.toString();
    }
    
    public void testDecrypt(){
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        int key1 = 23;
        int key2 = 2;
        String decrypted = decrypt(encrypted, key1, key2);
        System.out.println("Decoded message = " + decrypted);
    }
}
