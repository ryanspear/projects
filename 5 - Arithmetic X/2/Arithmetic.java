package arithmetic;
/** program reads in integers and a target and tells you whether it's possible to make that number
 @author Ryan Spear
 COSC326 2018 Summer School
 **/
import java.util.*;
import java.lang.*;

/** public class Arithmetic deals with the input and sends them to
 orderOfOperations to be dealt with according to the eqation type
 **/
public class Arithmetic{
    
    /** main method reads in values and order type and sends them to the appropriate methods
     **/
    public static void main(String [] args){
        
        ArrayList<Integer> values = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextLine()){
            String numbers = scanner.nextLine();
            Scanner stringRead = new Scanner(numbers);
            values.clear();
            while(stringRead.hasNext()){
                values.add(stringRead.nextInt());
            }
            
            String[] line2 = scanner.nextLine().split(" ");
            int target = Integer.parseInt(line2[0]);
            String type = line2[1];
            
            int[] a = new int[values.size()];
            for(int i = 0; i < a.length; i++){
                a[i] = values.get(i).intValue();
            }
            if(type.equals("N")){
                order2.search(a, target);
                if(!order2.getFound()){
                    System.out.println("N impossible");
                } else {
                    order2.setFound(false);
                }
            } else {
                LtoR.search(a, target);
                if(!LtoR.getFound()){
                    System.out.println("L impossible");
                } else {
                    LtoR.setFound(false);
                    
                }
            }
        }
    }
}

    



