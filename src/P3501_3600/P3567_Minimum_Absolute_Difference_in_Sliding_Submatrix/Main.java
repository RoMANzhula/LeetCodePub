package P3501_3600.P3567_Minimum_Absolute_Difference_in_Sliding_Submatrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[][] grid1 = {{1, 8}, {3, -2}};
        int k1 = 2;
        System.out.println(Arrays.deepToString(solution.minAbsDiff(grid1, k1))); // [[2]]

        int[][] grid2 = {{3, -1}};
        int k2 = 1;
        System.out.println(Arrays.deepToString(solution.minAbsDiff(grid2, k2))); // [[0, 0]]

        int[][] grid3 = {{1, -2, 3}, {2, 3, 5}};
        int k3 = 2;
        System.out.println(Arrays.deepToString(solution.minAbsDiff(grid3, k3))); // [[1, 2]]
    }

    public int[][] minAbsDiff(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] ans = new int[m - k + 1][n - k + 1];

        for (int i = 0; i <= m - k; i++) {
            for (int j = 0; j <= n - k; j++) {

                List<Integer> list = new ArrayList<>();

                for (int x = i; x < i + k; x++) {
                    for (int y = j; y < j + k; y++) {
                        list.add(grid[x][y]);
                    }
                }

                Collections.sort(list);

                int minDiff = Integer.MAX_VALUE;

                for (int t = 1; t < list.size(); t++) {
                    int prev = list.get(t - 1);
                    int curr = list.get(t);

                    // only consider distinct values
                    if (curr != prev) {
                        minDiff = Math.min(minDiff, curr - prev);
                    }
                }

                // if no distinct pair found → all values equal
                ans[i][j] = (minDiff == Integer.MAX_VALUE) ? 0 : minDiff;
            }
        }

        return ans;
    }

}

//Complexity:
// time - O(m * n * k^2 log k)
// space - O(k^2)


//You are given an m x n integer matrix grid and an integer k.
//For every contiguous k x k submatrix of grid, compute the minimum absolute difference between any two distinct values
// within that submatrix.
//Return a 2D array ans of size (m - k + 1) x (n - k + 1), where ans[i][j] is the minimum absolute difference in the
// submatrix whose top-left corner is (i, j) in grid.
//Note: If all elements in the submatrix have the same value, the answer will be 0.
//A submatrix (x1, y1, x2, y2) is a matrix that is formed by choosing all cells matrix[x][y] where
// x1 <= x <= x2 and y1 <= y <= y2.

//Example 1:
//Input: grid = [[1,8],[3,-2]], k = 2
//Output: [[2]]
//Explanation:
//There is only one possible k x k submatrix: [[1, 8], [3, -2]].
//Distinct values in the submatrix are [1, 8, 3, -2].
//The minimum absolute difference in the submatrix is |1 - 3| = 2. Thus, the answer is [[2]].

//Example 2:
//Input: grid = [[3,-1]], k = 1
//Output: [[0,0]]
//Explanation:
//Both k x k submatrix has only one distinct element.
//Thus, the answer is [[0, 0]].

//Example 3:
//Input: grid = [[1,-2,3],[2,3,5]], k = 2
//Output: [[1,2]]
//Explanation:
//There are two possible k × k submatrix:
//Starting at (0, 0): [[1, -2], [2, 3]].
//Distinct values in the submatrix are [1, -2, 2, 3].
//The minimum absolute difference in the submatrix is |1 - 2| = 1.
//Starting at (0, 1): [[-2, 3], [3, 5]].
//Distinct values in the submatrix are [-2, 3, 5].
//The minimum absolute difference in the submatrix is |3 - 5| = 2.
//Thus, the answer is [[1, 2]].

//Constraints:
//1 <= m == grid.length <= 30
//1 <= n == grid[i].length <= 30
//-105 <= grid[i][j] <= 105
//1 <= k <= min(m, n)
