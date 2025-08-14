package P1_100.P54_Spiral_Matrix;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[][] matrix1 = {{1,2,3},{4,5,6},{7,8,9}};
        System.out.println(solution.spiralOrder(matrix1)); // [1,2,3,6,9,8,7,4,5]

        int[][] matrix2 = {{1,2,3,4},{5,6,7,8},{9,10,11,12}};
        System.out.println(solution.spiralOrder(matrix2)); // [1,2,3,4,8,12,11,10,9,5,6,7]
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix == null || matrix.length == 0) return result;

        int top = 0;                      // starting row index
        int bottom = matrix.length - 1;   // ending row index
        int left = 0;                     // starting column index
        int right = matrix[0].length - 1; // ending column index

        while (top <= bottom && left <= right) {
            // move right
            for (int col = left; col <= right; col++) {
                result.add(matrix[top][col]);
            }
            top++;

            // move down
            for (int row = top; row <= bottom; row++) {
                result.add(matrix[row][right]);
            }
            right--;

            // move left (check if still within bounds)
            if (top <= bottom) {
                for (int col = right; col >= left; col--) {
                    result.add(matrix[bottom][col]);
                }
                bottom--;
            }

            // move up (check if still within bounds)
            if (left <= right) {
                for (int row = bottom; row >= top; row--) {
                    result.add(matrix[row][left]);
                }
                left++;
            }
        }

        return result;
    }

}

//Complexity:
// time - O(m * n)
// space - O(1)


//Given an m x n matrix, return all elements of the matrix in spiral order.

//Example 1:
//Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
//Output: [1,2,3,6,9,8,7,4,5]

//Example 2:
//Input: matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
//Output: [1,2,3,4,8,12,11,10,9,5,6,7]

//Constraints:
//m == matrix.length
//n == matrix[i].length
//1 <= m, n <= 10
//-100 <= matrix[i][j] <= 100
