import java.util.*;
import java.lang.Character;
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
    public static void solvePuzzle(int col, int row){
        //System.out.println("col: " + col + ", row: " + row);
        //System.out.println(board[row][col]);
        for(Pent p: pents){
            System.out.println("about to put in " + p.letterRepresentation + " at row: " + row + ", col: " + col);
            if(!gameOver() && !usedPents.contains(p)){
                for(int i = 0; i < p.shape.length; i++){ // for every shape in p
                    Point[] rotation = p.shape[i];
                    if(!insert(p, rotation, row, col)) // insert it if we can, if we cant try another rotation.
                        continue;
                    /** if(findEmpty(rotation, row, col)){
                        System.out.println("Found an impossible spot");
                        remove(rotation,row,col);
                        continue;
                        }**/
                    //System.out.println("about to add a new pent to used");
                    usedPents.add(p);
                    //System.out.println(usedPents);
                    if(usedPents.size() == pents.size()){ // every pent has been inserted. game over.
                        setGameOver(true);
                        printBoard();
                        return;
                    }
                    printBoard();
                    int nextRow = row;
                    int nextCol = col;
                    while(board[nextRow][nextCol] != '.'){
                        nextCol++;
                        if(nextCol == boardWidth){ // reached end of board.
                            nextCol = 0;
                            nextRow++;
                            if(nextRow == boardLength){
                                System.out.println("@@@@@@@@@@@@@@@@@@@@");
                                return; // reached the end of the board, deal with this somehow??
                            }
                        }
                    }
                    System.out.println("about to recursively call solvePuzzle");
                    solvePuzzle(nextCol, nextRow); // swapped these around
                    System.out.println("At " + col + " " + row + ", about to remove: " + p.letterRepresentation);
                    remove(rotation, row, col);
                    //printBoard();
                    usedPents.remove(p);
                }

                System.out.println("tried all rotations, need to try another pent now");
            }
        }

    }
            
    public static boolean gameOver(){
        return gameWon;
    }

    public static void setGameOver(boolean win){
        gameWon = win;
    }
    public static boolean insert(Pent p, Point[] form, int x, int y){
        //System.out.println("in insert. wanna insert " + p.letterRepresentation + " at row: " + x + ", col: " + y);
        if(testPlacement(p, form, x, y)){
            //System.out.println("test placement allowed it");
            for(int i = 0; i < form.length; i++){
                //System.out.println("i: " + i + "placing at: " + "rows: " + ((int)form[i].getY()+y) + ", cols: " + ((int) form[i].getX() + x));
                board[(int)form[i].getX()+x][(int) form[i].getY() + y] = p.letterRepresentation;
            }

            return true;
        }
        return false;
        
    }

    public static void remove(Point[] form, int x, int y){
        // remove this Pentomino object which has already been placed
        // on the board. Maybe it needs rotating or is the wrong type
        for (int i = 0; i < form.length; i++) {
            board[(int)form[i].getX()+x][(int)form[i].getY()+y] = '.';
        }
    }
    public static boolean findEmpty(Point[] form, int x, int y) {
        int boardHeight = board.length-1;
        int boardLength = board[0].length-1;
        ArrayList<Point> visited = new ArrayList<Point>();
        for (Point block : form) {
            //System.out.println("Block: " +block);
            if (((block.getX()+x)-1) >= 0) {//look at block above
                if (board[(int)block.getX()+x-1][((int)block.getY()+y)] == '.') {
                    Point above = new Point((int)block.getY()+x, (int)(block.getX()+y)-1);
                    visited.add(above);
                    //System.out.println("CALL ABOVE: " + above);
                    if (checkEmpty(above)) {
                        return true;
                    }
                }
            }
            if (((block.getX()+x)+1) <= boardHeight) {//look at block below
                if (board[((int)block.getX()+x)+1][(int)block.getY()+y] == '.') {
                    Point below = new Point((int)block.getY()+x, (int)(block.getX()+y)+1);
                    visited.add(below);
                    //System.out.println("CALL BELOW: " + below);
                    if (checkEmpty(below)) {
                        return true;
                    }
                }
            }
            if (((block.getY()+x)-1) >= 0) { //look at block left
                if (board[(int)block.getX()+x][(int)(block.getY()+y)-1] == '.') {
                    Point left = new Point((int)block.getY()+x -1, (int)block.getX()+y);
                    visited.add(left);
                    //System.out.println("CALL LEFT: "+left);
                    if (checkEmpty(left)) {
                        return true;
                    }
                }
            }
            if (((block.getY()+y)+1) <= boardLength) { //look at block right
                if (board[(int)block.getX()+x][(int)(block.getY()+y)+1] == '.') {
                    Point right = new Point((int)block.getY()+y +1, (int)block.getX()+x);
                    visited.add(right);
                    //System.out.println("CALL RIGHT: "+ right);
                    if (checkEmpty(right)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public static boolean checkEmpty(Point point) {//returns true if unfillable empty space is found
        int boardHeight = board.length-1;
        int boardLength = board[0].length-1;
        ArrayList<Point> visited = new ArrayList<Point>();
        Queue<Point> queue = new LinkedList<Point>();
        queue.add(point);
        visited.add(point);
        int empty = 0;
        while (!queue.isEmpty()) {
            Point current = queue.poll();
            empty++;
            //System.out.println("Empty: " +empty);
            if (empty >= 5) {
                return false;
            }
            if (current.getY()+1 <= boardHeight) {
                Point emptyBelow = new Point((int)current.getX(), (int)current.getY()+1);
                if (board[(int)current.getX()+1][(int)current.getY()] == '.' &&
                    !visited.contains(emptyBelow)) {
                    queue.add(emptyBelow);
                    visited.add(emptyBelow);
                }
            }
            if (current.getY()-1 >= 0) {
                Point emptyAbove = new Point((int)current.getX(), (int)current.getY()-1);
                if (board[(int)current.getX()-1][(int)current.getY()] == '.' &&
                    !visited.contains(emptyAbove)) {
                    queue.add(emptyAbove);
                    visited.add(emptyAbove);
                }
            }
            if (current.getX()-1 >= 0) {
                Point emptyLeft = new Point((int)current.getX()-1, (int)current.getY());
                if (board[(int)current.getX()][(int)current.getY()-1] == '.' &&
                    !visited.contains(emptyLeft)) {
                    queue.add(emptyLeft);
                    visited.add(emptyLeft);
                }
            }
            if (current.getX()+1 <= boardLength) {
                Point emptyRight = new Point((int)current.getX()+1, (int)current.getY());
                if (board[(int)current.getX()][(int)current.getY()+1] == '.' &&
                    !visited.contains(emptyRight)) {
                    queue.add(emptyRight);
                    visited.add(emptyRight);
                }
            }

        }
        return true;
    }


    /** public static ArrayList<Point> getNoZone(){
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
        }**/

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
            System.out.println();
        }
        System.out.println();
    }

    public static boolean testPlacement(Pent p, Point[] form, int x, int y){
        //System.out.println("\nIn test placement. row: " + x + ", col: " + y + " of type " + p.letterRepresentation);
        //System.out.println("char at row col: " + board[x][y]);
        int boardHeight = board.length-1;
        int boardLength = board[1].length-1;
        for (int i = 0; i < form.length; i++) {
            //System.out.println("looking at row: " + ((int)form[i].getX()+x) + ", col: " + ((int)form[i].getY()+y) + " to see if it is clashing");
            if ((int)form[i].getX() + x > boardHeight || (int)form[i].getY() + y > boardLength) {
                //System.out.println("Placement is beyond board bounds");
                return false;
            } else if (board[(int)form[i].getX()+x][(int)form[i].getY()+y] != '.'){
                
                //System.out.println("Placement attempted on an * or another Pentomino");
                //System.out.println("Form[i].getX = " + (int) form[i].getX() + ", plus y: " + y);
                //System.out.println("at location row: " + ((int)form[i].getY()+y) + ", col: " + ((int)form[i].getX()+y));
                //System.out.println(board[(int)form[i].getX()+x][(int)form[i].getY()+x]);
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
            //System.out.println("Line: " + line);
            if(!line.isEmpty()){ //line contains either board or pieces
                if (Character.isLetter(line.charAt(0))){ //line contains pieces
                    for (int i = 0; i < line.length(); i++) {
                        pents.add(Pent.charToPent(line.charAt(i)));
                    }
                } else { //line contains board
                    char[] boardLine = new char[line.length()];
                    for (int i = 0; i < line.length(); i++) {
                        boardLine[i] = line.charAt(i);
                    }
                    b.add(boardLine);
                }
            } else {//we have our puzzle to solve
                if (pents.isEmpty()) { //assume we are given one of each
                    fillPents();
                }
                fillBoard(b);
                solvePuzzle(0, 0); 
                //printBoard();
                if (isComplete()) {
                    printBoard();
                } else {
                    System.out.println("No solution");
                }
                pents.clear();
                b.clear();
            }
        }
        if (pents.isEmpty()) { //assume we are given one of each
            fillPents();
        }
        fillBoard(b);
        solvePuzzle(0, 0);
        //printBoard();
        if (isComplete()) {
            printBoard();
        } else {
            System.out.println("No solution");
        }
    }
    public static void fillPents() {
        String standardPents = "OPQRSTUVWXYZ";
        for (int i = 0; i < standardPents.length(); i++) {
            pents.add(Pent.charToPent(standardPents.charAt(i)));
        }
    }
}
