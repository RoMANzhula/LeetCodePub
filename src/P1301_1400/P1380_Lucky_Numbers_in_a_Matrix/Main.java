package P1301_1400.P1380_Lucky_Numbers_in_a_Matrix;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[][] matrix1 = {{3,7,8},{9,11,13},{15,16,17}};
        int[][] matrix2 = {{1,10,4,2},{9,3,8,7},{15,16,17,12}};
        int[][] matrix3 = {{7,8},{1,2}};

        System.out.println(solution.luckyNumbers(matrix1)); // Output: [15]
        System.out.println(solution.luckyNumbers(matrix2)); // Output: [12]
        System.out.println(solution.luckyNumbers(matrix3)); // Output: [7]
    }

    public List<Integer> luckyNumbers(int[][] matrix) {
        List<Integer> luckyNumbers = new ArrayList<>();
        int m = matrix.length;
        int n = matrix[0].length;

        // Step 1: Find the minimum elements in each row
        int[] minRow = new int[m];
        for (int i = 0; i < m; i++) {
            int min = matrix[i][0];
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] < min) {
                    min = matrix[i][j];
                }
            }
            minRow[i] = min;
        }

        // Step 2: Find the maximum elements in each column
        int[] maxCol = new int[n];
        for (int j = 0; j < n; j++) {
            int max = matrix[0][j];
            for (int i = 1; i < m; i++) {
                if (matrix[i][j] > max) {
                    max = matrix[i][j];
                }
            }
            maxCol[j] = max;
        }

        // Step 3: Compare the minRow and maxCol to find lucky numbers
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == minRow[i] && matrix[i][j] == maxCol[j]) {
                    luckyNumbers.add(matrix[i][j]);
                }
            }
        }

        return luckyNumbers;
    }
}

//Explanation:
//minRow Array: This array stores the minimum value from each row.
//maxCol Array: This array stores the maximum value from each column.
//Nested Loops: The outer loop iterates through each row, and the inner loop iterates through each column to
// compare if the element is both the minimum in its row and the maximum in its column.
//This code should successfully identify and print the lucky numbers for the given matrices.


//Given an m x n matrix of distinct numbers, return all lucky numbers in the matrix in any order.
//A lucky number is an element of the matrix such that it is the minimum element in its row and maximum in its column.
//
//Example 1:
//Input: matrix = [[3,7,8],[9,11,13],[15,16,17]]
//Output: [15]
//Explanation: 15 is the only lucky number since it is the minimum in its row and the maximum in its column.

//Example 2:
//Input: matrix = [[1,10,4,2],[9,3,8,7],[15,16,17,12]]
//Output: [12]
//Explanation: 12 is the only lucky number since it is the minimum in its row and the maximum in its column.

//Example 3:
//Input: matrix = [[7,8],[1,2]]
//Output: [7]
//Explanation: 7 is the only lucky number since it is the minimum in its row and the maximum in its column.
//
//Constraints:
//m == mat.length
//n == mat[i].length
//1 <= n, m <= 50
//1 <= matrix[i][j] <= 105.
//All elements in the matrix are distinct.