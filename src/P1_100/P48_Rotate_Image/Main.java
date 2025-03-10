package P1_100.P48_Rotate_Image;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int [][] matrix1 = {{1,2,3},{4,5,6},{7,8,9}};
        rotate(matrix1);
        for (int i = 0; i < matrix1.length; i++) {
            System.out.println(Arrays.toString(matrix1[i]));
        }
    }

    public static void rotate(int[][] matrix) {
        int n = matrix.length; //size our matrix(because it is square)
        int[][] result = new int[n][n]; //create new array for result

        //iterate to all matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[j][n - 1 - i] = matrix[i][j]; //reverse 90 grad
            }
        }

        //copy the rotated result back to the original matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = result[i][j];
            }
        }
    }

}

//You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).
//You have to rotate the image in-place, which means you have to modify the input 2D matrix directly.
//DO NOT allocate another 2D matrix and do the rotation.

//Example 1:
//Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
//Output: [[7,4,1],[8,5,2],[9,6,3]]

//Example 2:
//Input: matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
//Output: [[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
//
//Constraints:
//n == matrix.length == matrix[i].length
//1 <= n <= 20
//-1000 <= matrix[i][j] <= 1000
