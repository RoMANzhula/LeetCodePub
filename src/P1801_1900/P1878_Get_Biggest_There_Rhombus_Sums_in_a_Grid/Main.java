package P1801_1900.P1878_Get_Biggest_There_Rhombus_Sums_in_a_Grid;

import java.util.Arrays;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[][] grid = {
                {3,4,5,1,3},
                {3,3,4,2,3},
                {20,30,200,40,10},
                {1,5,5,4,1},
                {4,3,2,2,5}
        };

        System.out.println(Arrays.toString(solution.getBiggestThree(grid))); // [228, 216, 211]
    }

    public int[] getBiggestThree(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] diag1 = new int[m + 1][n + 1]; // right down
        int[][] diag2 = new int[m + 1][n + 2]; // left down

        // build prefix sums for diagonals
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                diag1[i + 1][j + 1] = diag1[i][j] + grid[i][j];
                diag2[i + 1][j] = diag2[i][j + 1] + grid[i][j];
            }
        }

        TreeSet<Integer> set = new TreeSet<>((a, b) -> b - a);

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {

                // area 0 rhombus
                set.add(grid[r][c]);

                int maxSize = Math.min(Math.min(r, m - 1 - r), Math.min(c, n - 1 - c));

                for (int k = 1; k <= maxSize; k++) {

                    int topR = r - k;
                    int topC = c;

                    int rightR = r;
                    int rightC = c + k;

                    int bottomR = r + k;
                    int bottomC = c;

                    int leftR = r;
                    int leftC = c - k;

                    int sum = 0;

                    // top -> right
                    sum += diag1[rightR + 1][rightC + 1] - diag1[topR][topC];

                    // right -> bottom
                    sum += diag2[bottomR + 1][bottomC] - diag2[rightR][rightC + 1];

                    // bottom -> left
                    sum += diag1[bottomR + 1][bottomC + 1] - diag1[leftR][leftC];

                    // left -> top
                    sum += diag2[leftR + 1][leftC] - diag2[topR][topC + 1];

                    // remove double-counted corners
                    sum -= grid[topR][topC];
                    sum -= grid[rightR][rightC];
                    sum -= grid[bottomR][bottomC];
                    sum -= grid[leftR][leftC];

                    set.add(sum);
                }
            }
        }

        int size = Math.min(3, set.size());
        int[] res = new int[size];

        int i = 0;
        for (int v : set) {
            res[i++] = v;
            if (i == size) break;
        }

        return res;
    }

}


//You are given an m x n integer matrix grid.
//A rhombus sum is the sum of the elements that form the border of a regular rhombus shape in grid. The rhombus must
// have the shape of a square rotated 45 degrees with each of the corners centered in a grid cell. Below is an image
// of four valid rhombus shapes with the corresponding colored cells that should be included in each rhombus sum:

//Note that the rhombus can have an area of 0, which is depicted by the purple rhombus in the bottom right corner.
//Return the biggest three distinct rhombus sums in the grid in descending order. If there are less than three
// distinct values, return all of them.

//Example 1:
//Input: grid = [[3,4,5,1,3],[3,3,4,2,3],[20,30,200,40,10],[1,5,5,4,1],[4,3,2,2,5]]
//Output: [228,216,211]
//Explanation: The rhombus shapes for the three biggest distinct rhombus sums are depicted above.
//- Blue: 20 + 3 + 200 + 5 = 228
//- Red: 200 + 2 + 10 + 4 = 216
//- Green: 5 + 200 + 4 + 2 = 211

//Example 2:
//Input: grid = [[1,2,3],[4,5,6],[7,8,9]]
//Output: [20,9,8]
//Explanation: The rhombus shapes for the three biggest distinct rhombus sums are depicted above.
//- Blue: 4 + 2 + 6 + 8 = 20
//- Red: 9 (area 0 rhombus in the bottom right corner)
//- Green: 8 (area 0 rhombus in the bottom middle)

//Example 3:
//Input: grid = [[7,7,7]]
//Output: [7]
//Explanation: All three possible rhombus sums are the same, so return [7].

//Constraints:
//m == grid.length
//n == grid[i].length
//1 <= m, n <= 50
//1 <= grid[i][j] <= 105
