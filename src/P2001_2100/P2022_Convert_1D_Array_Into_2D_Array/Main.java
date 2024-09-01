package P2001_2100.P2022_Convert_1D_Array_Into_2D_Array;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] original1 = {1, 2, 3, 4};
        int m1 = 2, n1 = 2;
        int[][] result1 = solution.construct2DArray(original1, m1, n1);
        solution.print2DArray(result1); // Output: [[1, 2], [3, 4]]

        int[] original2 = {1, 2, 3};
        int m2 = 1, n2 = 3;
        int[][] result2 = solution.construct2DArray(original2, m2, n2);
        solution.print2DArray(result2); // Output: [[1, 2, 3]]

        int[] original3 = {1, 2};
        int m3 = 1, n3 = 1;
        int[][] result3 = solution.construct2DArray(original3, m3, n3);
        solution.print2DArray(result3); // Output: []
    }

//    public void print2DArray(int[][] array) {
//        for (int[] row : array) {
//            for (int element : row) {
//                System.out.print(element + " ");
//            }
//            System.out.println();
//        }
//    }

    public void print2DArray(int[][] array) {
        System.out.print("[");
        for (int i = 0; i < array.length; i++) {
            System.out.print("[");
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j]);
                if (j < array[i].length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.print("]");
            if (i < array.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    public int[][] construct2DArray(int[] original, int m, int n) {
        // Step 1: Check if it's possible to create the m x n array
        if (original.length != m * n) {
            return new int[0][0];  // Return an empty 2D array
        }

        // Step 2: Initialize the 2D array
        int[][] result = new int[m][n];

        // Step 3: Fill the 2D array with elements from original
        for (int i = 0; i < original.length; i++) {
            result[i / n][i % n] = original[i];
        }

        // Step 4: Return the constructed 2D array
        return result;
    }
}


//You are given a 0-indexed 1-dimensional (1D) integer array original, and two integers, m and n. You are tasked with
// creating a 2-dimensional (2D) array with  m rows and n columns using all the elements from original.
//The elements from indices 0 to n - 1 (inclusive) of original should form the first row of the
// constructed 2D array, the elements from indices n to 2 * n - 1 (inclusive) should form the
// second row of the constructed 2D array, and so on.
//Return an m x n 2D array constructed according to the above procedure, or an empty 2D array if it is impossible.
//
//Example 1:
//Input: original = [1,2,3,4], m = 2, n = 2
//Output: [[1,2],[3,4]]
//Explanation: The constructed 2D array should contain 2 rows and 2 columns.
//The first group of n=2 elements in original, [1,2], becomes the first row in the constructed 2D array.
//The second group of n=2 elements in original, [3,4], becomes the second row in the constructed 2D array.

//Example 2:
//Input: original = [1,2,3], m = 1, n = 3
//Output: [[1,2,3]]
//Explanation: The constructed 2D array should contain 1 row and 3 columns.
//Put all three elements in original into the first row of the constructed 2D array.

//Example 3:
//Input: original = [1,2], m = 1, n = 1
//Output: []
//Explanation: There are 2 elements in original.
//It is impossible to fit 2 elements in a 1x1 2D array, so return an empty 2D array.
//
//Constraints:
//1 <= original.length <= 5 * 104
//1 <= original[i] <= 105
//1 <= m, n <= 4 * 104