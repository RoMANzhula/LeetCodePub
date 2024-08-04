package P1501_1600.P1508_Range_Sum_of_Sorted_Subarray_Sums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        // Example 1
        int[] nums1 = {1, 2, 3, 4};
        int n1 = 4;
        int left1 = 1;
        int right1 = 5;
        System.out.println(solution.rangeSum(nums1, n1, left1, right1)); // Output: 13

        // Example 2
        int[] nums2 = {1, 2, 3, 4};
        int n2 = 4;
        int left2 = 3;
        int right2 = 4;
        System.out.println(solution.rangeSum(nums2, n2, left2, right2)); // Output: 6

        // Example 3
        int[] nums3 = {1, 2, 3, 4};
        int n3 = 4;
        int left3 = 1;
        int right3 = 10;
        System.out.println(solution.rangeSum(nums3, n3, left3, right3)); // Output: 50
    }

    private static final int MOD = 1_000_000_007;

//    public int rangeSum(int[] nums, int n, int left, int right) {
//        List<Integer> subarraySums = new ArrayList<>();
//
//        // Step 1: Generate all subarray sums
//        for (int i = 0; i < n; i++) {
//            int sum = 0;
//            for (int j = i; j < n; j++) {
//                sum += nums[j];
//                subarraySums.add(sum);
//            }
//        }
//
//        // Step 2: Sort the subarray sums
//        Collections.sort(subarraySums);
//
//        // Step 3: Calculate the sum of elements from index `left` to `right`
//        long result = 0;
//        for (int k = left - 1; k < right; k++) {
//            result = (result + subarraySums.get(k)) % MOD;
//        }
//
//        return (int) result;
//    }

    //faster solution
    public int rangeSum(int[] nums, int n, int left, int right) {
        // Крок 1: Розрахунок префіксних сум
        int[] prefixSums = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefixSums[i + 1] = prefixSums[i] + nums[i];
        }

        // Крок 2: Підрахунок кількості підмасивів
        int totalSubarrays = n * (n + 1) / 2;
        int[] subarraySums = new int[totalSubarrays];
        int index = 0;

        // Крок 3: Обчислення сум підмасивів
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                subarraySums[index++] = prefixSums[j + 1] - prefixSums[i];
            }
        }

        // Крок 4: Сортування масиву сум підмасивів
        Arrays.sort(subarraySums);

        // Крок 5: Підрахунок суми елементів у діапазоні від `left` до `right`
        long result = 0;
        for (int k = left - 1; k < right; k++) {
            result = (result + subarraySums[k]) % MOD;
        }

        return (int) result;
    }

}

//Пояснення:
//Префіксні суми: Ми розраховуємо префіксні суми масиву для швидкого обчислення підмасивних сум.
//Масив підмасивних сум: Створюємо масив, щоб зберігати всі можливі підмасивні суми.
//Обчислення сум підмасивів: Заповнюємо масив підмасивних сум за допомогою префіксних сум.
//Сортування: Сортуємо масив підмасивних сум.
//Підрахунок суми: Підраховуємо суму елементів у діапазоні від left до right, враховуючи модуль 10 в 9 ступ. +7.
//Цей підхід зводить до мінімуму використання колекцій, що прискорює виконання програми.


//You are given the array nums consisting of n positive integers. You computed the sum of all non-empty continuous
// subarrays from the array and then sorted them in non-decreasing order, creating a new
// array of n * (n + 1) / 2 numbers.
//Return the sum of the numbers from index left to index right (indexed from 1), inclusive, in the new array. Since
// the answer can be a huge number return it modulo 109 + 7.
//
//Example 1:
//Input: nums = [1,2,3,4], n = 4, left = 1, right = 5
//Output: 13
//Explanation: All subarray sums are 1, 3, 6, 10, 2, 5, 9, 3, 7, 4. After sorting them in non-decreasing order
// we have the new array [1, 2, 3, 3, 4, 5, 6, 7, 9, 10]. The sum of the numbers from index le = 1 to
// ri = 5 is 1 + 2 + 3 + 3 + 4 = 13.

//Example 2:
//Input: nums = [1,2,3,4], n = 4, left = 3, right = 4
//Output: 6
//Explanation: The given array is the same as example 1. We have the new array [1, 2, 3, 3, 4, 5, 6, 7, 9, 10]. The
// sum of the numbers from index le = 3 to ri = 4 is 3 + 3 = 6.

//Example 3:
//Input: nums = [1,2,3,4], n = 4, left = 1, right = 10
//Output: 50
//
//Constraints:
//n == nums.length
//1 <= nums.length <= 1000
//1 <= nums[i] <= 100
//1 <= left <= right <= n * (n + 1) / 2
