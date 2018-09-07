import java.util.*;

public class Harmonic{

    public static void main(String [] args){
        float x = 10000;
        double xD = 10000;
        double eulerConstant = 0.577215664901532;
        double logged = Math.log(xD);
        System.out.println("n: " + x);
        System.out.println("Float in order:  \t" + floatCompute(x));
        System.out.println("Float in reverse:\t" + floatComputeReverse(x));
        System.out.println("Double in order:\t" + doubleCompute(xD));
        System.out.println("Double in reverse:\t" + doubleComputeReverse(xD));

        double ans = logged + eulerConstant;
        System.out.println("Euler-Mascheroni: " + ans);
        float add = (1/x);
        System.out.println(floatCompute(x-1) + ", + " + add);
        System.out.println(floatCompute(x-1) + add);
        
    }

    public static float floatCompute(float n){
        float i = 1;
        float output = 0;
        while(i <= n){
            output += (1/i);
            i++;
        }

        return output;


    }

    public static float floatComputeReverse(float n){
        float i = 0;
        float output = 0;
        while(i < n){
            output += 1/(n-i);
            i++;
        }

        return output;
    }


    public static double doubleCompute(double n){
        double i = 1;
        float output = 0;
        while(i <= n){
            output += (1/i);
            i++;
        }

        return output;
    }

    public static double doubleComputeReverse(double n){
        double i = 0;
        float output = 0;
        while(i < n){
            output += 1/(n-i);
            i++;
        }

        return output;
    }
}
