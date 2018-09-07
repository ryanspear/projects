package arithmetic;

import java.util.*;
import java.lang.*;
import java.math.*;

public class LtoR{

    private static boolean check = false;


    public static void main(String [] args){
        
    }
    public static void changeCheck(boolean change){
        check = change;
    }

    public static boolean getCheck(){
        return check;
    }
        
    
    public static int maxValue(ArrayList<Integer> values){
        int i = 1;
        int product = values.get(0);
        System.out.println(values.size());
        while(i < values.size()){
            if(values.get(i) == 1){
                product += values.get(i);
            } else {
                product *= values.get(i);
                //System.out.println("Product: " + product);
                i++;
            }
        }

        return product;
    }
  
    /* This method tries to find whether the target is able to be reached via left to right method
     * uses recursive calls using both addition and multiplication.
     * need to add an additional while statement or something to make sure return 0 doesn't get reached before
     * all possibilities are used or the target is reached
     * 
     */
  
    public static void recursiveProduct(int count, ArrayList<Integer> numbers, int target, int value, String printer, int limit){
        System.out.println("value: " + value + ", target: " + target + ", count: " + count);
        if(value == target){
            // System.out.println("Reached!: " + count);
            System.out.println(printer + " = " + target);
            changeCheck(true);
            return;
        } 
        if(count < numbers.size()){
            System.out.println("additive call is doing: " + value + " += " + numbers.get(count));
            value += numbers.get(count);
            if(count == 0){
                printer += numbers.get(count);
            } else {
                printer += " + " + numbers.get(count);
            }
            count++;
            System.out.println("Gives us: " + value);
            recursiveProduct(count, numbers, target, value, printer, limit);
        }

        
        System.out.println("Count before x loop: " + count);
        if(count < numbers.size()){
            System.out.println("multiple call is doing: " + value + " *= " + numbers.get(count));
            System.out.println("at count: " + count);
            value *= numbers.get(count);
            if(count == 0){
                printer += numbers.get(count);
            } else {
                printer += " * " + numbers.get(count);
            }
            count++;
            System.out.println("Gives us: " + value);
            recursiveProduct(count, numbers, target, value, printer, limit);
          
        }
    }
}

    
    

