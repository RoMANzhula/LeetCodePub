package P501_600.P576_Out_of_Boundary_Paths;


public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.findPaths(2, 2, 2, 0, 0)); // Output: 6
        System.out.println(solution.findPaths(1, 3, 3, 0, 1)); // Output: 12
        System.out.println(solution.findPaths(45, 35, 47, 20, 31)); // Output: 951853874
    }

    private static final int MOD = 1000000007;

    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int result = 0;

        // Initialize base case: when maxMove is 0, there is only one way (stay in the same cell)
        int[][] dp = new int[m][n];
        dp[startRow][startColumn] = 1;

        // Calculate the number of paths for each move
        for (int moves = 1; moves <= maxMove; moves++) {
            int[][] temp = new int[m][n];
            for (int row = 0; row < m; row++) {
                for (int col = 0; col < n; col++) {
                    for (int[] dir : directions) {
                        int newRow = row + dir[0];
                        int newCol = col + dir[1];

                        if (newRow < 0 || newRow >= m || newCol < 0 || newCol >= n) {
                            // If moving out of the boundary, increment the count
                            result = (result + dp[row][col]) % MOD;
                        } else {
                            // Otherwise, add the count from the adjacent cell
                            temp[newRow][newCol] = (temp[newRow][newCol] + dp[row][col]) % MOD;
                        }
                    }
                }
            }
            dp = temp;
        }

        return result;
    }

}

//Задача полягає в тому, щоб знайти кількість можливих шляхів для м'яча на сітці заданого розміру так, щоб
// він вийшов за межі сітки за певну кількість кроків.
//
//Основна ідея у використанні динамічного програмування. Ми створюємо двовимірний масив dp, де dp[i][j] представляє
// кількість шляхів для м'яча, який знаходиться у комірці (i, j).
//
//Ініціалізуємо dp[startRow][startColumn] на першому кроці як 1, оскільки м'яч вже знаходиться в стартовій комірці,
// і йому не потрібно робити жоден крок.
//
//Далі ми починаємо ітерації по кількості кроків (від 1 до maxMove). На кожному кроці ми розглядаємо кожну
// комірку (i, j) у сітці і обчислюємо кількість шляхів для м'яча в цій комірці. Для цього ми розглядаємо
// всі можливі напрямки руху (вгору, вниз, вліво, вправо) та додаємо кількість шляхів з попередньої комірки
// до поточної, якщо м'яч залишається в межах сітки. Якщо м'яч рухається за межі сітки, ми збільшуємо результат
// на кількість шляхів, що виходять за межі.
//
//Ми використовуємо додатковий двовимірний масив temp, щоб зберегти оновлені значення. По закінченні кожного кроку
// ми присвоюємо значення temp до основного масиву dp.
//
//Таким чином, після завершення усіх ітерацій ми отримуємо кількість шляхів для м'яча, який виходить за межі
// сітки за задану кількість кроків. Це число і є відповіддю на задачу.
//
//Наведений код реалізує цю логіку, і ми використовуємо динамічний підхід, обчислюючи кількість шляхів для кожної
// комірки на кожному етапі.


//There is an m x n grid with a ball. The ball is initially at the position [startRow, startColumn]. You are
// allowed to move the ball to one of the four adjacent cells in the grid (possibly out of the grid crossing
// the grid boundary). You can apply at most maxMove moves to the ball.
//
//Given the five integers m, n, maxMove, startRow, startColumn, return the number of paths to move the ball
// out of the grid boundary. Since the answer can be very large, return it modulo 109 + 7.
//Example 1:
// image path_1
//Input: m = 2, n = 2, maxMove = 2, startRow = 0, startColumn = 0
//Output: 6
//Example 2:
// image path_2
//Input: m = 1, n = 3, maxMove = 3, startRow = 0, startColumn = 1
//Output: 12

//Constraints:
//1 <= m, n <= 50
//0 <= maxMove <= 50
//0 <= startRow < m
//0 <= startColumn < n
