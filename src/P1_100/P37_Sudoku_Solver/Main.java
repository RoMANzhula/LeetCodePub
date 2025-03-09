package P1_100.P37_Sudoku_Solver;

public class Main {

    public void solveSudoku(char[][] board) {
        if (board == null || board.length == 0) {
            return;
        }

        solve(board); // start solving the Sudoku
    }

    private boolean solve(char[][] board) {
        for (int row = 0; row < board.length; row++) { // iterate through each row
            for (int col = 0; col < board[0].length; col++) { // iterate through each column

                if (board[row][col] == '.') { // if the cell is empty ('.'), try to fill it
                    for (char num = '1'; num <= '9'; num++) { // try placing numbers 1 to 9

                        if (isValid(board, row, col, num)) { // check if the number is valid in the current position
                            board[row][col] = num; // place the number in the cell

                            if (solve(board)) { // recursively attempt to solve the board with this placement
                                return true; // if the board is solved
                            } else {
                                board[row][col] = '.'; // if it leads to an invalid state
                            }
                        }
                    }
                    return false; // if no valid number can be placed
                }
            }
        }
        return true; // when the board is successfully solved
    }

    private boolean isValid(char[][] board, int row, int col, char num) {
        for (int i = 0; i < 9; i++) { // check row, column, and 3x3 subgrid
            if (board[row][i] == num || // check if the number exists in the same row
                    board[i][col] == num || // check if the number exists in the same column
                    board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == num) { // check if the number exists in the 3x3 subgrid
                return false; // if found in any of these, return false
            }
        }
        return true; // if the number is not found, it's a valid placement
    }

}

//Write a program to solve a Sudoku puzzle by filling the empty cells.
//A sudoku solution must satisfy all of the following rules:
//Each of the digits 1-9 must occur exactly once in each row.
//Each of the digits 1-9 must occur exactly once in each column.
//Each of the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
//The '.' character indicates empty cells.

//Example 1:
//Input: board =
// [["5","3",".",".","7",".",".",".","."],
// ["6",".",".","1","9","5",".",".","."],
// [".","9","8",".",".",".",".","6","."],
// ["8",".",".",".","6",".",".",".","3"],
// ["4",".",".","8",".","3",".",".","1"],
// ["7",".",".",".","2",".",".",".","6"],
// [".","6",".",".",".",".","2","8","."],
// [".",".",".","4","1","9",".",".","5"],
// [".",".",".",".","8",".",".","7","9"]]
//Output: [
// ["5","3","4","6","7","8","9","1","2"],
// ["6","7","2","1","9","5","3","4","8"],
// ["1","9","8","3","4","2","5","6","7"],
// ["8","5","9","7","6","1","4","2","3"],
// ["4","2","6","8","5","3","7","9","1"],
// ["7","1","3","9","2","4","8","5","6"],
// ["9","6","1","5","3","7","2","8","4"],
// ["2","8","7","4","1","9","6","3","5"],
// ["3","4","5","2","8","6","1","7","9"]]
//Explanation: The input board is shown above and the only valid solution is shown below:

//Constraints:
//board.length == 9
//board[i].length == 9
//board[i][j] is a digit or '.'.
//It is guaranteed that the input board has only one solution.
