package P1201_1300.P1254_Number_of_Closed_Islands;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[][] grid1 = {
                {1, 1, 1, 1, 1, 1, 1, 0},
                {1, 0, 0, 0, 0, 1, 1, 0},
                {1, 0, 1, 0, 1, 1, 1, 0},
                {1, 0, 0, 0, 0, 1, 0, 1},
                {1, 1, 1, 1, 1, 1, 1, 0}
        };

        int[][] grid2 = {
                {0, 0, 1, 0, 0},
                {0, 1, 0, 1, 0},
                {0, 1, 1, 1, 0}
        };

        int[][] grid3 = {
                {1, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 0, 0, 0, 1},
                {1, 0, 1, 1, 1, 0, 1},
                {1, 0, 1, 0, 1, 0, 1},
                {1, 0, 1, 1, 1, 0, 1},
                {1, 0, 0, 0, 0, 0, 1},
                {1, 1, 1, 1, 1, 1, 1}
        };

        System.out.println(solution.closedIsland(grid1)); // 2
        System.out.println(solution.closedIsland(grid2)); // 1
        System.out.println(solution.closedIsland(grid3)); // 2
    }

    private int rows;
    private int cols;

    public int closedIsland(int[][] grid) {
        rows = grid.length;
        cols = grid[0].length;

        for (int r = 0; r < rows; r++) {
            dfs(grid, r, 0);
            dfs(grid, r, cols - 1);
        }

        for (int c = 0; c < cols; c++) {
            dfs(grid, 0, c);
            dfs(grid, rows - 1, c);
        }

        int count = 0;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 0) {
                    dfs(grid, r, c);
                    count++;
                }
            }
        }

        return count;
    }

    private void dfs(int[][] grid, int r, int c) {
        if (r < 0 || r >= rows || c < 0 || c >= cols
                || grid[r][c] == 1) {
            return;
        }

        grid[r][c] = 1;

        dfs(grid, r - 1, c);
        dfs(grid, r + 1, c);
        dfs(grid, r, c - 1);
        dfs(grid, r, c + 1);
    }

}

//Complexity:
// time and space - O(m * n)     m - number of rows, n - number of columns


//Given a 2D grid consists of 0s (land) and 1s (water).  An island is a maximal 4-directionally connected group of
// 0s and a closed island is an island totally (all left, top, right, bottom) surrounded by 1s.
//Return the number of closed islands.

//Example 1:
// here image
//Input: grid = [[1,1,1,1,1,1,1,0],[1,0,0,0,0,1,1,0],[1,0,1,0,1,1,1,0],[1,0,0,0,0,1,0,1],[1,1,1,1,1,1,1,0]]
//Output: 2
//Explanation:
//Islands in gray are closed because they are completely surrounded by water (group of 1s).

//Example 2:
// here image
//Input: grid = [[0,0,1,0,0],[0,1,0,1,0],[0,1,1,1,0]]
//Output: 1

//Example 3:
//Input: grid = [[1,1,1,1,1,1,1],
//               [1,0,0,0,0,0,1],
//               [1,0,1,1,1,0,1],
//               [1,0,1,0,1,0,1],
//               [1,0,1,1,1,0,1],
//               [1,0,0,0,0,0,1],
//               [1,1,1,1,1,1,1]]
//Output: 2

//Constraints:
//1 <= grid.length, grid[0].length <= 100
//0 <= grid[i][j] <=1
