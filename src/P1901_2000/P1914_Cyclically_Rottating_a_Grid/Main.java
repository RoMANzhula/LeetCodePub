package P1901_2000.P1914_Cyclically_Rottating_a_Grid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[][] grid1 = {
                {40, 10},
                {30, 20}
        };

        int[][] result1 = solution.rotateGrid(grid1, 1);
        print(result1);

        System.out.println();

        int[][] grid2 = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9,10,11,12},
                {13,14,15,16}
        };

        int[][] result2 = solution.rotateGrid(grid2, 2);
        print(result2);
    }

    private static void print(int[][] grid) {
        for (int[] row : grid) {
            System.out.println(Arrays.toString(row));
        }
    }

    public int[][] rotateGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;

        int layers = Math.min(m, n) / 2;

        for (int layer = 0; layer < layers; layer++) {
            List<Integer> vals = new ArrayList<>();

            int top = layer, left = layer;
            int bottom = m - layer - 1, right = n - layer - 1;

            // top row
            for (int j = left; j <= right; j++)
                vals.add(grid[top][j]);

            // right column
            for (int i = top + 1; i <= bottom - 1; i++)
                vals.add(grid[i][right]);

            // bottom row
            for (int j = right; j >= left; j--)
                vals.add(grid[bottom][j]);

            // left column
            for (int i = bottom - 1; i >= top + 1; i--)
                vals.add(grid[i][left]);

            int len = vals.size();
            int shift = k % len;

            List<Integer> rotated = new ArrayList<>(len);

            for (int i = 0; i < len; i++) {
                rotated.add(vals.get((i + shift) % len));
            }

            int idx = 0;

            // write back top row
            for (int j = left; j <= right; j++)
                grid[top][j] = rotated.get(idx++);

            // write back right column
            for (int i = top + 1; i <= bottom - 1; i++)
                grid[i][right] = rotated.get(idx++);

            // write back bottom row
            for (int j = right; j >= left; j--)
                grid[bottom][j] = rotated.get(idx++);

            // write back left column
            for (int i = bottom - 1; i >= top + 1; i--)
                grid[i][left] = rotated.get(idx++);
        }

        return grid;
    }

}

//Complexity:
// time and space - O(m * n)


//You are given an m x n integer matrix grid, where m and n are both even integers, and an integer k.
//The matrix is composed of several layers, which is shown in the below image, where each color is its own layer:

//A cyclic rotation of the matrix is done by cyclically rotating each layer in the matrix. To cyclically rotate a
// layer once, each element in the layer will take the place of the adjacent element in the counter-clockwise
// direction. An example rotation is shown below:

//Return the matrix after applying k cyclic rotations to it.

//Example 1:
//Input: grid = [[40,10],[30,20]], k = 1
//Output: [[10,20],[40,30]]
//Explanation: The figures above represent the grid at every state.

//Example 2:
//Input: grid = [[1,2,3,4],[5,6,7,8],[9,10,11,12],[13,14,15,16]], k = 2
//Output: [[3,4,8,12],[2,11,10,16],[1,7,6,15],[5,9,13,14]]
//Explanation: The figures above represent the grid at every state.

//Constraints:
//m == grid.length
//n == grid[i].length
//2 <= m, n <= 50
//Both m and n are even integers.
//1 <= grid[i][j] <= 5000
//1 <= k <= 109
