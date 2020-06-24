
/**
 * Write a description of EncryptAndDecrypt here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class EncryptAndDecrypt {
    
    public String encrypt(String input, int key){
        String Alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String E = Alphabet.substring(key) + Alphabet.substring(0, key);
        String alphabet = Alphabet.toLowerCase();
        String e = E.toLowerCase();
        StringBuilder in = new StringBuilder(input);  
        for(int i=0; i<in.length(); i++)
        {
            int IDX = Alphabet.indexOf(in.charAt(i));
            if(IDX != -1)
                in.setCharAt(i, E.charAt(IDX));
            int idx = alphabet.indexOf(in.charAt(i));
            if(idx != -1)
                in.setCharAt(i, e.charAt(idx));
        }
        return in.toString();
    }
    
    public void testCaesar(){
        //FileResource fr = new FileResource();
        //String message = fr.asString();
        String message = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
        int key1 = 8;
        int key2 = 21;
        String encrypted = encryptTwoKeys(message, key1, key2);
        System.out.println("Key1 & key2 is " + key1 + " & " + key2 + "\n" + encrypted);
    }
    
    public String encryptTwoKeys(String input, int key1, int key2){
        String Alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String E1 = Alphabet.substring(key1) + Alphabet.substring(0, key1);
        String E2 = Alphabet.substring(key2) + Alphabet.substring(0, key2);
        String alphabet = Alphabet.toLowerCase();
        String e1 = E1.toLowerCase();
        String e2 = E2.toLowerCase();
        StringBuilder in = new StringBuilder(input);
        for(int i=0; i<in.length(); i++)
        {
            int IDX = Alphabet.indexOf(in.charAt(i));
            if(IDX != -1)
            {
                if(i%2 == 0)
                    in.setCharAt(i, E1.charAt(IDX));
                else
                    in.setCharAt(i, E2.charAt(IDX));
            }
            int idx = alphabet.indexOf(in.charAt(i));
            if(idx != -1)
            {
                if(i%2 == 0)
                    in.setCharAt(i, e1.charAt(idx));
                else
                    in.setCharAt(i, e2.charAt(idx));
            }
        }
        return in.toString();
    }
    
    public String decrypt(String encrypted){
        int key = getKey(encrypted);
        System.out.println("key = " + key);
        return encrypt(encrypted, 26 - key);
    }
    
    public void testDecrypt(){
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        String decoded = decrypt(encrypted);
        System.out.println("Decoded message : " + decoded);
    }
    
    public String decryptTwoKeys(String encrypted){
        String s1 = halfOfString(encrypted, 0);
        String s2 = halfOfString(encrypted, 1);
        int key1 = getKey(s1);
        int key2 = getKey(s2);
        System.out.println("key1 = " + key1 + "\n" + "key2 = " + key2);
        return encryptTwoKeys(encrypted, 26 - key1, 26 - key2);
    }
        
    public void testDecryptTwoKeys(){
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        String decoded = decryptTwoKeys(encrypted);
        System.out.println("Decoded message : " + decoded);
    }
    
    public void countLetters(String s, int[] counts){
        s = s.toLowerCase();
        String alpha = "abcdefghijklmnopqrstuvwxyz";
        for(int i=0; i<s.length(); i++)
        {
            int idx = alpha.indexOf(s.charAt(i));
            if(idx > -1)
                counts[idx] += 1;
        }
    }
    
    public void testCountLetters(){
        FileResource fr = new FileResource();
        String s = fr.asString();
        int[] counts = new int[26];
        countLetters(s, counts);
        for(int i=0; i<26; i++)
        {
            if(counts[i] > 0)
                System.out.println("counts[" + i + "] = " + counts[i]);
        }
        int max = maxIndex(counts);
        System.out.println("Max index = " + max);
    }
    
    public int maxIndex(int[] values){
        int max = 0;
        int maxIndex = -1;
        for(int i=0; i<values.length; i++)
        {
            if(values[i] > max)
            {
                max = values[i];
                maxIndex = i; 
            }
        }
        return maxIndex;
    }
    
    public String halfOfString(String message, int start){
        String m = null;
        if(start == 0)
        {
            for(int i=0; i<message.length(); i+=2)
            {
                m = m + message.substring(i, i+1);
            }
        }
        else if(start == 1)
        {
            for(int i=1; i<message.length(); i+=2)
            {
                m = m + message.substring(i, i+1);
            }
        }
        return m;
    }
    
    public int getKey(String s){
        int[] counts = new int[26]; 
        countLetters(s, counts);
        int max = maxIndex(counts);
        int key = -1;
        if(max > 4)
            key = max - 4;
        else if(max <= 4)
            key = 26 - (4 - max);
        return key;    
    }
}