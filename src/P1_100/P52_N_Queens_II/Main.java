package P1_100.P52_N_Queens_II;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int n = 4;
        System.out.println("Total solutions for n = " + n + ": " + solution.totalNQueens(n));

        n = 1;
        System.out.println("Total solutions for n = " + n + ": " + solution.totalNQueens(n));
    }

    private int count = 0;

    public int totalNQueens(int n) {
        // columns, diagonals, and anti-diagonals usage tracking
        boolean[] cols = new boolean[n];
        boolean[] diag = new boolean[2 * n - 1];       // from top-left to bottom-right
        boolean[] antiDiag = new boolean[2 * n - 1];   // from top-right to bottom-left

        backtrack(0, n, cols, diag, antiDiag);
        return count;
    }

    private void backtrack(int row, int n, boolean[] cols, boolean[] diag, boolean[] antiDiag) {
        if (row == n) {
            count++;
            return;
        }

        for (int col = 0; col < n; col++) {
            int d = row - col + n - 1;
            int ad = row + col;
            if (cols[col] || diag[d] || antiDiag[ad]) continue;

            // place queen
            cols[col] = diag[d] = antiDiag[ad] = true;

            backtrack(row + 1, n, cols, diag, antiDiag);

            // backtrack
            cols[col] = diag[d] = antiDiag[ad] = false;
        }
    }

}

//Complexity:
// time - O(n!)
// space - O(n)


//The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack
// each other.
//Given an integer n, return the number of distinct solutions to the n-queens puzzle.

//Example 1:
//Input: n = 4
//Output: 2
//Explanation: There are two distinct solutions to the 4-queens puzzle as shown.

//Example 2:
//Input: n = 1
//Output: 1

//Constraints:
//1 <= n <= 9
