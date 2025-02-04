package P1701_1800.P1800_Maximum_Ascending_Subarray_Sum;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {10, 20, 30, 5, 10, 50};
        int[] nums2 = {10, 20, 30, 40, 50};
        int[] nums3 = {12, 17, 15, 13, 10, 11, 12};

        System.out.println(solution.maxAscendingSum(nums1)); // Output: 65
        System.out.println(solution.maxAscendingSum(nums2)); // Output: 150
        System.out.println(solution.maxAscendingSum(nums3)); // Output: 33
    }

    public int maxAscendingSum(int[] nums) {
        int len = nums.length;
        int maxSum = nums[0];
        int currentSum = nums[0];

        for (int i = 1; i < len; i++) {
            if (nums[i] > nums[i - 1]) currentSum += nums[i]; // add current num to sum
            else currentSum = nums[i]; // starting new subarray

            maxSum = Math.max(maxSum, currentSum); // update max sum
        }

        return maxSum;
    }
}

//Complexity: O(n)

//Given an array of positive integers nums, return the maximum possible sum of an ascending subarray in nums.
//A subarray is defined as a contiguous sequence of numbers in an array.
//A subarray [numsl, numsl+1, ..., numsr-1, numsr] is ascending if for all i where l <= i < r, numsi  < numsi+1. Note
// that a subarray of size 1 is ascending.
//
//Example 1:
//Input: nums = [10,20,30,5,10,50]
//Output: 65
//Explanation: [5,10,50] is the ascending subarray with the maximum sum of 65.

//Example 2:
//Input: nums = [10,20,30,40,50]
//Output: 150
//Explanation: [10,20,30,40,50] is the ascending subarray with the maximum sum of 150.

//Example 3:
//Input: nums = [12,17,15,13,10,11,12]
//Output: 33
//Explanation: [10,11,12] is the ascending subarray with the maximum sum of 33.
//
//Constraints:
//1 <= nums.length <= 100
//1 <= nums[i] <= 100
