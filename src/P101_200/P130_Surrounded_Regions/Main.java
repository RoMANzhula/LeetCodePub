package P101_200.P130_Surrounded_Regions;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        char[][] board = {
                {'X','X','X','X'},
                {'X','O','O','X'},
                {'X','X','O','X'},
                {'X','O','X','X'}
        };

        solution.solve(board);
        printBoard(board);
    }

    static void printBoard(char[][] board) {
        for (char[] row : board) {
            for (char c : row) {
                System.out.print(c + " ");
            }

            System.out.println();
        }
    }

    public void solve (char[][] board) {
        if (board == null || board.length == 0) return;

        int m = board.length;
        int n = board[0].length;

        // mark border-connected 'O's
        for (int i = 0; i < m; i++) {
            dfs(board, i, 0);       // left border
            dfs(board, i, n - 1);   // right border
        }

        for (int j = 0; j < n; j++) {
            dfs(board, 0, j);       // top border
            dfs(board, m - 1, j);   // bottom border
        }

        //flip surrounded 'O' to 'X', and restore marked '#' back to 'O'
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';   // surrounded
                } else if (board[i][j] == '#') {
                    board[i][j] = 'O';   // safe
                }
            }
        }
    }

    private void dfs(char[][] board, int i, int j) {
        int m = board.length;
        int n = board[0].length;

        // boundary + condition check
        if (i < 0 || j < 0 || i >= m || j >= n || board[i][j] != 'O') {
            return;
        }

        //mark as visited (safe)
        board[i][j] = '#';

        // explore neighbors
        dfs(board, i + 1, j);
        dfs(board, i - 1, j);
        dfs(board, i, j + 1);
        dfs(board, i, j - 1);
    }

}

//Complexity:
// time and space - O(m * n)


//You are given an m x n matrix board containing letters 'X' and 'O', capture regions that are surrounded:
//Connect: A cell is connected to adjacent cells horizontally or vertically.
//Region: To form a region connect every 'O' cell.
//Surround: A region is surrounded if none of the 'O' cells in that region are on the edge of the board. Such
// regions are completely enclosed by 'X' cells.
//To capture a surrounded region, replace all 'O's with 'X's in-place within the original board. You do not need to
// return anything.

//Example 1:
//Input: board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
//Output: [["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
//Explanation:
//In the above diagram, the bottom region is not captured because it is on the edge of the board and cannot be surrounded.

//Example 2:
//Input: board = [["X"]]
//Output: [["X"]]

//Constraints:
//m == board.length
//n == board[i].length
//1 <= m, n <= 200
//board[i][j] is 'X' or 'O'.
