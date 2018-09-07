import java.util.*;
import java.awt.Point;
/** @authors Ryan Spear, Logan Richard, Kent Loh, Angus McMillan
    Pentominos part 1
**/
public enum Pent {
    O('O', new Point[][]{{new Point(0,0), new Point(1,0), new Point(2,0), new Point(3,0), new Point(4,0)}, //original O
                         {new Point(0,0), new Point(0,1), new Point(0,2), new Point(0,3), new Point(0,4)}}), //rotated 90 degrees
 
    P('P', new Point[][]{{new Point(0,0), new Point(0,1), new Point(1,0), new Point(1,1), new Point(2,0)}, //original P
                         {new Point(0,0), new Point(0,1), new Point(0,2), new Point(1,1), new Point(1,2)}, //rotated 90 degrees
                         {new Point(0,1), new Point(1,0), new Point(1,1), new Point(2,0), new Point(2,1)}, //rotated 180 degrees
                         {new Point(0,0), new Point(0,1), new Point(1,0), new Point(1,1), new Point(1,2)}, //rotated 270 degrees
                         {new Point(0,0), new Point(0,1), new Point(1,0), new Point(1,1), new Point(2,1)}, //original reflected
                         {new Point(0,1), new Point(0,2), new Point(1,0), new Point(1,1), new Point(1,2)}, //reflected rotated 90 degrees
                         {new Point(0,0), new Point(1,0), new Point(1,1), new Point(2,0), new Point(2,1)}, //reflected rotated 180 degrees
                         {new Point(0,0), new Point(0,1), new Point(0,2), new Point(1,0), new Point(1,1)}}), //reflected rotated 270 degrees
 
    Q('Q', new Point[][]{{new Point(0,0), new Point(0,1), new Point(1,1), new Point(2,1), new Point(3,1)}, //original Q
                         {new Point(0,3), new Point(1,0), new Point(1,1), new Point(1,2), new Point(1,3)}, //rotated 90 degrees
                         {new Point(0,0), new Point(1,0), new Point(2,0), new Point(3,0), new Point(3,1)}, //rotated 180 degrees
                         {new Point(0,0), new Point(0,1), new Point(0,2), new Point(0,3), new Point(1,0)}, //rotated 270 degrees
                         {new Point(0,0), new Point(0,1), new Point(1,0), new Point(2,0), new Point(3,0)}, //orignal reflected
                         {new Point(0,0), new Point(0,1), new Point(0,2), new Point(0,3), new Point(1,3)}, //reflected rotated 90 degrees
                         {new Point(0,1), new Point(1,1), new Point(2,1), new Point(3,0), new Point(3,1)}, //reflected rotated 180 degrees
                         {new Point(0,0), new Point(1,0), new Point(1,1), new Point(1,2), new Point(1,3)}}), //reflected rotated 270 degrees
 
    R('R', new Point[][]{{new Point(0,1), new Point(0,2), new Point(1,0), new Point(1,1), new Point(2,1)}, //original R
                         {new Point(0,1), new Point(1,0), new Point(1,1), new Point(1,2), new Point(2,2)}, //rotated 90 degrees
                         {new Point(0,1), new Point(1,1), new Point(1,2), new Point(2,0), new Point(2,1)}, //rotated 180 degrees
                         {new Point(0,0), new Point(1,0), new Point(1,1), new Point(1,2), new Point(2,1)}, //rotated 270 degrees
                         {new Point(0,0), new Point(0,1), new Point(1,1), new Point(1,2), new Point(2,1)}, //original reflected
                         {new Point(0,2), new Point(1,0), new Point(1,1), new Point(1,2), new Point(2,1)}, //reflected rotated 90 degrees
                         {new Point(0,1), new Point(1,0), new Point(1,1), new Point(2,1), new Point(2,2)}, //reflected rotated 180 degrees
                         {new Point(0,1), new Point(1,0), new Point(1,1), new Point(1,2), new Point(2,0)}}), //reflected rotated 270 degrees
 
