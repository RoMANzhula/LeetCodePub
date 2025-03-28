package P2801_2900.P2815_Max_Pair_Sum_in_an_Array;

public class Main {

    public static void main(String[] args) {
        int[] nums1 = {51, 71, 17, 24, 42};
        System.out.println("Max Sum with Same Max Digit: " + maxSumWithSameMaxDigit(nums1)); // Output: 88

        int[] nums2 = {1, 2, 3, 4};
        System.out.println("Max Sum with Same Max Digit: " + maxSumWithSameMaxDigit(nums2)); // output: -1
    }

    public static int maxSumWithSameMaxDigit(int[] nums) {
        int maxSum = -1;

        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (haveSameMaxDigit(nums[i], nums[j])) {
                    maxSum = Math.max(maxSum, nums[i] + nums[j]);
                }
            }
        }

        return maxSum;
    }

    private static boolean haveSameMaxDigit(int num1, int num2) {
        int maxDigit1 = getMaxDigit(num1);
        int maxDigit2 = getMaxDigit(num2);

        return maxDigit1 == maxDigit2;
    }

    private static int getMaxDigit(int num) {
        int maxDigit = 0;

        while (num > 0) {
            int digit = num % 10;
            maxDigit = Math.max(maxDigit, digit);
            num /= 10;
        }

        return maxDigit;
    }

}

//You are given an integer array nums. You have to find the maximum sum of a pair of numbers from nums such that
// the largest digit in both numbers is equal.
//For example, 2373 is made up of three distinct digits: 2, 3, and 7, where 7 is the largest among them.
//Return the maximum sum or -1 if no such pair exists.

//Example 1:
//Input: nums = [112,131,411]
//Output: -1
//Explanation:
//Each numbers largest digit in order is [2,3,4].

//Example 2:
//Input: nums = [2536,1613,3366,162]
//Output: 5902
//Explanation:
//All the numbers have 6 as their largest digit, so the answer is 2536 + 3366 = 5902.

//Example 3:
//Input: nums = [51,71,17,24,42]
//Output: 88
//Explanation:
//Each number's largest digit in order is [5,7,7,4,4].
//So we have only two possible pairs, 71 + 17 = 88 and 24 + 42 = 66.

//Constraints:
//2 <= nums.length <= 100
//1 <= nums[i] <= 104
