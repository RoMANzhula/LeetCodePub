package P201_300.P268_Missing_Number;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {3, 0, 1};
        System.out.println("Missing number in nums1: " + solution.missingNumber(nums1)); // Output: 2

        int[] nums2 = {0, 1};
        System.out.println("Missing number in nums2: " + solution.missingNumber(nums2)); // Output: 2

        int[] nums3 = {9, 6, 4, 2, 3, 5, 7, 0, 1};
        System.out.println("Missing number in nums3: " + solution.missingNumber(nums3)); // Output: 8
    }

    public int missingNumber(int[] nums) {
        int n = nums.length;
        // Calculate the expected sum of numbers from 0 to n
        int expectedSum = n * (n + 1) / 2;
        // Calculate the actual sum of numbers in nums
        int actualSum = 0;
        for (int num : nums) {
            actualSum += num;
        }
        // The difference between the expected sum and the actual sum is the missing number
        return expectedSum - actualSum;
    }
}

//У даній задачі ми маємо масив чисел nums, який містить n унікальних чисел у діапазоні від 0 до n. Ми
// повинні знайти те єдине число в цьому діапазоні, яке відсутнє у масиві.
//
//Алгоритм розв'язку полягає в тому, щоб спочатку знайти очікувану суму чисел від 0 до n, використовуючи
// формулу для суми арифметичної прогресії. Потім ми обчислюємо фактичну суму чисел у масиві nums. Різниця
// між очікуваною сумою та фактичною сумою буде відсутнім числом в масиві.

//Given an array nums containing n distinct numbers in the range [0, n], return the only number in the
// range that is missing from the array.

//Example 1:
//Input: nums = [3,0,1]
//Output: 2
//Explanation: n = 3 since there are 3 numbers, so all numbers are in the range [0,3]. 2 is the missing number
// in the range since it does not appear in nums.

//Example 2:
//Input: nums = [0,1]
//Output: 2
//Explanation: n = 2 since there are 2 numbers, so all numbers are in the range [0,2]. 2 is the missing number
// in the range since it does not appear in nums.

//Example 3:
//Input: nums = [9,6,4,2,3,5,7,0,1]
//Output: 8
//Explanation: n = 9 since there are 9 numbers, so all numbers are in the range [0,9]. 8 is the missing number
// in the range since it does not appear in nums.

//Constraints:
//n == nums.length
//1 <= n <= 104
//0 <= nums[i] <= n
//All the numbers of nums are unique.
