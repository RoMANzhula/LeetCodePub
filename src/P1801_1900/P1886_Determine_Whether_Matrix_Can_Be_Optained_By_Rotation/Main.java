package P1801_1900.P1886_Determine_Whether_Matrix_Can_Be_Optained_By_Rotation;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[][] mat = {
                {0, 1},
                {1, 0}
        };

        int[][] target = {
                {1, 0},
                {0, 1}
        };

        System.out.println(solution.findRotation(mat, target)); // true
    }

    public boolean findRotation(int[][] mat, int[][] target) {
        for (int i = 0; i < 4; i++) {
            if (areEqual(mat, target)) {
                return true;
            }
            rotate(mat);
        }

        return false;
    }

    private static void rotate(int[][] mat) {
        int n = mat.length;

        // transpose
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int temp = mat[i][j];
                mat[i][j] = mat[j][i];
                mat[j][i] = temp;
            }
        }

        // reverse each row
        for (int i = 0; i < n; i++) {
            reverse(mat[i]);
        }
    }

    private static void reverse(int[] row) {
        int left = 0, right = row.length - 1;

        while (left < right) {
            int temp = row[left];
            row[left] = row[right];
            row[right] = temp;
            left++;
            right--;
        }
    }


    private static boolean areEqual(int[][] a, int[][] b) {
        int n = a.length;

        for (int i = 0; i < n; i++) {
            if (!Arrays.equals(a[i], b[i])) {
                return false;
            }
        }

        return true;
    }

}

//Complexity:
// time - O(n^2)
// space - O(1)


//Given two n x n binary matrices mat and target, return true if it is possible to make mat equal to target by
// rotating mat in 90-degree increments, or false otherwise.

//Example 1:
//Input: mat = [[0,1],[1,0]], target = [[1,0],[0,1]]
//Output: true
//Explanation: We can rotate mat 90 degrees clockwise to make mat equal target.

//Example 2:
//Input: mat = [[0,1],[1,1]], target = [[1,0],[0,1]]
//Output: false
//Explanation: It is impossible to make mat equal to target by rotating mat.

//Example 3:
//Input: mat = [[0,0,0],[0,1,0],[1,1,1]], target = [[1,1,1],[0,1,0],[0,0,0]]
//Output: true
//Explanation: We can rotate mat 90 degrees clockwise two times to make mat equal target.

//Constraints:
//n == mat.length == target.length
//n == mat[i].length == target[i].length
//1 <= n <= 10
//mat[i][j] and target[i][j] are either 0 or 1.
