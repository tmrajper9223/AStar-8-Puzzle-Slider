A* Algorithm For 8 Puzzle Slider (Graph Approach)
Solution State:
0 | 1 | 2
3 | 4 | 5
6 | 7 | 8

Run Each Heuristic over 500 iterations to compare Average Execution Time.
Display Sample Solution Path

Execute Program
-Give Execution Permisions to start_program.sh file -> chmod +x start_program.sh

To Compile from Command Line:
-Change to the directory to which all of the .java files are located
-Make sure is included in your path environment variables
-Run "javac Main.java Node.java AStar.java"
-Run "java Main"

Output:
-Will first be prompted to wait for about 20 Seconds. Runs 500 instances of each heuristic
-Once execution is complete, there will be the execution time for each heuristic displayed along with the average step cost for each heuristic.
-Lastly, There will be two solution paths printed out fro both heuristics to view how each algirorithm reached the goal.

Heuristics:
-First heuristic calculates how many tiles are misplaced on the board
-Seconds heuristic Sums each tile's distance from their goal position.