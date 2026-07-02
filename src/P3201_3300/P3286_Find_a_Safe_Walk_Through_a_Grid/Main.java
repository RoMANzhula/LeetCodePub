package P3201_3300.P3286_Find_a_Safe_Walk_Through_a_Grid;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        List<List<Integer>> grid1 = List.of(
                List.of(0, 1, 0, 0, 0),
                List.of(0, 1, 0, 1, 0),
                List.of(0, 0, 0, 1, 0)
        );

        System.out.println(solution.findSafeWalk(grid1, 1)); // true

        List<List<Integer>> grid2 = List.of(
                List.of(0, 1, 1, 0, 0, 0),
                List.of(1, 0, 1, 0, 0, 0),
                List.of(0, 1, 1, 1, 0, 1),
                List.of(0, 0, 1, 0, 1, 0)
        );

        System.out.println(solution.findSafeWalk(grid2, 3)); // false

        List<List<Integer>> grid3 = List.of(
                List.of(1, 1, 1),
                List.of(1, 0, 1),
                List.of(1, 1, 1)
        );

        System.out.println(solution.findSafeWalk(grid3, 5)); // true
    }

    private static final int[][] DIRS = {
            {1, 0},
            {-1, 0},
            {0, 1},
            {0, -1}
    };

    public boolean findSafeWalk(List<List<Integer>> grid, int health) {

        int m = grid.size();
        int n = grid.get(0).size();

        int[][] dist = new int[m][n];

        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        Deque<int[]> deque = new ArrayDeque<>();

        dist[0][0] = grid.get(0).get(0);
        deque.offerFirst(new int[]{0, 0});

        while (!deque.isEmpty()) {

            int[] cur = deque.pollFirst();

            int x = cur[0];
            int y = cur[1];

            for (int[] dir : DIRS) {

                int nx = x + dir[0];
                int ny = y + dir[1];

                if (nx < 0 || ny < 0 || nx >= m || ny >= n) {
                    continue;
                }

                int newCost = dist[x][y] + grid.get(nx).get(ny);

                if (newCost < dist[nx][ny]) {

                    dist[nx][ny] = newCost;

                    if (grid.get(nx).get(ny) == 0) {
                        deque.offerFirst(new int[]{nx, ny});
                    } else {
                        deque.offerLast(new int[]{nx, ny});
                    }
                }
            }
        }

        return dist[m - 1][n - 1] < health;
    }

}

//Complexity:
// time and space - O(m × n)


//You are given an m x n binary matrix grid and an integer health.
//You start on the upper-left corner (0, 0) and would like to get to the lower-right corner (m - 1, n - 1).
//You can move up, down, left, or right from one cell to another adjacent cell as long as your health remains positive.
//Cells (i, j) with grid[i][j] = 1 are considered unsafe and reduce your health by 1.
//Return true if you can reach the final cell with a health value of 1 or more, and false otherwise.

//Example 1:
//Input: grid = [[0,1,0,0,0],[0,1,0,1,0],[0,0,0,1,0]], health = 1
//Output: true
//Explanation:
//The final cell can be reached safely by walking along the gray cells below.

//Example 2:
//Input: grid = [[0,1,1,0,0,0],[1,0,1,0,0,0],[0,1,1,1,0,1],[0,0,1,0,1,0]], health = 3
//Output: false
//Explanation:
//A minimum of 4 health points is needed to reach the final cell safely.

//Example 3:
//Input: grid = [[1,1,1],[1,0,1],[1,1,1]], health = 5
//Output: true
//Explanation:
//The final cell can be reached safely by walking along the gray cells below.

//Any path that does not go through the cell (1, 1) is unsafe since your health will drop to 0 when reaching the
// final cell.

//Constraints:
//m == grid.length
//n == grid[i].length
//1 <= m, n <= 50
//2 <= m * n
//1 <= health <= m + n
//grid[i][j] is either 0 or 1.
