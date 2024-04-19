package P101_200.P200_Number_of_Islands;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        char[][] grid1 = {
                {'1','1','1','1','0'},
                {'1','1','0','1','0'},
                {'1','1','0','0','0'},
                {'0','0','0','0','0'}
        };

        char[][] grid2 = {
                {'1','1','0','0','0'},
                {'1','1','0','0','0'},
                {'0','0','1','0','0'},
                {'0','0','0','1','1'}
        };

        System.out.println("Number of islands in grid1: " + solution.numIslands(grid1)); // Output: 1
        System.out.println("Number of islands in grid2: " + solution.numIslands(grid2)); // Output: 3
    }

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int m = grid.length;
        int n = grid[0].length;
        int count = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    dfs(grid, i, j);
                }
            }
        }

        return count;
    }

    private void dfs(char[][] grid, int i, int j) {
        int m = grid.length;
        int n = grid[0].length;

        if (i < 0 || j < 0 || i >= m || j >= n || grid[i][j] == '0') {
            return;
        }

        grid[i][j] = '0'; // Marking as visited

        dfs(grid, i + 1, j); // Check bottom
        dfs(grid, i - 1, j); // Check top
        dfs(grid, i, j + 1); // Check right
        dfs(grid, i, j - 1); // Check left
    }

}

//задача полягає в тому, щоб знайти кількість островів у заданій двовимірній сітці з нулями та одиницями. Острів
// у цьому контексті - це група одиниць, які з'єднані горизонтально або вертикально.
//Ми використовуємо алгоритм пошуку у глибину (DFS), щоб пройтися по кожній клітинці сітки. Коли ми знаходимо
// клітинку з одиницею (означаючу острів), ми відзначаємо її як відвідану та розглядаємо всі сусідні клітинки,
// щоб перевірити, чи вони також належать до цього острова. Ми рекурсивно викликаємо функцію DFS для всіх таких
// сусідніх клітинок. Після обходу всіх клітинок острова ми збільшуємо лічильник островів.
//Це трошки схоже на проходження лабіринту: ми ідемо вглиб шукаючи одиниці та збільшуємо лічильник кожного разу,
// коли знаходимо новий острів. Якщо знаходимо острів, ми досліджуємо всі сусідні клітинки, щоб впевнитися,
// що вони також належать до цього острова.

//Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the
// number of islands.
//An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may
// assume all four edges of the grid are all surrounded by water.
//
//Example 1:
//Input: grid = [
//  ["1","1","1","1","0"],
//  ["1","1","0","1","0"],
//  ["1","1","0","0","0"],
//  ["0","0","0","0","0"]
//]
//Output: 1

//Example 2:
//Input: grid = [
//  ["1","1","0","0","0"],
//  ["1","1","0","0","0"],
//  ["0","0","1","0","0"],
//  ["0","0","0","1","1"]
//]
//Output: 3
//
//Constraints:
//m == grid.length
//n == grid[i].length
//1 <= m, n <= 300
//grid[i][j] is '0' or '1'.