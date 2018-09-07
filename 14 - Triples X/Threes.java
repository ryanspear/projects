import java.util.*;

/** @author Ryan Spear ID: 1801851
    COSC 326 Summer School 2018
**/

public class Threes{
    /** x, y AND z must all be odd **/
    
    public static void main(String[] args){

        long startTime = System.nanoTime();
        
        int count = 1;
        TreeMap<Integer, Long> perfectSquares = new TreeMap<Integer, Long>(); // holds all ODD perfect squares up to 60,000
        int a = 1;
        Long l = 1L;
        while(a < 200000){
            if(a%5 == 0){
                a +=2;
                l +=2;
                continue;
            }
            perfectSquares.put(a, l*l);
            a += 2;
            l += 2;
        }
        a = 1;
        int o = 1;
        TreeMap<Integer, Long> toDaFourth = new TreeMap<Integer, Long>(); // holds all ODD powers to the 4th up to 300
        while(a < 1000){
            if(a%5 == 0){
                a +=2;
                o +=2;
                continue;
            }
            toDaFourth.put(a, powerN(o, 4));
            a +=2;
            o +=2;
        }


        /** ordered by x **/
        Set<Integer> fourthKeys = toDaFourth.keySet();
        Set<Integer> squareKeys = perfectSquares.keySet();
        Set<Integer> squareKeys2 = perfectSquares.keySet();
        while(count <= 70){
            for(int x: squareKeys){
                if(count > 70){
                    break;
                }
                TreeMap<Integer, Long> subSetA = new TreeMap<Integer, Long>();
                subSetA.putAll(toDaFourth.headMap(x));
                Set<Integer> Zkeys = subSetA.keySet();
                for(int z: Zkeys){
                    if(z == 421){
                    }
                    if(gcd(x,z) != 1){
                        continue;
                    }
                    long find = subSetA.get(z) - perfectSquares.get(x) + 1;
                    long y = isPerfect(find);
                    if(y == -1 || y <  x || gcd((long) x,y) != 1){
                        continue;
                    } 
                    if(gcd((long) z,y) == 1){
                        System.out.println(count + " " + x + " " + y + " " + z);
                        count++;
                            
                    }
                }
                    
            }
        }
    
        
        System.out.println("");
        count = 1;

        
        /** orderered by z **/
        while(count <= 70){
            for(int z: fourthKeys){ // for every fourth
                if(count > 70){
                    break;
                }
                TreeMap<Integer, Long> subSet = new TreeMap<Integer, Long>();
                subSet.putAll(perfectSquares.subMap(z, (int) Math.pow(z, 2))); // make a subMap from z -> z squared.
                Set<Integer> keys = subSet.keySet();
                for(int x: keys){
                    if(count > 70){
                        break;
                    }
                    Long find = (toDaFourth.get(z)+1) - subSet.get(x);
                    if(subSet.containsValue(find)){
                        int y = (int) Math.sqrt(find);
                        if(y > x){
                            if(gcd(x,y) == 1 && gcd(x,z) == 1 && gcd(y,z) == 1){
                                System.out.println(count + " " + x + " " + y + " " + z);
                                count++;
                                if(count > 70){
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
    

         // System.out.println("Done");

         long endTime = System.nanoTime();
         long duration = (endTime - startTime);
         long seconds = duration/1000000000;
         //un-comment this to see how many seconds the program takes
         //System.out.println("Time Taken: " + seconds);
         
    }


    public static long isPerfect(long y){
        double variable = Math.sqrt(y);
        if((variable == Math.floor(variable)) && !Double.isInfinite(variable)){
            return (long) variable;
        }

        return -1;
    }

    /** greatest common divisor **/        
    public static Long gcd (Long x , Long y)
    {
        if ( y == 0 )                        
            return x;
        else if ( x >= y && y > 0)
            return gcd ( y , x % y );
        else return gcd ( y , x );     
    }


    public static int gcd (int x , int y)
    {
        if ( y == 0 )                        
            return x;
        else if ( x >= y && y > 0)
            return gcd ( y , x % y );
        else return gcd ( y , x );     
    }

    

    /** returns a number to the power of 4 as a long. **/
    public static long powerN(int number, int power) {
        return (long) Math.pow(number, power);
    }
        


}
