package cubes;

public class Cubes{

    private static int[][][] bigCube = new int[2][2][2]; // larger 2x2x2 cube.
    public static LittleCube[] yellowCubes = new LittleCube[4];
    public static LittleCube[] blueCubes = new LittleCube[4];
    
    public static void main(String [] args){
        for(int i = 0; i < yellowCubes.length; i++){
            yellowCubes[i] = new LittleCube(0);
            blueCubes[i] = new LittleCube(1);
        }


        System.out.println(yellowCubes[2].getType());
        
        /** fill the bigCube with LittleCubes **/
        for(int layer = 0; layer < bigCube[0].length; layer++){
            //  for(int row = 0; row < bigCube[
        bigCube[layer][0][0] = yellowCubes[2].getType();
        System.out.println(bigCube[0][0][0]);
        }
        
        
    }
        
}
