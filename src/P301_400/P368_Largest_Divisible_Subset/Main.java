package P301_400.P368_Largest_Divisible_Subset;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {1, 2, 3};
        System.out.println(solution.largestDivisibleSubset(nums1)); // Output: [1, 2] or [1, 3]

        int[] nums2 = {1, 2, 4, 8};
        System.out.println(solution.largestDivisibleSubset(nums2)); // Output: [1, 2, 4, 8]
    }

    public List<Integer> largestDivisibleSubset(int[] nums) {
        List<Integer> result = new ArrayList<>();
        if (nums == null || nums.length == 0) return result;

        Arrays.sort(nums);
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int maxIndex = 0;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            if (dp[i] > dp[maxIndex]) {
                maxIndex = i;
            }
        }

        int count = dp[maxIndex];
        int value = nums[maxIndex];
        for (int i = maxIndex; i >= 0; i--) {
            if (value % nums[i] == 0 && dp[i] == count) {
                result.add(nums[i]);
                value = nums[i];
                count--;
            }
        }

        Collections.reverse(result);
        return result;
    }
}

//Задача полягає в тому, щоб знайти найбільший підмасив чисел у заданому масиві, де кожна пара чисел задовольняє
// умову: одне число повинно бути кратним іншому або націло ділитися на нього. Наприклад, якщо ми маємо
// масив [1, 2, 4, 8], то можливі варіанти такого підмасиву: [1, 2, 4, 8] або [1, 2, 8].
//Для вирішення цієї задачі ми використовуємо динамічне програмування. Спочатку ми сортуємо вхідний масив
// чисел. Потім створюємо допоміжний масив dp, де dp[i] буде представляти довжину найбільшого підмасиву чисел,
// до якого входить nums[i].
//Далі ми проходимо по кожному елементу nums[i] і перевіряємо, чи ділиться він без залишку на будь-яке число,
// що стоїть перед ним у відсортованому масиві nums. Якщо таке число знаходиться, ми оновлюємо значення dp[i]
// на одиницю більше, ніж значення dp[j] + 1, де j - індекс числа, яке ділиться на nums[i].
//Після проходження всього масиву ми знаходимо індекс maxIndex, де dp[maxIndex] має найбільше
// значення. Потім ми використовуємо цей індекс, щоб зібрати найбільший підмасив чисел, починаючи з кінця. Ми
// додаємо до результату кожне число, перевіряючи, чи воно ділиться на попереднє число у підмасиві, і зменшуємо
// лічильник довжини підмасиву.
//У результаті ми отримуємо найбільший підмасив чисел, що задовольняє умові задачі.

//Given a set of distinct positive integers nums, return the largest subset answer such that every
// pair (answer[i], answer[j]) of elements in this subset satisfies:
//
//answer[i] % answer[j] == 0, or
//answer[j] % answer[i] == 0
//If there are multiple solutions, return any of them.

//Example 1:
//Input: nums = [1,2,3]
//Output: [1,2]
//Explanation: [1,3] is also accepted.

//Example 2:
//Input: nums = [1,2,4,8]
//Output: [1,2,4,8]

//Constraints:
//1 <= nums.length <= 1000
//1 <= nums[i] <= 2 * 109
//All the integers in nums are unique.

