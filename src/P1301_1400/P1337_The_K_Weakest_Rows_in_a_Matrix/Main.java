package P1301_1400.P1337_The_K_Weakest_Rows_in_a_Matrix;

import java.util.Arrays;

public class Main {

    public int[] kWeakestRows(int[][] mat, int k) {
        int rows = mat.length; //rows in input matrix
        int[] soldiersCount = new int[rows]; //soldiers in each row

        //calculate the number of soldiers in each row
        for (int i = 0; i < rows; i++) {
            soldiersCount[i] = countSoldiers(mat[i]);
        }

        //create an array of row indices and sort it based on soldiers count
        Integer[] rowIndices = new Integer[rows]; //we use Integer for sort data
        for (int i = 0; i < rows; i++) {
            rowIndices[i] = i;
        }

        Arrays.sort(rowIndices, (a, b) -> { //sorting indices rows of matrix by numbers of soldiers
            int countA = soldiersCount[a];
            int countB = soldiersCount[b];
            if (countA == countB) {
                return a - b; //if counts are equal, compare row indices
            }
            return countA - countB;
        });

        //create the result array with the k weakest rows
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = rowIndices[i]; //add indices of rows
        }

        return result; //bingo
    }


    private int countSoldiers(int[] row) { //our helper method to count the number of soldiers in a row
        int count = 0;
        for (int value : row) {
            if (value == 1) {
                count++;
            } else {
                break; //as all 1's appear to the left of 0's
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Main solution = new Main();
        int[][] mat1 = {
                {1, 1, 0, 0, 0},
                {1, 1, 1, 1, 0},
                {1, 0, 0, 0, 0},
                {1, 1, 0, 0, 0},
                {1, 1, 1, 1, 1}
        };
        int k1 = 3;
        int[] result1 = solution.kWeakestRows(mat1, k1);
        System.out.println(Arrays.toString(result1)); // Output: [2, 0, 3]

        int[][] mat2 = {
                {1, 0, 0, 0},
                {1, 1, 1, 1},
                {1, 0, 0, 0},
                {1, 0, 0, 0}
        };
        int k2 = 2;
        int[] result2 = solution.kWeakestRows(mat2, k2);
        System.out.println(Arrays.toString(result2)); // Output: [0, 2]
    }

}

//You are given an m x n binary matrix mat of 1's (representing soldiers) and 0's (representing civilians). The
// soldiers are positioned in front of the civilians. That is, all the 1's will appear to the left of all the 0's
// in each row.
//A row i is weaker than a row j if one of the following is true:
//The number of soldiers in row i is less than the number of soldiers in row j.
//Both rows have the same number of soldiers and i < j.
//Return the indices of the k weakest rows in the matrix ordered from weakest to strongest.

//Example 1:
//Input: mat =
//[[1,1,0,0,0],
// [1,1,1,1,0],
// [1,0,0,0,0],
// [1,1,0,0,0],
// [1,1,1,1,1]],
//k = 3
//Output: [2,0,3]
//Explanation:
//The number of soldiers in each row is:
//- Row 0: 2
//- Row 1: 4
//- Row 2: 1
//- Row 3: 2
//- Row 4: 5
//The rows ordered from weakest to strongest are [2,0,3,1,4].

//Example 2:
//Input: mat =
//[[1,0,0,0],
// [1,1,1,1],
// [1,0,0,0],
// [1,0,0,0]],
//k = 2
//Output: [0,2]
//Explanation:
//The number of soldiers in each row is:
//- Row 0: 1
//- Row 1: 4
//- Row 2: 1
//- Row 3: 1
//The rows ordered from weakest to strongest are [0,2,3,1].

//Constraints:
//m == mat.length
//n == mat[i].length
//2 <= n, m <= 100
//1 <= k <= m
//matrix[i][j] is either 0 or 1.
