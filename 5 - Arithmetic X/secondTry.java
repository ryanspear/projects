import java.util.*;

public class secondTry{


    public static void main(String [] args){

        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(2);
        numbers.add(4);
        numbers.add(7);
        int target = 42;
        findOp(numbers, target, 1);
        

    }


    public static int findOp(ArrayList<Integer> numbers, int target, int i){
        int tempTarget = target;
        if(i > numbers.size() - 1){
            return 0;
        }
        if(target == 0){
            return 1;
        } else {
            if(target < 0){
                return 0;
            }
        }
        target /= numbers.size() - i;
        findOp(numbers, target/=numbers.size() -i, i++);
        findOp(numbers, target-=numbers.size() -i, i++);

        return 0;
    }
        
}
