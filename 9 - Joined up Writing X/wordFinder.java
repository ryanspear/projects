package words;

import java.util.*;

/**
   ~~~~~~~~~ TO DO LIST ~~~~~~~~~~
   make beginSearch word recursively by adding the left and right sets to the parentNode sets.
   make another findSuffix and findPrefix for only singly linked case.
   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
**/

public class wordFinder{
    
    private static String ogWord1;
    private static String ogWord2;
    private static Node<String> Tree1;
    private static Node<String> Tree2;

    
    public static void main(String [] args){


        
        HashSet<Node<String>> setLeft = new HashSet<Node<String>>();
        HashSet<Node<String>> setRight = new HashSet<Node<String>>();
        
        /**
           
        Node<String> node1 = new Node("apple");
        Node<String> node2 = new Node("banana");

        aSet.add(node1);
        aSet.add(node2);

        for(Node<String> s: aSet){
            if(s.getData().equals("apple")){
                // s is parent.
                System.out.println(s.getData());
            }
            
            }**/
                              

        
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
        ogWord2 = args[1];
        TreeSet<String> words2 = new TreeSet<String>();
        words2.add(word2);
        Tree1 = new Node<String>(word1);
        Tree2 = new Node<String>(word2);
        System.out.println("Word1: " + word1 + ", word2: " + word2);

        setLeft.add(Tree1); 
        setRight.add(Tree2);
        
        startSearching(words1, words2, dict, reverseDict, setLeft, setRight);
        

    }

    /** reverses the String given **/
    public static String stringReverse(String word){
        return new StringBuilder(word).reverse().toString();
    }

    /** returns an int half (rounded up) the size of the String **/
    public static int half(String word){ 
        return (int) Math.ceil(word.length()/2.0);
    }
    /** needs to find the last common word **/
    public static String findCommonWord(TreeSet<String> suffixSet, TreeSet<String> prefixSet){
        for(String s: suffixSet){
            if(prefixSet.contains(s)){
                return s;
            }
        } return null;
    }


    /** finds all words that start with the same letters word ends with
        @return the words that link with word
    **/
    public static TreeSet<String> getSuffixList(TreeSet<String> dictionary, String word){
        int i = 0;
        TreeSet<String> searchSet = new TreeSet<String>();
        while(i <= half(word)){
            String suffix = word.substring(i, word.length()); //reducing the word size from the front
            String suffix2 = increaseByOne(suffix);
            
            searchSet.addAll( (TreeSet) dictionary.subSet(suffix, true, suffix2, false));
            i++;
        }
        
        return searchSet;
    }

    public static TreeSet<String> getPrefixList(TreeSet<String> reverseDictionary, String word){
        int i = word.length();
        TreeSet<String> rightWayRound = new TreeSet<String>();
        while(i >= half(word)){
            String prefix = stringReverse(word.substring(0, i));
            String prefix2 = increaseByOne(prefix);
            
            TreeSet<String> searchSet = (TreeSet) reverseDictionary.subSet(prefix, true, prefix2, false);
            
            for(String s: searchSet){
                rightWayRound.add(stringReverse(s));
            }

            i--;
        }
        return rightWayRound;
    }

    public static Set<Node> getNodes(TreeSet<String> children, Node<String> parentNode){
        Set<Node> kids = new HashSet<Node>();
        for(String s: children){
            parentNode.addChild(s);
        }
        return kids;
    }

    public static void startSearching(TreeSet<String> word1, TreeSet<String> word2, TreeSet<String> dictionary, TreeSet<String> reverseDictionary, HashSet<Node<String>> parentSetLeft, HashSet<Node<String>> parentSetRight){

        TreeSet<String> temp = new TreeSet<String>();
        HashSet<Node<String>> leftSet = new HashSet<Node<String>>();
        HashSet<Node<String>> rightSet = new HashSet<Node<String>>();
        String print = "";
        
        if(word1.isEmpty() || word2.isEmpty()){
            return;
        }
        
    
        TreeSet<String> possibleSuffix = new TreeSet<String>();
        TreeSet<String> possiblePrefix = new TreeSet<String>();

        /** for every word in word1 list **/
        for(String s: word1){
            System.out.println("word1 s = " + s);
            Node<String> parent = new Node<String>("default");
           
            for(Node<String> n: parentSetLeft){ // find that word in the parentSetLeft.
                System.out.println("n: " + n.getData());
                
                if(n.getData().equals(s)){
                    parent = n;
                    break;
                }
            }
            System.out.println("just changed parent to n. getting parents data: " + parent.getData());
            temp = getSuffixList(dictionary, s); // treeSet of all words that links with word S
            for(String p: temp){
                if(p.equals(parent.getData())){
                    continue;
                }
                Node<String> nude = new Node<String>(p);
                System.out.println("adding to leftSet: " + nude.getData());
                parent.addChild(nude); // make them all children of parent
                leftSet.add(nude);
            }
            
            possibleSuffix.addAll(temp); // whole list of linked words is added.
            
        }
        
        for(String s: possibleSuffix){
            System.out.println("adding " + s + " to leftSet");
            leftSet.add(new Node<String>(s));
        }

        for(String s: word2){
            Node<String> parent2 = new Node<String>("");
            for(Node<String> n: parentSetRight){
                if(n.getData().equals(s)){
                    parent2 = n;
                    break;
                }
            }

            temp = getPrefixList(reverseDictionary, s);
            for(String p: temp){
                if(p.equals(parent2.getData())){
                    continue;
                }
                Node<String> nude = new Node<String>(p);
                parent2.addChild(nude);
                rightSet.add(nude);
            }

            possiblePrefix.addAll(temp);
        }

        /** if link != null, we can link the two words together **/ 
        String link = findCommonWord(possibleSuffix, possiblePrefix);
        if(link!= null){
            System.out.println("suffixLisT: " + possibleSuffix);
            System.out.println("prefixList: " + possiblePrefix);
            System.out.println("Common word: " + link);
            int count = 0;
            for(Node n: leftSet){ // working backward to the front
                if(n.getData().equals(link)){ // found the parent.
                    System.out.println("n data: " + n.getData());
                    System.out.println("n's parent data: " + n.getParent().getData());
                    while(n.getParent() != null){
                        count++;
                        print = n.getData() + " " + print;
                        n = n.getParent();
                    }
                    count++;
                    print = n.getData() + " " + print;
                    break;
                
                
                }
            }

            for(Node n: rightSet){
                
                if(n.getData().equals(link)){
                    while(n.getParent() != null){
                        count++;
                        n = n.getParent();
                        print += n.getData() + " ";
                    }

                    print = count + " " + print;
                    break;
                }
            }
            System.out.println(print);
            return;
        }

        System.out.println("about to call recursively giving leftset: ");
        for(Node n: leftSet){
            System.out.println(n.getData());
        }
        System.out.println("Possibleprefix: " + possiblePrefix);
        startSearching(possiblePrefix, possibleSuffix, dictionary, reverseDictionary, leftSet, rightSet);

                          
                    
            
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
}
