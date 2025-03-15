package P1101_1200.P1155_Number_of_Dice_Rolls_With_Turget_Sum;

public class Main {

    public static void main(String[] args) {
        int n1 = 1, k1 = 6, target1 = 3;
        int n2 = 2, k2 = 6, target2 = 7;
        int n3 = 30, k3 = 30, target3 = 500;

        System.out.println(numRollsToTarget(n1, k1, target1)); // Output: 1
        System.out.println(numRollsToTarget(n2, k2, target2)); // Output: 6
        System.out.println(numRollsToTarget(n3, k3, target3)); // Output: 222616187
    }

    private static final int MOD = 1_000_000_007;
    public static int numRollsToTarget(int n, int k, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;

        for (int i = 1; i <= n; i++) {
            int[] newDp = new int[target + 1];
            int maxVal = Math.min(k, target);

            for (int j = 1; j <= target; j++) {
                int ways = 0;
                for (int face = 1; face <= maxVal && face <= j; face++) {
                    ways = (ways + dp[j - face]) % MOD;
                }
                newDp[j] = ways;
            }

            dp = newDp;
        }

        return dp[target];
    }

}


//Ця задача є класичним прикладом задачі динамічного програмування. Мета полягає в тому, щоб знайти кількість
// можливих комбінацій результатів підкидання n кидків грального кубика з k граней, так, щоб сума випавших
// чисел становила target.
//Для вирішення цієї задачі використовуються динамічне програмування та запам'ятовування проміжних результатів
// для ефективної обробки всіх можливих станів.
//Основна ідея:
//Створіть двовимірний масив dp, де dp[i][j] відповідає кількості способів отримати суму j використовуючи
// перші i кидків кубика.
//Ініціалізуйте базовий випадок dp[0][0] = 1, оскільки є лише один спосіб отримати суму 0 з 0 кидків.
//Заповнюйте масив dp знизу вгору та зліва направо. Для кожного dp[i][j] розглядаємо всі можливі значення face
// від 1 до k, і додаємо dp[i-1][j-face] (кількість способів отримати суму j-face використовуючи перші i-1 кидків)
// до поточного dp[i][j].
//В результаті dp[n][target] буде містити кількість способів отримати суму target використовуючи n кидків.
//Враховуйте модулю MOD (1_000_000_007) для уникнення переповнення.
//Оптимізації включають в себе використання одновимірного масиву та оптимізації розгалужень для поліпшення
// продуктивності.


//You have n dice, and each die has k faces numbered from 1 to k.
//Given three integers n, k, and target, return the number of possible ways (out of the kn total ways)
// to roll the dice, so the sum of the face-up numbers equals target. Since the answer may be too large, return
// it modulo 109 + 7.

//Example 1:
//Input: n = 1, k = 6, target = 3
//Output: 1
//Explanation: You throw one die with 6 faces.
//There is only one way to get a sum of 3.

//Example 2:
//Input: n = 2, k = 6, target = 7
//Output: 6
//Explanation: You throw two dice, each with 6 faces.
//There are 6 ways to get a sum of 7: 1+6, 2+5, 3+4, 4+3, 5+2, 6+1.

//Example 3:
//Input: n = 30, k = 30, target = 500
//Output: 222616187
//Explanation: The answer must be returned modulo 109 + 7.

//Constraints:
//1 <= n, k <= 30
//1 <= target <= 1000
