package P1801_1900.P1895_Largest_Magic_Square;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[][] grid1 = {
                {7, 1, 4, 5, 6},
                {2, 5, 1, 6, 4},
                {1, 5, 4, 3, 2},
                {1, 2, 7, 3, 4}
        };

        int[][] grid2 = {
                {5, 1, 3, 1},
                {9, 3, 3, 1},
                {1, 3, 3, 8}
        };

        System.out.println("Out: " + solution.largestMagicSquare(grid1)); // 3
        System.out.println("Out: " + solution.largestMagicSquare(grid2)); // 2
    }

    public int largestMagicSquare(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // prefix sums
        int[][] row = new int[m][n + 1];
        int[][] col = new int[m + 1][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                row[i][j + 1] = row[i][j] + grid[i][j];
                col[i + 1][j] = col[i][j] + grid[i][j];
            }
        }

        int maxK = Math.min(m, n);

        for (int k = maxK; k >= 1; k--) {
            for (int r = 0; r + k <= m; r++) {
                for (int c = 0; c + k <= n; c++) {
                    if (isMagic(grid, row, col, r, c, k)) {
                        return k;
                    }
                }
            }
        }

        return 1; //at least 1x1 is always magic
    }

    private boolean isMagic(int[][] grid, int[][] row, int[][] col,
                            int r, int c, int k) {

        int target = row[r][c + k] - row[r][c];

        //check rows
        for (int i = 0; i < k; i++) {
            int sum = row[r + i][c + k] - row[r + i][c];
            if (sum != target) return false;
        }

        // check columns
        for (int j = 0; j < k; j++) {
            int sum = col[r + k][c + j] - col[r][c + j];
            if (sum != target) return false;
        }

        // main diagonal
        int diag1 = 0;
        for (int i = 0; i < k; i++) {
            diag1 += grid[r + i][c + i];
        }
        if (diag1 != target) return false;

        // anti-diagonal
        int diag2 = 0;
        for (int i = 0; i < k; i++) {
            diag2 += grid[r + i][c + k - 1 - i];
        }
        return diag2 == target;
    }

}

//Complexity:
// time - O(min(m,n)³ × m × n)
// space - O(m * n)


//A k x k magic square is a k x k grid filled with integers such that every row sum, every column sum, and both
// diagonal sums are all equal. The integers in the magic square do not have to be distinct. Every 1 x 1 grid is
// trivially a magic square.

//Given an m x n integer grid, return the size (i.e., the side length k) of the largest magic square that can be
// found within this grid.

//Example 1:
//Input: grid = [[7,1,4,5,6],[2,5,1,6,4],[1,5,4,3,2],[1,2,7,3,4]]
//Output: 3
//Explanation: The largest magic square has a size of 3.
//Every row sum, column sum, and diagonal sum of this magic square is equal to 12.
//- Row sums: 5+1+6 = 5+4+3 = 2+7+3 = 12
//- Column sums: 5+5+2 = 1+4+7 = 6+3+3 = 12
//- Diagonal sums: 5+4+3 = 6+4+2 = 12

//Example 2:
//Input: grid = [[5,1,3,1],[9,3,3,1],[1,3,3,8]]
//Output: 2

//Constraints:
//m == grid.length
//n == grid[i].length
//1 <= m, n <= 50
//1 <= grid[i][j] <= 106
