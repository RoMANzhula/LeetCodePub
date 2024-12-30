package P1_100.P70_Climbing_Stairs;

public class Main {
    public static void main(String[] args) {
        System.out.println(climbStairs(2)); // output 2
        System.out.println(climbStairs(3)); // output 3
        System.out.println(climbStairs(4)); // output 5
        System.out.println(climbStairs(5)); // output 8

        System.out.println("second method:");
        System.out.println(climbStairs2(2)); // output 2
        System.out.println(climbStairs2(3)); // output 3
        System.out.println(climbStairs2(4)); // output 5
        System.out.println(climbStairs2(5)); // output 8
    }

    public static int climbStairs(int n) {
        if (n < 3) {
            return n;
        }

        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }

    // prev/current/next - are stapes
    public static int climbStairs2(int n) {
        if (n == 1) return 1;

        int prev = 1;
        int current = 2;

        for (int i = 3; i <= n + 1; i++) {
            int next = prev + current;
            prev = current;
            current = next;
        }

        return prev;
    }
}

//Задача полягає у тому, щоб знайти кількість унікальних способів підняття по сходах на верх. Ми можемо
// підніматися по сходах по 1 або 2 кроки за раз.
//
//Наприклад, якщо у нас є 2 сходинки (n = 2), то ми можемо піднятися на верх двома способами: зробити 2
// кроки одразу або зробити 1 крок, а потім іще один.
//
//Аналогічно, якщо у нас є 3 сходинки (n = 3), то є три унікальні шляхи: 1 + 1 + 1, 1 + 2, 2 + 1.
//
//Ми можемо вирішити цю задачу за допомогою динамічного програмування. Створимо масив dp, де dp[i] буде
// представляти кількість унікальних шляхів для підняття на і-ту сходинку. Початково dp[1] = 1 і dp[2] = 2,
// оскільки для 1-ї сходинки є лише один спосіб, а для 2-х сходинок - два способи.
//
//Потім ми будемо обчислювати dp[i] для кожного i від 3 до n, використовуючи формулу dp[i] = dp[i-1] + dp[i-2],
// оскільки ми можемо зробити крок на одну сходинку або два.

//You are climbing a staircase. It takes n steps to reach the top.
//
//Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
//
//
//
//Example 1:
//
//Input: n = 2
//Output: 2
//Explanation: There are two ways to climb to the top.
//1. 1 step + 1 step
//2. 2 steps
//Example 2:
//
//Input: n = 3
//Output: 3
//Explanation: There are three ways to climb to the top.
//1. 1 step + 1 step + 1 step
//2. 1 step + 2 steps
//3. 2 steps + 1 step
//
//
//Constraints:
//
//1 <= n <= 45