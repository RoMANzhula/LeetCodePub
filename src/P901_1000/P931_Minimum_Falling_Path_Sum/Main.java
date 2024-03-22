package P901_1000.P931_Minimum_Falling_Path_Sum;

public class Main {
    public static void main(String[] args) {
        // Example 1
        int[][] matrix1 = {{2, 1, 3}, {6, 5, 4}, {7, 8, 9}};
        System.out.println("Example 1 Output: " + minFallingPathSum(matrix1));

        // Example 2
        int[][] matrix2 = {{-19, 57}, {-40, -5}};
        System.out.println("Example 2 Output: " + minFallingPathSum(matrix2));
    }
    public static int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;

        // Iterate through the matrix starting from the second row
        for (int row = 1; row < n; row++) {
            for (int col = 0; col < n; col++) {
                // Calculate the minimum falling path sum for each cell
                int minPrevPath = matrix[row - 1][col];
                if (col > 0) {
                    minPrevPath = Math.min(minPrevPath, matrix[row - 1][col - 1]);
                }
                if (col < n - 1) {
                    minPrevPath = Math.min(minPrevPath, matrix[row - 1][col + 1]);
                }

                // Update the current cell with the minimum falling path sum
                matrix[row][col] += minPrevPath;
            }
        }

        // Find the minimum sum in the last row
        int minSum = Integer.MAX_VALUE;
        for (int col = 0; col < n; col++) {
            minSum = Math.min(minSum, matrix[n - 1][col]);
        }

        return minSum;
    }
}

//Ініціалізація:
//
//Визначаємо розмірність матриці n.
//Починаємо ітерацію через матрицю, починаючи з другого рядка (for (int row = 1; row < n; row++)).
//Обчислення мінімального шляху для кожної клітини:
//
//Для кожної клітини у поточному рядку обчислюємо мінімальний шлях із попереднього рядка, який може призвести
// до поточної клітини.
//Мінімальний шлях може йти з клітини вище, зліва вище, або справа вище в залежності від положення клітини.
//Оновлення значень в поточній клітині:
//
//Поточне значення клітини оновлюється, додаючи до нього мінімальний шлях з попереднього кроку.
//Знаходження мінімальної суми в останньому рядку:
//
//Після завершення ітерації усіх рядків, шукаємо мінімальне значення в останньому рядку, оскільки це і буде
// мінімальною сумою на шляху.
//Повернення результату:
//
//Повертаємо знайдене мінімальне значення як результат.
//Цей алгоритм дозволяє знайти мінімальну суму на шляху вниз по матриці, починаючи з будь-якого елемента першого
// рядка і рухаючись вниз за правилами задачі.


//Given an n x n array of integers matrix, return the minimum sum of any falling path through matrix.
//
//A falling path starts at any element in the first row and chooses the element in the next row that is either
// directly below or diagonally left/right. Specifically, the next element from position (row, col) will be (row + 1, col - 1), (row + 1, col), or (row + 1, col + 1).

//Example 1:
//Input: matrix = [[2,1,3],[6,5,4],[7,8,9]]
//Output: 13
//Explanation: There are two falling paths with a minimum sum as shown.

//Example 2:
//Input: matrix = [[-19,57],[-40,-5]]
//Output: -59
//Explanation: The falling path with a minimum sum is shown.

//Constraints:
//n == matrix.length == matrix[i].length
//1 <= n <= 100
//-100 <= matrix[i][j] <= 100
