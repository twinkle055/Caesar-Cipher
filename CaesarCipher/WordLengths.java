
/**
 * Write a description of WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class WordLengths {
    
    public void countWordLengths(FileResource resource, int[] counts){
        for(String word: resource.words())
        {
            word = word.toLowerCase();
            int length = word.length();
            char chStart = word.charAt(0);
            char chEnd = word.charAt(length - 1);
            if(Character.isLetter(chStart) && Character.isLetter(chEnd))
                counts[length] += 1;
            else if(Character.isAlphabetic(chStart))
                counts[length - 1] += 1;
            else if((length - 2) > 0)
                counts[length - 2] += 1;
        }
    }
    
    public void testCountWordLengths(){
        FileResource fr = new FileResource();
        int[] counts = new int[31];
        countWordLengths(fr, counts);
        for(int i=0; i<31; i++)
        {
            if(counts[i] > 0)
            System.out.println("counts[" + i + "] = " + counts[i]);
        }
        int max = indexOfMax(counts);
        System.out.println("Index position of largest element = " + max);
    }
    
    public int indexOfMax(int[] values){
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
}
