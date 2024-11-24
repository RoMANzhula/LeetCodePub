package P1901_2000.P1975_Maximum_Matrix_Sum;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[][] matrix1 = {{1, -1}, {-1, 1}};
        System.out.println("Output: " + solution.maxMatrixSum(matrix1)); // Output: 4

        int[][] matrix2 = {{1, 2, 3}, {-1, -2, -3}, {1, 2, 3}};
        System.out.println("Output: " + solution.maxMatrixSum(matrix2)); // Output: 16
    }

    public long maxMatrixSum(int[][] matrix) {
        int n = matrix.length;
        long totalSum = 0;
        int minAbsValue = Integer.MAX_VALUE;
        int negativeCount = 0;

        // Traverse the matrix to calculate total sum, count negatives, and find minimum absolute value.
        for (int[] ints : matrix) {
            for (int j = 0; j < n; j++) {
                totalSum += Math.abs(ints[j]);
                if (ints[j] < 0) {
                    negativeCount++;
                }
                minAbsValue = Math.min(minAbsValue, Math.abs(ints[j]));
            }
        }

        // If negative count is odd, subtract twice the smallest absolute value to maximize the sum.
        if (negativeCount % 2 != 0) {
            totalSum -= 2 * minAbsValue;
        }

        return totalSum;
    }
}

//Explanation:
//totalSum:
//Accumulate the absolute values of all elements to simulate flipping all negatives to positives.
//negativeCount:
//Count the number of negative elements to determine if an odd number of negative signs remain.
//minAbsValue:
//Keep track of the smallest absolute value to account for the case where we need to leave one element negative.
//Odd Negatives:
//If the count of negatives is odd, we subtract twice the smallest absolute value to maximize the overall sum.
//Complexity:
//Time Complexity: O(n 2 degree), where n is the size of the matrix.
//Space Complexity:
//O(1), as we use constant extra space.


//You are given an n x n integer matrix. You can do the following operation any number of times:
//Choose any two adjacent elements of matrix and multiply each of them by -1.
//Two elements are considered adjacent if and only if they share a border.
//Your goal is to maximize the summation of the matrix's elements. Return the maximum sum of the matrix's
// elements using the operation mentioned above.
//
//Example 1:
//Input: matrix = [[1,-1],[-1,1]]
//Output: 4
//Explanation: We can follow the following steps to reach sum equals 4:
//- Multiply the 2 elements in the first row by -1.
//- Multiply the 2 elements in the first column by -1.

//Example 2:
//Input: matrix = [[1,2,3],[-1,-2,-3],[1,2,3]]
//Output: 16
//Explanation: We can follow the following step to reach sum equals 16:
//- Multiply the 2 last elements in the second row by -1.

//Constraints:
//n == matrix.length == matrix[i].length
//2 <= n <= 250
//-105 <= matrix[i][j] <= 105