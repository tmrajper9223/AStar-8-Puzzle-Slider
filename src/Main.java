import java.util.concurrent.TimeUnit;

/**
 * 
 */

/**
 * @author Tarik Rajper
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Solving 500 Instances of 8 Puzzle using A* for both Heuristics.");
		System.out.println("This should take about 20 seconds...");
		searches();
		System.out.println("\nExample Search Paths...");
		printSolutionPath();
	}
	
	public static void searches() {
		int sum = 0;
		
		long sTime = System.currentTimeMillis();
		for (int i = 0; i < 500; i++) {
			Node n = new Node();
			while (!n.isValid()) {
				n = new Node();
			}
			AStar a = new AStar(1);
			Node sol = a.graphSearch(n);
			sum += sol.getStepCost();
		}
		long eTime = System.currentTimeMillis();
		
		long heuristicOneTime = eTime - sTime;
		int heuristicOneAverage = sum / 500;
		
		sum = 0;
		sTime = System.currentTimeMillis();
		for (int i = 0; i < 500; i++) {
			Node n = new Node();
			while (!n.isValid()) {
				n = new Node();
			}
			AStar a = new AStar(2);
			Node sol = a.graphSearch(n);
			sum += sol.getStepCost();
		}
		eTime = System.currentTimeMillis();
		
		long heuristicTwoTime = eTime - sTime;
		int heuristicTwoAverage = sum / 500;
		
		System.out.println("Execution Time For Heuristic One over 500 Instances: " + 
		TimeUnit.MILLISECONDS.toSeconds(heuristicOneTime) + " Seconds");
		
		System.out.println("Execution Time For Heuristic Two over 500 Instances: " + 
		TimeUnit.MILLISECONDS.toSeconds(heuristicTwoTime) + " Seconds");
		
		System.out.println("Average Step Cost for Heuristic One: " + heuristicOneAverage);
		System.out.println("Average Step Cost for Heuristic Two: " + heuristicTwoAverage);
	}
	
	public static void printSolutionPath() {
		Node n = new Node();
		while (!n.isValid()) {
			n = new Node();
		}
		AStar a = new AStar(1);
		Node sol = a.graphSearch(n);
		System.out.println("\n==========SOLUTION PATH FOR HEURISTIC 1==========");
		a.print();
		System.out.println("Step Cost: " + sol.getStepCost());
		System.out.println("====================");
		
		n = new Node();
		while (!n.isValid()) {
			n = new Node();
		}
		a = new AStar(2);
		sol = a.graphSearch(n);
		System.out.println("\n==========SOLUTION PATH FOR HEURISTIC 2==========");
		a.print();
		System.out.println("Step Cost: " + sol.getStepCost());
		System.out.println("====================");
	}
}
