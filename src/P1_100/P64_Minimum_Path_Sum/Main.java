package P1_100.P64_Minimum_Path_Sum;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[][] grid1 = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        System.out.println("Output for Example 1: " + solution.minPathSum(grid1)); //output 7

        int[][] grid2 = {{1, 2, 3}, {4, 5, 6}};
        System.out.println("Output for Example 2: " + solution.minPathSum(grid2)); //output 12
    }

    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // create a dp array to store the minimum path sum
        int[][] dp = new int[m][n];

        // initialize the dp array with the values of the first row and first column
        dp[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int j = 1; j < n; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }

        // fill the dp array by finding the minimum path sum for each cell
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }

        // return the value of the bottom-right cell, which represents the minimum path sum
        return dp[m - 1][n - 1];
    }
}

//Задачу вирішуємо за допомогою динамічного програмування, зокрема, використовуючи метод "знизу вгору".
//У цьому рішенні ми проходимо крізь кожну клітинку сітки, обчислюючи мінімальну суму шляху до цієї
// клітинки, враховуючи мінімальну суму шляху з клітинок зверху та зліва. Нарешті, ми повертаємо значення
// останньої клітинки, яке представляє собою мінімальну суму шляху від верхньої лівої точки до нижньої правої.

//Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right, which
// minimizes the sum of all numbers along its path.
//Note: You can only move either down or right at any point in time.
//
//Example 1:
//Input: grid = [[1,3,1],[1,5,1],[4,2,1]]
//Output: 7
//Explanation: Because the path 1 → 3 → 1 → 1 → 1 minimizes the sum.

//Example 2:
//Input: grid = [[1,2,3],[4,5,6]]
//Output: 12
//
//Constraints:
//m == grid.length
//n == grid[i].length
//1 <= m, n <= 200
//0 <= grid[i][j] <= 200
