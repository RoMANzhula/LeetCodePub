package P2301_2400.P2342_Max_Sum_of_a_Pair_With_Equal_Sum_of_Digits;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {18, 43, 36, 13, 7};
        System.out.println(solution.maximumSum(nums1)); // Output: 54

        int[] nums2 = {10, 12, 19, 14};
        System.out.println(solution.maximumSum(nums2)); // Output: -1
    }

    public int maximumSum(int[] nums) {
        HashMap<Integer, Integer> maxSumMap = new HashMap<>(); // stores max value for each digit sum
        int maxSum = -1;

        for (int num : nums) {
            int sumOfDigits = getSumOfDigits(num);

            if (maxSumMap.containsKey(sumOfDigits)) {
                maxSum = Math.max(maxSum, maxSumMap.get(sumOfDigits) + num);
                maxSumMap.put(sumOfDigits, Math.max(maxSumMap.get(sumOfDigits), num));
            } else {
                maxSumMap.put(sumOfDigits, num);
            }
        }

        return maxSum;
    }

    public int getSumOfDigits(int num) {
        int sum = 0;

        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }

        return sum;
    }

}

//Explanation:
//Calculate the sum of digits for each number.
//Use a HashMap to store the maximum value for each sum of digits.
//Check if another number exists with the same digit sum:
//If found, update the maximum sum.
//Otherwise, store the number in the HashMap.
//Return the maximum sum found, or -1 if no valid pair exists.
//Complexity: O(n)


//You are given a 0-indexed array nums consisting of positive integers. You can choose two indices i and j, such
// that i != j, and the sum of digits of the number nums[i] is equal to that of nums[j].
//Return the maximum value of nums[i] + nums[j] that you can obtain over all possible indices i and j that
// satisfy the conditions.
//
//Example 1:
//Input: nums = [18,43,36,13,7]
//Output: 54
//Explanation: The pairs (i, j) that satisfy the conditions are:
//- (0, 2), both numbers have a sum of digits equal to 9, and their sum is 18 + 36 = 54.
//- (1, 4), both numbers have a sum of digits equal to 7, and their sum is 43 + 7 = 50.
//So the maximum sum that we can obtain is 54.

//Example 2:
//Input: nums = [10,12,19,14]
//Output: -1
//Explanation: There are no two numbers that satisfy the conditions, so we return -1.
//
//Constraints:
//1 <= nums.length <= 105
//1 <= nums[i] <= 109
