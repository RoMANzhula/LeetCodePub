package P1201_1300.P1260_Shift_2D_Grid;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[][] grid = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        int k = 1;

        List<List<Integer>> result = solution.shiftGrid(grid, k);

        for (List<Integer> row : result) {
            System.out.println(row);
        }
    }

    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int size = m * n;

        k %= size;

        int[][] shifted = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int index = i * n + j;
                int newIndex = (index + k) % size;

                int newRow = newIndex / n;
                int newCol = newIndex % n;

                shifted[newRow][newCol] = grid[i][j];
            }
        }

        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                row.add(shifted[i][j]);
            }
            result.add(row);
        }

        return result;
    }

}

//Complexity:
// time and space - O(m * n)
// m - number of rows
// n - number of columns


//Given a 2D grid of size m x n and an integer k. You need to shift the grid k times.
//In one shift operation:
//Element at grid[i][j] moves to grid[i][j + 1].
//Element at grid[i][n - 1] moves to grid[i + 1][0].
//Element at grid[m - 1][n - 1] moves to grid[0][0].
//Return the 2D grid after applying shift operation k times.

//Example 1:
//Input: grid = [[1,2,3],[4,5,6],[7,8,9]], k = 1
//Output: [[9,1,2],[3,4,5],[6,7,8]]

//Example 2:
//Input: grid = [[3,8,1,9],[19,7,2,5],[4,6,11,10],[12,0,21,13]], k = 4
//Output: [[12,0,21,13],[3,8,1,9],[19,7,2,5],[4,6,11,10]]

//Example 3:
//Input: grid = [[1,2,3],[4,5,6],[7,8,9]], k = 9
//Output: [[1,2,3],[4,5,6],[7,8,9]]

//Constraints:
//m == grid.length
//n == grid[i].length
//1 <= m <= 50
//1 <= n <= 50
//-1000 <= grid[i][j] <= 1000
//0 <= k <= 100
