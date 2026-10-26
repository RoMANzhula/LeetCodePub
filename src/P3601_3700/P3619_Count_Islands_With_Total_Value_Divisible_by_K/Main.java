package P3601_3700.P3619_Count_Islands_With_Total_Value_Divisible_by_K;

import java.util.ArrayDeque;
import java.util.Deque;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[][] grid1 = {
                {0, 2, 1, 0, 0},
                {0, 5, 0, 0, 5},
                {0, 0, 1, 0, 0},
                {0, 1, 4, 7, 0},
                {0, 2, 0, 0, 8}
        };
        System.out.println(solution.countIslands(grid1, 5)); // 2

        int[][] grid2 = {
                {3, 0, 3, 0},
                {0, 3, 0, 3},
                {3, 0, 3, 0}
        };
        System.out.println(solution.countIslands(grid2, 3)); // 6
    }

    public int countIslands(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 0) {
                    long sum = 0;
                    Deque<int[]> stack = new ArrayDeque<>();
                    stack.push(new int[]{i, j});

                    sum += grid[i][j];
                    grid[i][j] = 0;

                    while (!stack.isEmpty()) {
                        int[] cell = stack.pop();
                        int r = cell[0];
                        int c = cell[1];

                        for (int d = 0; d < 4; d++) {
                            int nr = r + dr[d];
                            int nc = c + dc[d];

                            if (nr >= 0 && nr < m &&
                                    nc >= 0 && nc < n &&
                                    grid[nr][nc] > 0) {

                                sum += grid[nr][nc];
                                grid[nr][nc] = 0;
                                stack.push(new int[]{nr, nc});
                            }
                        }
                    }

                    if (sum % k == 0) {
                        count++;
                    }
                }
            }
        }

        return count;
    }

}

//Complexity:
// time and space - O(m * n)    m - number of rows, n - number of columns


//You are given an m x n matrix grid and a positive integer k. An island is a group of positive
// integers (representing land) that are 4-directionally connected (horizontally or vertically).
//The total value of an island is the sum of the values of all cells in the island.
//Return the number of islands with a total value divisible by k.

//Example 1:
// here an image
//Input: grid = [[0,2,1,0,0],[0,5,0,0,5],[0,0,1,0,0],[0,1,4,7,0],[0,2,0,0,8]], k = 5
//Output: 2
//Explanation:
//The grid contains four islands. The islands highlighted in blue have a total value that is divisible by 5, while the
// islands highlighted in red do not.

//Example 2:
// here an image
//Input: grid = [[3,0,3,0], [0,3,0,3], [3,0,3,0]], k = 3
//Output: 6
//Explanation:
//The grid contains six islands, each with a total value that is divisible by 3.

//Constraints:
//m == grid.length
//n == grid[i].length
//1 <= m, n <= 1000
//1 <= m * n <= 105
//0 <= grid[i][j] <= 106
//1 <= k <= 106
