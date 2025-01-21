package P2001_2100.P2017_Grid_Game;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[][] grid1 = {{2, 5, 4}, {1, 5, 1}};
        System.out.println(solution.gridGame(grid1)); // Output: 4

        int[][] grid2 = {{3, 3, 1}, {8, 5, 2}};
        System.out.println(solution.gridGame(grid2)); // Output: 4

        int[][] grid3 = {{1, 3, 1, 15}, {1, 3, 3, 1}};
        System.out.println(solution.gridGame(grid3)); // Output: 7
    }

    public long gridGame(int[][] grid) {
        int n = grid[0].length;

        // Calculate prefix sums for rows
        long[] prefixTop = new long[n];
        long[] prefixBottom = new long[n];

        // Fill prefix sums for the top row
        prefixTop[0] = grid[0][0];
        for (int i = 1; i < n; i++) {
            prefixTop[i] = prefixTop[i - 1] + grid[0][i];
        }

        // Fill prefix sums for the bottom row
        prefixBottom[0] = grid[1][0];
        for (int i = 1; i < n; i++) {
            prefixBottom[i] = prefixBottom[i - 1] + grid[1][i];
        }

        long result = Long.MAX_VALUE;

        // Iterate over columns to decide where the first robot switches rows
        for (int i = 0; i < n; i++) {
            long pointsTop = (i == n - 1) ? 0 : prefixTop[n - 1] - prefixTop[i];
            long pointsBottom = (i == 0) ? 0 : prefixBottom[i - 1];

            // Calculate the maximum points the second robot can collect
            long secondRobotPoints = Math.max(pointsTop, pointsBottom);

            // Minimize the points collected by the second robot
            result = Math.min(result, secondRobotPoints);
        }

        return (long) result;
    }
}

//Explanation:
//Prefix Sums:
//prefixTop[i] gives the sum of the first i+1 elements in the top row.
//prefixBottom[i] gives the sum of the first i+1 elements in the bottom row.
//Switch Point Decision:
//For each column i, compute the points left for the second robot if the first robot switches rows at column i.
//Calculate the maximum points the second robot can collect and minimize this value.
//Result:
//The minimum of all maximum points is the answer, ensuring the first robot optimally minimizes the
// second robot's score.
//Complexity:
//Time Complexity:
//O(n)
//Space Complexity:
//O(n)


//You are given a 0-indexed 2D array grid of size 2 x n, where grid[r][c] represents the number of points at
// position (r, c) on the matrix. Two robots are playing a game on this matrix.
//Both robots initially start at (0, 0) and want to reach (1, n-1). Each robot may only move to the
// right ((r, c) to (r, c + 1)) or down ((r, c) to (r + 1, c)).
//At the start of the game, the first robot moves from (0, 0) to (1, n-1), collecting all the points from the
// cells on its path. For all cells (r, c) traversed on the path, grid[r][c] is set to 0. Then, the second
// robot moves from (0, 0) to (1, n-1), collecting the points on its path. Note that their paths may
// intersect with one another.
//The first robot wants to minimize the number of points collected by the second robot. In contrast, the second
// robot wants to maximize the number of points it collects. If both robots play optimally, return the number of
// points collected by the second robot.
//
//Example 1:
//Input: grid = [[2,5,4],[1,5,1]]
//Output: 4
//Explanation: The optimal path taken by the first robot is shown in red, and the optimal path taken by the
// second robot is shown in blue.
//The cells visited by the first robot are set to 0.
//The second robot will collect 0 + 0 + 4 + 0 = 4 points.

//Example 2:
//Input: grid = [[3,3,1],[8,5,2]]
//Output: 4
//Explanation: The optimal path taken by the first robot is shown in red, and the optimal path taken by the
// second robot is shown in blue.
//The cells visited by the first robot are set to 0.
//The second robot will collect 0 + 3 + 1 + 0 = 4 points.

//Example 3:
//Input: grid = [[1,3,1,15],[1,3,3,1]]
//Output: 7
//Explanation: The optimal path taken by the first robot is shown in red, and the optimal path taken by the second
// robot is shown in blue.
//The cells visited by the first robot are set to 0.
//The second robot will collect 0 + 1 + 3 + 3 + 0 = 7 points.
//
//Constraints:
//grid.length == 2
//n == grid[r].length
//1 <= n <= 5 * 104
//1 <= grid[r][c] <= 105
