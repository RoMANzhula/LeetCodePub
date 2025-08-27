package P3401_3500.P3459_Length_of_Longest_V_Shaped_Diagonal_Segment;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[][] grid1 = {
                {2,2,1,2,2},
                {2,0,2,2,0},
                {2,0,1,1,0},
                {1,0,2,2,2},
                {2,0,0,2,2}
        };
        System.out.println(solution.lenOfVDiagonal(grid1)); // 5

        int[][] grid2 = {
                {2,2,2,2,2},
                {2,0,2,2,0},
                {2,0,1,1,0},
                {1,0,2,2,2},
                {2,0,0,2,2}
        };
        System.out.println(solution.lenOfVDiagonal(grid2)); // 4

        int[][] grid3 = {
                {1,2,2,2,2},
                {2,2,2,2,0},
                {2,0,0,0,0},
                {0,0,2,2,2},
                {2,0,0,2,0}
        };
        System.out.println(solution.lenOfVDiagonal(grid3)); // 5

        int[][] grid4 = {{1}};
        System.out.println(solution.lenOfVDiagonal(grid4)); // 1
    }

    // diagonals in clockwise order: ↗, ↘, ↙, ↖
    private static final int[][] KDIRS = {{-1, 1}, {1, 1}, {1, -1}, {-1, -1}};

    public int lenOfVDiagonal(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // mem[i][j][turned][hashNum][dir]  => best length starting at (i,j)
        // turned: 0/1; hashNum: 0 for value 0, 1 for value 2; dir: 0..3
        int[][][][][] mem = new int[m][n][2][2][4];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int t = 0; t < 2; t++) {
                    for (int h = 0; h < 2; h++) {
                        Arrays.fill(mem[i][j][t][h], -1);
                    }
                }
            }
        }

        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    for (int d = 0; d < 4; d++) {
                        int dx = KDIRS[d][0], dy = KDIRS[d][1];
                        res = Math.max(res, 1 + dfs(grid, i + dx, j + dy, 0, 2, d, mem));
                    }
                }
            }
        }

        return res;
    }

    private int dfs(int[][] grid, int i, int j, int turned, int num, int dir, int[][][][][] mem) {
        int m = grid.length, n = grid[0].length;

        if (i < 0 || i >= m || j < 0 || j >= n) return 0;
        if (grid[i][j] != num) return 0;

        int hashNum = Math.max(0, num - 1); // 0 -> 0, 2 -> 1

        if (mem[i][j][turned][hashNum][dir] != -1) {
            return mem[i][j][turned][hashNum][dir];
        }

        int nextNum = (num == 2) ? 0 : 2;

        int dx = KDIRS[dir][0], dy = KDIRS[dir][1];
        int best = 1 + dfs(grid, i + dx, j + dy, turned, nextNum, dir, mem);

        if (turned == 0) {
            int nextDir = (dir + 1) % 4; // clockwise turn
            int ndx = KDIRS[nextDir][0], ndy = KDIRS[nextDir][1];
            best = Math.max(best, 1 + dfs(grid, i + ndx, j + ndy, 1, nextNum, nextDir, mem));
        }

        mem[i][j][turned][hashNum][dir] = best;

        return best;
    }

}

//Complexity:
// time and space - O(mn)


//You are given a 2D integer matrix grid of size n x m, where each element is either 0, 1, or 2.
//A V-shaped diagonal segment is defined as:
//The segment starts with 1.
//The subsequent elements follow this infinite sequence: 2, 0, 2, 0, ....
//The segment:
//Starts along a diagonal direction (top-left to bottom-right, bottom-right to top-left, top-right to bottom-left, or
// bottom-left to top-right).
//Continues the sequence in the same diagonal direction.
//Makes at most one clockwise 90-degree turn to another diagonal direction while maintaining the sequence.
//Return the length of the longest V-shaped diagonal segment. If no valid segment exists, return 0.

//Example 1:
//Input: grid = [[2,2,1,2,2],[2,0,2,2,0],[2,0,1,1,0],[1,0,2,2,2],[2,0,0,2,2]]
//Output: 5
//Explanation:
//The longest V-shaped diagonal segment has a length of 5 and follows these
// coordinates: (0,2) → (1,3) → (2,4), takes a 90-degree clockwise turn at (2,4), and continues as (3,3) → (4,2).

//Example 2:
//Input: grid = [[2,2,2,2,2],[2,0,2,2,0],[2,0,1,1,0],[1,0,2,2,2],[2,0,0,2,2]]
//Output: 4
//Explanation:
//The longest V-shaped diagonal segment has a length of 4 and follows these coordinates: (2,3) → (3,2), takes a
// 90-degree clockwise turn at (3,2), and continues as (2,1) → (1,0).

//Example 3:
//Input: grid = [[1,2,2,2,2],[2,2,2,2,0],[2,0,0,0,0],[0,0,2,2,2],[2,0,0,2,0]]
//Output: 5
//Explanation:
//The longest V-shaped diagonal segment has a length of 5 and follows these
// coordinates: (0,0) → (1,1) → (2,2) → (3,3) → (4,4).

//Example 4:
//Input: grid = [[1]]
//Output: 1
//Explanation:
//The longest V-shaped diagonal segment has a length of 1 and follows these coordinates: (0,0).

//Constraints:
//n == grid.length
//m == grid[i].length
//1 <= n, m <= 500
//grid[i][j] is either 0, 1 or 2.
