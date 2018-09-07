package numbers;
import java.util.*;
import java.lang.*;

/** public class deals with turning letters into numbers
    and also the methods for keeping track of duplicate
    numbers using a TreeSet

    @author Ryan Spear  ID: 1801851
*/

public class Letters{
  
    /** Takes a string of letters and turns them
     *   into a string of numbers corresponding
     * to each letters position on a phone keypad.
     * Using the char - 'A' to give a numerical value.
     * Also deals with the fact that the numberpad
     * has two pads with 4 digits rather than 3 using a set
     * @param letters is the string of letters to be changed to numbers
     * @return output which is the numbered version of the string
     **/
    public static String lettersToNumbers(String code, String letters){

        Set<Integer> nums = new HashSet<Integer>(); // to prevent key pad errors
        nums.add(18); nums.add(21); nums.add(24); nums.add(25);
        String numCheck = letters.replaceAll("[^0-9A-Z]", "");
        String output = "";
        String empty = "";
        int i = 0;
        int converted; 
        int limit = getLimit(code);
        if(numCheck.length() > 9){
            return empty;
        }

        if(numberCheck(code, numCheck)){
            return empty;
        }

       

        while(i < letters.length()){
            if(i == limit){
                return output;
            }
            char current = letters.charAt(i);
            if(!Character.isUpperCase(current)){ // if it's not a letter
                if(Character.isDigit(current)){
                output += current;              // plug it back in.
                i++;
                continue;
                } else {
                    return empty;
                }
            }
            
            converted = current - 'A';
            int dial = (converted/3) + 2;
            if(nums.contains(converted)){ 
                dial--;
            }
            char digit = (char) (dial + 48);
            String builder = Character.toString(digit);
            output += builder;
            i++;
        }
        return output;
    }

    /** constructor creates a new TreeSet to hold strings and
        test whether we have a duplicate
        @return the new TreeSet
    */
    public static TreeSet<String> BTree(){
        TreeSet<String> ts1 = new TreeSet<>();
        return ts1;
    }
    /** adds a new string to the TreeSet
     */
    public static void BTreeAdd(TreeSet ts, String number){
        ts.add(number);
    }
    /** searches the TreeSet to see if we have a duplicate number
        @return true for found false for not found
    */
    public static boolean BTreeSearch(TreeSet ts, String number){
        return ts.contains(number);
    }


    /** method finds out where we should cut off the string length
        @param code is the code of the number
        @return the cut off point of the number
    **/
    public static int getLimit(String code){
        
        if(code.equals("0508")){
            return 6;
        } else {
            if(code.equals("0800")){
                return 7;
            } else {
                if(code.equals("0900")){
                    return 5;
                }
            }
        }

        return 0;
        
    }
    /** checks to see if there are numbers past the point where there
        should be
        @param code is the code of the number
        @param numCheck is the number with only numbers and CAPS
        @return true if there are invalid numbers, false if not
    **/
    public static boolean numberCheck(String code, String numCheck){
        if(code.equals("0508") && numCheck.length() >= 7){
            for(int a = 6; a < numCheck.length(); a++){
                char check = numCheck.charAt(a);
                if(Character.isDigit(check)){
                    return true;
                }
            }
        } else {
            if(code.equals("0800") && numCheck.length() >= 8){
                for(int s = 7; s < numCheck.length(); s++){
                    char check = numCheck.charAt(s);
                    if(Character.isDigit(check)){
                        return true;
                    }
                }
            } else {        
                if(code.equals("0900") && numCheck.length() >= 6){
                    for(int f = 5; f < numCheck.length(); f++){
                        char check = numCheck.charAt(f);
                        if(Character.isDigit(check)){
                            return true;
                        }
                    }
                }
            }
        }

        return false;

    }
        
}



