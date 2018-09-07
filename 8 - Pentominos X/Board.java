import java.util.*;
import java.awt.Point;

public class Board{

    public static char[][] board; // our board represented by a 2d array of characters
    public static ArrayList<Pent> pents = new ArrayList<Pent>();
    public static ArrayList<Pent> usedPents = new ArrayList<Pent>();
    private static int boardWidth;
    private static int boardLength;
    private static boolean gameWon;

    public static void fillBoard(ArrayList<char[]> b){
        board = new char[b.size()][b.get(0).length];
        int i = 0;
        for(char[] shape: b){ // might work?
            board[i] = shape;
            i++;
        }

        boardLength = board.length-1;
        boardWidth = board[0].length-1;

    }
        
    public void solvePuzzle(int row, int col){
        for(Pent p: pents){
            if(!gameOver() && !usedPents.contains(p)){
                for(shape s: p){ // for every shape in p
                    Point[] rotation = s;
                    if(!insert(p, rotation, row, col)) // insert it if we can, if we cant try another rotation.
                        continue;
                    if(impossibleArea()){ // LOGAN rename this to whatever you called your method.
                        remove(rotation,row,col);
                        continue;
                    }

                    usedPents.add(p);

                    if(usedPents.size() == pents.size()){ // every pent has been inserted. game over.
                        setGameOver(true);
                        printBoard();
                        return;
                    }

                    int nextRow = row;
                    int nextCol = col;
                    while(board[nextRow][nextCol] != '.'){
                        nextCol++;
                        if(nextCol == boardWidth){ // reached end of board.
                            nextCol = 0;
                            nextRow++;
                            if(nextRow == boardLength){
                                return; // reached the end of the board, deal with this somehow??
                            }
                        }
                    }

                    solvePuzzle(nextRow, nextCol);

                    remove(rotation, row, col);
                    usedPents.remove(p);
                }
            }
        }

    }

    public static void remove(Point[] form, int x, int y){
        for(int i = 0; i < form.length; i++){
            board[(int) form[i].getX() + y][(int) form[i].getY() + x] = '.';
        }
    }

    public static boolean insert(Pent p, Point[] form, int x, int y){
        if(testPlacement(form, x, y)){
            for(int i = 0; i < form.length; i++){
                board[(int) form[i].getX() + y][(int) form[i].getY() + x] = p.letterRepresentation;
            }                    
        } else {
            return false;
        }
    }

    public static boolean gameOver(){
        return gameWon;
    }

    public static void setGameOver(boolean win){
        gameWon = win;
    }

    public static ArrayList<Point> getNoZone(){
        // goes through the board and identifies asterix positions
        // can be used to fit pentominos around them
        ArrayList<Point> points = new ArrayList<Point>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == '*') {
                    Point asterisk = new Point(j, i);
                    points.add(asterisk);
                }
            }
        }
        return points;
    }

    public static boolean isComplete(){
        // if no spare spaces are left return true
        // else return false
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == '.') {
                    return false;
                }
            }
        }
        return true;
    }

    public static void printBoard(){
        // prints a visual representation of the current
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println("");
        }
    }
    
    public static boolean testPlacement(Point[] form, int x, int y){
        //for each point in Pent
        int boardHeight = board.length-1;
        int boardLength = board[1].length-1;
        for (int i = 0; i < form.length; i++) {
            if ((int)form[i].getX() + y > boardHeight || (int)form[i].getY() + x > boardLength) {
                System.out.println("Placement is beyond board bounds");
                return false;
            } else if (board[(int)form[i].getX()+y][(int)form[i].getY()+x] == '*' ||
                       Character.isLetter(board[(int)form[i].getX()+y][(int)form[i].getY()+x])){
                System.out.println("Placement attempted on an * or another Pentomino");
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        ArrayList<char[]> b = new ArrayList<char[]>();
        Scanner fileScanner = new Scanner(System.in);
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            if (line.startsWith(".") || line.startsWith("*")) {
                char[] boardLine = new char[line.length()];
                for (int i = 0; i < line.length(); i++) {
                    boardLine[i] = line.charAt(i);
                }
                b.add(boardLine);
            } else if(line.isEmpty()) {
                //perform necessary operations to complete puzzle
                //or determine puzzle to have no solution
                fillBoard(b);
                //printBoard();
                if (isComplete()) {
                    printBoard();
                } else {
                    System.out.println("No solution");
                }
                pents.clear();
                b.clear();
            } else {
                for (int i = 0; i < line.length(); i++) {
                    pents.add(Pent.charToPent(line.charAt(i)));
                }
            }
        }
        fillBoard(b);
        //printBoard();
        if (isComplete()) {
            printBoard();
        } else {
            System.out.println("No solution");
        }
    }
}
