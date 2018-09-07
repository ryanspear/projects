package numbers;
import java.util.*;
import java.lang.*;

/** public class contains the main method, reads numbers from
    stdin and calls the codeFinder method to begin formatting

    @author Ryan Spear  ID: 1801851
*/

public class Telephone{

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            String input = scanner.nextLine();
            if(input.isEmpty()){
                System.out.println(" INV");
            } else {
                Code.codeFinder(input);
            }
        }
    }
  
  
}
