package P3201_3300.P3300_Minimum_Element_After_Replacement_With_Digit_Sum;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {10, 12, 13, 14};
        System.out.println(solution.minElement(nums1)); // Output: 1

        int[] nums2 = {1, 2, 3, 4};
        System.out.println(solution.minElement(nums2)); // Output: 1

        int[] nums3 = {999, 19, 199};
        System.out.println(solution.minElement(nums3)); // Output: 10
    }

    public int minElement(int[] nums) {
        int min = Integer.MAX_VALUE;

        for (int num : nums) {
            int digitSum = sumOfDigits(num);
            min = Math.min(min, digitSum);
        }

        return min;
    }

    private int sumOfDigits(int num) {
        int sum = 0;

        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }

        return sum;
    }

}

//Complexity:
// time - O(n * d)   n - number of elements, d - number of digits in each number
// space - O(1)


//You are given an integer array nums.
//You replace each element in nums with the sum of its digits.
//Return the minimum element in nums after all replacements.

//Example 1:
//Input: nums = [10,12,13,14]
//Output: 1
//Explanation:
//nums becomes [1, 3, 4, 5] after all replacements, with minimum element 1.

//Example 2:
//Input: nums = [1,2,3,4]
//Output: 1
//Explanation:
//nums becomes [1, 2, 3, 4] after all replacements, with minimum element 1.

//Example 3:
//Input: nums = [999,19,199]
//Output: 10
//Explanation:
//nums becomes [27, 10, 19] after all replacements, with minimum element 10.

//Constraints:
//1 <= nums.length <= 100
//1 <= nums[i] <= 104
