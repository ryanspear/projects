import java.util.*;

public class wordSearch{

    private static String ogWord1;
    private static String ogWord2;
    public static void main(String [] args){


        
        /** read into dictionary, reverse dictionary, and two words to connect
        TreeSet<String> dict = new TreeSet<String>(); // the dictionary
        TreeSet<String> reverseDict = new TreeSet<String>(); // everyword in the dictionary reversed.
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            String word = scanner.next();
            dict.add(word);
            reverseDict.add(stringReverse(word));
        }
        String word1 = args[0];
        ogWord1 = args[0];
        TreeSet<String> words1 = new TreeSet<String>();
        words1.add(word1);
        


        String word2 = args[1];
        ogWord2 = args[2];
        TreeSet<String> words2 = new TreeSet<String>();
        words2.add(word2);

        System.out.println("Word1: " + word1 + ", word2: " + word2);
        int wordNo = 2;
        beginFinding(words1, words2, dict, reverseDict, wordNo);
        **/
    }


    
    /** call this method to create a string which is reversed.
        Use this to create a dictionary of reversed words.
        This makes searching for a 'common part' linking with the second word much faster
    **/
    public static String stringReverse(String word){
        return new StringBuilder(word).reverse().toString();
    }

    public static int half(String word){ 
        return (int) Math.ceil(word.length()/2.0);
    }
        


    
    /** finds a common part that begins with the last HALF of word1. if this returns an empty TreeSet then
        we cannot find a doubly linked common part. We must try find a common part that ends in
        the first half of the characters word2 contains.
    **/
    // suffix list needs to hold all words from full word to half
    // suffix list needs to hold ALL words in case of singly linked list
    public static TreeSet<String> getSuffixList(TreeSet<String> dictionary, String word1, int half){
        int i = 0;
        TreeSet<String> searchSet = new TreeSet<String>();
        while(i <= half){
        String suffix = word1.substring(i, word1.length());
        char increment = suffix.charAt(suffix.length()-1); 
        increment++;
        char[] suffix2char = suffix.toCharArray();
        // suffix but last character is increased by one. this is to search for the correct suffix.
        suffix2char[suffix.length()-1] = increment;
        String suffix2 = String.valueOf(suffix2char);
        // search for words beginning with the suffix.
        searchSet.addAll((TreeSet) dictionary.subSet(suffix, true, suffix2, false));
        i++;
        }
        
        System.out.println(searchSet);
        
        return searchSet;
    }

    /** takes the reverse dictionary and finds words ending the the same letters word2 starts with.
        Then returns the words as a TreeSet, the RIGHT way round!!
    **/
    public static TreeSet<String> getPrefixList(TreeSet<String> reverseDict, String word2, int half){
        int i = word2.length();
        TreeSet<String> output = new TreeSet<String>();
        while(i >= half){
        String prefix = stringReverse(word2.substring(0, i)); // the prefix reversed to search reverseDict easily.
        System.out.println("Prefix: " + prefix);
        char increment = prefix.charAt(prefix.length()-1);
        increment++;
        char[] prefix2char = prefix.toCharArray();
        // suffix but last character is increased by one. this is to search for the correct suffix.
        prefix2char[prefix.length()-1] = increment;
        String prefix2 = String.valueOf(prefix2char);
        //System.out.println(prefix2);

        // try catch if subset doesnt exist
        TreeSet<String> searchSet = (TreeSet) reverseDict.subSet(prefix, true, prefix2, false);
        TreeSet<String> rightWayRound = new TreeSet<String>();
        Iterator iterator = searchSet.iterator();
        while(iterator.hasNext()){
            rightWayRound.add(stringReverse((String) iterator.next()));

        }
        output.addAll(rightWayRound);
        i--;
        }
        return output;
    }

