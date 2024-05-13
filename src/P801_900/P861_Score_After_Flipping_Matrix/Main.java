package P801_900.P861_Score_After_Flipping_Matrix;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[][] grid1 = {{0, 0, 1, 1}, {1, 0, 1, 0}, {1, 1, 0, 0}};
        System.out.println("Example 1 Output: " + solution.matrixScore(grid1)); // output: 39

        int[][] grid2 = {{0}};
        System.out.println("Example 2 Output: " + solution.matrixScore(grid2)); // output: 1
    }

    public int matrixScore(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // Step 1: Ensure that the first column has all 1s
        for (int i = 0; i < m; i++) {
            if (grid[i][0] == 0) {
                flipRow(grid, i);
            }
        }

        // Step 2: Ensure each column after the first has more 1s than 0s
        for (int j = 1; j < n; j++) {
            int countOnes = 0;
            for (int i = 0; i < m; i++) {
                countOnes += grid[i][j];
            }
            if (countOnes < (m + 1) / 2) {
                flipColumn(grid, j);
            }
        }

        // Step 3: Calculate the total score
        int score = 0;
        for (int i = 0; i < m; i++) {
            int rowScore = 0;
            for (int j = 0; j < n; j++) {
                rowScore = rowScore * 2 + grid[i][j];
            }
            score += rowScore;
        }

        return score;
    }

    private void flipRow(int[][] grid, int row) {
        for (int j = 0; j < grid[row].length; j++) {
            grid[row][j] = 1 - grid[row][j];
        }
    }

    private void flipColumn(int[][] grid, int col) {
        for (int i = 0; i < grid.length; i++) {
            grid[i][col] = 1 - grid[i][col];
        }
    }
}

//Проблема полягає в тому, щоб знайти найвищий можливий бал після будь-якої кількості ходів в заданій бінарній
// матриці. У кожному кроці можна вибрати будь-який рядок або стовпець і змінити кожне значення в цьому рядку
// або стовпці (тобто, змінити всі 0 на 1 і всі 1 на 0). Кожен рядок матриці інтерпретується як двійкове число, а
// бал матриці - це сума цих чисел.
//Отже, щоб розв'язати цю проблему, ми використовуємо алгоритм, що складається з наступних кроків:
//Переконатися, що перший стовпець має всі 1.
//Перевірити, чи має кожен стовпець після першого більше одиниць, ніж нулів.
//Перевірити, чи має кожен рядок більше одиниць, ніж нулів.
//Підрахувати загальний бал матриці.
//Цей підхід дозволяє знайти найвищий можливий бал, використовуючи зазначені правила для перетворення рядків і стовпців.

//You are given an m x n binary matrix grid.
//
//A move consists of choosing any row or column and toggling each value in that row or column (i.e., changing
// all 0's to 1's, and all 1's to 0's).
//Every row of the matrix is interpreted as a binary number, and the score of the matrix is the sum of these numbers.
//Return the highest possible score after making any number of moves (including zero moves).
//
//Example 1:
//Input: grid = [[0,0,1,1],[1,0,1,0],[1,1,0,0]]
//Output: 39
//Explanation: 0b1111 + 0b1001 + 0b1111 = 15 + 9 + 15 = 39

//Example 2:
//Input: grid = [[0]]
//Output: 1
//
//Constraints:
//m == grid.length
//n == grid[i].length
//1 <= m, n <= 20
//grid[i][j] is either 0 or 1.