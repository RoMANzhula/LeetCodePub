package P2401_2500.P2482_Difference_Between_Ones_and_Zeros_in_Row_And_Column;

public class Main {
    public static void main(String[] args) {
        int[][] grid1 = {{0, 1, 1}, {1, 0, 1}, {0, 0, 1}};
        int[][] result1 = differenceMatrix(grid1);
        printMatrix(result1);

        int[][] grid2 = {{1, 1, 1}, {1, 1, 1}};
        int[][] result2 = differenceMatrix(grid2);
        printMatrix(result2);

    }

    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static int[][] differenceMatrix(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] diff = new int[m][n];

        int[] onesRow = new int[m];
        int[] onesCol = new int[n];
        int[] zerosRow = new int[m];
        int[] zerosCol = new int[n];

        // Calculate the count of ones in each row and column
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                onesRow[i] += grid[i][j];
                onesCol[j] += grid[i][j];
            }
        }

        // Calculate the count of zeros in each row and column
        for (int i = 0; i < m; i++) {
            zerosRow[i] = n - onesRow[i];
        }

        for (int j = 0; j < n; j++) {
            zerosCol[j] = m - onesCol[j];
        }

        // Calculate the difference matrix
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                diff[i][j] = onesRow[i] + onesCol[j] - zerosRow[i] - zerosCol[j];
            }
        }

        return diff;
    }
}

//Цей код на Java вирішує задачу створення матриці різниці для заданої бінарної матриці. Основна ідея полягає в тому,
// щоб підрахувати кількість одиниць та нулів для кожного рядка та кожного стовпця, а потім використовувати ці
// підрахунки для створення матриці різниці.
//
//Підрахунок кількості одиниць в рядках та стовпцях:
//
//Для кожного елемента у вхідній матриці підраховується сума одиниць в рядку та стовпці.
//Підрахунок кількості нулів в рядках та стовпцях:
//
//Кількість нулів в рядках розраховується відніманням кількості одиниць від загальної кількості елементів у рядку.
//Кількість нулів в стовпцях розраховується відніманням кількості одиниць від загальної кількості елементів у стовпці.
//Створення матриці різниці:
//
//Елемент матриці різниці обчислюється відповідно до формули:
// diff[i][j] = onesRow[i] + onesCol[j] - zerosRow[i] - zerosCol[j].
//Програма демонструє використання цього підходу для наданого прикладу та виводить результат у консоль.


//You are given a 0-indexed m x n binary matrix grid.
//
//A 0-indexed m x n difference matrix diff is created with the following procedure:
//
//Let the number of ones in the ith row be onesRowi.
//Let the number of ones in the jth column be onesColj.
//Let the number of zeros in the ith row be zerosRowi.
//Let the number of zeros in the jth column be zerosColj.
//diff[i][j] = onesRowi + onesColj - zerosRowi - zerosColj
//Return the difference matrix diff.
//
//
//
//Example 1:
//
//
//Input: grid = [[0,1,1],[1,0,1],[0,0,1]]
//Output: [[0,0,4],[0,0,4],[-2,-2,2]]
//Explanation:
//- diff[0][0] = onesRow0 + onesCol0 - zerosRow0 - zerosCol0 = 2 + 1 - 1 - 2 = 0
//- diff[0][1] = onesRow0 + onesCol1 - zerosRow0 - zerosCol1 = 2 + 1 - 1 - 2 = 0
//- diff[0][2] = onesRow0 + onesCol2 - zerosRow0 - zerosCol2 = 2 + 3 - 1 - 0 = 4
//- diff[1][0] = onesRow1 + onesCol0 - zerosRow1 - zerosCol0 = 2 + 1 - 1 - 2 = 0
//- diff[1][1] = onesRow1 + onesCol1 - zerosRow1 - zerosCol1 = 2 + 1 - 1 - 2 = 0
//- diff[1][2] = onesRow1 + onesCol2 - zerosRow1 - zerosCol2 = 2 + 3 - 1 - 0 = 4
//- diff[2][0] = onesRow2 + onesCol0 - zerosRow2 - zerosCol0 = 1 + 1 - 2 - 2 = -2
//- diff[2][1] = onesRow2 + onesCol1 - zerosRow2 - zerosCol1 = 1 + 1 - 2 - 2 = -2
//- diff[2][2] = onesRow2 + onesCol2 - zerosRow2 - zerosCol2 = 1 + 3 - 2 - 0 = 2
//Example 2:
//
//
//Input: grid = [[1,1,1],[1,1,1]]
//Output: [[5,5,5],[5,5,5]]
//Explanation:
//- diff[0][0] = onesRow0 + onesCol0 - zerosRow0 - zerosCol0 = 3 + 2 - 0 - 0 = 5
//- diff[0][1] = onesRow0 + onesCol1 - zerosRow0 - zerosCol1 = 3 + 2 - 0 - 0 = 5
//- diff[0][2] = onesRow0 + onesCol2 - zerosRow0 - zerosCol2 = 3 + 2 - 0 - 0 = 5
//- diff[1][0] = onesRow1 + onesCol0 - zerosRow1 - zerosCol0 = 3 + 2 - 0 - 0 = 5
//- diff[1][1] = onesRow1 + onesCol1 - zerosRow1 - zerosCol1 = 3 + 2 - 0 - 0 = 5
//- diff[1][2] = onesRow1 + onesCol2 - zerosRow1 - zerosCol2 = 3 + 2 - 0 - 0 = 5
//
//
//Constraints:
//
//m == grid.length
//n == grid[i].length
//1 <= m, n <= 105
//1 <= m * n <= 105
//grid[i][j] is either 0 or 1.
