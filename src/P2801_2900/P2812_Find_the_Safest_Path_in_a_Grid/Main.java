package P2801_2900.P2812_Find_the_Safest_Path_in_a_Grid;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        List<List<Integer>> grid1 = Arrays.asList(
            Arrays.asList(1, 0, 0),
            Arrays.asList(0, 0, 0),
            Arrays.asList(0, 0, 1)
        );
        System.out.println(solution.maximumSafenessFactor(grid1)); //output: 0

        List<List<Integer>> grid2 = Arrays.asList(
            Arrays.asList(0, 0, 1),
            Arrays.asList(0, 0, 0),
            Arrays.asList(0, 0, 0)
        );
        System.out.println(solution.maximumSafenessFactor(grid2)); //output: 2

        List<List<Integer>> grid3 = Arrays.asList(
            Arrays.asList(0, 0, 0, 1),
            Arrays.asList(0, 0, 0, 0),
            Arrays.asList(0, 0, 0, 0),
            Arrays.asList(1, 0, 0, 0)
        );
        System.out.println(solution.maximumSafenessFactor(grid3)); //output: 3

    }

    private static final int[] DIRS = {-1, 0, 1, 0, -1};

    public int maximumSafenessFactor(List<List<Integer>> grid) {
        int n = grid.size();
        int[][] dist = new int[n][n];

        // Initialize distance array with a large value
        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        // Multi-source BFS from all thieves to calculate minimum distance to any thief
        Queue<int[]> queue = new LinkedList<>();
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (grid.get(r).get(c) == 1) {
                    queue.offer(new int[]{r, c});
                    dist[r][c] = 0;
                }
            }
        }

        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int r = cell[0], c = cell[1];
            for (int i = 0; i < 4; i++) {
                int nr = r + DIRS[i], nc = c + DIRS[i + 1];
                if (nr >= 0 && nr < n && nc >= 0 && nc < n && dist[nr][nc] > dist[r][c] + 1) {
                    dist[nr][nc] = dist[r][c] + 1;
                    queue.offer(new int[]{nr, nc});
                }
            }
        }

        // Binary search for the maximum safeness factor
        int left = 0, right = n * 2 - 2;
        while (left < right) {
            int mid = (right + left + 1) / 2;
            if (canReachWithSafeness(grid, dist, mid)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }

    private boolean canReachWithSafeness(List<List<Integer>> grid, int[][] dist, int safeness) {
        int n = grid.size();
        if (dist[0][0] < safeness || dist[n-1][n-1] < safeness) {
            return false;
        }

        boolean[][] visited = new boolean[n][n];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int r = cell[0], c = cell[1];
            if (r == n - 1 && c == n - 1) {
                return true;
            }
            for (int i = 0; i < 4; i++) {
                int nr = r + DIRS[i], nc = c + DIRS[i + 1];
                if (nr >= 0 && nr < n && nc >= 0 && nc < n && !visited[nr][nc] && dist[nr][nc] >= safeness) {
                    visited[nr][nc] = true;
                    queue.offer(new int[]{nr, nc});
                }
            }
        }

        return false;
    }
}

