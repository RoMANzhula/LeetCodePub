package P1801_1900.P1829_Maximum_XOR_for_Each_Query;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {0, 1, 1, 3};
        int maximumBit1 = 2;
        int[] result1 = solution.getMaximumXor(nums1, maximumBit1);
        System.out.println("Example 1 Output: " + Arrays.toString(result1)); // Expected: [0, 3, 2, 3]

        int[] nums2 = {2, 3, 4, 7};
        int maximumBit2 = 3;
        int[] result2 = solution.getMaximumXor(nums2, maximumBit2);
        System.out.println("Example 2 Output: " + Arrays.toString(result2)); // Expected: [5, 2, 6, 5]

        int[] nums3 = {0, 1, 2, 2, 5, 7};
        int maximumBit3 = 3;
        int[] result3 = solution.getMaximumXor(nums3, maximumBit3);
        System.out.println("Example 3 Output: " + Arrays.toString(result3)); // Expected: [4, 3, 6, 4, 6, 7]
    }

    public int[] getMaximumXor(int[] nums, int maximumBit) {
        int n = nums.length;
        int maxVal = (1 << maximumBit) - 1; // This is 2^maximumBit - 1, the max value with maximumBit bits set.
        int[] answer = new int[n];

        // Calculate the cumulative XOR of all elements initially
        int xorSum = 0;
        for (int num : nums) {
            xorSum ^= num;
        }

        // Process each query in reverse
        for (int i = 0; i < n; i++) {
            // The optimal k for maximum XOR is maxVal XOR current xorSum
            answer[i] = xorSum ^ maxVal;
            // Update xorSum by removing the last element in the array for the next query
            xorSum ^= nums[n - 1 - i];
        }

        return answer;
    }
}

//Explanation of the Code
//Initialize Variables:
//maxVal is the maximum value we can represent with maximumBit bits, calculated as (1 << maximumBit) - 1.
//xorSum stores the cumulative XOR of all elements in nums.
//Fill answer Array in Reverse:
//For each query, calculate the best k as xorSum ^ maxVal.
//Remove the last element from xorSum (simulating the removal in nums).
//Return the Result: The answer array contains the results of each query.
//Complexity Analysis
//Time Complexity:
//O(n) because we only perform one pass through nums for cumulative XOR calculation and another for
// filling the answer array.
//Space Complexity:
//O(n) for storing the answer array.


//You are given a sorted array nums of n non-negative integers and an integer maximumBit. You want
// to perform the following query n times:
//Find a non-negative integer k < 2maximumBit such that nums[0] XOR nums[1] XOR ... XOR nums[nums.length-1] XOR k is
// maximized. k is the answer to the ith query.
//Remove the last element from the current array nums.
//Return an array answer, where answer[i] is the answer to the ith query.
//
//Example 1:
//Input: nums = [0,1,1,3], maximumBit = 2
//Output: [0,3,2,3]
//Explanation: The queries are answered as follows:
//1st query: nums = [0,1,1,3], k = 0 since 0 XOR 1 XOR 1 XOR 3 XOR 0 = 3.
//2nd query: nums = [0,1,1], k = 3 since 0 XOR 1 XOR 1 XOR 3 = 3.
//3rd query: nums = [0,1], k = 2 since 0 XOR 1 XOR 2 = 3.
//4th query: nums = [0], k = 3 since 0 XOR 3 = 3.

//Example 2:
//Input: nums = [2,3,4,7], maximumBit = 3
//Output: [5,2,6,5]
//Explanation: The queries are answered as follows:
//1st query: nums = [2,3,4,7], k = 5 since 2 XOR 3 XOR 4 XOR 7 XOR 5 = 7.
//2nd query: nums = [2,3,4], k = 2 since 2 XOR 3 XOR 4 XOR 2 = 7.
//3rd query: nums = [2,3], k = 6 since 2 XOR 3 XOR 6 = 7.
//4th query: nums = [2], k = 5 since 2 XOR 5 = 7.

//Example 3:
//Input: nums = [0,1,2,2,5,7], maximumBit = 3
//Output: [4,3,6,4,6,7]
//
//Constraints:
//nums.length == n
//1 <= n <= 105
//1 <= maximumBit <= 20
//0 <= nums[i] < 2maximumBit
//nums is sorted in ascending order.
