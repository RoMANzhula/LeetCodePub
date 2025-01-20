package P2601_2700.P2661_First_Completely_Painted_Row_or_Column;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] arr1 = {1, 3, 4, 2};
        int[][] mat1 = {{1, 4}, {2, 3}};
        System.out.println(solution.firstCompleteIndex(arr1, mat1)); // Output: 2

        // Example 2
        int[] arr2 = {2, 8, 7, 4, 1, 3, 5, 6, 9};
        int[][] mat2 = {{3, 2, 5}, {1, 4, 6}, {8, 7, 9}};
        System.out.println(solution.firstCompleteIndex(arr2, mat2)); // Output: 3
    }

    public int firstCompleteIndex(int[] arr, int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        // map to store the position of each number in the matrix
        Map<Integer, int[]> positionMap = new HashMap<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                positionMap.put(mat[i][j], new int[]{i, j});
            }
        }

        // arrays to track the paint count for rows and columns
        int[] rows = new int[m];
        int[] cols = new int[n];

        // iterate through arr to paint cells
        for (int i = 0; i < arr.length; i++) {
            int num = arr[i];
            int[] pos = positionMap.get(num); // get position of th number
            int row = pos[0];
            int col = pos[1];

            //increment the paint counts for the corresponding row and column
            rows[row]++;
            cols[col]++;

            // check if th row or column is fully painted
            if (rows[row] == n || cols[col] == m) return i;
        }

        // if no row or column is fully painted (shouldn't happen with valid input)
        return -1;
    }

}

//Explanation:
//Position Mapping:
//The positionMap maps each integer in mat to its row and column indices, allowing O(1) lookup for the
// position of any number.
//Tracking Painted Rows and Columns:
//The rows and cols arrays keep count of painted cells for each row and column.
//Efficient Painting:
//For each number in arr, we update the corresponding row and column counts in O(1).
//We immediately check if any row or column is fully painted, ensuring the function terminates as soon as a
// solution is found.
//Performance:
//The algorithm processes each number in arr once, resulting in O(m * n) time complexity, which is efficient
// given the constraints.
//Space complexity is O(m + n) for the rows and cols arrays, and O(m * n) for the positionMap.


//You are given a 0-indexed integer array arr, and an m x n integer matrix mat. arr and mat both contain all
// the integers in the range [1, m * n].
//Go through each index i in arr starting from index 0 and paint the cell in mat containing the integer arr[i].
//Return the smallest index i at which either a row or a column will be completely painted in mat.
//
//Example 1:
//image explanation for example 1
//Input: arr = [1,3,4,2], mat = [[1,4],[2,3]]
//Output: 2
//Explanation: The moves are shown in order, and both the first row and second column of the matrix become fully
// painted at arr[2].

//Example 2:
//image explanation for example 2
//Input: arr = [2,8,7,4,1,3,5,6,9], mat = [[3,2,5],[1,4,6],[8,7,9]]
//Output: 3
//Explanation: The second column becomes fully painted at arr[3].
//
//Constraints:
//m == mat.length
//n = mat[i].length
//arr.length == m * n
//1 <= m, n <= 105
//1 <= m * n <= 105
//1 <= arr[i], mat[r][c] <= m * n
//All the integers of arr are unique.
//All the integers of mat are unique.
