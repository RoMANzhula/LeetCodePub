package P3901_4000.P3963_Create_Grid_With_Exactly_One_Path;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(Arrays.toString(solution.createGrid(2, 3))); // [..., ##.]
        System.out.println(Arrays.toString(solution.createGrid(3, 3))); // [..., ##., ##.]
        System.out.println(Arrays.toString(solution.createGrid(1, 4))); // [....]
    }

    public String[] createGrid(int m, int n) {
        char[][] grid = new char[m][n];

        for (int i = 0; i < m; i++) {
            Arrays.fill(grid[i], '#');
        }

        for (int j = 0; j < n; j++) {
            grid[0][j] = '.';
        }

        for (int i = 0; i < m; i++) {
            grid[i][n - 1] = '.';
        }

        String[] result = new String[m];

        for (int i = 0; i < m; i++) {
            result[i] = new String(grid[i]);
        }

        return result;
    }

}

//Complexity:
// time and space - O(m * n)


//You are given two integers m and n, representing the number of rows and columns of a grid.
//Construct any m x n grid consisting only of the characters '.' and '#', where:
//'.' represents a free cell.
//'#' represents an obstacle cell.
//A valid path is a sequence of free cells that:
//Starts at the top-left cell (0, 0).
//Ends at the bottom-right cell (m - 1, n - 1).
//Moves only:
//Right, from (i, j) to (i, j + 1), or
//Down, from (i, j) to (i + 1, j).
//Return any grid such that there is exactly one valid path from the top-left cell to the bottom-right cell.

//Example 1:
//Input: m = 2, n = 3
//Output: ["..#","#.."]
//Explanation: here an image
//The only valid path is: (0,0) → (0,1) → (1,1) → (1,2)

//Example 2:
//Input: m = 3, n = 3
//Output: ["..#","#..","##."]
//Explanation: and here an image
//The only valid path is: (0,0) → (0,1) → (1,1) → (1,2) → (2,2)

//Example 3:
//Input: m = 1, n = 4
//Output: ["...."]
//Explanation:
//The only valid path is: (0,0) → (0,1) → (0,2) → (0,3)

//Constraints:
//1 <= m, n <= 25
