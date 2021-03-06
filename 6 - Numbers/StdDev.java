import java.util.*;
public class StdDev{

    public static void main(String [] args){
        double doubleFixed = 100000;
        float floatFixed = 100000;
        double[] g = new double[1000];
        float[] r = new float[1000];
        Random rand = new Random();
        for(int i = 0; i < 10; i++){
            double wow = rand.nextInt(900000) +doubleFixed;
            r[i] = (float) wow;
            g[i] = wow;
        }
            
        //double[] d =  {2234, 4938, 3102, 1307, 4185, 1209, 8976, 9232, 3456};
        //float[] f = {2234, 4938, 3102, 1307, 4185, 1209, 8976, 9232, 3456};
        int fixed = 100;
        // System.out.println("a: " + Arrays.toString(g));
        System.out.println("Double:");
        System.out.println("(1) Deviation:\t" + sampleDeviation(g));
        System.out.println("(2) Deviation:\t" + populationDeviation(g) + "\n");

        System.out.println("Float:");
        System.out.println("(1) Deviation:\t" + sampleFloatDeviation(r));
        System.out.println("(2) Deviation:\t" + populationFloatDeviation(r));


        for(int i = 0; i < g.length; i++){
            r[i] += floatFixed;
            g[i] += floatFixed;
        }
        
        System.out.println("\nadding fixed amount: " + doubleFixed);
        System.out.println("Double:");
        System.out.println("(1) Deviation:\t" + sampleDeviation(g));
        System.out.println("(2) Deviation:\t" + populationDeviation(g) + "\n");

        System.out.println("Float:");
        System.out.println("(1) Deviation:\t" + sampleFloatDeviation(r));
        System.out.println("(2) Deviation:\t" + populationFloatDeviation(r));
        
        

    }




    public static double sampleDeviation(double[] n){
        double a = getA(n);
        double output = 0;
        for(int i = 0; i < n.length; i++){
            output += Math.pow((n[i] - a),2);
        }

        return Math.sqrt(output/n.length);
        
    }

    public static double getA(double[] n){
        double output = 0;
        for(int i = 0; i < n.length; i++){
            output += n[i];
        }

        return output/n.length;
    }


    public static double populationDeviation(double[] n){
        double output = 0;
        double minus = 0;
        for(int i = 0; i < n.length; i++){
            output += Math.pow(n[i], 2);
        }

        for(int i = 0; i < n.length; i++){
            minus += n[i];
        }

        minus = (Math.pow(minus, 2) / n.length);
        output -= minus;
        return Math.sqrt(output/n.length);

    }

    
    public static float sampleFloatDeviation(float[] n){
        float a = getFloatA(n);
        float output = 0;
        for(int i = 0; i < n.length; i++){
            output += ((n[i] - a) * (n[i] - a));
        }

        return (float) Math.sqrt(output/n.length);
        
    }

    public static float getFloatA(float[] n){
        float output = 0;
        for(int i = 0; i < n.length; i++){
            output += n[i];
        }

        return output/n.length;
    }

    public static float populationFloatDeviation(float[] n){
        float output = 0;
        float minus = 0;
        for(int i = 0; i < n.length; i++){
            output += Math.pow(n[i], 2);
        }

        for(int i = 0; i < n.length; i++){
            minus += n[i];
        }

        minus = ((minus*minus) / n.length);
        output -= minus;
        return (float) Math.sqrt(output/ n.length);

    }


                                     
}
