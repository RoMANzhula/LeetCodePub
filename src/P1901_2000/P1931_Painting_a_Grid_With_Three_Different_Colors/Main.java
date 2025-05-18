package P1901_2000.P1931_Painting_a_Grid_With_Three_Different_Colors;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.colorTheGrid(1, 1)); // Output: 3
        System.out.println(solution.colorTheGrid(1, 2)); // Output: 6
        System.out.println(solution.colorTheGrid(5, 5)); // Output: 580986
    }

    static final int MOD = 1_000_000_007;

    public int colorTheGrid(int m, int n) {
        List<int[]> validCols = new ArrayList<>();
        generateValidColumns(m, 0, new int[m], validCols);

        Map<String, Integer> patternIndex = new HashMap<>();

        for (int i = 0; i < validCols.size(); i++) {
            patternIndex.put(Arrays.toString(validCols.get(i)), i);
        }

        // build compatibility list
        List<List<Integer>> compatible = new ArrayList<>();
        for (int[] col1 : validCols) {
            List<Integer> list = new ArrayList<>();
            for (int[] col2 : validCols) {
                if (isCompatible(col1, col2)) {
                    list.add(patternIndex.get(Arrays.toString(col2)));
                }
            }
            compatible.add(list);
        }

        // DP: dp[colIndex][step]
        int[][] dp = new int[validCols.size()][n];

        for (int i = 0; i < validCols.size(); i++) {
            dp[i][0] = 1; // first column
        }

        for (int j = 1; j < n; j++) {
            for (int i = 0; i < validCols.size(); i++) {
                for (int next : compatible.get(i)) {
                    dp[next][j] = (dp[next][j] + dp[i][j - 1]) % MOD;
                }
            }
        }

        int res = 0;

        for (int i = 0; i < validCols.size(); i++) {
            res = (res + dp[i][n - 1]) % MOD;
        }

        return res;
    }

    // generate all vertical columns of height m such that adjacent colors are different
    private void generateValidColumns(int m, int pos, int[] current, List<int[]> validCols) {
        if (pos == m) {
            validCols.add(current.clone());
            return;
        }

        for (int color = 0; color < 3; color++) {
            if (pos == 0 || current[pos - 1] != color) {
                current[pos] = color;
                generateValidColumns(m, pos + 1, current, validCols);
            }
        }
    }

    // check if two columns are compatible (no cell in the same row has same color)
    private boolean isCompatible(int[] a, int[] b) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == b[i]) return false;
        }

        return true;
    }

}

//Explanation:
//generateValidColumns builds all vertical stripes of length m with different adjacent colors.
//DP matrix tracks the number of ways to extend the coloring column by column.
//Compatibility check ensures adjacent columns do not have the same color in the same row.
//Final answer is the sum of all DP values for the last column.
//Complexity:
// time - O(3^m + S^2 * m + n * S^2)
// space - O(S * n + S^2 + S * m)


//You are given two integers m and n. Consider an m x n grid where each cell is initially white. You can paint
// each cell red, green, or blue. All cells must be painted.
//Return the number of ways to color the grid with no two adjacent cells having the same color. Since the answer can
// be very large, return it modulo 109 + 7.

//Example 1:
//Input: m = 1, n = 1
//Output: 3
//Explanation: The three possible colorings are shown in the image above.

//Example 2:
//Input: m = 1, n = 2
//Output: 6
//Explanation: The six possible colorings are shown in the image above.

//Example 3:
//Input: m = 5, n = 5
//Output: 580986

//Constraints:
//1 <= m <= 5
//1 <= n <= 1000