//Пояснення задачі:
//Вхідні дані:
//У нас є квадратна матриця grid розміром n x n, де:
//grid[r][c] = 1 означає, що у клітинці знаходиться злодій.
//grid[r][c] = 0 означає, що клітинка порожня.
//Потрібно знайти шлях з клітинки (0, 0) до клітинки (n-1, n-1) з максимальним коефіцієнтом безпеки.
//Коефіцієнт безпеки шляху визначається як мінімальна відстань від будь-якої клітинки на шляху до найближчого злодія.
//Завдання:
//Знайти шлях з максимальним коефіцієнтом безпеки.
//Рішення:
//Обчислення мінімальної відстані до найближчого злодія для кожної клітинки:
//Використовуємо алгоритм пошуку в ширину (BFS) з усіх клітинок, що містять злодіїв, щоб обчислити мінімальну
// відстань до найближчого злодія для кожної клітинки. Результати зберігаються в матриці dist.
//Бінарний пошук максимального коефіцієнта безпеки:
//Використовуємо бінарний пошук, щоб знайти максимальний коефіцієнт безпеки. Для кожного середнього значення mid,
// перевіряємо, чи існує шлях з (0, 0) до (n-1, n-1) з мінімальною відстанню до злодія не меншою за mid.
//Перевірка шляху з використанням BFS:
//Використовуємо BFS для перевірки, чи існує шлях, який задовольняє поточне значення коефіцієнта безпеки mid. Якщо
// такий шлях існує, ми продовжуємо пошук з більшими значеннями, інакше з меншими.
//Пояснення:
//Ініціалізація матриці відстаней:
//Спочатку ми ініціалізуємо матрицю dist великим значенням (наприклад, Integer.MAX_VALUE).
//Обчислення відстаней BFS:
//За допомогою BFS ми обчислюємо мінімальну відстань від кожної клітинки до найближчого злодія. Для цього додаємо
// всі клітинки зі злодіями у чергу і розповсюджуємо їх вплив, оновлюючи відстані у матриці dist.
//Бінарний пошук:
//Використовуємо бінарний пошук для визначення максимальної безпечної відстані. Перевіряємо середнє значення (mid),
// чи можна пройти з початку до кінця з цим коефіцієнтом безпеки.
//Перевірка шляху:
//За допомогою BFS перевіряємо, чи існує шлях з (0, 0) до (n-1, n-1), де кожна клітинка на шляху має мінімальну
// відстань до злодія, не меншу за поточний коефіцієнт безпеки (mid).


//You are given a 0-indexed 2D matrix grid of size n x n, where (r, c) represents:
//A cell containing a thief if grid[r][c] = 1
//An empty cell if grid[r][c] = 0
//You are initially positioned at cell (0, 0). In one move, you can move to any adjacent cell in the grid, including
// cells containing thieves.
//The safeness factor of a path on the grid is defined as the minimum manhattan distance from any cell in the path
// to any thief in the grid.
//Return the maximum safeness factor of all paths leading to cell (n - 1, n - 1).
//An adjacent cell of cell (r, c), is one of the cells (r, c + 1), (r, c - 1), (r + 1, c) and (r - 1, c) if it exists.
//The Manhattan distance between two cells (a, b) and (x, y) is equal to |a - x| + |b - y|, where |val| denotes
// the absolute value of val.
//
//Example 1:
//Input: grid = [[1,0,0],[0,0,0],[0,0,1]]
//Output: 0
//Explanation: All paths from (0, 0) to (n - 1, n - 1) go through the thieves in cells (0, 0) and (n - 1, n - 1).

//Example 2:
//Input: grid = [[0,0,1],[0,0,0],[0,0,0]]
//Output: 2
//Explanation: The path depicted in the picture above has a safeness factor of 2 since:
//- The closest cell of the path to the thief at cell (0, 2) is cell (0, 0). The distance between them
// is | 0 - 0 | + | 0 - 2 | = 2.
//It can be shown that there are no other paths with a higher safeness factor.

//Example 3:
//Input: grid = [[0,0,0,1],[0,0,0,0],[0,0,0,0],[1,0,0,0]]
//Output: 2
//Explanation: The path depicted in the picture above has a safeness factor of 2 since:
//- The closest cell of the path to the thief at cell (0, 3) is cell (1, 2). The distance between
// them is | 0 - 1 | + | 3 - 2 | = 2.
//- The closest cell of the path to the thief at cell (3, 0) is cell (3, 2). The distance between
// them is | 3 - 3 | + | 0 - 2 | = 2.
//It can be shown that there are no other paths with a higher safeness factor.
//
//Constraints:
//1 <= grid.length == n <= 400
//grid[i].length == n
//grid[i][j] is either 0 or 1.
//There is at least one thief in the grid.