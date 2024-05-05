package P201_300.P240_Search_a_2D_Matrix_II;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[][] matrix1 = {
            {1, 4, 7, 11, 15},
            {2, 5, 8, 12, 19},
            {3, 6, 9, 16, 22},
            {10, 13, 14, 17, 24},
            {18, 21, 23, 26, 30}
        };

        int target1 = 5;
        System.out.println(solution.searchMatrix(matrix1, target1)); // Output: true

        int target2 = 20;
        System.out.println(solution.searchMatrix(matrix1, target2)); // Output: false
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;

        int row = 0;
        int col = cols - 1;

        while (row < rows && col >= 0) {
            if (matrix[row][col] == target) {
                return true;
            } else if (matrix[row][col] < target) {
                row++;
            } else {
                col--;
            }
        }

        return false;
    }
}

//Це рішення починає з правого верхнього кута матриці. Воно порівнює значення, яке потрібно знайти (це ціль), з
// елементом на цій позиції. Якщо ціль дорівнює поточному елементу, повертається true. Якщо ціль менше за
// поточний елемент, рухаємося вліво в тому ж рядку. Якщо ціль більша за поточний елемент, рухаємося вниз
// до наступного рядка. Цей процес продовжується, доки не знайдено ціль або не дійшли до кінця матриці.

//Write an efficient algorithm that searches for a value target in an m x n integer matrix matrix. This matrix
// has the following properties:
//Integers in each row are sorted in ascending from left to right.
//Integers in each column are sorted in ascending from top to bottom.
//
//Example 1:
//Input: matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
//Output: true

//Example 2:
//Input: matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 20
//Output: false
//
//Constraints:
//m == matrix.length
//n == matrix[i].length
//1 <= n, m <= 300
//-109 <= matrix[i][j] <= 109
//All the integers in each row are sorted in ascending order.
//All the integers in each column are sorted in ascending order.
//-109 <= target <= 109