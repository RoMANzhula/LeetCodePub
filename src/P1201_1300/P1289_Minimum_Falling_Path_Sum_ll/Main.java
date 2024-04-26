package P1201_1300.P1289_Minimum_Falling_Path_Sum_ll;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[][] grid1 = {{1,2,3},{4,5,6},{7,8,9}};
        System.out.println("Output for Example 1: " + solution.minFallingPathSum(grid1)); // Output: 13

        int[][] grid2 = {{7}};
        System.out.println("Output for Example 2: " + solution.minFallingPathSum(grid2)); // Output: 7
    }

    public int minFallingPathSum(int[][] grid) {
        int n = grid.length;

        // Create a dp array to store the minimum falling path sum
        int[][] dp = new int[n][n];

        // Copy the first row of the grid to dp
        System.arraycopy(grid[0], 0, dp[0], 0, n);
        //analog Copy
//        for (int j = 0; j < n; j++) {
//            dp[0][j] = grid[0][j];
//        }

        // Iterate through the remaining rows
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // Initialize minSum to the maximum possible integer value
                int minSum = Integer.MAX_VALUE;
                // Iterate through each column in the previous row
                for (int k = 0; k < n; k++) {
                    if (j != k) {
                        // Find the minimum falling path sum ending at the current cell
                        minSum = Math.min(minSum, dp[i - 1][k]);
                    }
                }
                // Update the dp array with the minimum falling path sum
                dp[i][j] = grid[i][j] + minSum;
            }
        }

        // Find the minimum falling path sum in the last row of dp
        int minPathSum = Integer.MAX_VALUE;
        for (int j = 0; j < n; j++) {
            minPathSum = Math.min(minPathSum, dp[n - 1][j]);
        }

        return minPathSum;
    }
}

//Given an n x n integer matrix grid, return the minimum sum of a falling path with non-zero shifts.
//A falling path with non-zero shifts is a choice of exactly one element from each row of grid such that no two
// elements chosen in adjacent rows are in the same column.
//
//Example 1:
//Input: grid = [[1,2,3],[4,5,6],[7,8,9]]
//Output: 13
//Explanation:
//The possible falling paths are:
//[1,5,9], [1,5,7], [1,6,7], [1,6,8],
//[2,4,8], [2,4,9], [2,6,7], [2,6,8],
//[3,4,8], [3,4,9], [3,5,7], [3,5,9]
//The falling path with the smallest sum is [1,5,7], so the answer is 13.

//Example 2:
//Input: grid = [[7]]
//Output: 7
//
//Constraints:
//n == grid.length == grid[i].length
//1 <= n <= 200
//-99 <= grid[i][j] <= 99
