import java.util.*;
import java.lang.*;

/** Etude 11 I before E
    Ryan Spear, Kent Loh, Logan Richard, Angus McMillan
    COSC 326 Summer School 2018
**/
public class TryIce{

    static ArrayList<Rule> RuleList = new ArrayList<Rule>();
    static String alphabet;
    static TreeMap<String, Long> oldWordList = new TreeMap<String, Long>();
    static TreeMap<String, Long> newWordList = new TreeMap<String, Long>();


    public static void main(String[] args){

        ArrayList<Long> searchSize = new ArrayList<Long>();
        ArrayList<String> words = new ArrayList<String>();
        ArrayList<String> rules = new ArrayList<String>();
        Scanner scanner = new Scanner(System.in);
        alphabet = scanner.nextLine();

        /** adds all the rules to an arraylist **/
        while(scanner.hasNextLine()){ 
            String rule = scanner.nextLine();
            if(rule.isEmpty()){
                break;
            }
            rules.add(rule);
        }

        /** scans in words to validate and length of strings **/
        while(scanner.hasNextLine()){
            String input = scanner.nextLine(); 
            words.add(input);
        }

        /** removes string lengths and adds them to search size **/
        ArrayList<String> removal = new ArrayList<String>();
        for(String s: words){
            if(isNumeric(s)){
                removal.add(s);
                searchSize.add(Long.parseLong(s));
            }
        }
        words.removeAll(removal);

        /** adds rules to rule object along with each rules' exception **/
        for(String s: rules){           
            Scanner stringScan = new Scanner(s);
            Rule rule = new Rule(stringScan.next());
            while(stringScan.hasNext()){
                rule.addException(stringScan.next());
            }
            RuleList.add(rule);
        }
        for(String hehe: words){
            System.out.println(validator(hehe));
        }
        


        char[] letters = alphabet.toCharArray();

        for(Long i: searchSize){
            if(i < 6L){
                printAllKLength(oldWordList, letters, i);
                Long total = 0L;
                for(Map.Entry<String,Long> entry : oldWordList.entrySet()) {
                    Long value = entry.getValue();
                    total += value;
                }
                System.out.println(total);
                oldWordList.clear();
                newWordList.clear();
            } else {
                printAllKLength(oldWordList, letters, 5L);  // 5 needs to be the max, with 2->4 being possible.
                newWordList = (TreeMap) oldWordList.clone();
                for(String key: newWordList.keySet()){
                    newWordList.put(key, 0L);
                }


                findStringNumber(5L, i);
                oldWordList.clear();
                newWordList.clear();
            }
        }
    }

    /** finds number of possible strings at a certain length
        @param stringSize will be 5 all the time
        @param MaxSize is the limit we want to know
    **/
    public static void findStringNumber(Long stringSize, Long maxSize){
        while(stringSize < maxSize){
            for(Map.Entry<String,Long> entry : oldWordList.entrySet()) {
                String key = entry.getKey();
                Long value = entry.getValue();

                if(value == 0){
                    continue;
                }

                addLetters(key, value);
            }
            // update the treemaps, old becomes the new.
            oldWordList = (TreeMap) newWordList.clone();
            for(String key: newWordList.keySet()){
                newWordList.put(key, 0L);
            }
            stringSize++;
        }
        Long total = 0L;
        //System.out.println(oldWordList);
        for(Map.Entry<String,Long> entry : oldWordList.entrySet()) {
            String key = entry.getKey();
            Long value = entry.getValue();
            if(value != 0L){
                total +=  value;
                //System.out.println("Value: " + value);
                //System.out.println("Total: " + total);
            }
        }

        System.out.println(total);
    }