    /** using two lists of Strings, can we find a word that links any one word from firstWords to any word
        in secondWords
        this doesn't work how I thought it should.
    **/
    public static String linkTreeSets(TreeSet<String> firstWords, TreeSet<String> secondWords){
        TreeSet<String> linkingWord = new TreeSet<String>();
        Iterator iterator =  secondWords.iterator();
        while(iterator.hasNext()){
            String word = (String) iterator.next();
            int half = half(word);
            int i = word.length();
            while(i > 0){
                linkingWord = (getSuffixList(firstWords, word, i));
                System.out.println("Trying to link " + word + " to " + firstWords);
                if(!linkingWord.isEmpty()){
                    Iterator linkIterator = linkingWord.iterator();
                    //System.out.println(linkIterator.next());
                    while(linkIterator.hasNext()){
                        String next = (String) linkIterator.next();
                        if(i < half && i < half(next)){
                            System.out.println("connector is less than half of BOTH");
                            System.out.println("continue");
                            continue;
                        } else {
                            System.out.println("Linking word: " + next);
                            System.out.println("Woohoo");
                           
                            return word + " " + next;
                        }
                    }
                }
                i--;
            }
        }

        return "0";
                            
    }
    /** tests whether there is a common word in the two sets **/
    public static String findCommonWord(TreeSet<String> suffixSet, TreeSet<String> prefixSet){
        Iterator iterator = suffixSet.iterator();
        while(iterator.hasNext()){
            String word = (String) iterator.next();
            if(prefixSet.contains(word)){
                System.out.println("SuffixSet: " + suffixSet + ", prefixSet: " + prefixSet);
                return word;
            }
        }
        return "0";
    }


    /** handles doubly linked pretty well, not sure if it will infinitely loop if it keeps finding repeating words? **/
    public static void beginFinding(TreeSet<String> word1, TreeSet<String> word2, TreeSet<String> dictionary, TreeSet<String> reverseDict, int wordNo){
        System.out.println("beginFinding entered");
        System.out.println("Word1: " + word1 + ", word2: " + word2);
        if(word1.isEmpty() || word2.isEmpty()){
            return;
        }
        int i = 0;
        Iterator iterator = word1.iterator();
        while(iterator.hasNext()){
            String word = (String) iterator.next();
            int half = half(word);
            TreeSet<String> outcome = getSuffixList(word2, word, half); // sees if word1 and word2 can link together
            if(!outcome.isEmpty()){
                String linker = outcome.pollFirst();
                System.out.println("Word1: " + word1 + ", word2: " + word2);
                System.out.println("hehe");
                System.out.println("Word: " + word + ", outcome: " + outcome); // outcome is right side word. 
                System.out.println("No of words: " + wordNo);
                // call a method that takes the starting two words, sets 1 and 2 and links them. We know it can link we just need
                // to know the number of words and print them correctly. 
                return;
                // needs to print the number of words and all of the words
            }        
        } // this finds whether one word can be linked to another using TreeSets.
        TreeSet<String> possibleSuffix = new TreeSet<String>();
        TreeSet<String> possiblePrefix = new TreeSet<String>();
        Iterator word1loop = word1.iterator();
        Iterator word2loop = word2.iterator();


        
        System.out.println("Possible suffix loop");
        while(word1loop.hasNext()){
            String w1 = (String) word1loop.next(); // word1 already contains getSuffix list, if it contains, remove word.
            if(getSuffixList(dictionary, w1, half(w1)).isEmpty()){
                System.out.println("Removing w1!");
                System.out.println("Word1 before: " + word1); // dont think this works
                word1.remove(w1);
                System.out.println("Word2 after: " + word1);
            } else {
                possibleSuffix.addAll(getSuffixList(dictionary, w1, half(w1))); // possibleSuffix contains all words that link with word1.
            }
        }
            while(word2loop.hasNext()){
                String w2 = (String) word2loop.next();
                if(getPrefixList(reverseDict, w2, half(w2)).isEmpty()){
                    word2.remove(w2);
                } else {
                    possiblePrefix.addAll(getPrefixList(reverseDict, w2, half(w2))); // possiblePrefix contains all words that link with word2.
                }
                String link = findCommonWord(possibleSuffix, possiblePrefix); // if there are common words in the sets we use that word to link 1 & 2.
                if(!link.equals("0")){
                    System.out.println("Found word to link them!: " + w1 + " " + link + " " + w2);
                    return;
                }
            }
        }
        wordNo += 2;
        beginFinding(possibleSuffix, possiblePrefix, dictionary, reverseDict, wordNo);
        // if we reach this point we have tried all to link a 3 pattern.
        // now make word1 and word2 two the new lists of suffix's and prefix's
        
        // 1) do the words link up? use word1 as dictionary and word2 to be paired
        // 2) find words from word1 suffix,
        // 3) find words from word2 prefix, are there any words in common? this means a single word can link word1 and 2
        // 4) the words in the lists now become the new word1 and word2 and we start at 1 again.
        // 5) double for loop to check if word1 and 2 link before moving on to step 2 again.
        // ???) maybe put the original word1 and word2 into seperate TreeSets to streamline the recursion?
    
        
    }



    public static void contains(TreeSet<String> suffixes, String word){
        


     }

}
