package P901_1000.P930_Binary_Subarrays_With_Sum;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {1, 0, 1, 0, 1};
        int goal1 = 2;
        System.out.println("Output for nums1: " + solution.numSubarrayWithSum(nums1, goal1)); // output 4

        int[] nums2 = {0, 0, 0, 0, 0};
        int goal2 = 0;
        System.out.println("Output for nums2: " + solution.numSubarrayWithSum(nums2, goal2)); // output 15
    }

    public int numSubarrayWithSum(int[] nums, int goal) {
        int count = 0;
        int sum = 0;
        Map<Integer, Integer> prefixSumCount = new HashMap<>();

        for (int num : nums) {
            prefixSumCount.put(sum, prefixSumCount.getOrDefault(sum, 0) + 1);
            sum += num;
            count += prefixSumCount.getOrDefault(sum - goal, 0);
        }

        return count;
    }
}

//Ми використовуємо техніку ковзного вікна (sliding window) для розв'язання цієї задачі. Під час проходження
// масиву nums, ми відстежуємо префіксну суму (суму елементів масиву з початку до поточного елементу) і кількість
// підмасивів, які мають таку саму суму.
//Ми використовуємо Map<Integer, Integer> prefixSumCount, де ключ - це сума префіксу, а значення - кількість
// підмасивів з цією сумою.
//Під час проходження масиву nums, ми додаємо суму префіксу до prefixSumCount. Потім, якщо ми знаходимо, що
// sum - goal є ключем у prefixSumCount, ми додаємо відповідне значення до лічильника.
//У кінці проходження масиву ми повертаємо значення лічильника, яке і буде кількістю непорожніх підмасивів
// зі сумою, рівною заданому goal.

//Given a binary array nums and an integer goal, return the number of non-empty subarrays with a sum goal.
//A subarray is a contiguous part of the array.
//
//Example 1:
//Input: nums = [1,0,1,0,1], goal = 2
//Output: 4
//Explanation: The 4 subarrays are bolded and underlined below:
//[1,0,1,0,1]
//[1,0,1,0,1]
//[1,0,1,0,1]
//[1,0,1,0,1]

//Example 2:
//Input: nums = [0,0,0,0,0], goal = 0
//Output: 15
//
//Constraints:
//1 <= nums.length <= 3 * 104
//nums[i] is either 0 or 1.
//0 <= goal <= nums.length
