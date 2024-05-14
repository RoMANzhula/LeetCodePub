package P1201_1300.P1219_Path_with_Maximum_Gold;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[][] grid1 = {
                {0, 6, 0},
                {5, 8, 7},
                {0, 9, 0}
        };
        System.out.println(solution.getMaximumGold(grid1));  // Output: 24

        int[][] grid2 = {
                {1, 0, 7},
                {2, 0, 6},
                {3, 4, 5},
                {0, 3, 0},
                {9, 0, 20}
        };
        System.out.println(solution.getMaximumGold(grid2));  // Output: 28
    }

    public int getMaximumGold(int[][] grid) {
        int maxGold = 0;
        int rows = grid.length;
        int cols = grid[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                //if the cell contains gold - start a DFS from there
                if (grid[i][j] != 0) {
                    maxGold = Math.max(maxGold, collectGold(grid, i, j, rows, cols));
                }
            }
        }

        return maxGold; //bingo
    }

    private int collectGold(int[][] grid, int x, int y, int rows, int cols) {
        //if out of bounds or cell has no gold
        if (x < 0 || x >= rows || y < 0 || y >= cols || grid[x][y] == 0) {
            return 0;
        }

        //collect the gold from the current cell
        int currentGold = grid[x][y];
        //mark the cell as visited by setting it to 0
        grid[x][y] = 0;

        // explore all four possible directions
        int left = collectGold(grid, x, y - 1, rows, cols);
        int right = collectGold(grid, x, y + 1, rows, cols);
        int up = collectGold(grid, x - 1, y, rows, cols);
        int down = collectGold(grid, x + 1, y, rows, cols);

        //restore the cell's original value for other paths
        grid[x][y] = currentGold;

        // return the maximum gold collected from this cell
        return currentGold + Math.max(Math.max(left, right), Math.max(up, down));
    }
}

//Опис завдання
//Нам потрібно знайти максимальну кількість золота, яку можна зібрати в сітці розміром m x n, де кожна клітинка
// містить деяку кількість золота (або 0, якщо вона порожня). Ви можете переміщуватись у чотирьох
// напрямках (ліворуч, праворуч, вгору, вниз) і не можете відвідувати ту саму клітинку більше одного разу.
//Підхід до розв'язання
//Для розв'язання цієї задачі ми використовуємо пошук у глибину (DFS) з кожної клітинки, яка містить золото. Під
// час DFS ми будемо обчислювати максимальну кількість золота, яку можна зібрати, починаючи з цієї клітинки.
//Пояснення коду
//Ініціалізація:
//В методі getMaximumGold ми ініціалізуємо змінну maxGold, яка буде зберігати максимальну кількість зібраного золота.
//Ми проходимо через кожну клітинку сітки. Якщо клітинка містить золото, ми викликаємо метод collectGold для збору
// золота, починаючи з цієї клітинки.
//Метод DFS (collectGold):
//Цей метод виконує пошук у глибину для дослідження всіх можливих шляхів від поточної клітинки.
//Якщо клітинка виходить за межі або не містить золота, ми повертаємо 0.
//Ми збираємо золото з поточної клітинки і тимчасово позначаємо її як відвідану, встановивши її значення в 0.
//Після дослідження всіх напрямків ми відновлюємо початкове значення клітинки.
//Ми повертаємо максимальну кількість зібраного золота, починаючи з поточної клітинки плюс золото в цій клітинці.
//Головний метод:
//В головному методі ми тестуємо функцію getMaximumGold з двома прикладами, щоб показати її правильність.
//Такий підхід забезпечує дослідження всіх можливих шляхів з кожної клітинки, що містить золото, і відстеження
// максимальної кількості зібраного золота.

//In a gold mine grid of size m x n, each cell in this mine has an integer representing the amount of gold in
// that cell, 0 if it is empty.
//Return the maximum amount of gold you can collect under the conditions:
//Every time you are located in a cell you will collect all the gold in that cell.
//From your position, you can walk one step to the left, right, up, or down.
//You can't visit the same cell more than once.
//Never visit a cell with 0 gold.
//You can start and stop collecting gold from any position in the grid that has some gold.

//Example 1:
//Input: grid = [[0,6,0],[5,8,7],[0,9,0]]
//Output: 24
//Explanation:
//[[0,6,0],
// [5,8,7],
// [0,9,0]]
//Path to get the maximum gold, 9 -> 8 -> 7.

//Example 2:
//Input: grid = [[1,0,7],[2,0,6],[3,4,5],[0,3,0],[9,0,20]]
//Output: 28
//Explanation:
//[[1,0,7],
// [2,0,6],
// [3,4,5],
// [0,3,0],
// [9,0,20]]
//Path to get the maximum gold, 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7.
//
//Constraints:
//m == grid.length
//n == grid[i].length
//1 <= m, n <= 15
//0 <= grid[i][j] <= 100
//There are at most 25 cells containing gold.
