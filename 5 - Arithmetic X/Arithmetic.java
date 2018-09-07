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
            String line2 = scanner.nextLine();
            stringRead = new Scanner(line2);
            int target = stringRead.nextInt();
            String stringType = stringRead.next();
            char type = stringType.charAt(0);
            String printer = "";

            
            int limit = (int) (Math.pow(2, values.size() -1)+2);
            orderOfOperations.order(values, target, type);
            if(!orderOfOperations.getCheck()){
                if(type == 'L'){
                System.out.println("L impossible");
                } else {
                    System.out.println("N impossible");
                }
            } else {
                orderOfOperations.setCheck(false);
            }
    
        }
    }
 
}
  

