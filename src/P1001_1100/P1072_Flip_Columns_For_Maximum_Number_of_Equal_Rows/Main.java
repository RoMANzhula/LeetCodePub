package P1001_1100.P1072_Flip_Columns_For_Maximum_Number_of_Equal_Rows;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[][] matrix1 = {{0, 1}, {1, 1}};
        System.out.println(solution.maxEqualRowsAfterFlips(matrix1)); // Output: 1

        int[][] matrix2 = {{0, 1}, {1, 0}};
        System.out.println(solution.maxEqualRowsAfterFlips(matrix2)); // output: 2

        int[][] matrix3 = {{0, 0, 0}, {0, 0, 1}, {1, 1, 0}};
        System.out.println(solution.maxEqualRowsAfterFlips(matrix3)); // Output: 2
    }

    public int maxEqualRowsAfterFlips(int[][] matrix) {
        HashMap<String, Integer> patternCount = new HashMap<>();

        for (int[] row : matrix) {
            // Generate the pattern relative to the first cell
            StringBuilder pattern = new StringBuilder();
            for (int cell : row) {
                pattern.append(cell ^ row[0]); // XOR to normalize the row based on the first element
            }

            // Convert pattern to string and update the count in the map
            String patternStr = pattern.toString();
            patternCount.put(patternStr, patternCount.getOrDefault(patternStr, 0) + 1);
        }

        // Find the maximum count of rows with the same pattern
        int maxRows = 0;
        for (int count : patternCount.values()) {
            maxRows = Math.max(maxRows, count);
        }

        return maxRows;
    }

}

//Explanation:
//XOR Normalization:
//Use XOR with the first cell of each row (cell ^ row[0]) to normalize rows. This makes equivalent
// rows have the same pattern.
//Pattern Storage:
//Store the normalized pattern in a HashMap and count occurrences.
//Result:
//The maximum count of any pattern in the map gives the desired result.
//Complexity:
//Time Complexity:
//O(m×n), where m is the number of rows and n is the number of columns.
//Space Complexity:
//O(m×n) for storing patterns in the HashMap.


//You are given an m x n binary matrix matrix.
//You can choose any number of columns in the matrix and flip every cell in that column (i.e., Change the
// value of the cell from 0 to 1 or vice versa).
//Return the maximum number of rows that have all values equal after some number of flips.
//
//Example 1:
//Input: matrix = [[0,1],[1,1]]
//Output: 1
//Explanation: After flipping no values, 1 row has all values equal.

//Example 2:
//Input: matrix = [[0,1],[1,0]]
//Output: 2
//Explanation: After flipping values in the first column, both rows have equal values.

//Example 3:
//Input: matrix = [[0,0,0],[0,0,1],[1,1,0]]
//Output: 2
//Explanation: After flipping values in the first two columns, the last two rows have equal values.
//
//Constraints:
//m == matrix.length
//n == matrix[i].length
//1 <= m, n <= 300
//matrix[i][j] is either 0 or 1.
