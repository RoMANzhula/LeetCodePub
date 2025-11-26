package P2401_2500.P2435_Paths_in_Matrix_Whose_Sum_Is_Divisible_by_K;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[][] grid1 = {{5,2,4},{3,0,5},{0,7,2}};
        System.out.println(solution.numberOfPaths(grid1, 3)); // 2

        int[][] grid2 = {{0,0}};
        System.out.println(solution.numberOfPaths(grid2, 5)); // 1

        int[][] grid3 = {{7,3,4,9},{2,3,6,2},{2,3,7,0}};
        System.out.println(solution.numberOfPaths(grid3, 1)); // 10
    }


    static final int MOD = 1_000_000_007;

    public int numberOfPaths(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;

        // dp[j][r] represents for current row -> column j -> remainder r
        int[][] dpPrev = new int[n][k];
        int[][] dpCurr = new int[n][k];

        // initialize first cell
        dpPrev[0][ grid[0][0] % k ] = 1;

        // fiirst row (only from left)
        for (int j = 1; j < n; j++) {
            for (int r = 0; r < k; r++) {
                if (dpPrev[j-1][r] > 0) {
                    int newR = (r + grid[0][j]) % k;
                    dpPrev[j][newR] = (dpPrev[j][newR] + dpPrev[j-1][r]) % MOD;
                }
            }
        }

        // process all other rows
        for (int i = 1; i < m; i++) {
            // clear dpCurr
            for (int[] row : dpCurr) Arrays.fill(row, 0);

            // first column (only from top)
            for (int r = 0; r < k; r++) {
                if (dpPrev[0][r] > 0) {
                    int newR = (r + grid[i][0]) % k;
                    dpCurr[0][newR] = (dpCurr[0][newR] + dpPrev[0][r]) % MOD;
                }
            }

            //  other columns (from left + top)
            for (int j = 1; j < n; j++) {
                for (int r = 0; r < k; r++) {
                    if (dpCurr[j-1][r] > 0) {
                        int newR = (r + grid[i][j]) % k;
                        dpCurr[j][newR] = (dpCurr[j][newR] + dpCurr[j-1][r]) % MOD;
                    }
                    if (dpPrev[j][r] > 0) {
                        int newR = (r + grid[i][j]) % k;
                        dpCurr[j][newR] = (dpCurr[j][newR] + dpPrev[j][r]) % MOD;
                    }
                }
            }

            // move dpCurr → dpPrev for next row
            int[][] tmp = dpPrev;
            dpPrev = dpCurr;
            dpCurr = tmp;
        }

        // result is remainder zero at bottom-right
        return dpPrev[n-1][0];
    }

}

//Complexity:
// time - O(m * n * k)
// space - O(n * k)


//You are given a 0-indexed m x n integer matrix grid and an integer k. You are currently at position (0, 0) and you
// want to reach position (m - 1, n - 1) moving only down or right.
//Return the number of paths where the sum of the elements on the path is divisible by k. Since the answer may be
// very large, return it modulo 109 + 7.

//Example 1:
//Input: grid = [[5,2,4],[3,0,5],[0,7,2]], k = 3
//Output: 2
//Explanation: There are two paths where the sum of the elements on the path is divisible by k.
//The first path highlighted in red has a sum of 5 + 2 + 4 + 5 + 2 = 18 which is divisible by 3.
//The second path highlighted in blue has a sum of 5 + 3 + 0 + 5 + 2 = 15 which is divisible by 3.

//Example 2:
//Input: grid = [[0,0]], k = 5
//Output: 1
//Explanation: The path highlighted in red has a sum of 0 + 0 = 0 which is divisible by 5.

//Example 3:
//Input: grid = [[7,3,4,9],[2,3,6,2],[2,3,7,0]], k = 1
//Output: 10
//Explanation: Every integer is divisible by 1 so the sum of the elements on every possible path is divisible by k.

//Constraints:
//m == grid.length
//n == grid[i].length
//1 <= m, n <= 5 * 104
//1 <= m * n <= 5 * 104
//0 <= grid[i][j] <= 100
//1 <= k <= 50
