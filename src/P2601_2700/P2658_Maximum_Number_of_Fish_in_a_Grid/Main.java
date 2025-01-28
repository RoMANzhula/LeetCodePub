package P2601_2700.P2658_Maximum_Number_of_Fish_in_a_Grid;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[][] grid1 = {
                {0, 2, 1, 0},
                {4, 0, 0, 3},
                {1, 0, 0, 4},
                {0, 3, 2, 0}
        };
        System.out.println(solution.findMaxFish(grid1)); // Output: 7

        int[][] grid2 = {
                {1, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 1}
        };
        System.out.println(solution.findMaxFish(grid2)); // Output: 1
    }

    public int findMaxFish(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        int maxFish = 0;

        // directions for moving in the Grid
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        // traverse the grid
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // start DFS from each water cell that is not visited
                if (grid[i][j] > 0 && !visited[i][j]) {
                    maxFish = Math.max(maxFish, dfs(grid, visited, i, j, m, n, directions));
                }
            }
        }

        return maxFish;
    }

    // dfs helper function
    int dfs(int[][] grid, boolean[][] visited, int r, int c, int m, int n, int[][] directions) {
        // base case: Out of bounds or already visited or land cell
        if (r < 0 || r >= m || c < 0 || c >= n || visited[r][c] || grid[r][c] == 0) {
            return 0;
        }

        // mark the cell as visited
        visited[r][c] = true;

        // start with the fish at the current cell
        int fish = grid[r][c];

        // visit all adjacent cells
        for (int[] dir : directions) {
            int newRow = r + dir[0];
            int newCol = c + dir[1];
            fish += dfs(grid, visited, newRow, newCol, m, n, directions);
        }

        return fish;
    }

}

//Explanation:
//DFS Traversal:
//-From each water cell, recursively visit all its connected water cells, collecting fish as you go.
//-Use a visited matrix to ensure no cell is processed more than once in a single traversal.
//Tracking Maximum Fish:
//For each connected component of water cells, compute the total fish collected and update maxFish if the current
// component gives a higher total.
//Grid Constraints:
//Since the grid size is at most
//10×10, the algorithm (O(m * n)) is efficient and handles all possible cases within the constraints.
//Complexity:
//Time Complexity: O(m×n)
//Space Complexity: O(m×n)


//You are given a 0-indexed 2D matrix grid of size m x n, where (r, c) represents:
//A land cell if grid[r][c] = 0, or
//A water cell containing grid[r][c] fish, if grid[r][c] > 0.
//A fisher can start at any water cell (r, c) and can do the following operations any number of times:
//Catch all the fish at cell (r, c), or
//Move to any adjacent water cell.
//Return the maximum number of fish the fisher can catch if he chooses his starting cell optimally,
// or 0 if no water cell exists.
//An adjacent cell of the cell (r, c), is one of the cells (r, c + 1), (r, c - 1), (r + 1, c) or (r - 1, c) if it exists.
//
//Example 1:
//Input: grid = [[0,2,1,0],[4,0,0,3],[1,0,0,4],[0,3,2,0]]
//Output: 7
//Explanation: The fisher can start at cell (1,3) and collect 3 fish, then move to cell (2,3) and collect 4 fish.

//Example 2:
//Input: grid = [[1,0,0,0],[0,0,0,0],[0,0,0,0],[0,0,0,1]]
//Output: 1
//Explanation: The fisher can start at cells (0,0) or (3,3) and collect a single fish.
//
//Constraints:
//m == grid.length
//n == grid[i].length
//1 <= m, n <= 10
//0 <= grid[i][j] <= 10
