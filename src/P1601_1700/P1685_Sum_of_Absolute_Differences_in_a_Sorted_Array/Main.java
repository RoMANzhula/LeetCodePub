package P1601_1700.P1685_Sum_of_Absolute_Differences_in_a_Sorted_Array;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int[] nums1 = {2, 3, 5};
        int[] result1 = getSumOfAbsoluteDifferences(nums1);
        System.out.println(Arrays.toString(result1)); // Output: [4, 3, 5]

        int[] nums2 = {1, 4, 6, 8, 10};
        int[] result2 = getSumOfAbsoluteDifferences(nums2);
        System.out.println(Arrays.toString(result2)); // Output: [24, 15, 13, 15, 21]
    }

    public static int[] getSumOfAbsoluteDifferences(int[] nums) {
        int n = nums.length;

        //calculate the total sum of the array
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }

        //calculate the prefix sum array
        int[] prefixSum = new int[n];
        prefixSum[0] = nums[0];
        for (int i = 1; i < n; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i];
        }

        //calculate the result array
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            int leftSum = i * nums[i] - prefixSum[i] + nums[i];
            int rightSum = (totalSum - prefixSum[i]) - (n - i - 1) * nums[i];
            result[i] = leftSum + rightSum;
        }

        return result; //bingo
    }

}


//You are given an integer array nums sorted in non-decreasing order.
//Build and return an integer array result with the same length as nums such that result[i] is equal to the summation
// of absolute differences between nums[i] and all the other elements in the array.
//In other words, result[i] is equal to sum(|nums[i]-nums[j]|) where 0 <= j < nums.length and j != i (0-indexed).

//Example 1:
//Input: nums = [2,3,5]
//Output: [4,3,5]
//Explanation: Assuming the arrays are 0-indexed, then
//result[0] = |2-2| + |2-3| + |2-5| = 0 + 1 + 3 = 4,
//result[1] = |3-2| + |3-3| + |3-5| = 1 + 0 + 2 = 3,
//result[2] = |5-2| + |5-3| + |5-5| = 3 + 2 + 0 = 5.

//Example 2:
//Input: nums = [1,4,6,8,10]
//Output: [24,15,13,15,21]

//Constraints:
//2 <= nums.length <= 105
//1 <= nums[i] <= nums[i + 1] <= 104