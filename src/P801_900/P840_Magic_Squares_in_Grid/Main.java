package P801_900.P840_Magic_Squares_in_Grid;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[][] grid1 = {
                {4, 3, 8, 4},
                {9, 5, 1, 9},
                {2, 7, 6, 2}
        };

        int[][] grid2 = {
                {8}
        };

        System.out.println(solution.numMagicSquaresInside(grid1));  // Output: 1
        System.out.println(solution.numMagicSquaresInside(grid2));  // Output: 0
    }

    public int numMagicSquaresInside(int[][] grid) {
        int count = 0;

        // Iterate over all possible 3x3 subgrids
        for (int i = 0; i < grid.length - 2; i++) {
            for (int j = 0; j < grid[i].length - 2; j++) {
                if (isMagic(grid, i, j)) {
                    count++;
                }
            }
        }

        return count;
    }

    private boolean isMagic(int[][] grid, int row, int col) {
        // Check if all numbers are distinct and between 1 and 9
        boolean[] seen = new boolean[10];

        // Check the distinctness and valid range of the numbers
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int num = grid[row + i][col + j];
                if (num < 1 || num > 9 || seen[num]) {
                    return false;
                }
                seen[num] = true;
            }
        }

        // Check the sums of rows, columns, and diagonals
        int sum1 = grid[row][col] + grid[row][col + 1] + grid[row][col + 2];  // first row
        int sum2 = grid[row + 1][col] + grid[row + 1][col + 1] + grid[row + 1][col + 2];  // second row
        int sum3 = grid[row + 2][col] + grid[row + 2][col + 1] + grid[row + 2][col + 2];  // third row
        int sum4 = grid[row][col] + grid[row + 1][col] + grid[row + 2][col];  // first column
        int sum5 = grid[row][col + 1] + grid[row + 1][col + 1] + grid[row + 2][col + 1];  // second column
        int sum6 = grid[row][col + 2] + grid[row + 1][col + 2] + grid[row + 2][col + 2];  // third column
        int sum7 = grid[row][col] + grid[row + 1][col + 1] + grid[row + 2][col + 2];  // main diagonal
        int sum8 = grid[row][col + 2] + grid[row + 1][col + 1] + grid[row + 2][col];  // anti diagonal

        return sum1 == 15 && sum2 == 15 && sum3 == 15 &&
                sum4 == 15 && sum5 == 15 && sum6 == 15 &&
                sum7 == 15 && sum8 == 15;
    }
}

//Explanation:
//Grid Iteration: The numMagicSquaresInside method iterates over all possible 3x3 subgrids in the given grid.
//Magic Square Validation: The isMagic method checks if the 3x3 subgrid, starting at the top-left
// corner (row, col), is a magic square:
//It checks that all numbers in the 3x3 subgrid are distinct and between 1 and 9.
//It verifies that the sums of the rows, columns, and diagonals are all equal to 15 (the magic constant for a
// 3x3 grid with numbers from 1 to 9).
//Counting Magic Squares: If a 3x3 subgrid is identified as a magic square, the count is incremented.
//Performance:
//This solution efficiently checks each possible 3x3 subgrid within the grid and is suitable given the
// constraints of the problem.
//This approach should correctly count the number of 3x3 magic square subgrids within any given grid.


//A 3 x 3 magic square is a 3 x 3 grid filled with distinct numbers from 1 to 9 such that each row, column, and
// both diagonals all have the same sum.
//Given a row x col grid of integers, how many 3 x 3 contiguous magic square subgrids are there?
//Note: while a magic square can only contain numbers from 1 to 9, grid may contain numbers up to 15.
//
//Example 1:
//Input: grid = [[4,3,8,4],[9,5,1,9],[2,7,6,2]]
//Output: 1
//Explanation:
//The following subgrid is a 3 x 3 magic square:
//while this one is not:
//In total, there is only one magic square inside the given grid.

//Example 2:
//Input: grid = [[8]]
//Output: 0
//
//Constraints:
//row == grid.length
//col == grid[i].length
//1 <= row, col <= 10
//0 <= grid[i][j] <= 15