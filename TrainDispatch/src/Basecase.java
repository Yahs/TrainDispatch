import java.util.Random;

public class Basecase {

  public static int[] start(int numofstations){
	Random r = new Random();
	int[] result = new int[numofstations]  ;
	for(int i = 0; i < numofstations; i++){
		result[i] = r.nextInt(numofstations) + 1; //random int from 1 to numofstation
	}
	return result;
  }
  
  public static int[] end(int numofstations) {
	Random r = new Random();
	int[] result = new int[numofstations]  ;
	for(int i = 0; i < numofstations; i++){
	  result[i] = r.nextInt(numofstations) + 1;
	}
	return result;
  }
  
  public static int[] cost(int numofcost, int numofstations) {
	Random r = new Random();
	int[] result = new int[numofstations]  ;
	for(int i = 0; i < numofstations; i++){
	  result[i] = r.nextInt(numofcost) + 1;
	}
	return result;
  }
  
  public static String toString(int numofcost, int numofstations) {
    String result = "StratStation"+ "   " + "EndStation" + "   " + "Cost\n";
    Random r = new Random();
    int i = 0;
    int [] startstation,endstation,costs;
    startstation = start(numofstations);
    endstation = end(numofstations);
    costs = cost(numofcost,numofstations);
    while (i < 10){
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
	
    System.out.println(toString(20,10));
  }
  
}