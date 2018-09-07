import java.util.*;


public class Writing{

    /** creates a dictionary from the input as a treeSet.
        Using a treeSet because it has the quickest search and
        we will be searching a lot to find joining words
    **/
    public static void main(String [] args){
        String word1 = "extra";
        String word2 = "portray"; // transport would be a middle word
        TreeSet<String> dictionary = new TreeSet<String>();
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            String[] words = line.split("\\s+");
            for(int i = 0; i < words.length; i++){
                dictionary.add(words[i]);
            }
        }
        
        System.out.println(dictionary);

        // search through dictionary for a word starting with
        // word1's last three letters. if that fails go to last 2 letters.
        // if case 1 is successful, check word2's first 2 letters.

        // for the second case, work forward from word1 and backward from
        // word 2 simultaneously to try find a MIDDLE word.
        // there will be many possible starting points so we should
        // check them all.
    }
}
