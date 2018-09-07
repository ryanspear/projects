/** Quilt.java
    Solves the problem for Etude 7, Quilting Bee.
    @author Ryan Spear, 2018
*/

import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.math.*;

/**public class Quilt draws a quilt pattern in a new window
   using recursive calls
**/
public class Quilt extends JFrame{

    private static int WCenter; // y coordinate for center of window
    private static int HCenter; // x coordinate for center of window
    final private static int DEFAULTSIZE = 1000; // size of window
    private static double scaleDefault;
    private static ArrayList<Integer> rArray = new ArrayList<>(); // red values
    private static ArrayList<Integer> gArray = new ArrayList<>();// green values
    private static ArrayList<Integer> bArray = new ArrayList<>();// blue values
    private static ArrayList<Double> scaleArray = new ArrayList<>();//scales
    private static ArrayList<Double> adjustedScales = new ArrayList<>();
    private static int count; // current count
    
    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        count = 0;
        while(scanner.hasNextLine()){
            count++;
            if(scanner.hasNextDouble()){
            scaleArray.add(scanner.nextDouble());
            }
            if(scanner.hasNextInt()){
            rArray.add(scanner.nextInt());
            }
            if(scanner.hasNextInt()){
            gArray.add(scanner.nextInt());
            }
            if(scanner.hasNextInt()){
            bArray.add(scanner.nextInt());
        }
            scanner.nextLine();
        }

        
        Quilt frame = new Quilt();
        int width = 1000;
        int height = 1000;
        WCenter = width/2;
        HCenter = height/2;
        frame.setSize(width,height);
        frame.setVisible(true);
        ArrayList scaleClone = (ArrayList) scaleArray.clone();

        adjustedScales = adjustScales(scaleClone);
        ArrayList cloneOfClone = (ArrayList) adjustedScales.clone();
        scaleDefault = findDefault(cloneOfClone, 900);
        
    }
    /** paint method is automatically called and begins the recursive calls
        @param page is the Graphics object
    **/
    public void paint(Graphics page){
        int[] center = {WCenter, HCenter};
        recursiveRect(page, adjustedScales, rArray, gArray, bArray, 0, count, center); 
    }

    /** recursively draws rectangles on each successive rectangle's
        corners by sending the coordinates and the size. The scale
        of the rectangles is manipulated by the amount of layers
        in the image.
        @param page is the graphics object
        @param scaleArray holds the scales of each layer
        @param rArray holds all red values
        @param gArray holds all green values
        @param bArray holds all blue values
        @param squareNo tracks what layer we are up to
        @param count tells us the max layers
        @param center is an array of size 2 holding x and y coordinates
        for where we should start drawing our rectangle.
    **/
    public void recursiveRect(Graphics page, ArrayList<Double> scaleArray, ArrayList<Integer> rArray,  ArrayList<Integer> gArray,  ArrayList<Integer> bArray, int squareNo, int count, int[] center){
        int size = 0;
        if(squareNo == count){
            return;
        }
        if(count == 1){
            size = 600;
            } else {
            size = (int) Math.round(scaleDefault / (scaleArray.get(squareNo)));
         }
        Color c = new Color(rArray.get(squareNo), bArray.get(squareNo), gArray.get(squareNo));
        page.setColor(c);
        page.fillRect(center[0] - size/2, center[1] - size/2, size, size);

        int[] TLCoords = getTL(center, size);
        int[] TRCoords = getTR(center, size);
        int[] BLCoords = getBL(center, size);
        int[] BRCoords = getBR(center, size);

        recursiveRect(page, scaleArray, rArray, gArray, bArray, squareNo+1, count, TLCoords);
        recursiveRect(page, scaleArray, rArray, gArray, bArray, squareNo+1, count, TRCoords);
        recursiveRect(page, scaleArray, rArray, gArray, bArray, squareNo+1, count, BLCoords);
        recursiveRect(page, scaleArray, rArray, gArray, bArray, squareNo+1, count, BRCoords);
        
    }

        
    /** method gets the x, y coordinates for the top left corner of
        the triangle.
        @return the coordinates
        @param center is the center coordinates for rectangle to be
        draw over the top of
        @param size is the size of the rectange in pixels.
    **/
    public int[] getTL(int[] center, int size){
        int[] TLCoords = new int[2];
        for(int i = 0; i < center.length; i++){
            TLCoords[i] = center[i] - size/2;
        }
        return TLCoords;
    }
    /** method gets the x, y coordinates for the top right corner of
        the triangle.
        @return the coordinates
        @param center is the center coordinates for rectangle to be
        draw over the top of
        @param size is the size of the rectange in pixels.
    **/
    public int[] getTR(int[] center, int size){
        int[] TRCoords = new int[2];
        TRCoords[0] = center[0] + size/2;
        TRCoords[1] = center[1] - size/2;
        return TRCoords;
    }

    /** method gets the x, y coordinates for the bottom left corner of
        the triangle.
        @return the coordinates
        @param center is the center coordinates for rectangle to be
        draw over the top of
        @param size is the size of the rectange in pixels.
    **/
    public int[] getBL(int[] center, int size){
        int[] BLCoords = new int[2];
        BLCoords[0] = center[0] - size/2;
        BLCoords[1] = center[1] + size/2;
        return BLCoords;
    }


    /** method gets the x, y coordinates for the bottom right corner of
        the triangle.
        @return the coordinates
        @param center is the center coordinates for rectangle to be
        draw over the top of
        @param size is the size of the rectange in pixels.
    **/
    public int[] getBR(int[] center, int size){
        int[] BRCoords = new int[2];
        for(int i = 0; i < center.length; i++){
            BRCoords[i] = center[i] + size/2;
        }
        return BRCoords;
    }

    /** adjusts the scales in comparison to the largest scale
        @param scales is an ArrayList of doubles containing the scales
        @return the adjusted ArrayList
    **/
    public static ArrayList<Double> adjustScales(ArrayList<Double> scales){
        double max = Collections.max(scales);
        for(int i = 0; i < scales.size(); i++){
            scales.set(i, round((max/scales.get(i)), 1));
        }    
        return scales;
    }
    /** finds what size the LARGEST scaled rectangle should be
        to make sure that all rectangles fit onto the screen
        @param scales are the adjusted scales of the rectangles
        @param windowSize is the size of the window the rectangles are going into
        @return how big the largest scaled rectangle should be
    **/
    public static double findDefault(ArrayList<Double> scales, int windowSize){
        double defaultSize, sum = 0;

        double lcm = lcm(scales);
        windowSize *= lcm;
        for(int i = 0; i < scales.size(); i++){
            scales.set(i, lcm/scales.get(i));
            sum += scales.get(i);
        }
        defaultSize = windowSize/sum;
        return defaultSize;

    }
    /** returns a common multiple, not neccessary the lowest
        @param input is an arraylist of doubles that will be
        multiplied together
        @return to common multiple
    **/
    private static double lcm(ArrayList<Double> input)
    {

        double result = 1;
        for(int i = 0; i < input.size(); i++){
            result *= input.get(i);
        }
        return result;
    }
    /** rounds a double to a certain dp
        @param precision is how many dp you want to round to
        @param value is the double to be rounded
        @return the rounded value
    **/
    private static double round (double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }

}
