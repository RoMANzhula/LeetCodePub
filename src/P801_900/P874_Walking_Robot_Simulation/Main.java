package P801_900.P874_Walking_Robot_Simulation;

import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] commands1 = {4, -1, 3};
        int[][] obstacles1 = {};
        System.out.println(solution.robotSim(commands1, obstacles1)); // Output: 25

        int[] commands2 = {4, -1, 4, -2, 4};
        int[][] obstacles2 = {{2, 4}};
        System.out.println(solution.robotSim(commands2, obstacles2)); // Output: 65

        int[] commands3 = {6, -1, -1, 6};
        int[][] obstacles3 = {};
        System.out.println(solution.robotSim(commands3, obstacles3)); // Output: 36
    }

    public int robotSim(int[] commands, int[][] obstacles) {
        // Directions: north, east, south, west
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int directionIndex = 0; // Initially facing north
        int x = 0, y = 0; // Robot's initial position

        // Convert obstacles to a set of strings for quick lookup
        Set<String> obstacleSet = new HashSet<>();
        for (int[] obstacle : obstacles) {
            obstacleSet.add(obstacle[0] + "," + obstacle[1]);
        }

        int maxDistanceSquared = 0;

        for (int command : commands) {
            if (command == -1) {
                // Turn right
                directionIndex = (directionIndex + 1) % 4;
            } else if (command == -2) {
                // Turn left
                directionIndex = (directionIndex + 3) % 4;
            } else {
                // Move forward
                for (int i = 0; i < command; i++) {
                    int nextX = x + directions[directionIndex][0];
                    int nextY = y + directions[directionIndex][1];

                    // Check if the next position is an obstacle
                    if (!obstacleSet.contains(nextX + "," + nextY)) {
                        x = nextX;
                        y = nextY;
                        // Update the maximum distance squared
                        maxDistanceSquared = Math.max(maxDistanceSquared, x * x + y * y);
                    } else {
                        // Hit an obstacle, stop moving
                        break;
                    }
                }
            }
        }

        return maxDistanceSquared;
    }
}

//Key Points:
//Direction Array: We use the directions array to determine the current direction based on directionIndex.
//Set for Obstacles: Using a Set to store obstacles allows O(1) lookup time, making it efficient to check
// if a move is blocked.
//Distance Calculation: We continuously update the maximum distance squared every time the robot moves to a new position.
//This approach ensures that the solution is efficient and handles all edge cases, such as obstacles and multiple turns.


//A robot on an infinite XY-plane starts at point (0, 0) facing north. The robot can receive a sequence of these
// three possible types of commands:
//-2: Turn left 90 degrees.
//-1: Turn right 90 degrees.
//1 <= k <= 9: Move forward k units, one unit at a time.
//Some of the grid squares are obstacles. The ith obstacle is at grid point obstacles[i] = (xi, yi). If the robot
// runs into an obstacle, then it will instead stay in its current location and move on to the next command.
//Return the maximum Euclidean distance that the robot ever gets from the origin squared (i.e. if the distance
// is 5, return 25).
//Note:
//North means +Y direction.
//East means +X direction.
//South means -Y direction.
//West means -X direction.
//There can be obstacle in [0,0].
//
//Example 1:
//Input: commands = [4,-1,3], obstacles = []
//Output: 25
//Explanation: The robot starts at (0, 0):
//1. Move north 4 units to (0, 4).
//2. Turn right.
//3. Move east 3 units to (3, 4).
//The furthest point the robot ever gets from the origin is (3, 4), which squared is 32 + 42 = 25 units away.

//Example 2:
//Input: commands = [4,-1,4,-2,4], obstacles = [[2,4]]
//Output: 65
//Explanation: The robot starts at (0, 0):
//1. Move north 4 units to (0, 4).
//2. Turn right.
//3. Move east 1 unit and get blocked by the obstacle at (2, 4), robot is at (1, 4).
//4. Turn left.
//5. Move north 4 units to (1, 8).
//The furthest point the robot ever gets from the origin is (1, 8), which squared is 12 + 82 = 65 units away.

//Example 3:
//Input: commands = [6,-1,-1,6], obstacles = []
//Output: 36
//Explanation: The robot starts at (0, 0):
//1. Move north 6 units to (0, 6).
//2. Turn right.
//3. Turn right.
//4. Move south 6 units to (0, 0).
//The furthest point the robot ever gets from the origin is (0, 6), which squared is 62 = 36 units away.
//
//Constraints:
//1 <= commands.length <= 104
//commands[i] is either -2, -1, or an integer in the range [1, 9].
//0 <= obstacles.length <= 104
//-3 * 104 <= xi, yi <= 3 * 104
//The answer is guaranteed to be less than 231.