package P2001_2100.P2033_Minimum_Operations_to_Make_a_Uni_Value_Grid;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[][] grid1 = {{2, 4}, {6, 8}};
        int x1 = 2;
        System.out.println(solution.minOperations(grid1, x1)); // Output: 4

        int[][] grid2 = {{1, 5}, {2, 3}};
        int x2 = 1;
        System.out.println(solution.minOperations(grid2, x2)); // Output: 5

        int[][] grid3 = {{1, 2}, {3, 4}};
        int x3 = 2;
        System.out.println(solution.minOperations(grid3, x3)); // Output: -1
    }

    public int minOperations(int[][] grid, int x) {
        List<Integer> values = new ArrayList<>();
        int m = grid.length, n = grid[0].length;

        // flatten the grid into a list
        for (int[] row : grid) {
            for (int num : row) {
                values.add(num);
            }
        }

        //check if transformation is possible
        int remainder = values.get(0) % x;
        for (int num : values) {
            if (num % x != remainder) {
                return -1; // ff any number has a different remainder, it's impossible
            }
        }

        // sort values to find median
        Collections.sort(values);
        int median = values.get(values.size() / 2);

        // calculate operations needed to make all elements equal to median
        int operations = 0;
        for (int num : values) {
            operations += Math.abs(num - median) / x;
        }

        return operations;
    }

}

//Explanation of code:
//Flatten the 2D grid into a list:
//-Convert the grid into a one-dimensional list for easier processing.
//Check for the feasibility condition:
//-If the remainder when dividing by x is different across elements, return -1.
//Find the Median:
//-Sort the list and choose the middle element as the target value.
//Compute the operations:
//-Calculate the absolute difference between each element and the median, then divide by x to count the
// required operations.
//Complexity:
//Time Complexity: O(m * n log(m * n))
//Space complexity: O(m * n)


//You are given a 2D integer grid of size m x n and an integer x. In one operation, you can add x to or
// subtract x from any element in the grid.
//A uni-value grid is a grid where all the elements of it are equal.
//Return the minimum number of operations to make the grid uni-value. If it is not possible, return -1.

//Example 1:
//Input: grid = [[2,4],[6,8]], x = 2
//Output: 4
//Explanation: We can make every element equal to 4 by doing the following:
//- Add x to 2 once.
//- Subtract x from 6 once.
//- Subtract x from 8 twice.
//A total of 4 operations were used.

//Example 2:
//Input: grid = [[1,5],[2,3]], x = 1
//Output: 5
//Explanation: We can make every element equal to 3.

//Example 3:
//Input: grid = [[1,2],[3,4]], x = 2
//Output: -1
//Explanation: It is impossible to make every element equal.

//Constraints:
//m == grid.length
//n == grid[i].length
//1 <= m, n <= 105
//1 <= m * n <= 105
//1 <= x, grid[i][j] <= 104
