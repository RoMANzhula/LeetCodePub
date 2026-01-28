package P3601_3700.P3651_Minimum_Cost_Path_with_Teleportations;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[][] grid1 = {
                {1, 3, 3},
                {2, 5, 4},
                {4, 3, 5}
        };
        int k1 = 2;
        System.out.println(solution.minCost(grid1, k1)); // expected 7

        int[][] grid2 = {
                {1, 2},
                {2, 3},
                {3, 4}
        };
        int k2 = 1;
        System.out.println(solution.minCost(grid2, k2)); // expected 9
    }

    class State {
        int x, y, t;
        long cost;

        State(int x, int y, int t, long cost) {
            this.x = x;
            this.y = y;
            this.t = t;
            this.cost = cost;
        }
    }

    public int minCost(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;

        long[][][] dist = new long[m][n][k + 1];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                Arrays.fill(dist[i][j], Long.MAX_VALUE);

        // collect all cells
        List<int[]> cells = new ArrayList<>();
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                cells.add(new int[]{i, j});

        // sort cells by grid value
        cells.sort(Comparator.comparingInt(a -> grid[a[0]][a[1]]));

        PriorityQueue<State> pq =
                new PriorityQueue<>(Comparator.comparingLong(a -> a.cost));

        dist[0][0][0] = 0;
        pq.offer(new State(0, 0, 0, 0));

        int[] dx = {0, 1};
        int[] dy = {1, 0};

        while (!pq.isEmpty()) {
            State cur = pq.poll();

            if (cur.cost > dist[cur.x][cur.y][cur.t]) continue;

            // reached target
            if (cur.x == m - 1 && cur.y == n - 1)
                return (int) cur.cost;

            // normal moves (right, down)
            for (int d = 0; d < 2; d++) {
                int nx = cur.x + dx[d];
                int ny = cur.y + dy[d];

                if (nx < m && ny < n) {
                    long newCost = cur.cost + grid[nx][ny];
                    if (newCost < dist[nx][ny][cur.t]) {
                        dist[nx][ny][cur.t] = newCost;
                        pq.offer(new State(nx, ny, cur.t, newCost));
                    }
                }
            }

            // teleport
            if (cur.t < k) {
                for (int[] c : cells) {
                    int x = c[0], y = c[1];
                    if (grid[x][y] > grid[cur.x][cur.y]) break;

                    if (cur.cost < dist[x][y][cur.t + 1]) {
                        dist[x][y][cur.t + 1] = cur.cost;
                        pq.offer(new State(x, y, cur.t + 1, cur.cost));
                    }
                }
            }
        }

        return -1;
    }

}

//Complexity:
// time - O(m * n * k * log(m*n*k))
// space - O(m*n*k)


//You are given a m x n 2D integer array grid and an integer k. You start at the top-left cell (0, 0) and your goal
// is to reach the bottom‐right cell (m - 1, n - 1).
//There are two types of moves available:
//Normal move: You can move right or down from your current cell (i, j), i.e. you can move to (i, j + 1) (right) or
// (i + 1, j) (down). The cost is the value of the destination cell.
//Teleportation: You can teleport from any cell (i, j), to any cell (x, y) such that grid[x][y] <= grid[i][j]; the
// cost of this move is 0. You may teleport at most k times.
//Return the minimum total cost to reach cell (m - 1, n - 1) from (0, 0).

//Example 1:
//Input: grid = [[1,3,3],[2,5,4],[4,3,5]], k = 2
//Output: 7
//Explanation:
//Initially we are at (0, 0) and cost is 0.
//Current Position	Move	New Position	Total Cost
//(0, 0)	Move Down	(1, 0)	0 + 2 = 2
//(1, 0)	Move Right	(1, 1)	2 + 5 = 7
//(1, 1)	Teleport to (2, 2)	(2, 2)	7 + 0 = 7
//The minimum cost to reach bottom-right cell is 7.

//Example 2:
//Input: grid = [[1,2],[2,3],[3,4]], k = 1
//Output: 9
//Explanation:
//Initially we are at (0, 0) and cost is 0.
//Current Position	Move	New Position	Total Cost
//(0, 0)	Move Down	(1, 0)	0 + 2 = 2
//(1, 0)	Move Right	(1, 1)	2 + 3 = 5
//(1, 1)	Move Down	(2, 1)	5 + 4 = 9
//The minimum cost to reach bottom-right cell is 9.

//Constraints:
//2 <= m, n <= 80
//m == grid.length
//n == grid[i].length
//0 <= grid[i][j] <= 104
//0 <= k <= 10
