package P1001_1100.P1074_Number_of_Submatrices_That_Sum_to_Target;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        int[][] matrix1 = {{0, 1, 0}, {1, 1, 1}, {0, 1, 0}};
        int target1 = 0;
        System.out.println(numSubmatrixSumTarget(matrix1, target1)); // Output: 4

        int[][] matrix2 = {{1, -1}, {-1, 1}};
        int target2 = 0;
        System.out.println(numSubmatrixSumTarget(matrix2, target2)); // Output: 5

        int[][] matrix3 = {{904}};
        int target3 = 0;
        System.out.println(numSubmatrixSumTarget(matrix3, target3)); // Output: 0
    }

    public static int numSubmatrixSumTarget(int[][] matrix, int target) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int count = 0;

        // Precompute the prefix sum for each row
        for (int i = 0; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                matrix[i][j] += matrix[i][j - 1];
            }
        }

        // Iterate over all possible column pairs
        for (int col1 = 0; col1 < cols; col1++) {
            for (int col2 = col1; col2 < cols; col2++) {
                Map<Integer, Integer> prefixSumCount = new HashMap<>();
                prefixSumCount.put(0, 1);
                int currentSum = 0;

                // Iterate over all rows
                for (int row = 0; row < rows; row++) {
                    int submatrixSum = matrix[row][col2];
                    if (col1 > 0) {
                        submatrixSum -= matrix[row][col1 - 1];
                    }

                    currentSum += submatrixSum;

                    // Check if there is a submatrix with the target sum
                    if (prefixSumCount.containsKey(currentSum - target)) {
                        count += prefixSumCount.get(currentSum - target);
                    }

                    // Update the prefix sum count
                    prefixSumCount.put(currentSum, prefixSumCount.getOrDefault(currentSum, 0) + 1);
                }
            }
        }

        return count;
    }
}

//Задача передбачає знаходження кількості підматриць у заданій матриці, сума елементів яких дорівнює
// заданому значенню (цілеве значення).
//
//Основна ідея розв'язку полягає в використанні попередньо обчислених префіксних сум для кожного рядка
// матриці. Спочатку ми обчислюємо префіксні суми для кожного рядка, щоб швидше знаходити суму підматриць.
//
//Потім ми використовуємо два вкладені цикли для вибору всіх можливих пар стовпців. Для кожної такої пари
// ми використовуємо мапу для відстеження кількості підматриць з певною сумою. По ходу ітерації по рядках,
// ми обчислюємо суму підматриці, обмеженої вибраними стовпцями, та використовуємо мапу для визначення
// кількості підматриць із заданою сумою.
//
//Якщо знаходимо підматрицю з сумою, яка дорівнює цільовому значенню, ми збільшуємо лічильник. Після
// завершення обходу отримуємо загальну кількість підматриць, що задовольняють умові.

//Given a matrix and a target, return the number of non-empty submatrices that sum to target.
//A submatrix x1, y1, x2, y2 is the set of all cells matrix[x][y] with x1 <= x <= x2 and y1 <= y <= y2.
//Two submatrices (x1, y1, x2, y2) and (x1', y1', x2', y2') are different if they have some coordinate that
// is different: for example, if x1 != x1'.


//Example 1:
//Input: matrix = [[0,1,0],[1,1,1],[0,1,0]], target = 0
//Output: 4
//Explanation: The four 1x1 submatrices that only contain 0.

//Example 2:
//Input: matrix = [[1,-1],[-1,1]], target = 0
//Output: 5
//Explanation: The two 1x2 submatrices, plus the two 2x1 submatrices, plus the 2x2 submatrix.

//Example 3:
//Input: matrix = [[904]], target = 0
//Output: 0

//Constraints:
//1 <= matrix.length <= 100
//1 <= matrix[0].length <= 100
//-1000 <= matrix[i] <= 1000
//-10^8 <= target <= 10^8
