import java.util.Random;

/**
 * 
 */

/**
 * @author Tarik Rajper
 *
 */
public class Node implements Comparable<Node> {
	
	private int[] state;
	private int stepCost;
	private int key;
	private int totalCost;
	private Node parent;
	private final static int[] goalState = {0,1,2,3,4,5,6,7,8};
	
	public Node() {
		this(generateState(), null, 0);
	}
	
	public Node(int[] state, Node parent, int stepCost) {
		this.state = state;
		this.stepCost = stepCost;
		this.key = generateKey();
		this.parent = parent;
	}
	
	/**
	 * Generates the Initial board/problem
	 * @returns a new board
	 */
	public static int[] generateState() {
		int[] board = goalState;
		Random rand = new Random();
		
		for (int i = board.length - 1; i > -1; i--) {
			int index = rand.nextInt(i + 1);
			int tmp = board[index];
			board[index] = board[i];
			board[i] = tmp;
		}
		return board;
	}
	
	/**
	 * Shifts the Zero Position Up if possible
	 * @returns new State
	 */
	public Node moveUp() {
		int[] board = state.clone();
		int position = getZeroPosition(board);
		if (position < 3) {
			return null;
		}
		swap(board, position, position - 3);
		return new Node(board, this, stepCost++);
	}
	
	/**
	 * Shifts the Zero Position Down in Possible
	 * @returns new State
	 */
	public Node moveDown() {
		int[] board = state.clone();
		int position = getZeroPosition(board);
		if (position > 5) {
			return null;
		}
		swap(board, position, position + 3);
		return new Node(board, this, stepCost++);
	}
	
	/**
	 * Shifts Zero Position Left if possible
	 * @returns a new State
	 */
	public Node moveLeft() {
		int[] board = state.clone();
		int position = getZeroPosition(board);
		if (position == 0 || position == 3 || position == 6)
			return null;
		swap(board, position, position - 1);
		return new Node(board, this, stepCost++);
	}
	
	/**
	 * Shifts Zero Position Right if possible.
	 * @returns a new State
	 */
	public Node moveRight() {
		int[] board = state.clone();
		int position = getZeroPosition(board);
		if (position == 2 || position == 5 || position == 8)
			return null;
		swap(board, position, position + 1);
		return new Node(board, this, stepCost++);
	}
	
	/**
	 * Finds the Position of the Zero on the board
	 * @param board
	 * @returns The index of Zero
	 */
	public int getZeroPosition(int[] board) {
		int position = -1;
		for (int i = 0; i < board.length; i++) {
			if (board[i] == 0) {
				position = i;
				return position;
			}
		}
		return position;
	}
	
	/**
	 * Swaps Two elements on a board
	 * @param board
	 * @param pos1
	 * @param pos2
	 */
	public void swap(int[] board, int pos1, int pos2) {
		int temp = board[pos1];
		board[pos1] = board[pos2];
		board[pos2] = temp;
	}
	
	/**
	 * Returns Number of misplaced Tiles on the board
	 * @return
	 */
	public int calculateHeuristicOne() {
		int h = 0;
		for (int i = 0; i < state.length; i++) {
			if (state[i] != i)
				h++;
		}
		return h;
	}
	
	/**
	 * Returns the sum of the distance of each misplaced tile on the board
	 * @return
	 */
	public int calculateHeuristicTwo() {
		int h = 0;
		for (int i = 0; i < state.length; i++) {
			if (state[i] != i) {
				int distance = state[i] - i;
				if (distance < 0)
					distance = distance * -1;
				h += distance;
			}
		}
		return h;
	}
	
	/**
	 * Checks if the initial board is solvable
	 * @return
	 */
	public boolean isValid() {
		int counter = 0;
		for (int i = 0; i < state.length - 1; i++) {
			if (state[i] == 0)
				continue;
			for (int j = i + 1; j < state.length; j++) {
				if (state[j] == 0)
					continue;
				if (state[j] < state[i])
					counter++;
			}
		}
		return counter % 2 == 0;
	}
	
	/**
	 * Checks if Goal is Reached
	 * @return
	 */
	public boolean goalTest() {
		for (int i = 0; i < state.length; i++) {
			if (state[i] != i)
				return false;
		}
		return true;
	}
	
	/*
	 * Generated a Unique Key for the frontier and explored set
	 */
	public int generateKey() {
		String uniqueKey = "";
		for (int i = 0; i < state.length; i++) {
			uniqueKey += state[i];
		}
		return Integer.parseInt(uniqueKey);
	}
	
	/**
	 * Sets total cost for deciding on next state
	 * @param cost
	 */
	public void setTotalCost(int cost) {
		this.totalCost = cost;
	}
	
	public int getTotalCost() {
		return totalCost;
	}
	
	public int getKey() {
		return key;
	}
	
	public int[] getState() {
		return state;
	}
	
	public int getStepCost() {
		return stepCost;
	}
	
	public void setParent(Node parent) {
		this.parent = parent;
	}
	
	public Node getParent() {
		return parent;
	}
	
	public void print() {
		int size = state.length;
		for (int i = 0; i < size; i++) {
			if (i % 3 == 0) {
				System.out.print("\n" + state[i] + " ");
				continue;
			}
			System.out.print(state[i] + " ");
		}
	}

	@Override
	public int compareTo(Node o) {
		return this.totalCost - o.totalCost;
	}

}
