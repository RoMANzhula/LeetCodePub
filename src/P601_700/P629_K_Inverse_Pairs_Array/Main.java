package P601_700.P629_K_Inverse_Pairs_Array;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();
        System.out.println(solution.kInversePairs(3, 0)); // output 1
        System.out.println(solution.kInversePairs(3, 1)); // output 2
    }

//    public int kInversePairs(int n, int k) {
//        int MOD = 1000000007;
//        int[][] dp = new int[n + 1][k + 1];
//
//        for (int i = 1; i <= n; i++) {
//            for (int j = 0; j <= k; j++) {
//                if (j == 0) {
//                    dp[i][j] = 1;
//                } else {
//                    for (int p = 0; p <= Math.min(j, i - 1); p++) {
//                        dp[i][j] = (dp[i][j] + dp[i - 1][j - p]) % MOD;
//                    }
//                }
//            }
//        }
//
//        return dp[n][k];
//    }

    //faster solve
    public int kInversePairs(int n, int k) {
        int MOD = 1000000007;
        int[][] dp = new int[n + 1][k + 1];

        dp[0][0] = 1;

        for (int i = 1; i <= n; i++) {
            int sum = 0;
            for (int j = 0; j <= k; j++) {
                sum = (sum + dp[i - 1][j]) % MOD;
                if (j >= i) {
                    sum = (sum - dp[i - 1][j - i] + MOD) % MOD;
                }
                dp[i][j] = sum;
            }
        }

        return dp[n][k];
    }
}

//Задача полягає в тому, щоб знайти кількість різних масивів чисел від 1 до n, у яких кількість інверсій (пар чисел,
// де перше число більше за друге, і ці числа розташовані в зворотному порядку) дорівнює заданому k.
//
//Основна ідея використовує динамічне програмування, де dp[i][j] представляє кількість різних масивів чисел від 1
// до i, які мають точно j інверсій. Далі використовується оновлення dp[i][j] з попередніх значень для побудови
// результату для більших значень i.
//
//Основний цикл ітерується від 1 до n, представляючи довжину масиву. У цьому циклі використовується внутрішній
// цикл для обчислення dp[i][j] для різних значень j.
//
//У коді використовується додаткова змінна sum, яка обчислює суму значень з попереднього рядка (dp[i-1][j]). Ця
// сума додається до поточного значення, а також віднімається попереднє значення (dp[i-1][j-i]), щоб уникнути
// подвійного підрахунку. Результати обчислення зберігаються в dp[i][j].
//
//На завершення, повертається значення dp[n][k], що представляє кількість різних масивів чисел від 1 до n, які
// мають точно k інверсій.


//For an integer array nums, an inverse pair is a pair of integers [i, j] where 0 <= i < j < nums.length and
// nums[i] > nums[j].
//Given two integers n and k, return the number of different arrays consist of numbers from 1 to n such that
// there are exactly k inverse pairs. Since the answer can be huge, return it modulo 109 + 7.
//
//Example 1:
//Input: n = 3, k = 0
//Output: 1
//Explanation: Only the array [1,2,3] which consists of numbers from 1 to 3 has exactly 0 inverse pairs.

//Example 2:
//Input: n = 3, k = 1
//Output: 2
//Explanation: The array [1,3,2] and [2,1,3] have exactly 1 inverse pair.

//Constraints:
//1 <= n <= 1000
//0 <= k <= 1000
