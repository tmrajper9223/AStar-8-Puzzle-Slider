import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * 
 */

/**
 * @author Tarik Rajper
 *
 */
public class AStar {
	
	private HashSet<Integer> frontierSet;	//Unique Key for every State in Frontier
	private PriorityQueue<Node> frontier;	//All unexplored states in the frontier
	private HashSet<Integer> explored;		//All explored stated in this set
	private ArrayList<Node> solutionPath;
	private int heuristic;					//Which heuristic will be executed
	
	public AStar(int heuristic) {
		frontier = new PriorityQueue<Node>();
		frontierSet = new HashSet<Integer>();
		explored = new HashSet<Integer>();
		solutionPath = new ArrayList<Node>();
		this.heuristic = heuristic;
	}
	
	/**
	 * Keeps track of explored States with a HashSet
	 * Finds smallest path cost from initial state to goal
	 * @param state
	 * @return Solution State Node
	 */
	public Node graphSearch(Node state) {
		Node current = state;
		frontier.add(current);
		frontierSet.add(current.getKey());
		while (!current.goalTest()) {
			
			if (frontier.isEmpty()) {
				return null;
			}
			
			current = frontier.poll();
			frontierSet.remove(current.getKey());
			
			if (current.goalTest()) {
				getSolutionPath(current);
				return current;
			}
			
			explored.add(current.getKey());
			
			explore(current.moveDown());
			explore(current.moveLeft());
			explore(current.moveRight());
			explore(current.moveUp());
		}
		return null;
	}
	
	/**
	 * Determines Whether Current State Should Be Added To The Frontier And Calculates Heuristics Based On Input
	 */
	public void explore(Node child) {
		if (child != null && !frontierSet.contains(child.getKey()) && !explored.contains(child.getKey())) {
			if (heuristic == 1)
				child.setTotalCost(child.calculateHeuristicOne() + child.getStepCost());
			else if (heuristic == 2)
				child.setTotalCost(child.calculateHeuristicTwo() + child.getStepCost());
			frontier.offer(child);
			frontierSet.add(child.getKey());
		} else if (child != null && frontierSet.contains(child.getKey())) {
			if (heuristic == 1)
				child.setTotalCost(child.calculateHeuristicOne() + child.getStepCost());
			else if (heuristic == 2)
				child.setTotalCost(child.calculateHeuristicTwo() + child.getStepCost());
			frontier.offer(child);
			frontierSet.add(child.getKey());
		}
	}
	
	/**
	 * Backtracks From The Solution Node To The Initial State
	 * @param state
	 * @return Solution Path
	 */
	public ArrayList<Node> getSolutionPath(Node state) {
		solutionPath.add(state);
		while (state.getParent() != null) {
			solutionPath.add(state.getParent());
			state = state.getParent();
		}
		return solutionPath;
	}
	
	/**
	 * Prints Solution Path To Console
	 */
	public void print() {
		for (int i = solutionPath.size() - 1; i > -1; i--) {
			solutionPath.get(i).print();
			System.out.println();
		}
	}

}
