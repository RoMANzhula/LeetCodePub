package P1_100.P74_Search_a_2D_Matrix;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[][] matrix = {{1,3,5,7},
            {10,11,16,20},
            {23,30,34,60}};

        int target1 = 3;
        int target2 = 13;

        System.out.println("Matrix, Target = " + target1 + " → " + solution.searchMatrix(matrix, target1)); // true
        System.out.println("Matrix, target = " + target2 + " → " + solution.searchMatrix(matrix, target2)); // false
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;

        int left = 0;
        int right = m * n - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int midValue = matrix[mid / n][mid % n]; // convert 1D index to 2D

            if (midValue == target) {
                return true;
            } else if (midValue < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return false;
    }

}

//Complexity:
// time - O(log(m * n))
// space - O(1)


//You are given an m x n integer matrix matrix with the following two properties:
//Each row is sorted in non-decreasing order.
//The first integer of each row is greater than the last integer of the previous row.
//Given an integer target, return true if target is in matrix or false otherwise.
//You must write a solution in O(log(m * n)) time complexity.

//Example 1:
//Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
//Output: true

//Example 2:
//Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
//Output: false

//Constraints:
//m == matrix.length
//n == matrix[i].length
//1 <= m, n <= 100
//-104 <= matrix[i][j], target <= 104
