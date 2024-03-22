package P1_100.P1_Two_Sum;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {2, 7, 11, 15};
        int target1 = 9;
        int[] result1 = solution.twoSum(nums1, target1);
        System.out.println("Example 1 Output: [" + result1[0] + ", " + result1[1] + "]"); // Output: [0, 1]

        int[] nums2 = {3, 2, 4};
        int target2 = 6;
        int[] result2 = solution.twoSum(nums2, target2);
        System.out.println("Example 2 Output: [" + result2[0] + ", " + result2[1] + "]"); // Output: [1, 2]

        int[] nums3 = {3, 3};
        int target3 = 6;
        int[] result3 = solution.twoSum(nums3, target3);
        System.out.println("Example 3 Output: [" + result3[0] + ", " + result3[1] + "]"); // Output: [0, 1]
    }

    public int[] twoSum(int[] nums, int target) {
        // create a hash map to store the complement of each number
        HashMap<Integer, Integer> map = new HashMap<>();

        // iterate through the array
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];

            // if the complement exists in the map, return its index along with the current index
            if (map.containsKey(complement)) {
                return new int[] {map.get(complement), i};
            }

            // otherwise, store the current number along with its index in the map
            map.put(nums[i], i);
        }

        // if no solution is found, return an empty array
        return new int[] {};
    }
}

//Для розв'язання цієї задачі ми використовуємо метод хешування (HashMap). Ось як працює цей метод:
//Ми створюємо HashMap для зберігання комплементів (доповнень) кожного числа, які ми знаходимо під час
// проходження масиву.
//Проходимо по всім числам у масиві. Для кожного числа, ми обчислюємо його комплемент (різницю між цільовим
// числом і поточним числом).
//Перевіряємо, чи є цей комплемент у HashMap. Якщо так, то ми знаходимо рішення: індекс поточного числа і
// індекс числа, яке є комплементом. Повертаємо ці індекси.
//Якщо комплемент не знайдено у HashMap, ми додаємо поточне число до HashMap разом з його індексом.
//Якщо жодне рішення не знайдено після проходження всього масиву, повертаємо порожній масив, оскільки за умовою
// задачі гарантовано, що рішення завжди існує.
//Цей метод дозволяє швидко знаходити пари чисел, які утворюють суму, не обходячи масив багато разів, оскільки
// ми використовуємо HashMap для зберігання та пошуку комплементів

//Given an array of integers nums and an integer target, return indices of the two numbers such that
// they add up to target.
//You may assume that each input would have exactly one solution, and you may not use the same element twice.
//You can return the answer in any order.
//
//Example 1:
//Input: nums = [2,7,11,15], target = 9
//Output: [0,1]
//Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].

//Example 2:
//Input: nums = [3,2,4], target = 6
//Output: [1,2]

//Example 3:
//Input: nums = [3,3], target = 6
//Output: [0,1]
//
//Constraints:
//2 <= nums.length <= 104
//-109 <= nums[i] <= 109
//-109 <= target <= 109
//Only one valid answer exists.
