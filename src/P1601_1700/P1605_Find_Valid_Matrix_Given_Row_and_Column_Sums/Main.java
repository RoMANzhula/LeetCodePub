package P1601_1700.P1605_Find_Valid_Matrix_Given_Row_and_Column_Sums;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();
        // Example 1
        int[] rowSum1 = {3, 8};
        int[] colSum1 = {4, 7};
        int[][] result1 = solution.restoreMatrix(rowSum1, colSum1);
        printMatrix(result1);

        // Example 2
        int[] rowSum2 = {5, 7, 10};
        int[] colSum2 = {8, 6, 8};
        int[][] result2 = solution.restoreMatrix(rowSum2, colSum2);
        printMatrix(result2);
    }

    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }

    public int[][] restoreMatrix(int[] rowSum, int[] colSum) {
        int numRows = rowSum.length;
        int numCols = colSum.length;
        int[][] matrix = new int[numRows][numCols];

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                int minVal = Math.min(rowSum[i], colSum[j]);
                matrix[i][j] = minVal;
                rowSum[i] -= minVal;
                colSum[j] -= minVal;
            }
        }

        return matrix;
    }
}

//Explanation:
//Initialization: We create a matrix matrix of size numRows x numCols initialized to zero.
//Filling the Matrix:
//For each cell (i, j), we determine the value to be placed by taking the minimum of rowSum[i] and colSum[j].
//We place this minimum value in matrix[i][j].
//We then subtract this value from rowSum[i] and colSum[j] to update the remaining sums.
//Repeat until all cells are filled, ensuring that at each step the row and column sums are updated correctly.
//This greedy approach guarantees that we can construct a matrix satisfying the given constraints, as it's always
// possible to adjust subsequent cells to fit the remaining sums.


//You are given two arrays rowSum and colSum of non-negative integers where rowSum[i] is the sum of the
// elements in the ith row and colSum[j] is the sum of the elements of the jth column of a 2D matrix. In other
// words, you do not know the elements of the matrix, but you do know the sums of each row and column.
//Find any matrix of non-negative integers of size rowSum.length x colSum.length that satisfies the rowSum and
// colSum requirements.
//Return a 2D array representing any matrix that fulfills the requirements. It's guaranteed that at least one
// matrix that fulfills the requirements exists.
//
//Example 1:
//Input: rowSum = [3,8], colSum = [4,7]
//Output: [[3,0],
//         [1,7]]
//Explanation:
//0th row: 3 + 0 = 3 == rowSum[0]
//1st row: 1 + 7 = 8 == rowSum[1]
//0th column: 3 + 1 = 4 == colSum[0]
//1st column: 0 + 7 = 7 == colSum[1]
//The row and column sums match, and all matrix elements are non-negative.
//Another possible matrix is: [[1,2],
//                             [3,5]]

//Example 2:
//Input: rowSum = [5,7,10], colSum = [8,6,8]
//Output: [[0,5,0],
//         [6,1,0],
//         [2,0,8]]

//Constraints:
//1 <= rowSum.length, colSum.length <= 500
//0 <= rowSum[i], colSum[i] <= 108
//sum(rowSum) == sum(colSum)