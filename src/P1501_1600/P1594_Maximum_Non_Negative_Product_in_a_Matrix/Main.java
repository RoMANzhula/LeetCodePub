package P1501_1600.P1594_Maximum_Non_Negative_Product_in_a_Matrix;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[][] grid = {
                {1, -2, 1},
                {1, -2, 1},
                {3, -4, 1}
        };

        int result = solution.maxProductPath(grid);
        System.out.println("Res: " + result);
    }

    public int maxProductPath(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        long[][] max = new long[m][n];
        long[][] min = new long[m][n];

        max[0][0] = grid[0][0];
        min[0][0] = grid[0][0];

        // first column
        for (int i = 1; i < m; i++) {
            long val = grid[i][0];
            max[i][0] = max[i - 1][0] * val;
            min[i][0] = max[i][0];
        }

        //first row
        for (int j = 1; j < n; j++) {
            long val = grid[0][j];
            max[0][j] = max[0][j - 1] * val;
            min[0][j] = max[0][j];
        }

        // fill DP
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                long val = grid[i][j];

                if (val == 0) {
                    max[i][j] = 0;
                    min[i][j] = 0;
                    continue;
                }

                long a = max[i - 1][j] * val;
                long b = min[i - 1][j] * val;
                long c = max[i][j - 1] * val;
                long d = min[i][j - 1] * val;

                max[i][j] = Math.max(Math.max(a, b), Math.max(c, d));
                min[i][j] = Math.min(Math.min(a, b), Math.min(c, d));
            }
        }

        long result = max[m - 1][n - 1];
        int MOD = 1_000_000_007;

        if (result < 0) return -1;

        return (int)(result % MOD);
    }

}

//Complexity:
// time and space - O(m * n)


//You are given a m x n matrix grid. Initially, you are located at the top-left corner (0, 0), and in each step, you
// can only move right or down in the matrix.
//Among all possible paths starting from the top-left corner (0, 0) and ending in the bottom-right
// corner (m - 1, n - 1), find the path with the maximum non-negative product. The product of a path is the product of
// all integers in the grid cells visited along the path.
//Return the maximum non-negative product modulo 109 + 7. If the maximum product is negative, return -1.
//Notice that the modulo is performed after getting the maximum product.

//Example 1:
//Input: grid = [[-1,-2,-3],[-2,-3,-3],[-3,-3,-2]]
//Output: -1
//Explanation: It is not possible to get non-negative product in the path from (0, 0) to (2, 2), so return -1.

//Example 2:
//Input: grid = [[1,-2,1],[1,-2,1],[3,-4,1]]
//Output: 8
//Explanation: Maximum non-negative product is shown (1 * 1 * -2 * -4 * 1 = 8).

//Example 3:
//Input: grid = [[1,3],[0,-4]]
//Output: 0
//Explanation: Maximum non-negative product is shown (1 * 0 * -4 = 0).

//Constraints:
//m == grid.length
//n == grid[i].length
//1 <= m, n <= 15
//-4 <= grid[i][j] <= 4