    S('S', new Point[][]{{new Point(0,2), new Point(0,3), new Point(1,0), new Point(1,1), new Point(1,2)}, //original S
                         {new Point(0,0), new Point(1,0), new Point(2,0), new Point(2,1), new Point(3,1)}, //rotated 90 degrees
                         {new Point(0,1), new Point(0,2), new Point(0,3), new Point(1,0), new Point(1,1)}, //rotated 180 degrees
                         {new Point(0,0), new Point(1,0), new Point(1,1), new Point(2,1), new Point(3,1)}, //rotated 270 degrees
                         {new Point(0,0), new Point(0,1), new Point(1,1), new Point(1,2), new Point(1,3)}, //original reflected
                         {new Point(0,1), new Point(1,0), new Point(1,1), new Point(2,0), new Point(3,0)}, //reflected rotated 90 degrees
                         {new Point(0,0), new Point(0,1), new Point(0,2), new Point(1,2), new Point(1,3)}, //reflected rotated 180 degrees
                         {new Point(0,1), new Point(1,1), new Point(2,0), new Point(2,1), new Point(3,0)}}), //reflected rotated 270 degrees
 
    T('T', new Point[][]{{new Point(0,0), new Point(0,1), new Point(0,2), new Point(1,1), new Point(2,1)}, //original T
                         {new Point(0,2), new Point(1,0), new Point(1,1), new Point(1,2), new Point(2,2)}, //rotated 90 degrees
                         {new Point(0,1), new Point(1,1), new Point(2,0), new Point(2,1), new Point(2,2)}, //rotated 180 degrees
                         {new Point(0,0), new Point(1,0), new Point(1,1), new Point(1,2), new Point(2,0)}}), //rotated 270 degrees
 
    U('U', new Point[][]{{new Point(0,0), new Point(0,2), new Point(1,0), new Point(1,1), new Point(1,2)}, //orignal U
                         {new Point(0,0), new Point(0,1), new Point(1,0), new Point(2,0), new Point(2,1)}, //rotated 90 degrees
                         {new Point(0,0), new Point(0,1), new Point(0,2), new Point(1,0), new Point(1,2)}, //rotated 180 degrees
                         {new Point(0,0), new Point(0,1), new Point(1,1), new Point(2,0), new Point(2,1)}}), //rotated 270
   
    V('V', new Point[][]{{new Point(0,0), new Point(1,0), new Point(2,0), new Point(2,1), new Point(2,2)}, //orignal V (looks like an L)
                         {new Point(0,0), new Point(0,1), new Point(0,2), new Point(1,0), new Point(2,0)}, //rotated 90 degrees
                         {new Point(0,0), new Point(0,1), new Point(0,2), new Point(1,2), new Point(2,2)}, //rotated 180 degrees
                         {new Point(0,2), new Point(1,2), new Point(2,0), new Point(2,1), new Point(2,2)}}), //rotated 270 degrees
 
    W('W', new Point[][]{{new Point(0,0), new Point(1,0), new Point(1,1), new Point(2,1), new Point(2,2)}, //orignal W (rotated 45 degrees)
                         {new Point(0,1), new Point(0,2), new Point(1,0), new Point(1,1), new Point(2,0)}, //rotated 90 degrees
                         {new Point(0,0), new Point(0,1), new Point(1,1), new Point(1,2), new Point(2,2)}, //rotated 180 degrees
                         {new Point(0,2), new Point(1,1), new Point(1,2), new Point(2,0), new Point(2,1)}}), //rotated 270 degrees
       
    X('X', new Point[][]{{new Point(0,1), new Point(1,0), new Point(1,1), new Point(1,2), new Point(2,1)}}),
   
