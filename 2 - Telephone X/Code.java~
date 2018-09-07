package numbers;
import java.util.*;
import java.lang.*;


/** a public class that decides whether an input number is valid.
    Prints the number accordingly

    @author Ryan Spear  ID: 1801851
*/
    
public class Code{

    /** private TreeSet that all methods in this class can acess **/
    private static TreeSet<String> ts = Letters.BTree();


    public static void main(String [] args){
        codeFinder("0800 RUBBISH");
    }
    /** This method works out what part of the number is the code
     *  and what part is the unique number making use of other validating methods
     * @param input is the entire number scanned from the input
     */
    public static void codeFinder(String input){
    
        if(input.charAt(0) == '('){ //if parenthesis then this is code
      
            String[] parts = input.split("[\\(\\)]");
            String code = parts[1];
            String number = parts[2];
            if(!numberValidator(code, number)){
                System.out.println(input + " INV");
            }
                
        } else {
            /** if first 2 digits are 02 or 09 they can any kind of code
                So this decides which one
            */
            String code = input.substring(0, 2); // look at the first 2 digits
            String number = input.substring(2);
      
            if(code.equals("02") || code.equals("09")){ // if it is 09 or 02 we dont know what code it is yet
                if(!numberValidator(code, number)){ // if this is false it isn't an area code
                    if(code.equals("02")){ // if 02 must be a mobile code
                        code = input.substring(0, 3); // adjust code length
                        number = input.substring(3);
                        if(!numberValidator(code, number)){ // not a mobile number so it is invalid
                                System.out.println(input + " INV");
                        }
                    } else { // if 09 must be an initial code
                        code = input.substring(0,4); //adjust code length 
                        number = input.substring(4); 
                        if(!numberValidator(code, number)){
                            System.out.println(input + " INV");
                        }
                    }
                }
                
                /** if first two digits are NOT 02 or 09,
                    then we can tell what code they are easily
                */
            } else {
                if(code.equals("05") || code.equals("08")){ // must be an initial code
                    code = input.substring(0,4); //adjust code length
                    number = input.substring(4);
                    if(!numberValidator(code, number)){
                            System.out.println(input + " INV");
                    } 
                } else {
                    if(!numberValidator(code, number)){
                            System.out.println(input + " INV");
                    }
                }
            }
        }
    }

  
    /** uses codes and numbers length to select a which type of code it could potentially have
     * and makes a call to the corresponding method
     * @param code is the first 2, 3 or 4 digits of the number made from codeFinder method
     * @param number is the remaining digits of the number after the code
     * @returns true if the number is valid and false if not
     **/
    public static boolean numberValidator(String code, String number){
        if(number.charAt(0) == '-'){
            return false;
        }
        if(code.length() == 2){
            if(inputFormat(number) != null){
                number = inputFormat(number); // change here
                return AreaValidator(code, number);
            } else { return false; }
        } else {
            if(code.length() == 3){
                if(inputFormat(number) != null){
                    number = inputFormat(number);
                
                    return mobileValidator(code, number);
                } else { return false; }
            } else {
                if(code.length() == 4){
                    if(inputFormat(number) != null){ //is valid
                        number = inputFormat(number); // change here
                        return initialValidator(code, number);
                    } else {
                        return false;
                    }
                }else {
                    return false;
                }
            }
        }
    
    
    }

    /** prepares the string to be checked for input validation
        @param number is the number string to be tested
        @return true if it is valid and false if not
    */
    
    public static String inputFormat(String number){
        String output = null;
        if(number.charAt(0) == ' '){
            number = number.substring(1);
        }
        int count = number.length() - number.replaceAll("[^A-Z0-9] ", "").length(); 
        if(count > 1){
            return output;
        }
        

        return number;
    }
    
    
        


    
    /** assesses whether the number fufills the specifications of an Initial number
     *  if it does, makes the call to the method designed to print inital numbers
     * @param code is the potential code of the number
     * @param number is the remaining number without the code
     * @returns true if it is an Initial code and false if not
     **/
    public static boolean initialValidator(String code, String number){
        for(int i = 0; i < number.length(); i++){ // if number has capital letters in it
            char ch = number.charAt(i);           // turn them into numbers
            if(Character.isUpperCase(ch)){
                number = Letters.lettersToNumbers(code, number);
                if(number.isEmpty()){
                    return false;
                }
            }
        }
        String pattern0508 = "\\d{3}([\\s||\\-])?\\d{3}"; // 3 numbers then maybe a space or hyphen followed by 3 numbers.
        String pattern0800 = "\\d{3}([\\s||\\-])?\\d{3,4}";
        String pattern0900 = "\\d{5}";
        
        if(code.equals("0508") && number.matches(pattern0508)){
            initialPrint(code, number);
            return true;
        }
    
        if(code.equals("0800") && number.matches(pattern0800)){
            initialPrint(code, number);
            return true;
        }
            
    
        if(code.equals("0900") && number.matches(pattern0900)){
            initialPrint(code, number);
            return true;
        }
        return false;
    }
    
  
    /** asesses whether the number fufills the specifications of the area codes
     *   if it does, calls the method to print area numbers
     * @param code is the potential code of the number
     * @param number is the remaining number without the code
     * @returns true if it is an Area code and false if not
     **/
    public static boolean AreaValidator(String code, String number){
        String format = "\\d{3}([\\s||\\-])?\\d{4}"; // 3 numbers then maybe a space or hyphen then 4 more numbers
        
        Set<String> areas = new HashSet<String>();
        areas.add("03");
        areas.add("04");
        areas.add("06");
        areas.add("07");
        areas.add("09");
    
        if(code.equals("02") && number.matches(format)){
            String scottBase = number.substring(0, 3);
            if(scottBase.equals("409")){
                areaPrint(code, number);
                return true;
            }
        }
    
        if(areas.contains(code)){
            if(number.charAt(0) == '1' || number.charAt(0) == '0'){
                return false;
            }
            String unallowed = number.substring(0,3);
            if(unallowed.matches("911|999|900")){
                return false;
            }
            if(number.matches(format)){
            areaPrint(code, number);
            return true;
                }
        }
        return false;
    }
    
