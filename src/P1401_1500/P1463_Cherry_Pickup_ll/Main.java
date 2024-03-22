package P1401_1500.P1463_Cherry_Pickup_ll;

public class Main {
    public static void main(String[] args) {
        int[][] grid1 = {{3,1,1},{2,5,1},{1,5,5},{2,1,1}};
        int[][] grid2 = {{1,0,0,0,0,0,1},{2,0,0,0,0,3,0},{2,0,9,0,0,0,0},{0,3,0,5,4,0,0},{1,0,2,3,0,0,6}};

        Main cherryPicker = new Main();
        System.out.println(cherryPicker.cherryPickup(grid1)); // Output: 24
        System.out.println(cherryPicker.cherryPickup(grid2)); // Output: 28
    }

    public int cherryPickup(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        // dp[i][j][k] represents the maximum cherries collected starting from position (i, j) for robot 1
        // and position (k, j) for robot 2
        int[][][] dp = new int[rows][cols][cols];

        // initialize dp array
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                for (int k = 0; k < cols; k++) {
                    dp[i][j][k] = -1;
                }
            }
        }

        // call the recursive function to find the maximum cherries
        return Math.max(0, cherryPickupHelper(grid, 0, 0, cols - 1, dp));
    }

    private int cherryPickupHelper(int[][] grid, int row, int col1, int col2, int[][][] dp) {
        int rows = grid.length;
        int cols = grid[0].length;

        // if out of bounds or if either robot has reached the bottom row, return 0 cherries
        if (row == rows || col1 < 0 || col1 == cols || col2 < 0 || col2 == cols)
            return 0;

        // if the value is already calculated, return it
        if (dp[row][col1][col2] != -1)
            return dp[row][col1][col2];

        // calculate cherries collected by both robots
        int cherries = grid[row][col1] + (col1 != col2 ? grid[row][col2] : 0);

        // check all possible next positions for both robots
        int maxCherries = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                maxCherries = Math.max(maxCherries, cherries + cherryPickupHelper(grid, row + 1, col1 + i, col2 + j, dp));
            }
        }

        // store the result in dp array and return
        return dp[row][col1][col2] = maxCherries;
    }


}

//Задача полягає у знаходженні максимальної кількості вишень, які можуть бути зібрані двома роботами, що
// рухаються внизу сітки з вишнями, з деяких початкових позицій. Робот №1 починає зверху ліворуч, а
// робот №2 - зверху праворуч. Обидва роботи можуть рухатися лише вниз та діагонально вліво або вправо.
//Щоб вирішити цю задачу, ми використовуємо динамічне програмування. Ми створюємо тривимірний масив dp,
// де dp[i][j][k] представляє максимальну кількість вишень, які можуть бути зібрані, починаючи з позиції (i, j)
// для робота №1 та (i, k) для робота №2. Ми обчислюємо це значення рекурсивно, перебираючи всі можливі напрямки
// руху для обох роботів та обираючи кращий варіант.
//Використовуючи цей підхід, ми обчислюємо максимальну кількість вишень, яку можуть зібрати обидва роботи, та
// повертаємо це значення як результат.

//You are given a rows x cols matrix grid representing a field of cherries where grid[i][j] represents
// the number of cherries that you can collect from the (i, j) cell.
//You have two robots that can collect cherries for you:
//Robot #1 is located at the top-left corner (0, 0), and
//Robot #2 is located at the top-right corner (0, cols - 1).
//Return the maximum number of cherries collection using both robots by following the rules below:
//From a cell (i, j), robots can move to cell (i + 1, j - 1), (i + 1, j), or (i + 1, j + 1).
//When any robot passes through a cell, It picks up all cherries, and the cell becomes an empty cell.
//When both robots stay in the same cell, only one takes the cherries.
//Both robots cannot move outside of the grid at any moment.
//Both robots should reach the bottom row in grid.
//
//Example 1:
//Input: grid = [[3,1,1],[2,5,1],[1,5,5],[2,1,1]]
//Output: 24
//Explanation: Path of robot #1 and #2 are described in color green and blue respectively.
//Cherries taken by Robot #1, (3 + 2 + 5 + 2) = 12.
//Cherries taken by Robot #2, (1 + 5 + 5 + 1) = 12.
//Total of cherries: 12 + 12 = 24.

//Example 2:
//Input: grid = [[1,0,0,0,0,0,1],[2,0,0,0,0,3,0],[2,0,9,0,0,0,0],[0,3,0,5,4,0,0],[1,0,2,3,0,0,6]]
//Output: 28
//Explanation: Path of robot #1 and #2 are described in color green and blue respectively.
//Cherries taken by Robot #1, (1 + 9 + 5 + 2) = 17.
//Cherries taken by Robot #2, (1 + 3 + 4 + 3) = 11.
//Total of cherries: 17 + 11 = 28.
//
//Constraints:
//rows == grid.length
//cols == grid[i].length
//2 <= rows, cols <= 70
//0 <= grid[i][j] <= 100
