import java.util.Random;
import java.util.Timer;

public class Basecase {

  public static int[] start(int numofstations, int trains){
	Random r = new Random();
	int[] result = new int[trains]  ;
	for(int i = 0; i < trains; i++){
		result[i] = r.nextInt(numofstations) + 1; //random int from 1 to numofstation
	}
	return result;
  }
  
  public static int[] end(int numofstations, int trains) {
	Random r = new Random();
	int[] result = new int[trains]  ;
	for(int i = 0; i < trains; i++){
	  result[i] = r.nextInt(numofstations) + 1;
	}
	return result;
  }
  
  public static int[] cost(int numofcost, int numofstations, int trains) {
	Random r = new Random();
	int[] result = new int[trains]  ;
	for(int i = 0; i < trains; i++){
	  result[i] = r.nextInt(numofcost) + 1;
	}
	return result;
  }
  
  public static String toString(int numofcost, int numofstations, int trains) {
    String result = "StratStation"+ "   " + "EndStation" + "   " + "Cost\n";
    Random r = new Random();
    int i = 0;
    Timer timer = new Timer();
    int [] startstation,endstation,costs;
    startstation = start(numofstations, trains);
    endstation = end(numofstations, trains);
    costs = cost(numofcost,numofstations, trains);
    while (i < trains){
    	if(startstation[i] - endstation[i] == 0){
    		endstation[i] = r.nextInt(numofstations) + 1;
        } else if(startstation[i] != endstation[i]) {
          result += startstation[i] + "              " + endstation[i] + "             " + costs[i] + "\n";
          i++;
        }
    }
    return result;
  }
  
  public static void main(String[] args) {
	
    System.out.println(toString(20,10, 20));
  }
  
}
