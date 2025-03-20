package P1701_1800.P1727_Largest_Submatrix_With_Rearrangements;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int[][] matrix1 = {{0, 0, 1}, {1, 1, 1}, {1, 0, 1}};
        System.out.println(largestSubmatrix(matrix1)); // Output: 4

        int[][] matrix2 = {{1, 0, 1, 0, 1}};
        System.out.println(largestSubmatrix(matrix2)); // Output: 3

        int[][] matrix3 = {{1, 1, 0}, {1, 0, 1}};
        System.out.println(largestSubmatrix(matrix3)); // Output: 2
    }

    public static int largestSubmatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int m = matrix.length;
        int n = matrix[0].length;

        int[][] heights = new int[m][n];

        //calculate heights of consecutive 1s ending at each cell
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) {
                    heights[i][j] = i == 0 ? 1 : heights[i - 1][j] + 1;
                }
            }
        }

        int maxArea = 0;

        //sort rows based on their heights
        for (int i = 0; i < m; i++) {
            int[] rowHeights = heights[i];
            Arrays.sort(rowHeights);

            //calculate the area of the largest rectangle for the current row
            for (int j = 0; j < n; j++) {
                int height = rowHeights[j];
                int width = n - j;
                maxArea = Math.max(maxArea, height * width);
            }
        }

        return maxArea;
    }

}

//You are given a binary matrix matrix of size m x n, and you are allowed to rearrange the columns of the
// matrix in any order.
//Return the area of the largest submatrix within matrix where every element of the submatrix is 1 after
// reordering the columns optimally.

//Example 1:
//Input: matrix = [[0,0,1],[1,1,1],[1,0,1]]
//Output: 4
//Explanation: You can rearrange the columns as shown above.
//The largest submatrix of 1s, in bold, has an area of 4.

//Example 2:
//Input: matrix = [[1,0,1,0,1]]
//Output: 3
//Explanation: You can rearrange the columns as shown above.
//The largest submatrix of 1s, in bold, has an area of 3.

//Example 3:
//Input: matrix = [[1,1,0],[1,0,1]]
//Output: 2
//Explanation: Notice that you must rearrange entire columns, and there is no way to make a submatrix of 1s
// larger than an area of 2.

//Constraints:
//m == matrix.length
//n == matrix[i].length
//1 <= m * n <= 105
//matrix[i][j] is either 0 or 1.
