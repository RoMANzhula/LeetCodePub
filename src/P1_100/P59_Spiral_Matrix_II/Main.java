package P1_100.P59_Spiral_Matrix_II;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int n = 3;
        int[][] matrix = solution.generateMatrix(n);

        for (int[] row : matrix) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }

    public int[][] generateMatrix(int n) {
        int[][] result = new int[n][n];

        int left = 0, right = n - 1;
        int top = 0, bottom = n - 1;
        int num = 1;

        while (left <= right && top <= bottom) {
            // traverse from left to right
            for (int j = left; j <= right; j++) {
                result[top][j] = num++;
            }

            top++;

            // traverse from top to bottom
            for (int i = top; i <= bottom; i++) {
                result[i][right] = num++;
            }

            right--;

            // traverse from right to left
            if (top <= bottom) {
                for (int j = right; j >= left; j--) {
                    result[bottom][j] = num++;
                }

                bottom--;
            }

            // traverse from bottom to top
            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    result[i][left] = num++;
                }

                left++;
            }
        }

        return result;
    }

}

//Complexity:
// time and space - O(n^2)


//Given a positive integer n, generate an n x n matrix filled with elements from 1 to n2 in spiral order.

//Example 1:
//Input: n = 3
//Output: [[1,2,3],[8,9,4],[7,6,5]]

//Example 2:
//Input: n = 1
//Output: [[1]]

//Constraints:
//1 <= n <= 20
