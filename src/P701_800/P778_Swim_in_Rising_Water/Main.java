package P701_800.P778_Swim_in_Rising_Water;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[][] grid1 = {{0,2},{1,3}};
        System.out.println(solution.swimInWater(grid1)); // Output: 3

        int[][] grid2 = {
                {0,1,2,3,4},
                {24,23,22,21,5},
                {12,13,14,15,16},
                {11,17,18,19,20},
                {10,9,8,7,6}
        };
        System.out.println(solution.swimInWater(grid2)); // Output: 16
    }

    public int swimInWater(int[][] grid) {
        int n = grid.length;
        int[][] directions = {{1,0}, {-1,0}, {0,1}, {0,-1}};
        boolean[][] visited = new boolean[n][n];

        // min-heap: [time, row, col]
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.offer(new int[]{grid[0][0], 0, 0});
        visited[0][0] = true;

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int time = cur[0], r = cur[1], c = cur[2];

            // If we reached bottom-right, return time
            if (r == n - 1 && c == n - 1) return time;

            for (int[] d : directions) {
                int nr = r + d[0];
                int nc = c + d[1];

                if (nr >= 0 && nr < n && nc >= 0 && nc < n && !visited[nr][nc]) {
                    visited[nr][nc] = true;

                    // max of current water level and next cell's height
                    pq.offer(new int[]{Math.max(time, grid[nr][nc]), nr, nc});
                }
            }
        }

        return -1; // should never reach here
    }

}

//Complexity:
// time - O(n^2 log n)
// space - O(n^2)


//You are given an n x n integer matrix grid where each value grid[i][j] represents the elevation at that point (i, j).
//It starts raining, and water gradually rises over time. At time t, the water level is t, meaning any cell with
// elevation less than equal to t is submerged or reachable.
//You can swim from a square to another 4-directionally adjacent square if and only if the elevation of both squares
// individually are at most t. You can swim infinite distances in zero time. Of course, you must stay within the
// boundaries of the grid during your swim.
//Return the minimum time until you can reach the bottom right square (n - 1, n - 1) if you start at the top
// left square (0, 0).

//Example 1:
//Input: grid = [[0,2],[1,3]]
//Output: 3
//Explanation:
//At time 0, you are in grid location (0, 0).
//You cannot go anywhere else because 4-directionally adjacent neighbors have a higher elevation than t = 0.
//You cannot reach point (1, 1) until time 3.
//When the depth of water is 3, we can swim anywhere inside the grid.

//Example 2:
//Input: grid = [[0,1,2,3,4],[24,23,22,21,5],[12,13,14,15,16],[11,17,18,19,20],[10,9,8,7,6]]
//Output: 16
//Explanation: The final route is shown.
//We need to wait until time 16 so that (0, 0) and (4, 4) are connected.

//Constraints:
//n == grid.length
//n == grid[i].length
//1 <= n <= 50
//0 <= grid[i][j] < n2
//Each value grid[i][j] is unique.
