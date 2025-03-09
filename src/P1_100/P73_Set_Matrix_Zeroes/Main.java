package P1_100.P73_Set_Matrix_Zeroes;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[][] matrix = {{1,1,1,1,1},{1,1,1,1,1},{1,1,0,1,1},{1,1,1,1,1},{1,1,1,1,1}};
        solution.setZeroes(matrix);
    }

    public void setZeroes(int[][] matrix) {
        //create new array = copy of matrix
        int[][] copyArray =  Arrays.copyOf(matrix, matrix.length);

        int m = copyArray.length; //number of rows
        int n = copyArray[0].length; //number of columns

        boolean[] rowZero = new boolean[m]; //for zero in row
        boolean[] colZero = new boolean[n]; //for zero in column

        //find rows and columns whose have zero
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (copyArray[i][j] == 0) { //if found
                    rowZero[i] = true; //replace flag
                    colZero[j] = true; //and here
                }
            }
        }

        //rep;ace data if
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rowZero[i] || colZero[j]) { //founded zero
                    copyArray[i][j] = 0; //replace
                }
            }
        }

        for (int i = 0; i < copyArray.length; i++) {
            for (int j = 0; j < copyArray[i].length; j++) {
                System.out.print(copyArray[i][j] + " ");
            }
            System.out.println();
        }

    }
}

//Given an m x n integer matrix matrix, if an element is 0, set its entire row and column to 0's.
//You must do it in place.

//Example 1:
//Input: matrix = [[1,1,1],[1,0,1],[1,1,1]]
//Output: [[1,0,1],[0,0,0],[1,0,1]]

//Example 2:
//Input: matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
//Output: [[0,0,0,0],[0,4,5,0],[0,3,1,0]]
//
//Constraints:
//m == matrix.length
//n == matrix[0].length
//1 <= m, n <= 200
//-231 <= matrix[i][j] <= 231 - 1
