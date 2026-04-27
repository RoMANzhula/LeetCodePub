package P1301_1400.P1391_Check_ifThere_is_a_Valid_Path_in_a_Grid;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[][] grid1 = {{2,4,3},{6,5,2}};
        System.out.println(solution.hasValidPath(grid1)); // true

        int[][] grid2 = {{1,2,1},{1,2,1}};
        System.out.println(solution.hasValidPath(grid2)); // false

        int[][] grid3 = {{1,1,2}};
        System.out.println(solution.hasValidPath(grid3)); // false
    }

    // directions: up, down, left, right
    private static final int[][] DIRS = {
            {-1, 0}, // up
            {1, 0},  // down
            {0, -1}, // left
            {0, 1}   // right
    };

    // for each type, which directions are allowed
    private static final Map<Integer, int[]> STREET_MAP = new HashMap<>();

    static {
        STREET_MAP.put(1, new int[]{2, 3}); // left, right
        STREET_MAP.put(2, new int[]{0, 1}); // up, down
        STREET_MAP.put(3, new int[]{2, 1}); // left, down
        STREET_MAP.put(4, new int[]{3, 1}); // right, down
        STREET_MAP.put(5, new int[]{2, 0}); // left, up
        STREET_MAP.put(6, new int[]{3, 0}); // right, up
    }

    public boolean hasValidPath(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        boolean[][] visited = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int x = curr[0], y = curr[1];

            if (x == m - 1 && y == n - 1) return true;

            int type = grid[x][y];

            for (int dir : STREET_MAP.get(type)) {
                int nx = x + DIRS[dir][0];
                int ny = y + DIRS[dir][1];

                if (nx < 0 || ny < 0 || nx >= m || ny >= n || visited[nx][ny])
                    continue;

                int nextType = grid[nx][ny];

                //check reverse direction
                int reverseDir = getReverse(dir);

                if (canConnect(nextType, reverseDir)) {
                    visited[nx][ny] = true;
                    queue.offer(new int[]{nx, ny});
                }
            }
        }

        return false;
    }

    private int getReverse(int dir) {
        if (dir == 0) return 1; // up -> down
        if (dir == 1) return 0; // down -> up
        if (dir == 2) return 3; // left -> right

        return 2;               // right -> left
    }

    private boolean canConnect(int type, int neededDir) {
        for (int dir : STREET_MAP.get(type)) {
            if (dir == neededDir) return true;
        }

        return false;
    }

}

//Complexity:
// time and space - O(m * n)


//You are given an m x n grid. Each cell of grid represents a street. The street of grid[i][j] can be:
//1 which means a street connecting the left cell and the right cell.
//2 which means a street connecting the upper cell and the lower cell.
//3 which means a street connecting the left cell and the lower cell.
//4 which means a street connecting the right cell and the lower cell.
//5 which means a street connecting the left cell and the upper cell.
//6 which means a street connecting the right cell and the upper cell.
//You will initially start at the street of the upper-left cell (0, 0). A valid path in the grid is a path that starts
// from the upper left cell (0, 0) and ends at the bottom-right cell (m - 1, n - 1). The path should only follow
// the streets.
//Notice that you are not allowed to change any street.
//Return true if there is a valid path in the grid or false otherwise.

//Example 1:
//Input: grid = [[2,4,3],[6,5,2]]
//Output: true
//Explanation: As shown you can start at cell (0, 0) and visit all the cells of the grid to reach (m - 1, n - 1).

//Example 2:
//Input: grid = [[1,2,1],[1,2,1]]
//Output: false
//Explanation: As shown you the street at cell (0, 0) is not connected with any street of any other cell and you
// will get stuck at cell (0, 0)

//Example 3:
//Input: grid = [[1,1,2]]
//Output: false
//Explanation: You will get stuck at cell (0, 1) and you cannot reach cell (0, 2).

//Constraints:
//m == grid.length
//n == grid[i].length
//1 <= m, n <= 300
//1 <= grid[i][j] <= 6
