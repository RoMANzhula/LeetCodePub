package P3201_3300.P3212_Count_Submatrices_With_Equal_Frequency_of_X_and_Y;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        char[][] grid1 = {
                {'X', 'Y', '.'},
                {'Y', '.', '.'}
        };

        char[][] grid2 = {
                {'X', 'X'},
                {'X', 'Y'}
        };

        char[][] grid3 = {
                {'.', '.'},
                {'.', '.'}
        };

        System.out.println(solution.numberOfSubmatrices(grid1)); // 3
        System.out.println(solution.numberOfSubmatrices(grid2)); // 0
        System.out.println(solution.numberOfSubmatrices(grid3)); // 0
    }

    public int numberOfSubmatrices(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] balance = new int[m + 1][n + 1];
        int[][] countX = new int[m + 1][n + 1];

        // build prefix sums
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                int val = 0;
                int x = 0;

                if (grid[i - 1][j - 1] == 'X') {
                    val = 1;
                    x = 1;
                } else if (grid[i - 1][j - 1] == 'Y') {
                    val = -1;
                }

                balance[i][j] = val
                        + balance[i - 1][j]
                        + balance[i][j - 1]
                        - balance[i - 1][j - 1];

                countX[i][j] = x
                        + countX[i - 1][j]
                        + countX[i][j - 1]
                        - countX[i - 1][j - 1];
            }
        }

        int result = 0;

        // check all submatrices starting at (0,0)
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {

                if (balance[i][j] == 0 && countX[i][j] > 0) {
                    result++;
                }
            }
        }

        return result;
    }

}

//Complexity:
// time and space - O(m * n)


//Given a 2D character matrix grid, where grid[i][j] is either 'X', 'Y', or '.', return the number of submatrices
// that contain:
//grid[0][0]
//an equal frequency of 'X' and 'Y'.
//at least one 'X'.

//Example 1:
//Input: grid = [["X","Y","."],["Y",".","."]]
//Output: 3
//Explanation:

//Example 2:
//Input: grid = [["X","X"],["X","Y"]]
//Output: 0
//Explanation:
//No submatrix has an equal frequency of 'X' and 'Y'.

//Example 3:
//Input: grid = [[".","."],[".","."]]
//Output: 0
//Explanation:
//No submatrix has at least one 'X'.

//Constraints:
//1 <= grid.length, grid[i].length <= 1000
//grid[i][j] is either 'X', 'Y', or '.'.
