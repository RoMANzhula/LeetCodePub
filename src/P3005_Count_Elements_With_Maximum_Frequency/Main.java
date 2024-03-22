package P3005_Count_Elements_With_Maximum_Frequency;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {1, 2, 2, 3, 1, 4}; // output 4
        int[] nums2 = {1, 2, 3, 4, 5}; // output 5
        int[] nums3 = {17, 17, 2, 12, 20, 17, 12}; // output 5

        System.out.println(solution.maxFrequencyElements(nums1));
        System.out.println(solution.maxFrequencyElements(nums2));
        System.out.println(solution.maxFrequencyElements(nums3));
    }

//    public int maxFrequencyElements(int[] nums) {
//        Map<Integer, Integer> counts = new HashMap<>();
//
//        for (int num : nums) {
//            counts.put(num, counts.getOrDefault(num, 0) + 1);
//        }
//
//        int maxCount = 0;
//        int totalCount = 0;
//
//        for (int count : counts.values()) {
//            maxCount = Math.max(maxCount, count);
//        }
//
//        int maxCountNum = 0;
//        for (int num : counts.keySet()) {
//            if (counts.get(num) == maxCount) {
//                maxCountNum++;
//            }
//        }
//
//        if (maxCountNum == 1) {
//            return totalCount = maxCount;
//        } else {
//            return totalCount = maxCount * maxCountNum;
//        }
//
//    }

    // faster solve
    public int maxFrequencyElements(int[] nums) {
        Map<Integer, Integer> counts = new HashMap<>();
        int maxCount = 0;
        int maxCountNum = 0;

        for (int num : nums) {
            int count = counts.getOrDefault(num, 0) + 1;
            counts.put(num, count);
            if (count > maxCount) {
                maxCount = count;
                maxCountNum = 1;
            } else if (count == maxCount) {
                maxCountNum++;
            }
        }

        return maxCountNum == 1 ? maxCount : maxCount * maxCountNum;

    }
}

//ми використовуємо лише один цикл для проходження по масиву. Кожного разу, коли ми знаходимо новий елемент,
// ми оновлюємо значення максимальної кількості повторень (maxCount). Якщо кількість повторень поточного
// елемента дорівнює maxCount, ми збільшуємо лічильник maxCountNum. По завершенню циклу, ми повертаємо
// відповідне значення в залежності від кількості елементів з максимальною кількістю повторень.

//You are given an array nums consisting of positive integers.
//Return the total frequencies of elements in nums such that those elements all have the maximum frequency.
//The frequency of an element is the number of occurrences of that element in the array.

//Example 1:
//Input: nums = [1,2,2,3,1,4]
//Output: 4
//Explanation: The elements 1 and 2 have a frequency of 2 which is the maximum frequency in the array.
//So the number of elements in the array with maximum frequency is 4.

//Example 2:
//Input: nums = [1,2,3,4,5]
//Output: 5
//Explanation: All elements of the array have a frequency of 1 which is the maximum.
//So the number of elements in the array with maximum frequency is 5.
//
//Constraints:
//1 <= nums.length <= 100
//1 <= nums[i] <= 100
