import java.util.*;

public class CountUp{

    /** the limit we can have for an int is 12! (factorial)
     here the problem is that the output will always be smaller than the factorial of n
     So we need to solve how to hold the factorial of n which is going to be too big for
     a Long obect.
     Work out the relationships between the 3 variables.
     **/
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        Long n = 6L;
        Long k = 4L;

        Long nFact = Lfact(n);
        Long kFact = Lfact(k);
        Long diffFact = Lfact(n-k);
        System.out.println("n: " + n + ", k: " + k + ", difference: " + (n-k));
        System.out.println("n Factorised: " + nFact + ", k factorised: " + kFact + ", difference factorised: " + diffFact);
        Long answer = formula(nFact, kFact, diffFact);
        
        Long noFact = n/(k*(n-k));
        Long Denom = (k*(n-k));
        Long wow = Lfact(Denom);
        Long DenomFact = (kFact*diffFact);
        double eight = 9;
        double fifteen = 18;
        double outcome = (eight/fifteen);
        System.out.println("Denom: " + Denom);
        System.out.println("Literally that number factorised ^ : " + wow);
        System.out.println("Denom Factorised: " + DenomFact);
        System.out.println("Before factorial: " + noFact);
        System.out.println("After: " + Lfact(noFact));
        System.out.println("n/denom : " + outcome);
        System.out.println("Actual answer: " + answer);
        
    }
    
    public static Long Lfact(Long n){
        Long minus = 1L;
        Long result;
        if(n==0L || n==1L)
            return 1L;
        
        result = Lfact(n-minus) * n;
        return result;
    }
    
    public static Long formula(Long n, Long k, Long difference){
        Long output = n/(k*difference);
        //System.out.println(output);
        return output;
    }


}