    /** ~~~~~~~~~~~~~~~~
        updates the value of each string in accordance to the values that came before it
        @param key is the string eg baaba
        @param value is the current amount of valid strings ending in that key
        ~~~~~~~~~~~~~~~~
    **/
    public static void addLetters(String key, Long value){
        ArrayList<String> validWords = new ArrayList<String>();
        //System.out.println("word: " + w.getSuffix());
        char[] letters = alphabet.toCharArray();
        for(int i = 0; i < letters.length; i++){
            char letter = letters[i];
            String phrase = key + letter; // the new string of length 6.
            // System.out.println("Phrase: " + phrase);
            
            boolean broken = false;
            ruleLoop:
            for(Rule r: RuleList){
                String rule = r.getRule();
                String phraseCopy = phrase.substring(phrase.length() - rule.length());
                //System.out.println("phraseCopy: " + phraseCopy);
                if(!phraseCopy.equals(rule)){ // if the new word doesn't break this particular rule.
                    continue;
                } else {
                    //System.out.println("ends by breaking the rule: " + rule);
                    ArrayList<String> exceptions = r.getExceptions();
                    if(exceptions == null){
                        broken = true;
                        continue;
                    }

                    for(String e: exceptions){
                        String ex = phrase.substring(0, phrase.length()-rule.length());
                        ex = ex.substring(ex.length() - e.length());
                        if(ex.equals(e)){
                            //count++;
                            continue ruleLoop;
                        } else {
                            broken = true;
                            continue;
                        }
                    }
                }
            }
            if(broken == false){
                String newPhrase = phrase.substring(1);
                newWordList.put(newPhrase, newWordList.get(newPhrase) + value);

            }
        }
    }
    
    
                    

    /** validates the word in accordance to the rules
        @param the word to be validated
        error in case :
        cabiii

        where
        ab c
        iii b
        SOLVE IT
    **/

    public static String validator(String word){
        // System.out.println(word);
        String wordCopy = word;
        /** check all the characters are in the alphabet **/
        for(int i = 0; i < word.length(); i++){
            if(alphabet.indexOf(word.charAt(i)) != -1){
                continue;
            } else {
                return "Invalid";
            }
        }
        for(Rule r: RuleList){
            wordCopy = word;
            int instances = countMatches(word, r.getRule());
            //System.out.println("Instances: " + instances);
            whileloop:
            while(instances != 0){
                // System.out.println("wordCopy: " + wordCopy);
                int location = wordCopy.indexOf(r.getRule()); // find first index of the rule.
                //System.out.println("location of rule breaker: " + location);
                ArrayList<String> exceptions = r.getExceptions();
                if(exceptions == null){
                    return "Invalid";
                }
                for(String s: exceptions){
                    if(location-s.length() < 0){
                        continue;
                    }
                    String possibleException = wordCopy.substring(location-s.length(), location);
                    //System.out.println("possibleException: " + possibleException);
                    if(possibleException.equals(s)){                        
                        wordCopy = wordCopy.substring(location+r.getRule().length()-1);
                        instances--;
                        continue whileloop; // this needs to break twice? and go back to the while loop.
                    }
                    
                }
                //System.out.println("now im here");
                if(instances != 0){
                    return "Invalid";
                }
            }
        }

        return "Valid";
    }

    /** counts how many times the substring is present in the string **/
    public static int countMatches(String str, String sub) {
        if (str.isEmpty() || sub.isEmpty()) {
            return 0;
        }
        int count = 0;
        int idx = 0;
        while ((idx = str.indexOf(sub, idx)) != -1) {
            count++;
            idx += sub.length()-1;
        }
        return count;
    }
    
    /** returns true if the string is a number **/     
    public static boolean isNumeric(String str){
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }


       
    // The method that prints all possible strings of length k.  It is
    //  mainly a wrapper over recursive function printAllKLengthRec()
    static void printAllKLength(TreeMap<String, Long> wordList, char set[], Long k) {
        int n = set.length;
        printAllKLengthRec(wordList, set, "", n, k);
    }
 
    // The main recursive method to print all possible strings of length k
    static void printAllKLengthRec(TreeMap<String, Long> wordList, char set[], String prefix, int n, Long k){
        // Base case: k is 0, print prefix
        if (k == 0L) {
            //System.out.println(prefix);
            if(validator(prefix).equals("Invalid")){ 
                // validate the word, if valid, put 1 in the constructor, else send 0.
                wordList.put(prefix, 0L);
            } else {
                wordList.put(prefix, 1L);
            }

        
            //System.out.println(prefix);
            return;
        }
 
        // One by one add all characters from set and recursively 
        // call for k equals to k-1
        for (int i = 0; i < n; ++i) {
             
            // Next character of input added
            String newPrefix = prefix + set[i]; 
             
            // k is decreased, because we have added a new character
            printAllKLengthRec(wordList, set, newPrefix, n, k - 1L); 
        }
    }

    
}
