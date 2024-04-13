package P1_100.P85_Maximal_Rectangle;

import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        char[][] matrix1 = {
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        };

        int result1 = solution.maximalRectangle(matrix1);
        System.out.println("Output 1: " + result1); // Output: 6


        char[][] matrix2 = {
                {'0'}
        };

        int result2 = solution.maximalRectangle(matrix2);
        System.out.println("Output 2: " + result2); // Output: 0


        char[][] matrix3 = {
                {'1'}
        };

        int result3 = solution.maximalRectangle(matrix3);
        System.out.println("Output 3: " + result3); // Output: 1
    }

    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] heights = new int[rows][cols];
        int maxArea = 0;

        // calculate heights matrix
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == '1') {
                    heights[i][j] = (i == 0) ? 1 : heights[i - 1][j] + 1;
                }
            }
        }

        // find largest rectangle in each row
        for (int i = 0; i < rows; i++) {
            maxArea = Math.max(maxArea, largestRectangleArea(heights[i]));
        }

        return maxArea;
    }

    // find largest rectangle in histogram
    private int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }

        int n = heights.length;
        int maxArea = 0;
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i <= n; i++) {
            int h = (i == n) ? 0 : heights[i];
            while (!stack.isEmpty() && h < heights[stack.peek()]) {
                int height = heights[stack.pop()];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }
            stack.push(i);
        }

        return maxArea;
    }
}

//Цей код обчислює найбільшу площу прямокутника в кожному рядку, використовуючи алгоритм гістограми, та відстежує
// найбільшу площу, яка була знайдена.
//Перетворення матриці висот: Спочатку ми перетворюємо задану бінарну матрицю у матрицю цілих чисел, де кожна
// комірка відображає висоту послідовних одиниць над нею. Це означає, що якщо у нас є рядок, де є кілька
// одиниць підряд, то відповідні комірки у матриці висот отримають відповідні значення.
//Підрахунок найбільшого прямокутника в кожному рядку: Потім ми обчислюємо найбільший прямокутник у кожному рядку,
// використовуючи алгоритм для обчислення найбільшого прямокутника в гістограмі.
//Збереження максимальної площі: Ми відстежуємо найбільшу площу, яка була знайдена до цього моменту.

//Given a rows x cols binary matrix filled with 0's and 1's, find the largest rectangle containing only
// 1's and return its area.
//
//Example 1:
//Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
//Output: 6
//Explanation: The maximal rectangle is shown in the above picture.

//Example 2:
//Input: matrix = [["0"]]
//Output: 0

//Example 3:
//Input: matrix = [["1"]]
//Output: 1
//
//Constraints:
//rows == matrix.length
//cols == matrix[i].length
//1 <= row, cols <= 200
//matrix[i][j] is '0' or '1'.