    /** assesses whether the code and number recieved are valid for a mobile
     *   if yes, calls the method for printing mobile numbers
     * @param code is the potential mobile code
     * @param number is the remaining digits of the number
     * @returns true if it is a valid mobile code and number and false if not
     **/
    public static boolean mobileValidator(String code, String number){
        String format021a = "\\d{3}([\\s||\\-])?\\d{3,4}"; // 3 digits, maybe a space or hyphen, 3 or 4 digits.
        String format021b = "\\d{4}([\\s||\\-])?\\d{4}";
        String format0227 = "\\d{3}([\\s||\\-])?\\d{4}";
        String format025  = "\\d{3}([\\s||\\-])?\\d{3}";
        
        if(code.equals("021")){
            if(number.matches(format021a) || number.matches(format021b)){
                mobilePrint(code, number);
                return true;
            }
        }
    
        if(code.equals("022") || code.equals("027")){
            if(number.matches(format0227)){
                mobilePrint(code, number);
                return true;
            }
        }
    
        if(code.equals("025") && number.matches(format025)){
            mobilePrint(code, number);
            return true;
        }
        return false;
    }
  
    /** prints the number formatted correctly if it has a mobile code
     * @param code is the mobile code
     * @param number is the string of digits following the code
     **/
    public static void mobilePrint(String code, String number){
        if(code.equals("025")){
            code = "027";
            number = "4" + number;
            mobilePrint(code, number);
        }

        boolean dup = false;
        String search = code + number;
        search = search.replaceAll("[^\\d]", "");
        if(!Letters.BTreeSearch(ts, search)){
            Letters.BTreeAdd(ts, search);
            
        } else {
            dup = true;
        }
        if(search.length() == 11){
            String number1 = number.substring(0, 4);
            String number2 = number.substring(4);
            char printCheck = number2.charAt(0);
            if(!Character.isDigit(printCheck)){
                number2 = " " + number2.substring(1);
                if(!dup){
                    System.out.println(code + " " + number1 + number2);
                } else {
                    System.out.println(code + " " + number1 + number2 + " DUP");
                }
            } else {
                String finNumber = code + " " + number1 + " " + number2;
                if(!dup){
                    System.out.println(finNumber);
                } else {
                    System.out.println(finNumber + " DUP");
                }
            }
        } else { 
            String number1 = number.substring(0,3);
            String number2 = number.substring(3);
            char printCheck = number2.charAt(0);
            if(!Character.isDigit(printCheck)){
                number2 = " " + number2.substring(1);
                if(!dup){
                    System.out.println(code + " " + number1 + number2);
                } else {
                    System.out.println(code + " " + number1 + number2 + " DUP");
                }
            } else {
                String finNumber = code + " " + number1 + " " + number2;
                
                if(!dup){
                    System.out.println(finNumber);
                } else {
                    System.out.println(finNumber + " DUP");
                }
                
            } 
        }
    
    }
  
    /** prints the number formatted correctly if it has an area code
     * @param code is the area code
     * @param number is the string of digits following the code
     */
    public static void areaPrint(String code, String number){
        boolean dup = false;
        String search = code + number;
        search = search.replaceAll("[^0-9]", "");
        if(!Letters.BTreeSearch(ts, search)){
            Letters.BTreeAdd(ts, search);
        } else {
            dup = true;
        }
        
        String number1 = number.substring(0,3);
        String number2 = number.substring(3);
        char printCheck = number2.charAt(0);
        if(!Character.isDigit(printCheck)){
            number2 = " " + number2.substring(1);
            if(!dup){
                System.out.println(code + " " + number1 + number2);
            } else {
                System.out.println(code + " " + number1 + number2 + " DUP");
            }
        } else {
               
                
        String finNumber = code + " " + number1 + " " + number2;
        if(!dup){
            System.out.println(finNumber);
        } else {
            System.out.println(finNumber + " DUP");
        }
        }
     
    }
   
    /** prints the number formatted correctly if it has an initial code
     * @param code is the initial code
     * @param number is the string of digits following the code
     */
    public static void initialPrint(String code, String number){
        boolean dup = false;
        String search = code + number;
        search = search.replaceAll("[^0-9]", "");
        if(!Letters.BTreeSearch(ts, search)){
            Letters.BTreeAdd(ts, search);
        } else {
            dup = true;
        }
        
        if(number.length() == 5){
            String finNumber = code + " " + number;
            if(!dup){
                System.out.println(finNumber);
            } else {
                System.out.println(finNumber + " DUP");
            }
        } else {
            String number1 = number.substring(0, 3);
            String number2 = number.substring(3);
            char printCheck = number2.charAt(0);
            if(!Character.isDigit(printCheck)){
                number2 = " " + number2.substring(1);
                if(!dup){
                    System.out.println(code + " " + number1 + number2);
                } else {
                    System.out.println(code + " " + number1 + number2 + " DUP");
                }
               
            } else {
                String finNumber2 = code + " " + number1 + " " + number2;
                if(!dup){
                    System.out.println(finNumber2);
                } else {
                    System.out.println(finNumber2 + " DUP");
                }
            }
        }
     
    }
  
}




