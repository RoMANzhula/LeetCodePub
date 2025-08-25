package P401_500.P498_Diagonal_Traverse;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[][] mat1 = {{1,2,3},{4,5,6},{7,8,9}};
        System.out.println(Arrays.toString(solution.findDiagonalOrder(mat1))); // Output: [1, 2, 4, 7, 5, 3, 6, 8, 9]

        int[][] mat2 = {{1,2},{3,4}};
        System.out.println(Arrays.toString(solution.findDiagonalOrder(mat2))); // Output: [1, 2, 3, 4]
    }

    public int[] findDiagonalOrder(int[][] mat) {
        if (mat == null || mat.length == 0) return new int[0];

        int m = mat.length;
        int n = mat[0].length;
        int[] result = new int[m * n];

        int row = 0, col = 0, dir = 1; // dir = 1 (up-right), dir = -1 (down-left)
        int idx = 0;

        while (idx < m * n) {
            result[idx++] = mat[row][col];

            // moving up-right
            if (dir == 1) {
                if (col == n - 1) { // reach last col, go down
                    row++;
                    dir = -1;
                } else if (row == 0) { // reach top, go right
                    col++;
                    dir = -1;
                } else { // normal move up-right
                    row--;
                    col++;
                }
            }
            // moving down-left
            else {
                if (row == m - 1) { // reach last row, go right
                    col++;
                    dir = 1;
                } else if (col == 0) { // reach first col, go down
                    row++;
                    dir = 1;
                } else { // normal move down-left
                    row++;
                    col--;
                }
            }
        }

        return result;
    }

}

//Complexity:
// time - O(m * n)
// space - O(1)


//Given an m x n matrix mat, return an array of all the elements of the array in a diagonal order.

//Example 1:
//Input: mat = [[1,2,3],[4,5,6],[7,8,9]]
//Output: [1,2,4,7,5,3,6,8,9]

//Example 2:
//Input: mat = [[1,2],[3,4]]
//Output: [1,2,3,4]

//Constraints:
//m == mat.length
//n == mat[i].length
//1 <= m, n <= 104
//1 <= m * n <= 104
//-105 <= mat[i][j] <= 105
