package P3401_3500.P3446_Sort_Matrix_by_Diagonals;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[][] grid1 = {{1,7,3},{9,8,2},{4,5,6}};
        System.out.println(Arrays.deepToString(solution.sortMatrix(grid1))); // [[8, 2, 3], [9, 6, 7], [4, 5, 1]]

        int[][] grid2 = {{0,1},{1,2}};
        System.out.println(Arrays.deepToString(solution.sortMatrix(grid2))); // [[2, 1], [1, 0]]

        int[][] grid3 = {{1}};
        System.out.println(Arrays.deepToString(solution.sortMatrix(grid3))); // [[1]]
    }

    public int[][] sortMatrix(int[][] grid) {
        int n = grid.length;

        // Map difference (i - j) to list of values
        Map<Integer, List<Integer>> diagMap = new HashMap<>();

        // collect values
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int key = i - j;
                diagMap.putIfAbsent(key, new ArrayList<>());
                diagMap.get(key).add(grid[i][j]);
            }
        }

        // sort each diagonal according to the rule
        for (Map.Entry<Integer, List<Integer>> entry : diagMap.entrySet()) {
            List<Integer> diag = entry.getValue();
            int key = entry.getKey();

            if (key >= 0) {
                // bottom-left triangle (non-increasing)
                diag.sort(Collections.reverseOrder());
            } else {
                // top-right triangle (non-decreasing)
                Collections.sort(diag);
            }
        }

        // place values back
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int key = i - j;
                grid[i][j] = diagMap.get(key).remove(0);
            }
        }

        return grid;
    }

}

//Complexity:
// time - O(n^2 log n)
// space - O(n^2)


//You are given an n x n square matrix of integers grid. Return the matrix such that:
//The diagonals in the bottom-left triangle (including the middle diagonal) are sorted in non-increasing order.
//The diagonals in the top-right triangle are sorted in non-decreasing order.

//Example 1:
//Input: grid = [[1,7,3],[9,8,2],[4,5,6]]
//Output: [[8,2,3],[9,6,7],[4,5,1]]
//Explanation:
//The diagonals with a black arrow (bottom-left triangle) should be sorted in non-increasing order:
//[1, 8, 6] becomes [8, 6, 1].
//[9, 5] and [4] remain unchanged.
//The diagonals with a blue arrow (top-right triangle) should be sorted in non-decreasing order:
//[7, 2] becomes [2, 7].
//[3] remains unchanged.

//Example 2:
//Input: grid = [[0,1],[1,2]]
//Output: [[2,1],[1,0]]
//Explanation:
//The diagonals with a black arrow must be non-increasing, so [0, 2] is changed to [2, 0]. The other diagonals are
// already in the correct order.

//Example 3:
//Input: grid = [[1]]
//Output: [[1]]
//Explanation:
//Diagonals with exactly one element are already in order, so no changes are needed.

//Constraints:
//grid.length == grid[i].length == n
//1 <= n <= 10
//-105 <= grid[i][j] <= 105
