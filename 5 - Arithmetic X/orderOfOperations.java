package arithmetic;
/** @author Ryan Spear ID: 1801851
    COSC 326 2018 Summer School
**/
import java.util.*;
import java.lang.*;

public class orderOfOperations{
    /** private data field is used to check whether the equation is possible **/
    private static boolean check = false;

    /** sets whether the equation is possible
        @param e is true for possible, false for not
    **/
    public static void setCheck(boolean e){
        check = e;
    }

    /** gets the check variable
        @return check the variable to tell whether the equation is possible
    **/
    public static boolean getCheck(){
        return check;
    }
    /** the only method to be called out of the orderOfOperations class.
        This method sets up a string with #'s to show where the operands will go.
        creates an array that holds the index of these positions.
        creates another array iterate that holds all the possible combinations
        of how the multiplication and addition could appear in an equation.
        @param values is an ArrayList of the values scanned in
        @param target is the number we are aiming to hit
        @param type is either N or L to choose how we compute the target
    **/
    public static void order(ArrayList<Integer> values, int target, char type){
        String output = "";
        for(int i = 0; i < values.size(); i++){
            output += values.get(i) + " # ";
        }
       
        int operators = values.size()-1;
        int[] replacers = new int[operators]; // positions of the # to be replaced.
        output = output.substring(0, output.length() - 3);
        int index = output.indexOf('#');
        int p = 0;
        while (index >= 0) {
            replacers[p] = index;
            p++;
            index = output.indexOf('#', index + 1);
        }
        
        String possible;
        for (int i=0; i<Math.pow(2,operators); i++) {
            possible = Integer.toBinaryString(i);
            int difference = operators - possible.length();
            while(difference != 0){
                possible = "0"+possible;
                difference = operators - possible.length();
            }
            int[] iterate = new int[possible.length()];
            for(int j = 0; j < possible.length(); j++){
                iterate[j] = possible.charAt(j) - '0';
            }
            String sendIt = replacer(output, replacers, iterate);
            if(bedmas(sendIt, target, type)){
                break;
            }
        }
    }


    /** method replaces #'s with all possible combinations of multiplication and
        addition
        @param output is the string containing #'s instead of operands
        @param replacers[] is an array holding the indexes of the #'s
        @param iterate holds the possible combinations of positions
        @return the string that has been created by replacing the #'s
    **/
    public static String replacer(String output, int[] replacers, int[] iterate){
        StringBuilder input = new StringBuilder(output);

        
        for(int i = 0; i < iterate.length; i++){
            if(iterate[i] == 0){
                input.setCharAt(replacers[i], '+');
            } else {
                input.setCharAt(replacers[i], '*');
            }
        }
        return input.toString();
    }
        
        

    /** method creates an ArrayList holding the operands in their order
        also creates an ArrayList holding all the numbers in their order
        then using the type sends them to a respective equation solver
        @param E is the string of the equation to be solved
        @param target is the number we are trying to hit
        @param type is the type of solving we are going to do (LtR or bedmas)
    **/

    public static boolean bedmas(String E, int target, char type){
        String output = "";
        Scanner stringScan = new Scanner(E);
        ArrayList<Integer> numbers = new ArrayList<>();
        ArrayList<Character> operator = new ArrayList<>();
        for(int i = 0; i < E.length(); i++){
            if(E.charAt(i) == '*' || E.charAt(i) == '+'){
                operator.add(E.charAt(i));
            }
        }
        String onlyNo = E.replaceAll("[^0-9]", " ");
        Scanner numberScan = new Scanner(onlyNo);
        while(numberScan.hasNextInt()){
            
            int d = numberScan.nextInt();
            numbers.add(d);
        }

        
        if(type == 'N'){
            if(equation(numbers, operator) == target){
                for(int i = 0; i < operator.size(); i++){
                    output += numbers.get(i) + " " + operator.get(i) + " ";
                }
                setCheck(true);
                output += numbers.get(numbers.size() -1);
                System.out.println("N " + E);
                return true;
            }
        } else {
            if(LtoRequation(numbers, operator) == target){
                for(int i = 0; i < operator.size(); i++){
                    output += numbers.get(i) + " " + operator.get(i) + " ";
                }
                setCheck(true);
                output += numbers.get(numbers.size() -1);
                System.out.println("L " + E);
                return true;
            }
        }

        return false;
    }


    /** method deals with a proper order of operations type N
        works out all the multiplications first then moves to addition
        @param number is the arrayList of numbers in order
        @param operator is the arrayList of operators in order
        @return the final answer of this combination
    **/
    public static int equation(ArrayList<Integer> number, ArrayList<Character> operator){
        int temp;
        
        for(int i = 0; i < operator.size(); i++){
            if(operator.get(i) == '*'){

                temp = number.get(i) * number.get(i+1);
                number.set(i, temp);
                number.remove(i+1);
                operator.remove(i);
                i--;
            }
        }
        for(int i = 0; i < operator.size(); i++){
            temp = number.get(i) + number.get(i+1);
            number.set(i, temp);
            number.remove(i+1);
            operator.remove(i);
            i--;
        }


        return number.get(0);
                

    }

    /** method computes using leftToRight method
        works out the equation in order
        @param number is the arrayList of numbers in order
        @param operator is the arrayList of operators in order
        @return the final answer to this combination
    **/
    public static int LtoRequation(ArrayList<Integer> number, ArrayList<Character> operator){
        int temp;
        for(int i = 0; i < operator.size(); i++){
            if(operator.get(i) == '*'){  
                temp = number.get(i) * number.get(i+1);
                number.set(i, temp);
                number.remove(i+1);
                operator.remove(i);
                i--;
            } else {
                temp = number.get(i) + number.get(i+1);
                number.set(i, temp);
                number.remove(i+1);
                operator.remove(i);
                i--;
            }
        }

        return number.get(0);

    }



    public static int divTarget(ArrayList<Integer> numbers, int target, int i){
        //first call to this method, i must be 1.
        target = target/(numbers.get(numbers.size()-i));
        return target;
    }

    public static int subTarget(ArrayList<Integer> numbers, int target, int i){
        target = target - (numbers.get(numbers.size()-i));
        return target;
    }


    public static void LtoR(ArrayList<Integer> numbers, int target, int i){
        
    }
}
