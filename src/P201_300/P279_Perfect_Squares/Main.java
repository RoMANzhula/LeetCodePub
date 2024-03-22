package P201_300.P279_Perfect_Squares;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println(numSquares(12));
        System.out.println(numSquares(13));
        System.out.println(numSquares(32));
    }

    public static int numSquares(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }

        return dp[n];
    }
}

//розглянемо кожне число i від 1 до n та знаходити найменшу кількість квадратів, сума яких дорівнює i. Ми будемо
// використовувати масив dp, де dp[i] буде зберігати мінімальну кількість квадратів, необхідних для утворення числа i.
//Щоб обчислити dp[i], ми перебираємо всі можливі квадрати, які можуть бути додані до числа i. Для кожного
// квадрата j * j, де j - ціле число, ми робимо таке оновлення: dp[i] = min(dp[i], dp[i - j * j] + 1). Це означає,
// що ми перебираємо всі можливі квадрати, що можуть бути додані до числа i, і обираємо той, який додається з
// мінімальною кількістю квадратів.
//Таким чином, пройшовши всі числа від 1 до n, ми знайдемо мінімальну кількість квадратів, необхідних для
// утворення кожного числа. І останнє значення dp[n] буде мінімальною кількістю квадратів, які
// необхідні для утворення числа n.

//Given an integer n, return the least number of perfect square numbers that sum to n.
//
//A perfect square is an integer that is the square of an integer; in other words, it is the product
// of some integer with itself. For example, 1, 4, 9, and 16 are perfect squares while 3 and 11 are not.

//Example 1:
//Input: n = 12
//Output: 3
//Explanation: 12 = 4 + 4 + 4.

//Example 2:
//Input: n = 13
//Output: 2
//Explanation: 13 = 4 + 9.

//Constraints:
//1 <= n <= 104
