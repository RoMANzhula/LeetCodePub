package P2301_2400.P2373_Largest_Local_Values_in_a_Matrix;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[][] grid1 = {
                {9,9,8,1},
                {5,6,2,6},
                {8,2,6,4},
                {6,2,2,2}
        };
        int[][] maxLocal1 = solution.largestLocal(grid1);
        System.out.println("Example 1 Output:");
        solution.printMatrix(maxLocal1); // Output: [[9,9],[8,6]]

        int[][] grid2 = {
                {1,1,1,1,1},
                {1,1,1,1,1},
                {1,1,2,1,1},
                {1,1,1,1,1},
                {1,1,1,1,1}
        };
        int[][] maxLocal2 = solution.largestLocal(grid2);
        System.out.println("Example 2 Output:");
        solution.printMatrix(maxLocal2); //Output: [[2,2,2],[2,2,2],[2,2,2]]
    }

    public void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public int[][] largestLocal(int[][] grid) {
        int n = grid.length;
        int[][] maxLocal = new int[n - 2][n - 2];

        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                int max = Integer.MIN_VALUE;
                for (int k = i - 1; k <= i + 1; k++) {
                    for (int l = j - 1; l <= j + 1; l++) {
                        max = Math.max(max, grid[k][l]);
                    }
                }
                maxLocal[i - 1][j - 1] = max;
            }
        }

        return maxLocal;

    }
}

//Ми маємо матрицю цілих чисел n на n (n x n), і нам треба створити нову матрицю maxLocal розміром (n - 2) на
// (n - 2). Кожен елемент maxLocal[i][j] матриці maxLocal має бути рівним найбільшому значенню з усіх елементів, які
// входять в 3 x 3 матрицю у вихідній матриці, з центром у рядку i + 1 і стовпчику j + 1.
//Отже, для кожної позиції (i, j) у новій матриці ми проходимо по всім елементам 3 x 3 підматриці, що містить цю
// позицію, і знаходимо максимальне значення серед них. Це значення записуємо в матрицю maxLocal у відповідну
// позицію (i, j).
//У внутрішньому циклі ми перебираємо всі елементи 3 x 3 підматриці, що має центр у поточній позиції (i, j). Починаючи
// з рядка (i - 1) до (i + 1) і стовпчика (j - 1) до (j + 1), ми знаходимо максимальний елемент серед них за
// допомогою методу Math.max().

//You are given an n x n integer matrix grid.
//Generate an integer matrix maxLocal of size (n - 2) x (n - 2) such that:
//maxLocal[i][j] is equal to the largest value of the 3 x 3 matrix in grid centered around row i + 1 and column j + 1.
//In other words, we want to find the largest value in every contiguous 3 x 3 matrix in grid.
//Return the generated matrix.
//
//Example 1:
//Input: grid = [[9,9,8,1],[5,6,2,6],[8,2,6,4],[6,2,2,2]]
//Output: [[9,9],[8,6]]
//Explanation: The diagram above shows the original matrix and the generated matrix.
//Notice that each value in the generated matrix corresponds to the largest value of a contiguous 3 x 3 matrix in grid.

//Example 2:
//Input: grid = [[1,1,1,1,1],[1,1,1,1,1],[1,1,2,1,1],[1,1,1,1,1],[1,1,1,1,1]]
//Output: [[2,2,2],[2,2,2],[2,2,2]]
//Explanation: Notice that the 2 is contained within every contiguous 3 x 3 matrix in grid.
//
//Constraints:
//n == grid.length == grid[i].length
//3 <= n <= 100
//1 <= grid[i][j] <= 100