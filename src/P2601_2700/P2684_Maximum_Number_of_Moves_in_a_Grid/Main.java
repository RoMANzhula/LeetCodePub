package P2601_2700.P2684_Maximum_Number_of_Moves_in_a_Grid;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[][] grid1 = {
                {2, 4, 3, 5},
                {5, 4, 9, 3},
                {3, 4, 2, 11},
                {10, 9, 13, 15}
        };
        System.out.println("Example 1 Output: " + solution.maxMoves(grid1)); // Expected: 3

        int[][] grid2 = {
                {3, 2, 4},
                {2, 1, 9},
                {1, 1, 7}
        };
        System.out.println("Example 2 Output: " + solution.maxMoves(grid2)); // Expected: 0

    }

    public int maxMoves(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int maxMoves = 0;
        Integer[][] memo = new Integer[m][n];

        for (int row = 0; row < m; row++) {
            maxMoves = Math.max(maxMoves, dfs(grid, row, 0, memo));
        }

        return maxMoves;
    }

    private int dfs(int[][] grid, int row, int col, Integer[][] memo) {
        if (col == grid[0].length - 1) {
            return 0; // last column, no further moves possible
        }
        if (memo[row][col] != null) {
            return memo[row][col]; // return cached result
        }

        int maxMoves = 0;
        int currentValue = grid[row][col];

        // Check the three possible moves
        // Move to (row - 1, col + 1) if within bounds and greater value
        if (row > 0 && grid[row - 1][col + 1] > currentValue) {
            maxMoves = Math.max(maxMoves, 1 + dfs(grid, row - 1, col + 1, memo));
        }
        // Move to (row, col + 1)
        if (grid[row][col + 1] > currentValue) {
            maxMoves = Math.max(maxMoves, 1 + dfs(grid, row, col + 1, memo));
        }
        // Move to (row + 1, col + 1)
        if (row < grid.length - 1 && grid[row + 1][col + 1] > currentValue) {
            maxMoves = Math.max(maxMoves, 1 + dfs(grid, row + 1, col + 1, memo));
        }

        memo[row][col] = maxMoves; // store result in memo array
        return maxMoves;
    }

}

//Explanation of the Code:
//maxMoves function: Loops through each row in the first column and calls dfs to calculate the
// maximum moves starting from that cell.
//dfs function:
//Checks if the current cell is in the last column. If so, no moves can be made, so it returns 0.
//Checks the memo array to see if the maximum moves from the current cell have already been calculated.
//Tries moving to each of the three possible cells to the right and updates maxMoves based on the DFS results.
//Memoizes the result for the current cell to avoid redundant calculations in future DFS calls.
//Complexity Analysis
//Time Complexity:
//O(m×n), as each cell is visited once due to memoization.
//Space Complexity:
//O(m×n), for the memoization table.


//You are given a 0-indexed m x n matrix grid consisting of positive integers.
//You can start at any cell in the first column of the matrix, and traverse the grid in the following way:
//From a cell (row, col), you can move to any of the cells: (row - 1, col + 1), (row, col + 1) and
// (row + 1, col + 1) such that the value of the cell you move to, should be strictly bigger than
// the value of the current cell.
//Return the maximum number of moves that you can perform.
//
//Example 1:
//Input: grid = [[2,4,3,5],[5,4,9,3],[3,4,2,11],[10,9,13,15]]
//Output: 3
//Explanation: We can start at the cell (0, 0) and make the following moves:
//- (0, 0) -> (0, 1).
//- (0, 1) -> (1, 2).
//- (1, 2) -> (2, 3).
//It can be shown that it is the maximum number of moves that can be made.

//Example 2:
//Input: grid = [[3,2,4],[2,1,9],[1,1,7]]
//Output: 0
//Explanation: Starting from any cell in the first column we cannot perform any moves.
//
//Constraints:
//m == grid.length
//n == grid[i].length
//2 <= m, n <= 1000
//4 <= m * n <= 105
//1 <= grid[i][j] <= 106
