package joinedUp;
import java.util.*;

/** @authors Ryan Spear 1801851 and Kent Loh 5558196
    COSC326 Summer School 2018
**/

public class findLinks{

    /** interperse persecute cutenessiscool all link up.
        persecute shouldn't link with cutenessiscool because its not even singly linked?
    **/
    private static boolean singleFound = false;
    private static boolean doubleFound = false;

    public static void main(String [] args){
        
        ArrayDeque<Node<String>> theQ = new ArrayDeque<Node<String>>();
        TreeSet<String> seen = new TreeSet<String>();
    
        TreeSet<String> dict = new TreeSet<String>(); // the dictionary
        TreeSet<String> reverseDict = new TreeSet<String>(); // everyword in the dictionary reversed.
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            String word = scanner.next();
            dict.add(word);
            reverseDict.add(stringReverse(word));
        }
        String word1 = args[0];
        TreeSet<String> words1 = new TreeSet<String>();
        words1.add(word1);
        Node<String> begin = new Node<String>(word1);
        theQ.add(begin);
    
        
        String word2 = args[1];
        TreeSet<String> words2 = new TreeSet<String>();
        words2.add(word2);
        dict.add(word2);

        startSearching(theQ, word2, dict, seen, false, 0); // singly linked
        if(!singleFound)
            System.out.println("0");
            
        theQ.add(begin); // add starting word again
        seen.clear(); // clear seen words
        startSearching(theQ, word2, dict, seen, true, 0); // doubly linked
        if(!doubleFound)
            System.out.println("0");
            

        
 
    }
    /** increases a string's value by one.
        eg: nice becomes nicf
    **/
    public static String increaseByOne(String word){
        char increment = word.charAt(word.length()-1);
        increment++;
        char[] suffix2char = word.toCharArray();
        suffix2char[word.length()-1] = increment;
        String suffix2 = String.valueOf(suffix2char);
        return suffix2;
    }  
  
    /** reverses the String given **/
    public static String stringReverse(String word){
        return new StringBuilder(word).reverse().toString();
    }
  
    /** returns an int half (ROUNDED DOWN) the size of the String **/
    public static int floorHalf(String word){ 
        return (int) Math.floor(word.length()/2.0);
    }
    /** returns an int half (ROUNDED UP) the size of the String **/
    public static int ceilHalf(String word){
        return (int) Math.ceil(word.length()/2.0);
    }
    /** this there is a shared word in the two treeSets, this method will return it **/
    public static String findCommonWord(TreeSet<String> suffixSet, TreeSet<String> prefixSet){
        for(String s: suffixSet){
            if(prefixSet.contains(s)){
                return s;
            }
        } return null;
    }


    /** method prints the chain by looking up through parents **/
    public static void printChain(Node<String> word, String printer, int count, boolean doubly){
        if(doubly){
            doubleFound = true;
            
        } else {
            singleFound = true;
        }
        count++;
        printer = word.getData() + " " + printer;
        if(word.getParent() != null){
            printChain(word.getParent(), printer, count, doubly);
        } else {
            System.out.println(count + " " + printer);
        }
    }

    /** makes sure the linking piece is at least half as long as the word **/
    public static boolean isDoublyLinked(String s, String suffix){
        if(suffix.length() >= ceilHalf(s)){
            return true;
        }
        return false;
    }
  
  
    /** finds all words that start with the same letters word ends with
        @return the words that link with word
        need to make this the doubly linked case.
    **/
    public static TreeSet<String> getDoublySuffixList(TreeSet<String> dictionary, String word){
        int i = 0; 
        TreeSet<String> searchSet = new TreeSet<String>();
        while(i <= floorHalf(word)){
            String suffix = word.substring(i, word.length()); //reducing the word size from the front
            String suffix2 = increaseByOne(suffix);
            TreeSet<String> possibleLinks = new TreeSet<String>();
            possibleLinks = (TreeSet)  dictionary.subSet(suffix, true, suffix2, false);
            
            for(String s: possibleLinks){
                if(s.equals(word)){
                    continue;
                }
                if(isDoublyLinked(s, suffix)){
                    searchSet.add(s);
                }
            }
            i++;
        }
        return searchSet;
    }

    public static TreeSet<String> getSinglySuffixList(TreeSet<String> dictionary, String word){
        int i = 0;
        //System.out.println(word);
        TreeSet<String> searchSet = new TreeSet<String>();
        while(i < word.length()){ // go through the entirety of the word.
            String suffix = word.substring(i, word.length());
            //System.out.println("word: " + word + " , suffix: " + suffix);
            String suffix2 = increaseByOne(suffix);
            TreeSet<String> possibleLinks = new TreeSet<String>();
            possibleLinks = (TreeSet)  dictionary.subSet(suffix, true, suffix2, false);
            if(possibleLinks.isEmpty()){
                i++;
                continue;
            } else {
                if(i > floorHalf(word)){ // suffix must be at least half the size of words in possibleLinks now.
                    for(String s: possibleLinks){
                       
                        if(suffix.length() >= ceilHalf(s)){
                            //System.out.println(s + " was added");
                            searchSet.add(s);
                        }
                    }
                } else {
                    searchSet.addAll(possibleLinks);
                }
                i++;
            }
        }

        return searchSet;
    }

    
    /** this is doubly linked atm. Create a get suffixList for singly linked **/
    public static void startSearching(ArrayDeque<Node<String>> queue, String target, TreeSet<String> dictionary, TreeSet<String> seen, boolean doubly, int count){
        if(count > 3000){
            return;
        }
                             
        count++;
        while(!queue.isEmpty()){
            Node<String> deQ = queue.poll();
            if(seen.contains(deQ.getData())){ // if we have already seen this word.
                continue;
            }

            seen.add(deQ.getData());
            if(deQ.getData().equals(target)){ // if this node is the target, we win..
                String printer = "";
                printChain(deQ, printer, 0, doubly);
                queue.clear();
                return;
            } else {
                TreeSet<String> possibleSuffix = new TreeSet<String>();
                if(doubly){
                    possibleSuffix = getDoublySuffixList(dictionary, deQ.getData()); // doubly linked only
                } else {
                    possibleSuffix = getSinglySuffixList(dictionary, deQ.getData()); // singly linked.
                }
                for(String s: possibleSuffix){
                    Node<String> newNode = new Node<String>(s);
                    deQ.addChild(newNode);
                    queue.add(newNode);
                }
                
            }
    
        }
        return;
    }
}
