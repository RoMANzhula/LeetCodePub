package P2901_3000.P2958_Length_of_Longest_Subarray_With_at_Most_K_Frequency;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {1, 2, 3, 1, 2, 3, 1, 2};
        int k1 = 2;
        System.out.println(solution.maxSubarrayLength(nums1, k1)); // Output: 6

        int[] nums2 = {1, 2, 1, 2, 1, 2, 1, 2};
        int k2 = 1;
        System.out.println(solution.maxSubarrayLength(nums2, k2)); // Output: 2

        int[] nums3 = {5, 5, 5, 5, 5, 5, 5};
        int k3 = 4;
        System.out.println(solution.maxSubarrayLength(nums3, k3)); // Output: 4
    }

    public int maxSubarrayLength(int[] nums, int k) {
        int ans = 0;
        Map<Integer, Integer> count = new HashMap<>();

        int l = 0;
        for (int r = 0; r < nums.length; r++) {
            int num = nums[r];
            count.put(num, count.getOrDefault(num, 0) + 1);
            while (count.get(num) == k + 1) {
                count.put(nums[l], count.get(nums[l]) - 1);
                l++;
            }
            ans = Math.max(ans, r - l + 1);
        }

        return ans;
    }

}

//У цій програмі ми шукаємо найбільшу довжину підмасиву, в якому кожен елемент зустрічається не більше k разів. Для
// цього ми використовуємо метод двох вказівників.
//Ми маємо два вказівники l та r, що вказують на початок і кінець поточного підмасиву відповідно.
//Ми також маємо словник count, де ми зберігаємо кількість зустрічей кожного елементу в поточному підмасиві.
//Проходимося по масиву nums. Для кожного елемента r масиву ми збільшуємо лічильник count для цього елемента.
//Якщо кількість зустрічей якого-небудь елемента перевищує k, ми збільшуємо лічильник l та видаляємо з нього
// елементи, доки кількість зустрічей не стане менше або дорівнює k.
//Після цього оновлюємо найбільшу знайдену довжину підмасиву (maxLength) за допомогою r - l + 1.
//Повертаємо знайдену найбільшу довжину підмасиву.
//Цей алгоритм допомагає знайти найбільшу довжину підмасиву з обмеженням частоти кожного елементу до k.

//You are given an integer array nums and an integer k.
//The frequency of an element x is the number of times it occurs in an array.
//An array is called good if the frequency of each element in this array is less than or equal to k.
//Return the length of the longest good subarray of nums.
//A subarray is a contiguous non-empty sequence of elements within an array.
//
//Example 1:
//Input: nums = [1,2,3,1,2,3,1,2], k = 2
//Output: 6
//Explanation: The longest possible good subarray is [1,2,3,1,2,3] since the values 1, 2, and 3 occur at most twice
// in this subarray. Note that the subarrays [2,3,1,2,3,1] and [3,1,2,3,1,2] are also good.
//It can be shown that there are no good subarrays with length more than 6.

//Example 2:
//Input: nums = [1,2,1,2,1,2,1,2], k = 1
//Output: 2
//Explanation: The longest possible good subarray is [1,2] since the values 1 and 2 occur at most once in this
// subarray. Note that the subarray [2,1] is also good.
//It can be shown that there are no good subarrays with length more than 2

//Example 3:
//Input: nums = [5,5,5,5,5,5,5], k = 4
//Output: 4
//Explanation: The longest possible good subarray is [5,5,5,5] since the value 5 occurs 4 times in this subarray.
//It can be shown that there are no good subarrays with length more than 4.
//
//Constraints:
//1 <= nums.length <= 105
//1 <= nums[i] <= 109
//1 <= k <= nums.length