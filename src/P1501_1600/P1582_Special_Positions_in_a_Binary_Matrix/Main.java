package P1501_1600.P1582_Special_Positions_in_a_Binary_Matrix;

public class Main {

    public static void main(String[] args) {
        int[][] mat1 = {{1, 0, 0}, {0, 0, 1}, {1, 0, 0}};
        System.out.println("Output 1: " + numSpecial(mat1));

        int[][] mat2 = {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
        System.out.println("Output 2: " + numSpecial(mat2));
    }

    public static int numSpecial(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int[] rowSum = new int[m];
        int[] colSum = new int[n];
        int count = 0;

        // Calculate the sum of each row and column
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                rowSum[i] += mat[i][j];
                colSum[j] += mat[i][j];
            }
        }

        // Check for special positions
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 1 && rowSum[i] == 1 && colSum[j] == 1) {
                    count++;
                }
            }
        }

        return count;
    }

}

//Given an m x n binary matrix mat, return the number of special positions in mat.
//A position (i, j) is called special if mat[i][j] == 1 and all other elements in row i and column j are
// 0 (rows and columns are 0-indexed).

//Example 1:
//Input: mat = [[1,0,0],[0,0,1],[1,0,0]]
//Output: 1
//Explanation: (1, 2) is a special position because mat[1][2] == 1 and all other elements in row 1 and column 2 are 0.

//Example 2:
//Input: mat = [[1,0,0],[0,1,0],[0,0,1]]
//Output: 3
//Explanation: (0, 0), (1, 1) and (2, 2) are special positions.

//Constraints:
//m == mat.length
//n == mat[i].length
//1 <= m, n <= 100
//mat[i][j] is either 0 or 1.