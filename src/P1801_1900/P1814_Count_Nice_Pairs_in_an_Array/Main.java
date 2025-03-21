package P1801_1900.P1814_Count_Nice_Pairs_in_an_Array;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        int[] nums1 = {42, 11, 1, 97};
        int[] nums2 = {13, 10, 35, 24, 76};

        System.out.println("Example 1 Output: " + countNicePairs(nums1)); //output 2
        System.out.println("Example 2 Output: " + countNicePairs(nums2)); //output 4
    }

    public static int countNicePairs(int[] nums) {
        int MOD = 1000000007;
        int n = nums.length;
        long count = 0;

        //map to store the count of each difference
        Map<Integer, Integer> diffCount = new HashMap<>();

        for (int i = 0; i < n; i++) {
            //calculate the difference and reverse of the current number
            int diff = nums[i] - reverse(nums[i]);

            //update the count in the map
            diffCount.put(diff, diffCount.getOrDefault(diff, 0) + 1);
        }

        //calculate the total count of nice pairs
        for (int val : diffCount.values()) {
            count = (count + (long) val * (val - 1) / 2) % MOD;
        }

        return (int)count;
    }

    //function to reverse a non-negative integer
    private static int reverse(int num) {
        int reversed = 0;
        while (num > 0) {
            reversed = reversed * 10 + num % 10;
            num /= 10;
        }
        return reversed;
    }

}

//Для розв'язання цієї задачі використовується підрахунок різниць між кожним числом і його оберненим значенням, і
// використання мапи для зберігання кількості кожної різниці. Після цього визначається кількість "гарних" пар за
// допомогою формули комбінаторики: для кожної різниці підраховується кількість можливих пар за допомогою
// val * (val - 1) / 2, де val - кількість елементів з такою різницею.
//Наприклад, якщо маємо масив nums = [42, 11, 1, 97], обчислюємо різниці та зберігаємо їх у мапі:
//Різниця для 42: 42 - rev(42) = 42 - 24 = 18.
//Різниця для 11: 11 - rev(11) = 11 - 11 = 0.
//Різниця для 1: 1 - rev(1) = 1 - 1 = 0.
//Різниця для 97: 97 - rev(97) = 97 - 79 = 18.
//Тепер маємо мапу {18: 2, 0: 2}, де значення відповідають кількості елементів з відповідною різницею. Далі
// обчислюємо кількість "гарних" пар, використовуючи формулу комбінаторики:
//Для різниці 18: 2 * (2 - 1) / 2 = 1.
//Для різниці 0: 2 * (2 - 1) / 2 = 1.
//Отже, загальна кількість "гарних" пар у цьому прикладі дорівнює 1 + 1 = 2, що і є відповіддю.


//You are given an array nums that consists of non-negative integers. Let us define rev(x) as the reverse of
// the non-negative integer x. For example, rev(123) = 321, and rev(120) = 21. A pair of indices (i, j) is nice if
// it satisfies all of the following conditions:
//0 <= i < j < nums.length
//nums[i] + rev(nums[j]) == nums[j] + rev(nums[i])
//Return the number of nice pairs of indices. Since that number can be too large, return it modulo 109 + 7.

//Example 1:
//Input: nums = [42,11,1,97]
//Output: 2
//Explanation: The two pairs are:
// - (0,3) : 42 + rev(97) = 42 + 79 = 121, 97 + rev(42) = 97 + 24 = 121.
// - (1,2) : 11 + rev(1) = 11 + 1 = 12, 1 + rev(11) = 1 + 11 = 12.

//Example 2:
//Input: nums = [13,10,35,24,76]
//Output: 4

//Constraints:
//1 <= nums.length <= 105
//0 <= nums[i] <= 109
