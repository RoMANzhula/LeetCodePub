package P1301_1400.P1351_Count_Negative_Numbers_in_a_Sorted_Matrix;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[][] grid1 = {
                {4, 3, 2, -1},
                {3, 2, 1, -1},
                {1, 1, -1, -2},
                {-1, -1, -2, -3}
        };

        int[][] grid2 = {
                {3, 2},
                {1, 0}
        };

        System.out.println(solution.countNegatives(grid1)); // Output: 8
        System.out.println(solution.countNegatives(grid2)); // Output: 0
    }

    public int countNegatives(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int row = 0;
        int col = n - 1;
        int count = 0;

        while (row < m && col >= 0) {
            if (grid[row][col] < 0) {
                count += (m - row);
                col--;
            } else {
                row++;
            }
        }

        return count;
    }

}

//Complexity:
// time - O(n + m)
// spacce - O(1)


//Given a m x n matrix grid which is sorted in non-increasing order both row-wise and column-wise, return the
// number of negative numbers in grid.

//Example 1:
//Input: grid = [[4,3,2,-1],[3,2,1,-1],[1,1,-1,-2],[-1,-1,-2,-3]]
//Output: 8
//Explanation: There are 8 negatives number in the matrix.

//Example 2:
//Input: grid = [[3,2],[1,0]]
//Output: 0

//Constraints:
//m == grid.length
//n == grid[i].length
//1 <= m, n <= 100
//-100 <= grid[i][j] <= 100
