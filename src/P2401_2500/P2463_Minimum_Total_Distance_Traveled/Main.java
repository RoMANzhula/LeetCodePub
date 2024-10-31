package P2401_2500.P2463_Minimum_Total_Distance_Traveled;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        List<Integer> robots1 = List.of(0, 4, 6);
        int[][] factories1 = {{2, 2}, {6, 2}};
        System.out.println(solution.minimumTotalDistance(robots1, factories1)); // Output: 4

        List<Integer> robots2 = List.of(1, -1);
        int[][] factories2 = {{-2, 1}, {2, 1}};
        System.out.println(solution.minimumTotalDistance(robots2, factories2)); // Output: 2
    }

    public long minimumTotalDistance(List<Integer> robot, int[][] factory) {
        List<Integer> robots = new ArrayList<>(robot);
        // Sort both robots and factories by their positions to optimize assignments
        Collections.sort(robots);
        Arrays.sort(factory, Comparator.comparingInt(f -> f[0]));

        // Memoization table to store minimum distance calculations
        long[][] memo = new long[robots.size()][factory.length];
        for (long[] row : memo) {
            Arrays.fill(row, -1);  // Initialize with -1 to denote uncomputed states
        }

        // Start the recursive calculation
        return calculateMinDistance(robots, factory, 0, 0, memo);
    }

    private long calculateMinDistance(List<Integer> robots, int[][] factories, int robotIndex, int factoryIndex, long[][] memo) {
        // Base case: If all robots are assigned, total distance is zero
        if (robotIndex == robots.size()) return 0;
        // If we've considered all factories without assigning all robots, return a high cost
        if (factoryIndex == factories.length) return (long) 1e15;

        // Return memoized result if it already exists
        if (memo[robotIndex][factoryIndex] != -1) return memo[robotIndex][factoryIndex];

        // Option 1: Skip current factory, try with next one
        long answer = calculateMinDistance(robots, factories, robotIndex, factoryIndex + 1, memo);

        long tempDistance = 0;
        // Option 2: Try assigning up to the limit of the current factory
        for (int k = 0; k < factories[factoryIndex][1]; ++k) {
            if (robotIndex + k >= robots.size()) break;
            // Add distance for the k-th robot in this assignment
            tempDistance += Math.abs(robots.get(robotIndex + k) - factories[factoryIndex][0]);
            // Calculate distance for the rest of the robots after assigning this batch to the current factory
            answer = Math.min(answer, tempDistance + calculateMinDistance(robots, factories, robotIndex + k + 1, factoryIndex + 1, memo));
        }

        // Memoize and return the answer
        memo[robotIndex][factoryIndex] = answer;
        return answer;
    }

}

//Explanation
//Memoization Array: We use a memo array to store computed minimum distances for each (robotIndex, factoryIndex) pair
// to avoid recalculating the same state.
//Recursive Lambda Replacement: The calculateMinDistance method is a recursive function that explores
// two options for each factory:
//Skipping the Factory: We can choose not to use the current factory.
//Assigning Robots to the Factory: We calculate the cumulative distance for up to limit robots at
// the factory's location.
//Base Cases:
//If all robots are assigned (robotIndex == robots.size()), the additional cost is 0.
//If all factories are exhausted without assigning all robots (factoryIndex == factories.length), return
// a high cost (1e15).
//Optimization: Sorting the robots and factories by position allows assigning robots more effectively to
// nearby factories.


//There are some robots and factories on the X-axis. You are given an integer array robot where robot[i] is
// the position of the ith robot. You are also given a 2D integer array factory where factory[j] = [positionj, limitj]
// indicates that positionj is the position of the jth factory and that the jth factory can repair
// at most limitj robots.
//The positions of each robot are unique. The positions of each factory are also unique. Note that a robot can
// be in the same position as a factory initially.
//All the robots are initially broken; they keep moving in one direction. The direction could be the negative
// or the positive direction of the X-axis. When a robot reaches a factory that did not reach its limit, the
// factory repairs the robot, and it stops moving.
//At any moment, you can set the initial direction of moving for some robot. Your target is to minimize the
// total distance traveled by all the robots.
//Return the minimum total distance traveled by all the robots. The test cases are generated such that all
// the robots can be repaired.
//Note that
//
//All robots move at the same speed.
//If two robots move in the same direction, they will never collide.
//If two robots move in opposite directions and they meet at some point, they do not collide. They cross each other.
//If a robot passes by a factory that reached its limits, it crosses it as if it does not exist.
//If the robot moved from a position x to a position y, the distance it moved is |y - x|.
//
//Example 1:
//Input: robot = [0,4,6], factory = [[2,2],[6,2]]
//Output: 4
//Explanation: As shown in the figure:
//- The first robot at position 0 moves in the positive direction. It will be repaired at the first factory.
//- The second robot at position 4 moves in the negative direction. It will be repaired at the first factory.
//- The third robot at position 6 will be repaired at the second factory. It does not need to move.
//The limit of the first factory is 2, and it fixed 2 robots.
//The limit of the second factory is 2, and it fixed 1 robot.
//The total distance is |2 - 0| + |2 - 4| + |6 - 6| = 4. It can be shown that we cannot achieve a better
// total distance than 4.

//Example 2:
//Input: robot = [1,-1], factory = [[-2,1],[2,1]]
//Output: 2
//Explanation: As shown in the figure:
//- The first robot at position 1 moves in the positive direction. It will be repaired at the second factory.
//- The second robot at position -1 moves in the negative direction. It will be repaired at the first factory.
//The limit of the first factory is 1, and it fixed 1 robot.
//The limit of the second factory is 1, and it fixed 1 robot.
//The total distance is |2 - 1| + |(-2) - (-1)| = 2. It can be shown that we cannot achieve a better
// total distance than 2.
//
//Constraints:
//1 <= robot.length, factory.length <= 100
//factory[j].length == 2
//-109 <= robot[i], positionj <= 109
//0 <= limitj <= robot.length
//The input will be generated such that it is always possible to repair every robot.
