public class Identity{

    public static void main(String[] args){
        double x = 2000000;
        double y = 33;
        float xx = (float) x;
        float yy = (float) y;
        System.out.println("x: " + x + ", y: " + y);
        System.out.println("Double: " + calculate(x, y));
        System.out.println("Float: " + floatCalculate(xx, yy));
        

    }


    public static double calculate(double x, double y){

        double output = (((x/y) - (x*y)) * y) + (x * y * y);

        return output;
    }

    public static float floatCalculate(float x, float y){
        float output =  (((x/y) - (x*y)) * y) + (x * y * y);

        return output;
    }

}
