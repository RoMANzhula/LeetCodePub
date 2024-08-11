package P1501_1600.P1568_Minimum_Number_of_Days_to_Disconnect_Island;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[][] grid1 = {
                {0, 1, 1, 0},
                {0, 1, 1, 0},
                {0, 0, 0, 0}
        };
        System.out.println(solution.minDays(grid1)); // Output: 2

        int[][] grid2 = {
                {1, 1}
        };
        System.out.println(solution.minDays(grid2)); // Output: 2

        int[][] grid3 = {
                {1, 0, 1, 0}
        };
        System.out.println(solution.minDays(grid3)); // Output: 0
    }

    public int minDays(int[][] grid) {
        if (!isConnected(grid)) return 0;

        int m = grid.length, n = grid[0].length;

        // Try changing one cell to water and check if the grid becomes disconnected
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    grid[i][j] = 0;
                    if (!isConnected(grid)) return 1;
                    grid[i][j] = 1; // Revert back
                }
            }
        }

        // Try changing two cells to water and check if the grid becomes disconnected
        return 2;
    }

    private boolean isConnected(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];

        int landCells = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    landCells++;
                }
            }
        }

        if (landCells == 0) return false;

        int components = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    if (++components > 1) return false;
                    dfs(grid, visited, i, j);
                }
            }
        }

        return components == 1;
    }

    private void dfs(int[][] grid, boolean[][] visited, int i, int j) {
        int m = grid.length, n = grid[0].length;
        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == 0 || visited[i][j]) return;

        visited[i][j] = true;
        dfs(grid, visited, i + 1, j);
        dfs(grid, visited, i - 1, j);
        dfs(grid, visited, i, j + 1);
        dfs(grid, visited, i, j - 1);
    }
}

//Explanation:
//isConnected: Checks if the grid is connected using DFS. If more than one connected component of land cells is
// found, the grid is already disconnected.
//DFS: The DFS is used to explore all connected land cells starting from any unvisited land cell.
//Main Logic: The minDays method tries changing each land cell to water and checks if the grid becomes
// disconnected. If changing one cell doesn't work, it concludes that two changes are necessary.
//Time Complexity:
//The time complexity is O((m * n)^2) in the worst case, where you might need to check every cell multiple times.
//This approach should work well within the constraints provided.


//You are given an m x n binary grid grid where 1 represents land and 0 represents water. An island is a
// maximal 4-directionally (horizontal or vertical) connected group of 1's.
//The grid is said to be connected if we have exactly one island, otherwise is said disconnected.
//In one day, we are allowed to change any single land cell (1) into a water cell (0).
//Return the minimum number of days to disconnect the grid.
//
//Example 1:
//Input: grid = [[0,1,1,0],[0,1,1,0],[0,0,0,0]]
//Output: 2
//Explanation: We need at least 2 days to get a disconnected grid.
//Change land grid[1][1] and grid[0][2] to water and get 2 disconnected island.

//Example 2:
//Input: grid = [[1,1]]
//Output: 2
//Explanation: Grid of full water is also disconnected ([[1,1]] -> [[0,0]]), 0 islands.
//
//Constraints:
//m == grid.length
//n == grid[i].length
//1 <= m, n <= 30
//grid[i][j] is either 0 or 1.