    Y('Y', new Point[][]{{new Point(0,2), new Point(1,0), new Point(1,1), new Point(1,2), new Point(1,3)}, //original Y (rotated 45 degrees onto side)
                         {new Point(0,0), new Point(1,0), new Point(2,0), new Point(2,1), new Point(3,0)}, //rotated 90 degrees
                         {new Point(0,0), new Point(0,1), new Point(0,2), new Point(0,3), new Point(1,1)}, //rotated 180 degrees
                         {new Point(0,1), new Point(1,0), new Point(1,1), new Point(2,1), new Point(3,1)}, //rotated 270 degrees
                         {new Point(0,0), new Point(0,1), new Point(0,2), new Point(0,3), new Point(1,2)}, //original reflected
                         {new Point(0,1), new Point(1,1), new Point(2,0), new Point(2,1), new Point(3,1)}, //reflected rotated 90 degrees
                         {new Point(0,1), new Point(1,0), new Point(1,1), new Point(1,2), new Point(1,3)}, //reflected rotated 180 degrees
                         {new Point(0,0), new Point(1,0), new Point(1,1), new Point(2,0), new Point(3,0)}}), //rotated rotated 270 degrees
   
    Z('Z', new Point[][]{{new Point(0,0), new Point(0,1), new Point(1,1), new Point(2,1), new Point(2,2)}, //original Z
                         {new Point(0,2), new Point(1,0), new Point(1,1), new Point(1,2), new Point(2,0)}, //rotated 90 degrees
                         {new Point(0,1), new Point(0,2), new Point(1,1), new Point(2,0), new Point(2,1)}, //original reflected
                         {new Point(0,0), new Point(1,0), new Point(1,1), new Point(1,2), new Point(2,2)}}); //reflected rotated 90 degrees
 
    protected Point[][] shape;
    protected char letterRepresentation;
 
    private Pent(char letter, Point[][] points) {
        this.letterRepresentation = letter;
        this.shape = points;
    }
    
    public static void pentToString(Pent pent) {
        //prints a visualization of the pent in its forms
        
        for (int i = 0; i < pent.shape.length; i++) {
            char[][] visual = new char[5][5];
            visual=fillArray();
            for (int j = 0; j < pent.shape[i].length; j++){
                visual[(int)pent.shape[i][j].getX()][(int)pent.shape[i][j].getY()] = 'P';
            }
            for (int o = 0; o < visual.length; o++) {
                for (int a = 0; a < visual[o].length; a++) {
                   System.out.print(visual[o][a]);
                }
                System.out.println();
            }
            
        }
    }
    public static Pent charToPent(char a) {
        char pent = a;
        switch(pent) {
            case 'O':
                return Pent.O;               
            case 'P':
                return Pent.P;                
            case 'Q':
                return Pent.Q;                
            case 'R':
                return Pent.R;                
            case 'S':
                return Pent.S;       
            case 'T':
                return Pent.T;                
            case 'U':
                return Pent.U;                
            case 'V':
                return Pent.V;                
            case 'W':
                return Pent.W;                
            case 'X':
                return Pent.X;                
            case 'Y':
                return Pent.Y;                
            case 'Z':
                return Pent.Z;
                
        }
        return null;
    }
    public static void main(String[] args) {
        pentToString(Pent.Z);
    }
    public static void changeXY(Pent pent){
        for(int i = 0; i < pent.shape.length; i++){
            for(int j = 0; j < pent.shape[i].length; j++){
                //System.out.println("old point: " + pent.shape[i][j]);
                Point woohoo = pent.shape[i][j];
                woohoo.setLocation((int) Math.abs(pent.shape[i][j].getY()), (int) Math.abs(pent.shape[i][j].getX()));
                System.out.print(" new Point(" + (int)woohoo.getX() + "," + (int)woohoo.getY() + "),");
            }
            System.out.println();
        }
    }
    public static char[][] fillArray(){
        char[][] filled = new char[5][5];
        for (int u = 0; u < filled.length; u++){
            for (int y = 0; y < filled[u].length; y++){
                filled[u][y] = '.';
            }
        }
        return filled;
    }
}












