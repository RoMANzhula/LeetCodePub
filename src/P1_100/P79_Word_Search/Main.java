package P1_100.P79_Word_Search;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        char[][] board = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };

        String word1 = "ABCCED";
        String word2 = "SEE";
        String word3 = "ABCB";

        System.out.println("Word \"ABCCED\" exists: " + solution.exist(board, word1)); // true
        System.out.println("Word \"SEE\" exists: " + solution.exist(board, word2)); //true
        System.out.println("Word \"ABCB\" exists: " + solution.exist(board, word3)); //false

    }

    public boolean exist(char[][] board, String word) {
        int rows = board.length;
        int cols = board[0].length;

        // loop through the entire board
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // if we find the first character of the word, start the search
                if (board[i][j] == word.charAt(0) && backtrack(board, word, i, j, 0)) {
                    return true;
                }
            }
        }

        // if word is not found in any starting position
        return false;
    }

    private boolean backtrack(char[][] board, String word, int i, int j, int index) {
        // if we have found all characters of the word, return true
        if (index == word.length()) {
            return true;
        }

        // check if current position is out of bounds or not matching with the current character
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != word.charAt(index)) {
            return false;
        }

        // Temporarily mark the current cell as visited
        char temp = board[i][j];
        board[i][j] = ' ';

        // check in all four directions for the next character of the word
        boolean found = backtrack(board, word, i + 1, j, index + 1) ||
                backtrack(board, word, i - 1, j, index + 1) ||
                backtrack(board, word, i, j + 1, index + 1) ||
                backtrack(board, word, i, j - 1, index + 1);

        // restore the cell to its original state
        board[i][j] = temp;

        return found;
    }

}

//Цей код використовується для перевірки того, чи існує слово в заданій матриці board. Він розпочинає ітерацію крізь
// кожну комірку матриці і починає пошук з кожної з них. Метод backtrack рекурсивно досліджує всі можливі шляхи, щоб
// знайти слово.

//Given an m x n grid of characters board and a string word, return true if word exists in the grid.
//The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally
// or vertically neighboring. The same letter cell may not be used more than once.
//
//Example 1:
//Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
//Output: true

//Example 2:
//Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
//Output: true

//Example 3:
//Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
//Output: false
//
//Constraints:
//m == board.length
//n = board[i].length
//1 <= m, n <= 6
//1 <= word.length <= 15
//board and word consists of only lowercase and uppercase English letters.
