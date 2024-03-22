package P101_200.P198_House_Robber;

public class Main {
    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 1};
        int result1 = rob(nums1);
        System.out.println("Example 1: " + result1);

        int[] nums2 = {2, 7, 9, 3, 1};
        int result2 = rob(nums2);
        System.out.println("Example 2: " + result2);
    }

    public static int rob(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return nums[0];
        } else if (n == 2) {
            return Math.max(nums[0], nums[1]);
        }

        // Create an array to store the maximum amount of money that can be robbed at each house
        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        // Iterate through the array to calculate the maximum amount of money at each house
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }

        // The final result is the maximum amount of money that can be robbed from the last house
        return dp[n - 1];
    }
}

//Ми використовуємо динамічне програмування для ефективного розв'язання цієї задачі. У нас є масив nums, де
// nums[i] вказує на кількість грошей у будинку номер i. Наша мета - визначити максимальну суму грошей,
// яку ми можемо вкрасти, не спричинюючи сповіщення поліції. Оскільки суміжні будинки мають з'єднані системи
// безпеки, ми не можемо вкрасти гроші з двох суміжніх будинків в одну ніч.
//
//Створюємо масив dp довжиною n, де dp[i] вказує на максимальну суму грошей, яку ми можемо вкрасти, обходячи
// перші i будинків. Починаємо з визначення базових значень для перших трьох будинків.
//
//Далі ми використовуємо цикл, щоб пройтися по решті будинків і обчислити dp[i]. У кожному кроці ми порівнюємо,
// чи вигідніше взяти гроші з поточного будинку та dp[i-2] (гроші з попереднього незалежного будинку) чи залишити
// гроші з попереднього будинку (не брати гроші з поточного будинку).
//
//На завершення, результат знаходиться у dp[n-1], оскільки це вказує на максимальну суму грошей, яку можна вкрасти,
// обходячи всі будинки.


//You are a professional robber planning to rob houses along a street. Each house has a certain amount of money
// stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security
// systems connected and it will automatically contact the police if two adjacent houses were broken into
// on the same night.
//
//Given an integer array nums representing the amount of money of each house, return the maximum amount of
// money you can rob tonight without alerting the police.

//Example 1:
//Input: nums = [1,2,3,1]
//Output: 4
//Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
//Total amount you can rob = 1 + 3 = 4.

//Example 2:
//Input: nums = [2,7,9,3,1]
//Output: 12
//Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
//Total amount you can rob = 2 + 9 + 1 = 12.

//Constraints:
//1 <= nums.length <= 100
//0 <= nums[i] <= 400
