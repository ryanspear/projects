package arithmetic;
import java.util.*;

public class order2{
    
    public static boolean found = false;
    
    public static void search(int[] numbers, int target){
        ArrayList<Integer> formula = new ArrayList<Integer>();
        String printer = "";
        findIt(numbers, target, 0, formula, -1, printer);

        
    }
    /** returns the variable to tell us whether we have found the answer yet or not **/
    public static boolean getFound(){
        return found;
    }
    /** change the found variable to found or not **/
    public static void setFound(boolean a){
        found = a;
    }
    
    /** recursively finds if there is an answer or not **/
    public static void findIt(int[] numbers, int target, int value, ArrayList<Integer> formula, int i, String printer){
        if(getFound()){
            return;
        }
        if(value > target){
            return;
        }
        if(value == target){
            if(i == numbers.length-1){
                printer = printer.substring(3);
                System.out.println("N " + printer);
                setFound(true);
            }
        }
        if(i == numbers.length-1){
            return;
        }
        i++;
        if(i != 0){ // if its the first number we have to add it to 0 instead of multiplying by 0
        findIt(numbers, target, valueMultiplier(formula, numbers, value, i), multiplyFormula(formula, numbers, value, i), i, printer + " * " + numbers[i]);
        }
        
        findIt(numbers, target, valueAdder(formula, numbers, value, i), additionFormula(formula, numbers, i), i, printer + " + " + numbers[i]); // addition
        
        return;
    }
    
    /** deals with the fact that order of operations isn't just multiplying the last value **/
    public static int valueMultiplier(ArrayList<Integer> formula, int[] numbers, int value, int i){
        value -= formula.get(formula.size()-1);
        int temp = formula.get(formula.size()-1) * numbers[i];
        value += temp;
        return value;
    }
    
    public static ArrayList<Integer> multiplyFormula(ArrayList<Integer> formula, int[] numbers, int value, int i){
        value -= formula.get(formula.size()-1);
        int temp = formula.get(formula.size()-1) * numbers[i];
        formula.set(formula.size()-1, temp);
        value += temp;
        return formula;
    
    }
    
    public static ArrayList<Integer> additionFormula(ArrayList<Integer> formula, int[] numbers, int i){
        formula.add(numbers[i]);
        return formula;
    }
    
    public static int valueAdder(ArrayList<Integer> formula, int[] numbers, int value, int i){
        value += numbers[i];
        return value;
    }
  
}
