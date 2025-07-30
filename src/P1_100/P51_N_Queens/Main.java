package P1_100.P51_N_Queens;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int n = 4;
        List<List<String>> result = solution.solveNQueens(n);

        for (List<String> sol : result) {
            for (String row : sol) {
                System.out.println(row);
            }
            System.out.println();
        }
    }

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> solutions = new ArrayList<>();
        char[][] board = new char[n][n];

        // Iiitialize board with dots
        for (char[] row : board) {
            Arrays.fill(row, '.');
        }

        // start backtracking from the first row
        backtrack(0, board, solutions);
        return solutions;
    }

    private void backtrack(int row, char[][] board, List<List<String>> solutions) {
        int n = board.length;

        if (row == n) {
            solutions.add(construct(board));
            return;
        }

        for (int col = 0; col < n; col++) {
            if (isSafe(board, row, col)) {
                board[row][col] = 'Q';       // place queen
                backtrack(row + 1, board, solutions); // move to next row
                board[row][col] = '.';       // backtrack
            }
        }
    }

    private boolean isSafe(char[][] board, int row, int col) {
        int n = board.length;

        // check column
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 'Q') return false;
        }

        // check left upper diagonal
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') return false;
        }

        // check right upper diagonal
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (board[i][j] == 'Q') return false;
        }

        return true;
    }

    private List<String> construct(char[][] board) {
        List<String> res = new ArrayList<>();

        for (char[] row : board) {
            res.add(new String(row));
        }

        return res;
    }

}


//The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack
// each other.
//Given an integer n, return all distinct solutions to the n-queens puzzle. You may return the answer in any order.
//Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a
// queen and an empty space, respectively.

//Example 1:
//Input: n = 4
//Output: [[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
//Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above

//Example 2:
//Input: n = 1
//Output: [["Q"]]

//Constraints:
//1 <= n <= 9
