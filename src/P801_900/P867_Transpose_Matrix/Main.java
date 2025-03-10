package P801_900.P867_Transpose_Matrix;

public class Main {
    public static int[][] transpose(int[][] matrix) {
        int rows = matrix.length;
        int columns = matrix[0].length;

        int[][] result = new int[columns][rows]; //a new matrix for transposed values (для транспонованих значень

        // filling in a new matrix
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result[j][i] = matrix[i][j]; // rows and columns are swapped
            }
        }

        return result; //bingo
    }

    public static void main(String[] args) {
        int[][] matrix1 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] result1 = transpose(matrix1);

        System.out.println("Original Matrix:");
        printMatrix(matrix1);

        System.out.println("\nTransposed Matrix:");
        printMatrix(result1);

        System.out.println("\n------------------------");

        int[][] matrix2 = {{1, 2, 3}, {4, 5, 6}};
        int[][] result2 = transpose(matrix2);

        System.out.println("Original Matrix:");
        printMatrix(matrix2);

        System.out.println("\nTransposed Matrix:");
        printMatrix(result2);
    }

    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }
}
//потрібно отримати транспоновану матрицю, тобто матрицю, в якій рядки і стовпці міняються місцями
//Given a 2D integer array matrix, return the transpose of matrix.
//The transpose of a matrix is the matrix flipped over its main diagonal, switching the matrix's row and column indices.

//Example 1:
//Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
//Output: [[1,4,7],[2,5,8],[3,6,9]]

//Example 2:
//Input: matrix = [[1,2,3],[4,5,6]]
//Output: [[1,4],[2,5],[3,6]]

//Constraints:
//m == matrix.length
//n == matrix[i].length
//1 <= m, n <= 1000
//1 <= m * n <= 105
//-109 <= matrix[i][j] <= 109}
