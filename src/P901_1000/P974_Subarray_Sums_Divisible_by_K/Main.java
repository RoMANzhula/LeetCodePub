package P901_1000.P974_Subarray_Sums_Divisible_by_K;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {4, 5, 0, -2, -3, 1};
        int k1 = 5;
        System.out.println(solution.subarraysDivByK(nums1, k1)); // Output: 7

        int[] nums2 = {5};
        int k2 = 9;
        System.out.println(solution.subarraysDivByK(nums2, k2)); // Output: 0
    }

//    public int subarraysDivByK(int[] nums, int k) {
//        // Map to store the frequency of prefix sums mod k
//        Map<Integer, Integer> prefixSumCount = new HashMap<>();
//        // Initialize with prefix sum 0 having frequency 1
//        prefixSumCount.put(0, 1);
//
//        int count = 0;
//        int prefixSum = 0;
//
//        for (int num : nums) {
//            prefixSum += num;
//            // Get the current prefix sum modulo k
//            int mod = prefixSum % k;
//            // Adjust for negative mods to stay within the range [0, k-1]
//            if (mod < 0) {
//                mod += k;
//            }
//            //if the current mod value has been seen before, it means there are subarrays ending at the current
//            // index with sum divisible by k
//            if (prefixSumCount.containsKey(mod)) {
//                count += prefixSumCount.get(mod);
//            }
//            // Update the frequency of the current mod value in the map
//            prefixSumCount.put(mod, prefixSumCount.getOrDefault(mod, 0) + 1);
//        }
//
//        return count; // bingo
//    }

    // Faster solution with array
    public int subarraysDivByK(int[] nums, int k) {
        int[] prefixSumCount = new int[k];
        prefixSumCount[0] = 1;

        int count = 0;
        int prefixSum = 0;

        for (int num : nums) {
            prefixSum += num;
            int mod = prefixSum % k;
            if (mod < 0) {
                mod += k;
            }
            count += prefixSumCount[mod];
            prefixSumCount[mod]++;
        }

        return count;
    }

}

//Пояснення коду:
//Ініціалізація: Масив prefixSumCount розміром k використовується для зберігання частоти префіксних сум модулів k. Масив
// ініціалізується значенням 1 для prefixSumCount[0], що відповідає префіксній сумі 0.
//Цикл по nums: Префіксна сума оновлюється кожним елементом. Операція модулю забезпечує, що значення залишаються
// в межах [0, k-1]. Негативні значення модулю коригуються.
//Підрахунок і оновлення: Якщо поточне значення модулю існує в масиві, це означає, що існують підмасиви, сума
// яких ділимо на k. Частота поточного значення модулю оновлюється в масиві.
//Повернення результату.
//Це рішення також має часову складність O(n), де
//n - довжина масиву, і є ефективним для великих вхідних даних, як зазначено в умовах задачі.

//Given an integer array nums and an integer k, return the number of non-empty subarrays that have a sum divisible by k.
//A subarray is a contiguous part of an array.
//
//Example 1:
//Input: nums = [4,5,0,-2,-3,1], k = 5
//Output: 7
//Explanation: There are 7 subarrays with a sum divisible by k = 5:
//[4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]

//Example 2:
//Input: nums = [5], k = 9
//Output: 0
//
//Constraints:
//1 <= nums.length <= 3 * 104
//-104 <= nums[i] <= 104
//2 <= k <= 104