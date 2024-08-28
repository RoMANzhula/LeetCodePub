package P1901_2000.P1905_Count_Sub_Islands;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[][] grid1 = {
                {1, 1, 1, 0, 0},
                {0, 1, 1, 1, 1},
                {0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0},
                {1, 1, 0, 1, 1}
        };

        int[][] grid2 = {
                {1, 1, 1, 0, 0},
                {0, 0, 1, 1, 1},
                {0, 1, 0, 0, 0},
                {1, 0, 1, 1, 0},
                {0, 1, 0, 1, 0}
        };

        System.out.println(solution.countSubIslands(grid1, grid2)); // Output: 3
    }

    public int countSubIslands(int[][] grid1, int[][] grid2) {
        int m = grid1.length;
        int n = grid1[0].length;
        int count = 0;

        // Traverse the entire grid2
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // If we find a land cell in grid2, start a DFS
                if (grid2[i][j] == 1) {
                    // Perform DFS and check if this is a valid sub-island
                    if (dfs(grid1, grid2, i, j)) {
                        count++;
                    }
                }
            }
        }

        return count;
    }

    private boolean dfs(int[][] grid1, int[][] grid2, int i, int j) {
        int m = grid1.length;
        int n = grid1[0].length;

        // Check if we're out of bounds or at a water cell
        if (i < 0 || i >= m || j < 0 || j >= n || grid2[i][j] == 0) {
            return true;
        }

        // Mark the cell as visited by setting it to 0
        grid2[i][j] = 0;

        // Check if this cell is part of a valid sub-island (must be land in grid1)
        boolean isSubIsland = grid1[i][j] == 1;

        // Explore all 4 directions
        isSubIsland &= dfs(grid1, grid2, i + 1, j);
        isSubIsland &= dfs(grid1, grid2, i - 1, j);
        isSubIsland &= dfs(grid1, grid2, i, j + 1);
        isSubIsland &= dfs(grid1, grid2, i, j - 1);

        return isSubIsland;
    }
}

//Explanation:
//DFS Implementation: The dfs method recursively explores all connected land cells (1's) in grid2. If any part of
// the land in grid2 doesn't match the corresponding land in grid1, the island is not a sub-island.
//Main Function: The countSubIslands method iterates over all cells in grid2. If it finds a land cell, it
// triggers a DFS to check if it forms a sub-island. If it does, the count is incremented.
//Edge Cases: The implementation handles edge cases such as no islands in grid2 or all sub-islands being invalid.
//This solution efficiently counts sub-islands with a time complexity of O(m×n), where m and n are the
// dimensions of the grid.


//You are given two m x n binary matrices grid1 and grid2 containing only 0's (representing water) and
// 1's (representing land). An island is a group of 1's connected 4-directionally (horizontal or vertical). Any
// cells outside of the grid are considered water cells.
//An island in grid2 is considered a sub-island if there is an island in grid1 that contains all the cells that
// make up this island in grid2.
//Return the number of islands in grid2 that are considered sub-islands.
//
//Example 1:
//Input: grid1 = [[1,1,1,0,0],[0,1,1,1,1],[0,0,0,0,0],[1,0,0,0,0],[1,1,0,1,1]], grid2 =
// [[1,1,1,0,0],[0,0,1,1,1],[0,1,0,0,0],[1,0,1,1,0],[0,1,0,1,0]]
//Output: 3
//Explanation: In the picture above, the grid on the left is grid1 and the grid on the right is grid2.
//The 1s colored red in grid2 are those considered to be part of a sub-island. There are three sub-islands.

//Example 2:
//Input: grid1 = [[1,0,1,0,1],[1,1,1,1,1],[0,0,0,0,0],[1,1,1,1,1],[1,0,1,0,1]], grid2 =
// [[0,0,0,0,0],[1,1,1,1,1],[0,1,0,1,0],[0,1,0,1,0],[1,0,0,0,1]]
//Output: 2
//Explanation: In the picture above, the grid on the left is grid1 and the grid on the right is grid2.
//The 1s colored red in grid2 are those considered to be part of a sub-island. There are two sub-islands.
//
//Constraints:
//m == grid1.length == grid2.length
//n == grid1[i].length == grid2[i].length
//1 <= m, n <= 500
//grid1[i][j] and grid2[i][j] are either 0 or 1.
