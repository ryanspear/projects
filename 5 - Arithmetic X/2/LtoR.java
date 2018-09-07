package arithmetic;

public class LtoR{
  
  private static boolean found = false;
  public static void search(int[] numbers, int target){
    int sum = 0;
    for(int i = 0; i < numbers.length; i++){
      sum += numbers[i];
    }
    String printer = "";
    findCombo(numbers, target, 0, sum, 0, printer);
    
    
  }
  
  public static void setFound(boolean sfound){
    found = sfound;
  }
  
  public static boolean getFound(){
    return found;
  }
  
  
  public static void findCombo(int[] a, int value, int remainder, int sum, int i, String printer){
    i++;
    if(i == a.length+1 || value < sum-1 || getFound() || remainder != 0){
      return;
    }
    if(value == sum && remainder == 0){
      int j = i;
      while(j < a.length+1){
      printer = (" + " + a[a.length-j] + printer);
      j++;
      }
      printer = printer.substring(3);
      printer = "L " + printer;
      System.out.println(printer);
      setFound(true);
    }
      findCombo(a, value/a[a.length-i], value%a[a.length-i], sum-a[a.length -i], i, " * " + a[a.length-i] + printer);

      findCombo(a, value-a[a.length-i], 0, sum-a[a.length-i], i, " + " + a[a.length-i] + printer);
    
  }
}
