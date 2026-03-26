package P3501_3600.P3548_Equal_Sum_Grid_Partition_II;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[][] grid1 = {{1,4},{2,3}};
        System.out.println(solution.canPartitionGrid(grid1)); // true

        int[][] grid2 = {{1,2},{3,4}};
        System.out.println(solution.canPartitionGrid(grid2)); // true

        int[][] grid3 = {{1,2,4},{2,3,5}};
        System.out.println(solution.canPartitionGrid(grid3)); // false

        int[][] grid4 = {{4,1,8},{3,2,6}};
        System.out.println(solution.canPartitionGrid(grid4)); // false
    }

    public boolean canPartitionGrid(int[][] grid) {
        return check(grid) || check(rotate(grid));
    }

    private boolean check(int[][] g) {
        int m = g.length, n = g[0].length;

        long s1 = 0, s2 = 0;

        Map<Long, Integer> cnt1 = new HashMap<>();
        Map<Long, Integer> cnt2 = new HashMap<>();

        // fill total (bottom part initially)
        for (int[] row : g) {
            for (int x : row) {
                s2 += x;
                cnt2.put((long) x, cnt2.getOrDefault((long) x, 0) + 1);
            }
        }

        for (int i = 0; i < m - 1; i++) {

            for (int x : g[i]) {
                s1 += x;
                s2 -= x;

                cnt1.put((long) x, cnt1.getOrDefault((long) x, 0) + 1);

                cnt2.put((long) x, cnt2.get((long) x) - 1);
                if (cnt2.get((long) x) == 0) cnt2.remove((long) x);
            }

            if (s1 == s2) return true;

            if (s1 < s2) {
                long diff = s2 - s1;

                if (cnt2.getOrDefault(diff, 0) > 0) {

                    // bottom section
                    if (
                            (m - i - 1 > 1 && n > 1) ||
                                    (i == m - 2 && (g[i + 1][0] == diff || g[i + 1][n - 1] == diff)) ||
                                    (n == 1 && (g[i + 1][0] == diff || g[m - 1][0] == diff))
                    ) return true;
                }

            } else {
                long diff = s1 - s2;

                if (cnt1.getOrDefault(diff, 0) > 0) {

                    // top section
                    if (
                            (i + 1 > 1 && n > 1) ||
                                    (i == 0 && (g[0][0] == diff || g[0][n - 1] == diff)) ||
                                    (n == 1 && (g[0][0] == diff || g[i][0] == diff))
                    ) return true;
                }
            }
        }

        return false;
    }

    private int[][] rotate(int[][] grid) {
        int m = grid.length, n = grid[0].length;

        int[][] t = new int[n][m];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                t[j][i] = grid[i][j];
            }
        }

        return t;
    }

}

//Complexity:
// time and space - O(m * n)


//You are given an m x n matrix grid of positive integers. Your task is to determine if it is possible to make
// either one horizontal or one vertical cut on the grid such that:
//Each of the two resulting sections formed by the cut is non-empty.
//The sum of elements in both sections is equal, or can be made equal by discounting at most one single cell in
// total (from either section).
//If a cell is discounted, the rest of the section must remain connected.
//Return true if such a partition exists; otherwise, return false.
//Note: A section is connected if every cell in it can be reached from any other cell by moving up, down, left, or
// right through other cells in the section.

//Example 1:
//Input: grid = [[1,4],[2,3]]
//Output: true
//Explanation:
//A horizontal cut after the first row gives sums 1 + 4 = 5 and 2 + 3 = 5, which are equal. Thus, the answer is true.

//Example 2:
//Input: grid = [[1,2],[3,4]]
//Output: true
//Explanation:
//A vertical cut after the first column gives sums 1 + 3 = 4 and 2 + 4 = 6.
//By discounting 2 from the right section (6 - 2 = 4), both sections have equal sums and remain connected. Thus,
// the answer is true.

//Example 3:
//Input: grid = [[1,2,4],[2,3,5]]
//Output: false
//Explanation:
//A horizontal cut after the first row gives 1 + 2 + 4 = 7 and 2 + 3 + 5 = 10.
//By discounting 3 from the bottom section (10 - 3 = 7), both sections have equal sums, but they do not remain
// connected as it splits the bottom section into two parts ([2] and [5]). Thus, the answer is false.

//Example 4:
//Input: grid = [[4,1,8],[3,2,6]]
//Output: false
//Explanation:
//No valid cut exists, so the answer is false.

//Constraints:
//1 <= m == grid.length <= 105
//1 <= n == grid[i].length <= 105
//2 <= m * n <= 105
//1 <= grid[i][j] <= 105
