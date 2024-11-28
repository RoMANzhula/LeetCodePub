package P2201_2300.P2290_Minimun_Obstacle_Removal_to_Reach_Corner;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[][] grid1 = {{0, 1, 1}, {1, 1, 0}, {1, 1, 0}};
        System.out.println(solution.minimumObstacles(grid1)); // Output: 2

        int[][] grid2 = {{0, 1, 0, 0, 0}, {0, 1, 0, 1, 0}, {0, 0, 0, 1, 0}};
        System.out.println(solution.minimumObstacles(grid2)); // Output: 0
    }

    public int minimumObstacles(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // Directions for moving up, down, left, right
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        // A deque to store positions and their obstacle count
        Deque<int[]> deque = new ArrayDeque<>();
        int[][] dist = new int[m][n];

        // Initialize distance array with a large value
        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        // Start from the top-left corner
        dist[0][0] = 0;
        deque.offerFirst(new int[]{0, 0, 0}); // {row, col, obstacles}

        while (!deque.isEmpty()) {
            int[] current = deque.pollFirst();
            int x = current[0];
            int y = current[1];
            int obstacles = current[2];

            if (x == m - 1 && y == n - 1) {
                return obstacles; // Reached bottom-right corner
            }

            // Explore neighbors
            for (int[] dir : directions) {
                int nx = x + dir[0];
                int ny = y + dir[1];

                if (nx >= 0 && ny >= 0 && nx < m && ny < n) {
                    int newObstacles = obstacles + grid[nx][ny];
                    if (newObstacles < dist[nx][ny]) {
                        dist[nx][ny] = newObstacles;
                        if (grid[nx][ny] == 1) {
                            deque.offerLast(new int[]{nx, ny, newObstacles}); // Add to the back
                        } else {
                            deque.offerFirst(new int[]{nx, ny, newObstacles}); // Add to the front
                        }
                    }
                }
            }
        }

        return -1; // No path found
    }
}

//Explanation:
//Initialization:
//A dist array tracks the minimum obstacles removed to reach each cell.
//The deque processes cells based on their obstacle count.
//Processing:
//Cells with grid[x][y] == 0 (no obstacle) are prioritized using offerFirst.
//Cells with grid[x][y] == 1 (obstacle) are processed later using offerLast.
//Termination:
//The algorithm stops when reaching the bottom-right corner (m - 1, n - 1).
//Complexity:
//Time Complexity: O(m×n), as each cell is processed once.
//Space Complexity: O(m×n), for the dist array and deque storage.


//You are given a 0-indexed 2D integer array grid of size m x n. Each cell has one of two values:
//0 represents an empty cell,
//1 represents an obstacle that may be removed.
//You can move up, down, left, or right from and to an empty cell.
//Return the minimum number of obstacles to remove so you can move from the upper left corner (0, 0) to the
// lower right corner (m - 1, n - 1).
//
//Example 1:
//Input: grid = [[0,1,1],[1,1,0],[1,1,0]]
//Output: 2
//Explanation: We can remove the obstacles at (0, 1) and (0, 2) to create a path from (0, 0) to (2, 2).
//It can be shown that we need to remove at least 2 obstacles, so we return 2.
//Note that there may be other ways to remove 2 obstacles to create a path.

//Example 2:
//Input: grid = [[0,1,0,0,0],[0,1,0,1,0],[0,0,0,1,0]]
//Output: 0
//Explanation: We can move from (0, 0) to (2, 4) without removing any obstacles, so we return 0.
//
//Constraints:
//m == grid.length
//n == grid[i].length
//1 <= m, n <= 105
//2 <= m * n <= 105
//grid[i][j] is either 0 or 1.
//grid[0][0] == grid[m - 1][n - 1] == 0
