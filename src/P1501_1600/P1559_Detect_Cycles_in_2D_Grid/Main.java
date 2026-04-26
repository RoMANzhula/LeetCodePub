package P1501_1600.P1559_Detect_Cycles_in_2D_Grid;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        char[][] grid1 = {
                {'a','a','a','a'},
                {'a','b','b','a'},
                {'a','b','b','a'},
                {'a','a','a','a'}
        };

        System.out.println(solution.containsCycle(grid1)); // true

        char[][] grid2 = {
                {'a','b','b'},
                {'b','z','b'},
                {'b','b','a'}
        };

        System.out.println(solution.containsCycle(grid2)); // false
    }

    private int[][] directions = {{0,1}, {1,0}, {0,-1}, {-1,0}};
    private int m, n;

    public boolean containsCycle(char[][] grid) {
        m = grid.length;
        n = grid[0].length;

        boolean[][] visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    if (dfs(grid, visited, i, j, -1, -1, grid[i][j])) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private boolean dfs(
            char[][] grid,
            boolean[][] visited,
            int x,
            int y,
            int parentX,
            int parentY,
            char target
    ) {

        visited[x][y] = true;

        for (int[] dir : directions) {
            int nx = x + dir[0];
            int ny = y + dir[1];

            if (nx < 0 || ny < 0 || nx >= m || ny >= n) continue;
            if (grid[nx][ny] != target) continue;

            // skip the parent cell
            if (nx == parentX && ny == parentY) continue;

            if (visited[nx][ny]) {
                return true; // found cycle
            }

            if (dfs(grid, visited, nx, ny, x, y, target)) {
                return true;
            }
        }

        return false;
    }

}

//Complexity:
// time and space - O(m * n)


//Given a 2D array of characters grid of size m x n, you need to find if there exists any cycle consisting of
// the same value in grid.
//A cycle is a path of length 4 or more in the grid that starts and ends at the same cell. From a given cell, you can
// move to one of the cells adjacent to it - in one of the four directions (up, down, left, or right), if it has the
// same value of the current cell.
//Also, you cannot move to the cell that you visited in your last move. For example, the
// cycle (1, 1) -> (1, 2) -> (1, 1) is invalid because from (1, 2) we visited (1, 1) which was the last visited cell.
//Return true if any cycle of the same value exists in grid, otherwise, return false.

//Example 1:
//Input: grid = [["a","a","a","a"],["a","b","b","a"],["a","b","b","a"],["a","a","a","a"]]
//Output: true
//Explanation: There are two valid cycles shown in different colors in the image below:

//Example 2:
//Input: grid = [["c","c","c","a"],["c","d","c","c"],["c","c","e","c"],["f","c","c","c"]]
//Output: true
//Explanation: There is only one valid cycle highlighted in the image below:

//Example 3:
//Input: grid = [["a","b","b"],["b","z","b"],["b","b","a"]]
//Output: false

//Constraints:
//m == grid.length
//n == grid[i].length
//1 <= m, n <= 500
//grid consists only of lowercase English letters.
