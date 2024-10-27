package P1201_1300.P1277_Count_Square_Submatrices_with_All_Ones;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[][] matrix1 = {
                {0, 1, 1, 1},
                {1, 1, 1, 1},
                {0, 1, 1, 1}
        };
        System.out.println("Example 1 Output: " + solution.countSquares(matrix1)); // Expected output: 15

        int[][] matrix2 = {
                {1, 0, 1},
                {1, 1, 0},
                {1, 1, 0}
        };
        System.out.println("Example 2 Output: " + solution.countSquares(matrix2)); // Expected output: 7
    }

    public int countSquares(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];
        int totalSquares = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1; // edges can only form 1x1 squares
                    } else {
                        dp[i][j] = Math.min(dp[i-1][j], Math.min(dp[i][j-1], dp[i-1][j-1])) + 1;
                    }
                    totalSquares += dp[i][j];
                }
            }
        }

        return totalSquares;
    }
}

//Explanation
//Base Cases: If a cell is on the top row or left column, it can only be part of
// a 1x1 square (assuming matrix[i][j] == 1).
//Recursive Case: For cells not on the edges, dp[i][j] depends on the minimum size of squares ending at
// cells directly above, to the left, and diagonally up-left.
//Final Output: The sum of all values in dp gives the total count of square submatrices with all ones.
//Complexity Analysis
//Time Complexity:
//O(m×n) because we fill in each cell of the dp matrix once.
//Space Complexity:
//O(m×n) for the dp array.


//Given a m * n matrix of ones and zeros, return how many square submatrices have all ones.
//
//Example 1:
//Input: matrix =
//[
//  [0,1,1,1],
//  [1,1,1,1],
//  [0,1,1,1]
//]
//Output: 15
//Explanation:
//There are 10 squares of side 1.
//There are 4 squares of side 2.
//There is  1 square of side 3.
//Total number of squares = 10 + 4 + 1 = 15.

//Example 2:
//Input: matrix =
//[
//  [1,0,1],
//  [1,1,0],
//  [1,1,0]
//]
//Output: 7
//Explanation:
//There are 6 squares of side 1.
//There is 1 square of side 2.
//Total number of squares = 6 + 1 = 7.
//
//Constraints:
//1 <= arr.length <= 300
//1 <= arr[0].length <= 300
//0 <= arr[i][j] <= 1